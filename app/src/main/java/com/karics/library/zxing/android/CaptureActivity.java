package com.karics.library.zxing.android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Result;
import com.karics.library.zxing.camera.CameraManager;
import com.karics.library.zxing.view.ViewfinderView;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.application.MyApplication;
import com.qhzlwh.yigua.bean.SenicTicketChoice;
import com.qhzlwh.yigua.ui.activity.ChoiceTikect;
import com.qhzlwh.yigua.ui.activity.ScenicSpotsDetailsActivity;
import com.qhzlwh.yigua.ui.activity.TikectUseSure;
import com.qhzlwh.yigua.ui.activity.TourSelf;
import com.qhzlwh.yigua.util.DESUtils;
import com.qhzlwh.yigua.util.LogUtil;
import com.qhzlwh.yigua.util.Myconstant;
import com.qhzlwh.yigua.util.ToastUtil;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

/**
 * 这个activity打开相机，在后台线程做常规的扫描；它绘制了一个结果view来帮助正确地显示条形码，在扫描的时候显示反馈信息，
 * 然后在扫描成功的时候覆盖扫描结果
 * 
 */
public final class CaptureActivity extends Activity implements
		SurfaceHolder.Callback {

	private static final String TAG = CaptureActivity.class.getSimpleName();

	// 相机控制
	private CameraManager cameraManager;
	private CaptureActivityHandler handler;
	private ViewfinderView viewfinderView;
	private boolean hasSurface;
	private IntentSource source;
	private Collection<BarcodeFormat> decodeFormats;
	private Map<DecodeHintType, ?> decodeHints;
	private String characterSet;
	// 电量控制
	private InactivityTimer inactivityTimer;
	// 声音、震动控制
	private BeepManager beepManager;

	private ImageButton imageButton_back;
	private int senic_id;
	private int is_senic;
	private String qrcode;

	public ViewfinderView getViewfinderView() {
		return viewfinderView;
	}

	public Handler getHandler() {
		return handler;
	}

	public CameraManager getCameraManager() {
		return cameraManager;
	}

	public void drawViewfinder() {
		viewfinderView.drawViewfinder();
	}

	/**
	 * OnCreate中初始化一些辅助类，如InactivityTimer（休眠）、Beep（声音）以及AmbientLight（闪光灯）
	 */
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		// 保持Activity处于唤醒状态
		Window window = getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.capture);

		hasSurface = false;

		inactivityTimer = new InactivityTimer(this);
		beepManager = new BeepManager(this);

		imageButton_back = (ImageButton) findViewById(R.id.capture_imageview_back);
		imageButton_back.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();

		// CameraManager必须在这里初始化，而不是在onCreate()中。
		// 这是必须的，因为当我们第一次进入时需要显示帮助页，我们并不想打开Camera,测量屏幕大小
		// 当扫描框的尺寸不正确时会出现bug
		cameraManager = new CameraManager(getApplication());

		viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
		viewfinderView.setCameraManager(cameraManager);

		handler = null;

		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			// activity在paused时但不会stopped,因此surface仍旧存在；
			// surfaceCreated()不会调用，因此在这里初始化camera
			initCamera(surfaceHolder);
		} else {
			// 重置callback，等待surfaceCreated()来初始化camera
			surfaceHolder.addCallback(this);
		}

		beepManager.updatePrefs();
		inactivityTimer.onResume();

		source = IntentSource.NONE;
		decodeFormats = null;
		characterSet = null;
	}

	@Override
	protected void onPause() {
		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		inactivityTimer.onPause();
		beepManager.close();
		cameraManager.closeDriver();
		if (!hasSurface) {
			SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
			SurfaceHolder surfaceHolder = surfaceView.getHolder();
			surfaceHolder.removeCallback(this);
		}
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		inactivityTimer.shutdown();
		super.onDestroy();
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!hasSurface) {
			hasSurface = true;
			initCamera(holder);
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		hasSurface = false;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	/**
	 * 扫描成功，处理反馈信息
	 * 
	 * @param rawResult
	 * @param barcode
	 * @param scaleFactor
	 */
	public void handleDecode(Result rawResult, final Bitmap barcode, float scaleFactor) {
		inactivityTimer.onActivity();

		boolean fromLiveScan = barcode != null;
		//这里处理解码完成后的结果，此处将参数回传到Activity处理
		if (fromLiveScan) {
			beepManager.playBeepSoundAndVibrate();
/*
			Toast.makeText(this, "扫描成功", Toast.LENGTH_SHORT).show();

			Intent intent = getIntent();
			intent.putExtra("codedContent", rawResult.getText());
			intent.putExtra("codedBitmap", barcode);
			setResult(RESULT_OK, intent);*/
			ProgressDialog progressDialog=new ProgressDialog(this);
			progressDialog.setMessage("查询中。。。");
			String data=DESUtils.ebotongDecrypto(rawResult.getText());
			qrcode = rawResult.getText();
			LogUtil.e("caputure",data);
			try {
				JSONObject jsonObject=new JSONObject(data);
				senic_id = jsonObject.optInt("senic_id");
				is_senic = jsonObject.optInt("is_senic");
				if(senic_id !=-1){
					OkHttpUtils.post().url(Myconstant.ScanQRCode).addParams("qrcode_data",""+ rawResult.getText())
							.addParams("uid", ""+MyApplication.USER_ID)
						.build().execute(new StringCallback() {
						@Override
						public void onError(Request request, Exception e) {
							ToastUtil.showShortToast(CaptureActivity.this,"查询错误");
						}
						@Override
						public void onResponse(String response) {
							LogUtil.e("caputure",response);
							try {
								JSONObject jsonObject1=new JSONObject(response);
								int code=jsonObject1.getInt("retcode");
								String data=jsonObject1.getString("msg");
								Gson gson=new Gson();
								SenicTicketChoice choice=gson.fromJson(response,SenicTicketChoice.class);
								if(code!=2000){
									ToastUtil.showShortToast(CaptureActivity.this,data);
									showMsg(data,"","");
								}else{
									Intent intent=null;
									if(choice.getData().getTickets().size()>1){
										Bundle bundle=new Bundle();
										bundle.putString("senic_id", ""+senic_id);
										bundle.putString("is_senic",""+is_senic);
										bundle.putString("qrcode_data",""+ qrcode);
										intent=new Intent(CaptureActivity.this, ChoiceTikect.class);
										intent.putExtras(bundle);
									}
									else if(choice.getData().getTickets().size()==1){
										Bundle bundle=new Bundle();
										//JSONObject js=jsonObject1.getJSONObject("data");
										//bundle.putString("tikect",js.toString());
										SenicTicketChoice.DataBean.TicketsBean bean=choice.getData().getTickets().get(0);
										bundle.putString("SENIC_NAME",bean.getName());
										bundle.putString("SENIC_ID",bean.getSenic_id());
										bundle.putString("TICKET_TIME",bean.getCome_date());
										bundle.putString("TICKET_COUNT",bean.getNumber());
										bundle.putString("TICKET_TYPE",bean.getTicket_type_name());
										bundle.putString("ticket_type",""+ bean.getTicket_type());
										bundle.putString("ticket_id",""+ bean.getTicket_id());
										bundle.putString("is_senic",""+ bean.getIs_senic());
										intent=new Intent(CaptureActivity.this, TikectUseSure.class);
										intent.putExtras(bundle);
									}else{
										if(is_senic==0){
											Bundle bundle=new Bundle();
											bundle.putString("SENIC_ID",""+choice.getData().getSenic_id());
											//ScenicSpotsDetailsActivity.SENIC_ID=""+choice.getData().getSenic_id();
											Intent intent1=new Intent(CaptureActivity.this,ScenicSpotsDetailsActivity.class);
											intent1.putExtras(bundle);
											startActivity(intent1);
										}else{
											TourSelf.SENIC_ID = ""+senic_id;
											TourSelf.TYPE = "22";
											Log.e("Tranvel", ""+senic_id);
											Intent intent1 = new Intent(CaptureActivity.this, TourSelf.class);
											startActivity(intent1);
										}

									/*	ScenicSpotsDetailsActivity.SENIC_ID=""+choice.getData().getSenic_id();
										showMsg("景区下没有买票！","","");*/
										//finish();
										//showMsg(data,"","");
									}if(intent!=null){
										//ScenicSpotsDetailsActivity.SENIC_ID=""+choice.getData().getSenic_id();
										startActivity(intent);
										finish();
									}
								}
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}
					});
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		/*	senic_id	必须	景区ID
			uid	必须	用户UID
			is_senic	必须	是否景区二维码 1|是 0|否*/
		}

	}
	public void showMsg(String title, String msg1, String msg2) {
		String str = title;
		if (null !=msg1 && msg1.length() != 0) {
			str += "\n" + msg1;
		}
		if (null !=msg2 && msg2.length() != 0) {
			str += "\n" + msg2;
		}
		AlertDialog.Builder builder = new AlertDialog.Builder(CaptureActivity.this);
		builder.setMessage(str);
		builder.setTitle("提示");
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			/*	Intent intent=new Intent(CaptureActivity.this, ScenicSpotsDetailsActivity.class);
				startActivity(intent);*/
				finish();
			}
		});
		builder.create().show();
	}
	/**
	 * 初始化Camera
	 * 
	 * @param surfaceHolder
	 */
	private void initCamera(SurfaceHolder surfaceHolder) {
		if (surfaceHolder == null) {
			throw new IllegalStateException("No SurfaceHolder provided");
		}
		if (cameraManager.isOpen()) {
			return;
		}
		try {
			// 打开Camera硬件设备
			cameraManager.openDriver(surfaceHolder);
			// 创建一个handler来打开预览，并抛出一个运行时异常
			if (handler == null) {
				handler = new CaptureActivityHandler(this, decodeFormats,
						decodeHints, characterSet, cameraManager);
			}
		} catch (IOException ioe) {
			Log.w(TAG, ioe);
			displayFrameworkBugMessageAndExit();
		} catch (RuntimeException e) {
			Log.w(TAG, "Unexpected error initializing camera", e);
			displayFrameworkBugMessageAndExit();
		}
	}

	/**
	 * 显示底层错误信息并退出应用
	 */
	private void displayFrameworkBugMessageAndExit() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(getString(R.string.app_name));
		builder.setMessage(getString(R.string.msg_camera_framework_bug));
		builder.setPositiveButton(R.string.button_ok, new FinishListener(this));
		builder.setOnCancelListener(new FinishListener(this));
		builder.show();
	}

}
