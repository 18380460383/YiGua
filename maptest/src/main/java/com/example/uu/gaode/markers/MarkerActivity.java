package com.example.uu.gaode.markers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.InfoWindowAdapter;
import com.amap.api.maps.AMap.OnInfoWindowClickListener;
import com.amap.api.maps.AMap.OnMapLoadedListener;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.Projection;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.BusRouteResult;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveRouteResult;
import com.amap.api.services.route.RouteSearch;
import com.amap.api.services.route.WalkRouteResult;
import com.example.uu.gaode.DriveRouteColorfulOverLay;
import com.example.uu.gaode.R;
import com.example.uu.gaode.util.Constants;
import com.example.uu.gaode.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * AMapV2地图中简单介绍一些Marker的用法.
 */
public class MarkerActivity extends Activity implements OnMarkerClickListener,
		OnInfoWindowClickListener, OnMapLoadedListener,
		OnClickListener, InfoWindowAdapter ,LocationSource, AMapLocationListener, RouteSearch.OnRouteSearchListener {
	private MarkerOptions markerOption;
	private TextView markerText;
	private RadioGroup radioOption;
	private AMap aMap;
	private MapView mapView;
	private Marker marker2;// 有跳动效果的marker对象
	private Marker marker3;// 从地上生长的marker对象
	private LatLng latlng = new LatLng(36.061, 103.834);
	private OnLocationChangedListener mListener;
	private AMapLocationClient mlocationClient;
	private AMapLocationClientOption mLocationOption;
	private LatLonPoint mStartPoint ;//起点，
	private LatLonPoint mEndPoint ;//终点，
	private Context mContext;
	private final int ROUTE_TYPE_DRIVE = 2;
	private RouteSearch mRouteSearch;
	private DriveRouteResult mDriveRouteResult;
	private TextView mRotueTimeDes, mRouteDetailDes;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_marker);
		mContext=this.getApplicationContext();
		mapView = (MapView) findViewById(R.id.map);
		mapView.onCreate(savedInstanceState); // 此方法必须重写
		init();
	}

	/**
	 * 初始化AMap对象
	 */
	private void init() {
		markerText = (TextView) findViewById(R.id.mark_listenter_text);
		mRotueTimeDes = (TextView) findViewById(R.id.firstline);
		mRouteDetailDes = (TextView) findViewById(R.id.secondline);
		Button clearMap = (Button) findViewById(R.id.clearMap);
		clearMap.setOnClickListener(this);
		Button resetMap = (Button) findViewById(R.id.resetMap);
		resetMap.setOnClickListener(this);
		if (aMap == null) {
			aMap = mapView.getMap();
			setUpMap();
		}
		aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
		aMap.moveCamera(CameraUpdateFactory.zoomTo(30));
		mRouteSearch = new RouteSearch(this);
		mRouteSearch.setRouteSearchListener(this);
	}
	private void setUpMap() {
		aMap.setOnMapLoadedListener(this);// 设置amap加载成功事件监听器
		aMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
		aMap.setOnInfoWindowClickListener(this);// 设置点击infoWindow事件监听器
		aMap.setInfoWindowAdapter(this);// 设置自定义InfoWindow样式
		addMarkersToMap();// 往地图上添加marker
		aMap.setLocationSource(this);// 设置定位监听
		aMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
		aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
		// 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种
		aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
	}
	/**
	 * 方法必须重写
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
	}
	/**
	 * 方法必须重写
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
		if(null != mlocationClient){
			mlocationClient.onDestroy();
		}
	}
	/**
	 * 定位成功后回调函数
	 */
	@Override
	public void onLocationChanged(AMapLocation amapLocation) {
		if (mListener != null && amapLocation != null) {
			if (amapLocation != null
					&& amapLocation.getErrorCode() == 0) {
				//移动地图
				aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(
						amapLocation.getLatitude(), amapLocation.getLongitude())));
				CameraUpdateFactory.zoomTo(30);
				//mLocationErrText.setVisibility(View.GONE);
				mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
				ArrayList<BitmapDescriptor> giflist = new ArrayList<BitmapDescriptor>();
				giflist.add(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
				giflist.add(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_RED));
				giflist.add(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
				LatLng lan=new LatLng(
						amapLocation.getLatitude(), amapLocation.getLongitude());
				MarkerOptions markerOption1 = new MarkerOptions().anchor(0.5f, 0.5f)
						.position(lan).title("成都市")
						.snippet("成都市:"+lan.toString()).icons(giflist)
						.draggable(true).period(50);
				aMap.addMarker(markerOption1);

				mStartPoint=new LatLonPoint(amapLocation.getLatitude(),amapLocation.getLongitude());
			} else {
				String errText = "定位失败," + amapLocation.getErrorCode()+ ": " + amapLocation.getErrorInfo();
				Log.e("AmapErr",errText);
			}
		}
	}

	/**
	 * 激活定位
	 */
	@Override
	public void activate(OnLocationChangedListener listener) {
		mListener = listener;
		if (mlocationClient == null) {
			mlocationClient = new AMapLocationClient(this);
			mLocationOption = new AMapLocationClientOption();
			//设置定位监听
			mlocationClient.setLocationListener(MarkerActivity.this);
			//设置为高精度定位模式
			mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
			//设置定位参数
			mlocationClient.setLocationOption(mLocationOption);
			// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
			// 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
			// 在定位结束后，在合适的生命周期调用onDestroy()方法
			// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
			mlocationClient.startLocation();
		}
	}

	/**
	 * 停止定位
	 */
	@Override
	public void deactivate() {
		mListener = null;
		if (mlocationClient != null) {
			mlocationClient.stopLocation();
			mlocationClient.onDestroy();
		}
		mlocationClient = null;
	}
	/**
	 * 在地图上添加marker
	 */
	private void addMarkersToMap() {
		// 文字显示标注，可以设置显示内容，位置，字体大小颜色，背景色旋转角度
		ArrayList<MarkerOptions> markerOptionlst = new ArrayList<MarkerOptions>();
		List<LatLng>list=new ArrayList<>();
		list.add(new LatLng(30.679620,104.064855));
		list.add(new LatLng(30.678830,104.065755));
		list.add(new LatLng(30.679940,104.064155));
		MarkerOptions markerOption3;
		for(int i=0;i<list.size();i++){
			markerOption3 = new MarkerOptions();
			markerOption3.position(list.get(i));
			markerOption3.title("成都市").snippet("成都市："+list.get(i).toString());
			markerOption3.draggable(true);
			markerOption3.icon(
					// BitmapDescriptorFactory
					// .fromResource(R.drawable.location_marker)
					BitmapDescriptorFactory.fromBitmap(BitmapFactory
							.decodeResource(getResources(),
									R.drawable.location_marker)));
			// 将Marker设置为贴地显示，可以双指下拉看效果
			markerOption3.setFlat(true);
			markerOptionlst.add(markerOption3);
		}
		//List<Marker> markerlst = aMap.addMarkers(markerOptionlst, true);
		aMap.addMarkers(markerOptionlst, true);
	}
	/**
	 * 对marker标注点点击响应事件
	 */
	@Override
	public boolean onMarkerClick(final Marker marker) {

		markerText.setText("你点击的是" + marker.getTitle());
		return false;
	}
	private void showPopupWindow(View view, final Marker marker) {

		// 一个自定义的布局，作为显示的内容
		View contentView = LayoutInflater.from(mContext).inflate(
				R.layout.pop_window, null);
		// 设置按钮的点击事件
		final PopupWindow popupWindow = new PopupWindow(contentView,
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
		ImageView button = (ImageView) contentView.findViewById(R.id.serach_way);
		ImageView button1 = (ImageView) contentView.findViewById(R.id.add);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				LatLng lan=marker.getPosition();
				mEndPoint=new LatLonPoint(lan.latitude,lan.longitude);
				searchRouteResult(ROUTE_TYPE_DRIVE, RouteSearch.DrivingDefault);
				popupWindow.dismiss();
			}
		});
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ToastUtil.show(MarkerActivity.this,"点击了+");
				popupWindow.dismiss();
			}
		});

		popupWindow.setTouchable(true);
		popupWindow.setTouchInterceptor(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {

				Log.i("mengdd", "onTouch : ");
				return false;
				// 这里如果返回true的话，touch事件将被拦截
				// 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
			}
		});
		popupWindow.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.custom_info_bubble));
		popupWindow.showAsDropDown(view);
		//popupWindow.showAsDropDown(view,0,0, Gravity.RIGHT);
		//popupWindow.showAsDropDown(view,48,48);
	}
	/**
	 * 开始搜索路径规划方案
	 */
	public void searchRouteResult(int routeType, int mode) {
		if (mStartPoint == null) {
			ToastUtil.show(mContext, "定位中，稍后再试...");
			return;
		}
		if (mEndPoint == null) {
			ToastUtil.show(mContext, "终点未设置");
		}
		showProgressDialog();
		final RouteSearch.FromAndTo fromAndTo = new RouteSearch.FromAndTo(
				mStartPoint, mEndPoint);
		if (routeType == ROUTE_TYPE_DRIVE) {// 驾车路径规划
			RouteSearch.DriveRouteQuery query = new RouteSearch.DriveRouteQuery(fromAndTo, mode, null,
					null, "");// 第一个参数表示路径规划的起点和终点，第二个参数表示驾车模式，第三个参数表示途经点，第四个参数表示避让区域，第五个参数表示避让道路
			mRouteSearch.calculateDriveRouteAsyn(query);// 异步路径规划驾车模式查询
		}
	}


	/**
	 * marker点击时跳动一下
	 */
	public void jumpPoint(final Marker marker) {
		final Handler handler = new Handler();
		final long start = SystemClock.uptimeMillis();
		Projection proj = aMap.getProjection();
		Point startPoint = proj.toScreenLocation(Constants.XIAN);
		startPoint.offset(0, -100);
		final LatLng startLatLng = proj.fromScreenLocation(startPoint);
		final long duration = 1500;

		final Interpolator interpolator = new BounceInterpolator();
		handler.post(new Runnable() {
			@Override
			public void run() {
				long elapsed = SystemClock.uptimeMillis() - start;
				float t = interpolator.getInterpolation((float) elapsed
						/ duration);
				double lng = t * Constants.XIAN.longitude + (1 - t)
						* startLatLng.longitude;
				double lat = t * Constants.XIAN.latitude + (1 - t)
						* startLatLng.latitude;
				marker.setPosition(new LatLng(lat, lng));
				if (t < 1.0) {
					handler.postDelayed(this, 16);
				}
			}
		});
	}

	/**
	 * marker 必须有设置图标，否则无效果
	 * @param marker
	 */
	private void dropInto(final Marker marker) {
		final Handler handler = new Handler();
		final long start = SystemClock.uptimeMillis();
		final LatLng markerLatlng = marker.getPosition();
		Projection proj = aMap.getProjection();
		Point markerPoint = proj.toScreenLocation(markerLatlng);
		Point startPoint = new Point(markerPoint.x, 0);// 从marker的屏幕上方下落
		final LatLng startLatLng = proj.fromScreenLocation(startPoint);
		final long duration = 800;// 动画总时长

		final Interpolator interpolator = new AccelerateInterpolator();
		handler.post(new Runnable() {
			@Override
			public void run() {
				long elapsed = SystemClock.uptimeMillis() - start;
				float t = interpolator.getInterpolation((float) elapsed
						/ duration);
				double lng = t * markerLatlng.longitude + (1 - t)
						* startLatLng.longitude;
				double lat = t * markerLatlng.latitude + (1 - t)
						* startLatLng.latitude;
				marker.setPosition(new LatLng(lat, lng));
				if (t < 1.0) {
					handler.postDelayed(this, 16);
				}
			}
		});
	}

	private int count = 1;
	Bitmap lastMarkerBitMap = null;

	/**
	 * 从地上生长效果，实现思路
	 * 在较短的时间内，修改marker的图标大小，从而实现动画<br>
	 * 1.保存原始的图片；
	 * 2.在原始图片上缩放得到新的图片，并设置给marker；
	 * 3.回收上一张缩放后的图片资源；
	 * 4.重复2，3步骤到时间结束；
	 * 5.回收上一张缩放后的图片资源，设置marker的图标为最原始的图片；
	 * 其中时间变化由AccelerateInterpolator控制
	 * @param marker
	 */
	private void growInto(final Marker marker) {
		marker.setVisible(false);
		final Handler handler = new Handler();
		final long start = SystemClock.uptimeMillis();
		final long duration = 250;// 动画总时长
		final Bitmap bitMap = marker.getIcons().get(0).getBitmap();// BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
		final int width = bitMap.getWidth();
		final int height = bitMap.getHeight();

		final Interpolator interpolator = new AccelerateInterpolator();
		handler.post(new Runnable() {
			@Override
			public void run() {
				long elapsed = SystemClock.uptimeMillis() - start;
				float t = interpolator.getInterpolation((float) elapsed
						/ duration);

				if (t > 1) {
					t = 1;
				}

				// 计算缩放比例
				int scaleWidth = (int) (t * width);
				int scaleHeight = (int) (t * height);
				if (scaleWidth > 0 && scaleHeight > 0) {

					// 使用最原始的图片进行大小计算
					marker.setIcon(BitmapDescriptorFactory.fromBitmap(Bitmap
							.createScaledBitmap(bitMap, scaleWidth,
									scaleHeight, true)));
					marker.setVisible(true);

					// 因为替换了新的图片，所以把旧的图片销毁掉，注意在设置新的图片之后再销毁
					if (lastMarkerBitMap != null
							&& !lastMarkerBitMap.isRecycled()) {
						lastMarkerBitMap.recycle();
					}
					//第一次得到的缩放图片，在第二次回收，最后一次的缩放图片，在动画结束时回收
					ArrayList<BitmapDescriptor> list = marker.getIcons();
					if (list != null && list.size() > 0) {
						// 保存旧的图片
						lastMarkerBitMap = marker.getIcons().get(0).getBitmap();
					}

				}

				if (t < 1.0 && count < 10) {
					handler.postDelayed(this, 16);
				} else {
					// 动画结束回收缩放图片，并还原最原始的图片
					if (lastMarkerBitMap != null
							&& !lastMarkerBitMap.isRecycled()) {
						lastMarkerBitMap.recycle();
					}
					marker.setIcon(BitmapDescriptorFactory.fromBitmap(bitMap));
					marker.setVisible(true);
				}
			}
		});
	}

	/**
	 * 监听点击infowindow窗口事件回调
	 */
	@Override
	public void onInfoWindowClick(Marker marker) {
		ToastUtil.show(this, "你点击了infoWindow窗口" + marker.getTitle());
		ToastUtil.show(MarkerActivity.this, "当前地图可视区域内Marker数量:"
				+ aMap.getMapScreenMarkers().size());
	}

	/**
	 * 监听amap地图加载成功事件回调
	 */
	@Override
	public void onMapLoaded() {
		// 设置所有maker显示在当前可视区域地图中
	/*	LatLngBounds bounds = new LatLngBounds.Builder()
				.include(Constants.XIAN).include(Constants.CHENGDU)
				.include(Constants.BEIJING).include(latlng).build();
		aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 20));*/
	}

	/**
	 * 监听自定义infowindow窗口的infocontents事件回调
	 */
	@Override
	public View getInfoContents(Marker marker) {
		/*if (radioOption.getCheckedRadioButtonId() != R.id.custom_info_contents) {
			return null;
		}*/
		View infoContent = getLayoutInflater().inflate(
				R.layout.pop_window, null);
		render(marker, infoContent);
		return infoContent;
	}

	/**
	 * 监听自定义infowindow窗口的infowindow事件回调
	 */
	@Override
	public View getInfoWindow(Marker marker) {
		View infoContent = getLayoutInflater().inflate(
				R.layout.pop_window, null);
		render(marker, infoContent);
		return infoContent;
	}

	/**
	 * 自定义infowinfow窗口
	 */
	public void render(final Marker marker, View view) {
		ImageView button = (ImageView) view.findViewById(R.id.serach_way);
		ImageView button1 = (ImageView) view.findViewById(R.id.add);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				LatLng lan=marker.getPosition();
				mEndPoint=new LatLonPoint(lan.latitude,lan.longitude);
				searchRouteResult(ROUTE_TYPE_DRIVE, RouteSearch.DrivingDefault);
			}
		});
		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ToastUtil.show(MarkerActivity.this,"点击了+");
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		/**
		 * 清空地图上所有已经标注的marker
		 */
		/*case R.id.clearMap:
			if (aMap != null) {
				aMap.clear();
			}
			break;
		*//**
		 * 重新标注所有的marker
		 *//*
		case R.id.resetMap:
			if (aMap != null) {
				aMap.clear();
				addMarkersToMap();
			}
			LatLngBounds bounds = new LatLngBounds.Builder()
					.include(Constants.CHENGDU)
					.build();
			aMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 20));
			break;*/
		default:
			break;
		}
	}
	/**
	 * 显示进度框
	 */
	private ProgressDialog progDialog;
	private void showProgressDialog() {
		if (progDialog == null)
			progDialog = new ProgressDialog(this);
		progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progDialog.setIndeterminate(false);
		progDialog.setCancelable(true);
		progDialog.setMessage("正在搜索");
		progDialog.show();
	}

	/**
	 * 隐藏进度框
	 */
	private void dissmissProgressDialog() {
		if (progDialog != null) {
			progDialog.dismiss();
		}
	}

	@Override
	public void onBusRouteSearched(BusRouteResult busRouteResult, int i) {

	}

	@Override
	public void onDriveRouteSearched(DriveRouteResult result, int errorCode) {
		dissmissProgressDialog();
		aMap.clear();// 清理地图上的所有覆盖物
		if (errorCode == 1000) {
			if (result != null && result.getPaths() != null) {
				if (result.getPaths().size() > 0) {
					mDriveRouteResult = result;
					final DrivePath drivePath = mDriveRouteResult.getPaths()
							.get(0);
					DriveRouteColorfulOverLay drivingRouteOverlay = new DriveRouteColorfulOverLay(
							aMap, drivePath,
							mDriveRouteResult.getStartPos(),
							mDriveRouteResult.getTargetPos(), null);
					drivingRouteOverlay.setNodeIconVisibility(false);//设置节点marker是否显示
					drivingRouteOverlay.setIsColorfulline(true);//是否用颜色展示交通拥堵情况，默认true
					drivingRouteOverlay.removeFromMap();
					drivingRouteOverlay.addToMap();
					drivingRouteOverlay.zoomToSpan();
				} else if (result != null && result.getPaths() == null) {
					ToastUtil.show(mContext, R.string.no_result);
				}

			} else {
				ToastUtil.show(mContext, R.string.no_result);
			}
			addMarkersToMap();
		} else {
			ToastUtil.showerror(this.getApplicationContext(), errorCode);
		}
	}

	@Override
	public void onWalkRouteSearched(WalkRouteResult walkRouteResult, int i) {

	}
}
