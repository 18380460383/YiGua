package com.qhzlwh.yigua.ui.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.application.MyApplication;
import com.qhzlwh.yigua.bean.SenicFileBean;
import com.qhzlwh.yigua.util.FileUtil;
import com.qhzlwh.yigua.util.LogUtil;
import com.qhzlwh.yigua.util.Myconstant;
import com.qhzlwh.yigua.util.ToastUtils;
import com.qhzlwh.yigua.view.GridViewNoScoll;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import abslistview.CommonAdapter;
import abslistview.ViewHolder;
import butterknife.ButterKnife;
import butterknife.Bind;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class OrderCharge extends Activity {

    @Bind(R.id.top_back)
    ImageView topBack;
    @Bind(R.id.top_tilte_name)
    TextView topTilteName;
    @Bind(R.id.top_rigth_text)
    TextView topRigthText;
    @Bind(R.id.top)
    LinearLayout top;
    @Bind(R.id.order_charge_rating)
    RatingBar orderChargeRating;
    @Bind(R.id.order_charge_tranfic)
    RatingBar orderChargeTranfic;
    @Bind(R.id.order_charge_hotel)
    RatingBar orderChargeHotel;
    @Bind(R.id.order_charge_leader)
    RatingBar orderChargeLeader;
    @Bind(R.id.order_charge_content)
    EditText orderChargeContent;
    @Bind(R.id.order_charge_image)
    GridViewNoScoll orderChargeImage;
    @Bind(R.id.order_charge_submit)
    Button orderChargeSubmit;
    @Bind(R.id.order_charge_line)
    LinearLayout orderChargeLine;
    @Bind(R.id.line_image)
    LinearLayout lineImage;
    private List<String> mList;
    private static final int REQUEST_IMAGE = 11;
    private CommonAdapter<String> adapter;

    public static String SENIC_ID = "";
    public static String ORDER_ID = "";
    public static String TYPE = "";
    ProgressDialog dialog;
    private boolean iscomplete;
    private RequestParams params;
    private int line;
    private int hotel;
    private int eat;
    private int count=0;
    private AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_charge);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        mList = new ArrayList<>();
        mList.add("");
        //orderChargeImage.setAdapter(
        adapter = new CommonAdapter<String>(this, R.layout.comment_image_item, mList) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, int position) {
                if (item.isEmpty()) {
                    viewHolder.setBackgroundRes(R.id.item_image, R.mipmap.publish_add);
                    viewHolder.getView(R.id.item_image).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(OrderCharge.this, MultiImageSelectorActivity.class);
                            // 是否显示调用相机拍照
                            intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
                            // 最大图片选择数量
                            intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 9);
                            // 设置模式 (支持 单选/MultiImageSelectorActivity.MODE_SINGLE 或者 多选/MultiImageSelectorActivity.MODE_MULTI)
                            intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
                            startActivityForResult(intent, REQUEST_IMAGE);
                        }
                    });
                } else {
                    viewHolder.setImage(R.id.item_image, item);
                }
            }
        };
        orderChargeImage.setAdapter(adapter);
        // });
    }

    private void initView() {
        topTilteName.setText("发表评论");
        LogUtil.e("chargetype", SENIC_ID);
        if (TYPE.equals("1")) {
            orderChargeLine.setVisibility(View.GONE);
            lineImage.setVisibility(View.GONE);
        }

    }

    @OnClick({R.id.top_back, R.id.order_charge_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.top_back:
                finish();
                break;
            case R.id.order_charge_submit:
                dialog = new ProgressDialog(OrderCharge.this);
                dialog.setMessage("发布中。。。");
                dialog.show();
                client = new AsyncHttpClient();
                params = new RequestParams();
                int charge = (int) orderChargeRating.getRating();
                String content = orderChargeContent.getText().toString();

               /* senic_id	必须	景区ID
                uid	必须	用户UID
                star	可选	星级 整型数字
                description	必须	评论内容
                order_id	必须	订单ID
                traffic_stars	可选	交通路线|线路游必填
                hotel_stars	可选	住宿餐饮|线路游必填
                leader_stars	可选	领队合作|线路游必填
                images	可选	线路游图集[]*/
                params.add("senic_id", SENIC_ID);
                params.add("uid", "" + MyApplication.USER_ID);
                params.add("star", "" + charge);
                params.add("description", content);
                params.add("order_id", ORDER_ID);
                iscomplete = false;
                if (TYPE.equals("1")) {

                } else {
                    line = (int) orderChargeTranfic.getRating();
                    hotel = (int) orderChargeHotel.getRating();
                    eat = (int) orderChargeLeader.getRating();
                    params.add("traffic_stars", "" + line);
                    params.add("hotel_stars", "" + hotel);
                    params.add("leader_stars", "" + eat);
                    final List<String> list = new ArrayList<>();
                    for (int i = 0; i < mList.size() - 1; i++) {
                        final int j=i;
                        iscomplete =false;
                        OkHttpUtils.post().url(Myconstant.uploadPicture).
                                addFile("file", FileUtil.getFileName(mList.get(i)), new File(mList.get(i))).build().execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {
                            }

                            @Override
                            public void onResponse(String response) {
                                Gson gson = new Gson();
                                SenicFileBean bean = gson.fromJson(response, SenicFileBean.class);
                                list.add(bean.getData().getFile().getPath());
                                Log.e("charge", response);
                                Log.e("charge", ""+j);
                                iscomplete =true;
                                count++;
                                if(count==mList.size()-1){
                                    if(iscomplete){
                                        params.put("images", list);
                                        LogUtil.e("charge","tran"+ line +" hotel"+ hotel +" "+"leader"+ eat +"\n"+"list:"+list.toString());
                                         client.post(OrderCharge.this, Myconstant.senic_comment, params, new AsyncHttpResponseHandler() {
                                             @Override
                                             public void onSuccess(int i, Header[] headers, byte[] bytes) {
                                                 Log.e("ordercharge", new String(bytes));
                                                 dialog.dismiss();
                                             }
                                             @Override
                                             public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                                                 Log.e("ordercharge", new String(bytes));
                                                 dialog.dismiss();
                                             }
                                         });
                                    }
                                }
                            }
                        });
                    }
                }
                client.post(OrderCharge.this, Myconstant.senic_comment, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        Log.e("ordercharge", new String(bytes));
                        dialog.dismiss();
                        try {
                            JSONObject jsonObject=new JSONObject(new String(bytes));
                            String data=jsonObject.getString("msg");
                            int code=jsonObject.getInt("retcode");
                            if(code==2000){
                                ToastUtils.showToast(OrderCharge.this,data);
                                finish();
                            }else{
                                ToastUtils.showToast(OrderCharge.this,data);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        Log.e("ordercharge", new String(bytes));
                        dialog.dismiss();
                    }
                });
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                // 获取返回的图片列表
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                // 处理你自己的逻辑 ....
                mList = path;
                mList.add("");
                LogUtil.e("OrderCharge", "" + mList.size());
                adapter = new CommonAdapter<String>(this, R.layout.comment_image_item, mList) {
                    @Override
                    protected void convert(ViewHolder viewHolder, String item, int position) {
                        if (item.isEmpty()) {
                            viewHolder.setBackgroundRes(R.id.item_image, R.mipmap.publish_add);
                            viewHolder.getView(R.id.item_image).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    Intent intent = new Intent(OrderCharge.this, MultiImageSelectorActivity.class);
                                    // 是否显示调用相机拍照
                                    intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
                                    // 最大图片选择数量
                                    intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 9);
                                    // 设置模式 (支持 单选/MultiImageSelectorActivity.MODE_SINGLE 或者 多选/MultiImageSelectorActivity.MODE_MULTI)
                                    intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
                                    startActivityForResult(intent, REQUEST_IMAGE);
                                }
                            });
                        } else {
                            viewHolder.setImage(R.id.item_image, item);
                        }
                    }
                };
                orderChargeImage.setAdapter(adapter);
            }
        }

    }
    interface  loadingUp{
        void doreult();
    }

}
