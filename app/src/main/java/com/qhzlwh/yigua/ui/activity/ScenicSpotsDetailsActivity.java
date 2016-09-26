package com.qhzlwh.yigua.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.application.MyApplication;
import com.qhzlwh.yigua.bean.ScenicSpotsDetailsBean;
import com.qhzlwh.yigua.contral.ShareControl;
import com.qhzlwh.yigua.ui.fragment.HotelIntroduce;
import com.qhzlwh.yigua.ui.fragment.ScenicSpotIntroduceFragment;
import com.qhzlwh.yigua.ui.fragment.VoiceIntroduce;
import com.qhzlwh.yigua.util.LogUtil;
import com.qhzlwh.yigua.util.MyUtil;
import com.qhzlwh.yigua.util.Myconstant;
import com.qhzlwh.yigua.util.StringUtils;
import com.qhzlwh.yigua.util.ToastUtil;
import com.qhzlwh.yigua.util.ToastUtils;
import com.qhzlwh.yigua.view.CircularImage;
import com.qhzlwh.yigua.view.ListViewNoSrcoll;
import com.qhzlwh.yigua.view.MyScrollViewL;
import com.qhzlwh.yigua.view.ShareDialog;
import com.squareup.okhttp.Request;
import com.xander.panel.PanelInterface;
import com.xander.panel.XanderPanel;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import abslistview.CommonAdapter;
import abslistview.ViewHolder;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.tencent.qq.QQ;


/**
 * 创建者：Administrator
 * 时间：2016/8/15
 * 功能描述：
 */
public class ScenicSpotsDetailsActivity extends BaseActivity implements View.OnClickListener, MyScrollViewL.OnScrollListener, PlatformActionListener

{
    public static final String ID = "id";

    private TextView scenicSpotName;
    private TextView levelTextview;
    private TextView addressTextview;
    private TextView signup_explain_message;
    private TextView scenic_spot_detail_name;
    private TextView top_head_name;
    private TextView money_textview;
    private RadioButton introduceRadiobutton;
    private RadioButton introduceRadiobutton1;
    private String SENIC_ID = "1";
    private String url = "http://qh.91chuanbo.cn/Api/Api/SenicDetailById/senic_id/";
    private RadioButton hotelRadiobutton1;
    private RadioButton hotelRadiobutton;
    private RadioButton voiceRadiobutton;
    private RadioButton voiceRadiobutton1;
    private ScenicSpotsDetailsBean scenicSpotsDetailsBean;
    private VoiceIntroduce mvoice;
    private HotelIntroduce mhotel;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private ImageView imageView;
    private ImageView top_Back;
    private ImageView top_bark;
    private ListViewNoSrcoll mListView;
    private MyScrollViewL myScrollView;
    private int searchLayoutTop;
    private LinearLayout search01, search02;
    private LinearLayout rlayout;
    private RadioGroup mRadioGroup;
    private String TAG = "ScenicSpotsDetailsActivity";
    private CircularImage top_like;
    private String url1;
    private String senic_name;
    private TextView detail_address;
    private int collection = 1;
    private LinearLayout mRadio;
    private LinearLayout mRadioTop;
    private Button shareButton;
    private RelativeLayout navi_map;
    ShareDialog shareDialog;
    private Context mContext;
    private boolean iscompelet=false;
    private boolean iscompelet1=false;
    private String address="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenic_spots_details);
      //  getList();
        mContext=this;
        manager = getSupportFragmentManager();
        scenic_spot_detail_name = (TextView) findViewById(R.id.scenic_spot_detail_name);
        top_head_name = (TextView) findViewById(R.id.top_head_name);
        scenicSpotName = (TextView) findViewById(R.id.scenic_spot_name);
        levelTextview = (TextView) findViewById(R.id.level);
        addressTextview = (TextView) findViewById(R.id.address);
        money_textview = (TextView) findViewById(R.id.money_textview);
        detail_address = (TextView) findViewById(R.id.detail_address);
        signup_explain_message = (TextView) findViewById(R.id.signup_explain_message);
        introduceRadiobutton = (RadioButton) findViewById(R.id.introduce_radiobutton);
        introduceRadiobutton1 = (RadioButton) findViewById(R.id.introduce_radiobutton1);
        voiceRadiobutton = (RadioButton) findViewById(R.id.voice_radiobutton);
        voiceRadiobutton1 = (RadioButton) findViewById(R.id.voice_radiobutton1);
        hotelRadiobutton = (RadioButton) findViewById(R.id.cate_radiobutton);
        hotelRadiobutton1 = (RadioButton) findViewById(R.id.cate_radiobutton1);
        imageView = (ImageView) findViewById(R.id.top_image);
        top_Back = (ImageView) findViewById(R.id.top_Back);
        top_bark = (ImageView) findViewById(R.id.top_bak);
        mListView = (ListViewNoSrcoll) findViewById(R.id.item_listview);
        myScrollView = (MyScrollViewL) findViewById(R.id.myScrollView);
        mRadioGroup = (RadioGroup) findViewById(R.id.id_stickynavlayout_indicator);
      /*  search01 = (LinearLayout)findViewById(R.id.search01);
        search02 = (LinearLayout)findViewById(R.id.search02);*/
        rlayout = (LinearLayout) findViewById(R.id.id_stickynavlayout_topview);
        top_like = (CircularImage) findViewById(R.id.top_like);
        mRadio = (LinearLayout) findViewById(R.id.radio);
        mRadioTop = (LinearLayout) findViewById(R.id.top_radio);
        navi_map= (RelativeLayout) findViewById(R.id.navi_map);
        navi_map.setOnClickListener(this);
        top_like.setOnClickListener(this);
        top_bark.setOnClickListener(this);
        introduceRadiobutton.setOnClickListener(this);
        hotelRadiobutton.setOnClickListener(this);
        voiceRadiobutton.setOnClickListener(this);
        introduceRadiobutton1.setOnClickListener(this);
        hotelRadiobutton1.setOnClickListener(this);
        voiceRadiobutton1.setOnClickListener(this);
        myScrollView.setOnScrollListener(this);
        top_Back.setOnClickListener(this);
        getDetails(getIntent().getStringExtra(ID));

        //当布局的状态或者控件的可见性发生改变回调的接口
        findViewById(R.id.parent_layout).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //这一步很重要，使得上面的购买布局和下面的购买布局重合
                onScroll(myScrollView.getScrollY());
                System.out.println(myScrollView.getScrollY());
            }
        });
        ShareSDK.initSDK(this);
        showProgressDialog("数据加载中。。。");
    }

    private void getDetails(String stringExtra) {
        final Bundle bundle = getIntent().getExtras();
        SENIC_ID = bundle.getString("SENIC_ID");
        Log.e(TAG, url + SENIC_ID);
        OkHttpUtils.get().url(url + SENIC_ID).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                scenicSpotsDetailsBean = gson.fromJson
                        (response, ScenicSpotsDetailsBean.class);
                if (scenicSpotsDetailsBean.getRetcode() != 2000) {
                    ToastUtils.showToast(ScenicSpotsDetailsActivity.this, scenicSpotsDetailsBean.getMsg());
                } else {
                    setData(scenicSpotsDetailsBean);
                    LogUtil.e(TAG, "" + scenicSpotsDetailsBean.getData().get(0).getTickets().size());
                    mListView.setAdapter(new CommonAdapter<ScenicSpotsDetailsBean.DataEntity.TicketsEntity>(ScenicSpotsDetailsActivity.this, R.layout.tikect_listview_layout, scenicSpotsDetailsBean.getData().get(0).getTickets()) {
                        @Override
                        protected void convert(ViewHolder viewHolder, final ScenicSpotsDetailsBean.DataEntity.TicketsEntity item, int position) {
                            if (item.getTicket_type().equals("2")) {
                                viewHolder.setText(R.id.tikect_type, item.getTicket_name() + "(" + item.getTicket_type_name() + ")");
                            } else {
                                viewHolder.setText(R.id.tikect_type, item.getTicket_name());
                            }
                            viewHolder
                                    .setText(R.id.tikect_price, item.getTicket_yprice() + "/人")
                                    .setText(R.id.tikect_leave, item.getTicket_number())
                                    .setText(R.id.tikect_explan, item.getTicket_description())
                                    .setText(R.id.tikect_charge, item.getTips());
                            Button button = viewHolder.getView(R.id.item_submit);
                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    LoginFast.TYPE = 1;
                                    Bundle bundle1 = new Bundle();
                                    bundle1.putString("ORDER_TYPE", "门票");
                                    bundle1.putString("SENIC_ID", scenicSpotsDetailsBean.getData().get(0).getSenic_id());
                                    bundle1.putString("TIKECT_TYPE", item.getTicket_name());
                                    bundle1.putString("SENIC_NAME", scenicSpotsDetailsBean.getData().get(0).getName());
                                    bundle1.putString("SENIC_ADDRESS", scenicSpotsDetailsBean.getData().get(0).getAddress());
                                    bundle1.putFloat("PRICE", Float.valueOf(item.getTicket_yprice()));
                                    bundle1.putString("order_type", "1");
                                    bundle1.putString("TIKECT_ID", item.getTicket_id());
                                    if (MyApplication.IS_LOGIN) {
                                        Intent intent = new Intent(ScenicSpotsDetailsActivity.this, OrderWrite.class);
                                        intent.putExtras(bundle1);
                                        startActivity(intent);
                                    } else {
                                        Intent intent = new Intent(ScenicSpotsDetailsActivity.this, LoginFast.class);
                                        startActivity(intent);
                                    }
                                }
                            });
                        }
                    });
                }
            }
        });
        OkHttpUtils.get().url(Myconstant.SenicLikeOrCollect + "senic_id/" + SENIC_ID + "/uid/" + MyApplication.USER_ID).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
            }
            @Override
            public void onResponse(String response) {
                if (response.contains("\"is_collect\":1")) {
                    top_like.setBackgroundResource(R.mipmap.in_like);
                } else {
                    collection = 0;
                }
            }
        });
    }

    private void setData(ScenicSpotsDetailsBean scenicSpotsDetailsBean) {
        if (2000 != scenicSpotsDetailsBean.getRetcode()) {
            Toast.makeText(this, "数据异常", Toast.LENGTH_SHORT).show();
            return;
        }
        List<ScenicSpotsDetailsBean.DataEntity> data = scenicSpotsDetailsBean.getData();
        if (data.size() > 0) {
            ScenicSpotsDetailsBean.DataEntity dataEntity = data.get(0);
            System.out.println("dataEntity" + dataEntity);
            scenic_spot_detail_name.setText(dataEntity.getLong_title());
            detail_address.setText("距离您约 1小时30分钟车程");
            senic_name = dataEntity.getName();
            top_head_name.setText(senic_name);
            scenicSpotName.setText(dataEntity.getName());
            levelTextview.setText(dataEntity.getLevel());
            addressTextview.setText(dataEntity.getAddress());
            money_textview.setText("门票价格：");
            money_textview.append(getColorText("¥" + dataEntity.getPrice() + "/人", "#ffea15"));
            signup_explain_message.setText(Html.fromHtml(dataEntity.getTicket()));
            url1 = Myconstant.BASE_URL + dataEntity.getImage();
            Glide.with(this).load(url1).into(imageView);
            addFragment(dataEntity.getOpen(), dataEntity.getTraffic(), dataEntity.getShort_description(), dataEntity.getTelephone(), dataEntity.getSenic_id());
          getLocationLat();
             /* getLatlon(dataEntity.getAddress());*/
            address=dataEntity.getAddress();
            getLoc(address);
        }
        dismissProgressDialog();
    }

    /**
     * @param open              营业时间
     * @param traffic           驾车信息
     * @param short_description 景区介绍
     */
    private void addFragment(String open, String traffic, String short_description, String telphone, String senic_id) {
        introduceRadiobutton.setChecked(true);
        introduceRadiobutton.setTextColor(getResources().getColor(R.color.white));
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        ScenicSpotIntroduceFragment scenicSpotIntroduceFragment = new ScenicSpotIntroduceFragment();
        Bundle args = new Bundle();
        args.putString(ScenicSpotIntroduceFragment.OPEN, open);
        args.putString(ScenicSpotIntroduceFragment.TRAFFIC, traffic);
        args.putString(ScenicSpotIntroduceFragment.SHORT_DESCRIPTION, short_description);
        args.putString(ScenicSpotIntroduceFragment.TELPHONE, telphone);
        ScenicSpotIntroduceFragment.SENIC_ID = senic_id;
        scenicSpotIntroduceFragment.setArguments(args);
        fragmentTransaction.replace(R.id.fragment_framelayout, scenicSpotIntroduceFragment);
        fragmentTransaction.commit();
    }

    /**
     * 获取指定颜色的文字
     *
     * @param text
     * @param color
     * @return
     */
    public static SpannableStringBuilder getColorText(String text, String color) {
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        ForegroundColorSpan redSpan = new ForegroundColorSpan(Color.parseColor(color));
        builder.setSpan(redSpan, 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }

    @Override
    public void onClick(View v) {
        transaction = manager.beginTransaction();
        switch (v.getId()) {
            case R.id.navi_map:
            /*    Intent intent=null;
                try {
                    intent = Intent.getIntent("intent://map/marker?location=40.047669,116.313082&title=我的位置&content=百度奎科大厦&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
                    if(isInstallByread("com.baidu.BaiduMap")){
                        startActivity(intent); //启动调用
                        Log.e("GasStation", "百度地图客户端已经安装") ;
                    }else{
                        Log.e("GasStation", "没有安装百度地图客户端") ;
                    }
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
*/

                //  startActivity(new Intent(ScenicSpotsDetailsActivity.this, RouteActivity.class));39.923271, 116.396034
              /*  Intent intent = new Intent("android.intent.action.VIEW",android.net.Uri.parse("androidamap://navi?sourceApplication=一呱&lat="+"39.923271"+ "&lon="+"116.396034"+ "&dev=0"));
                intent.setPackage("com.autonavi.minimap");
                startActivity(intent);*/
              /*  if(iscompelet&&iscompelet1){
                    Intent intent=new Intent(ScenicSpotsDetailsActivity.this,RouteActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putDouble("startLat",startLat);
                    bundle.putDouble("startLag",startLag);
                    bundle.putDouble("endLat",endLat);
                    bundle.putDouble("endLag",endLag);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else{
                    ToastUtil.showShortToast(mContext,"相关数据错误无法导航");
                }
*/
                if(isCom&&iscompelet){
                    int count=0;
                    String []map;
                    // intent = Intent.getIntent("intent://map/marker?location=40.047669,116.313082&title=我的位置&content=百度奎科大厦&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
                    if(isInstallByread("com.baidu.BaiduMap")){
                        map=new String[]{"百度"};
                        count++;
                        // startActivity(intent); //启动调用
                        Log.e("GasStation", "百度地图客户端已经安装") ;
                    }else{
                        Log.e("GasStation", "没有安装百度地图客户端") ;
                    }
                    if(isInstallByread("com.autonavi.minimap")){
                        count++;
                        map=new String[]{"高德"};
                        Log.e("GasStation", "高德地图客户端已经安装") ;
                    }else{
                        Log.e("GasStation", "没有安装高德地图客户端") ;
                    }
                    if(count==2){
                        XanderPanel.Builder mBuilder = new XanderPanel.Builder(ScenicSpotsDetailsActivity.this);
                        map=new String[]{"百度","高德"};
                        mBuilder.setSheet(
                                map,
                                true,
                                new PanelInterface.SheetListener() {
                                    @Override
                                    public void onSheetItemClick(int position) {
                                        Intent intent=null;
                                        switch (position){
                                            case 0:
                                                try {
                                                    intent = Intent.getIntent("intent://map/direction?origin=latlng:"+startLat+","+startLag+"|name:我的位置&destination="+address+"&mode=driving®ion=西安&referer=Autohome|GasStation#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
                                                    //intent = Intent.getIntent("intent://map/marker?location=40.047669,116.313082&title=我的位置&content=百度奎科大厦&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
                                                    startActivity(intent);
                                                } catch (URISyntaxException e) {
                                                    e.printStackTrace();
                                                }
                                              /*      intent = new Intent("android.intent.action.VIEW",android.net.Uri.parse("androidamap://navi?sourceApplication=一呱&lat="+"39.923271"+ "&lon="+"116.396034"+ "&dev=0"));
                                                    intent.setPackage("com.autonavi.minimap");
                                                    startActivity(intent);*/
                                                break;
                                            case 1:
                                                intent = new Intent("android.intent.action.VIEW",android.net.Uri.parse("androidamap://route?sourceApplication=softname&slat="+startLat+"&slon="+startLag+"&sname=我的位置&dlat="+endLat+"&dlon="+endLag+"&dname="+address+"&dev=0&m=0&t=2"));
                                                intent.setPackage("com.autonavi.minimap");
                                                startActivity(intent);
                                                  /*  try {
                                                        intent = Intent.getIntent("androidamap://path?sourceApplication=GasStation&sid=BGVIS1&slat=34.264642646862&slon=108.95108518068&sname=当前位置&did=BGVIS2&dlat=34.364642646862&dlon=108.85108518068&dname=终点位置&dev=1&m=2&t=0");
                                                        startActivity(intent);
                                                    } catch (URISyntaxException e) {
                                                        e.printStackTrace();
                                                    }*/

                                                      /*  intent = Intent.getIntent("androidamap://viewMap?sourceApplication=厦门通&poiname=百度奎科大厦&lat=40.047669&lon=116.313082&dev=0");
                                                        startActivity(intent);*/
                                                break;
                                        }
                                    }
                                    @Override
                                    public void onSheetCancelClick() {
                                        ToastUtil.showShortToast(ScenicSpotsDetailsActivity.this,"取消");
                                    }
                                }
                        );
                        XanderPanel xanderPanel = mBuilder.create();
                        xanderPanel.show();
                    }
                }


                break;
            case R.id.introduce_radiobutton:
                introduceRadiobutton.setTextColor(getResources().getColor(R.color.white));
                setColor(1);
                setData(scenicSpotsDetailsBean);

                break;
            case R.id.voice_radiobutton:
                voiceRadiobutton.setTextColor(getResources().getColor(R.color.white));
                transaction.replace(R.id.fragment_framelayout, fragmentAtPosition(1));
                setColor(2);
                break;
            case R.id.cate_radiobutton:
                hotelRadiobutton.setTextColor(getResources().getColor(R.color.white));
                setColor(3);
                transaction.replace(R.id.fragment_framelayout, fragmentAtPosition(2));
                break;
            case R.id.introduce_radiobutton1:
                introduceRadiobutton1.setTextColor(getResources().getColor(R.color.white));
                setColor(1);
                setData(scenicSpotsDetailsBean);
                break;
            case R.id.voice_radiobutton1:
                voiceRadiobutton1.setTextColor(getResources().getColor(R.color.white));
                transaction.replace(R.id.fragment_framelayout, fragmentAtPosition(1));
                setColor(2);
                break;
            case R.id.cate_radiobutton1:
                hotelRadiobutton1.setTextColor(getResources().getColor(R.color.white));
                setColor(3);
                transaction.replace(R.id.fragment_framelayout, fragmentAtPosition(2));
                break;
            case R.id.top_Back:
                finish();
                break;
            case R.id.top_bak:
/*                shareDialog = new ShareDialog(this);
                shareDialog.setCancelButtonOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        shareDialog.dismiss();

                    }
                });
                shareDialog.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1,
                                            int arg2, long arg3) {
                        HashMap<String, Object> item = (HashMap<String, Object>) arg0.getItemAtPosition(arg2);
                        if (item.get("ItemText").equals("QQ")) {
                            QQ.ShareParams sp = new QQ.ShareParams();
                            sp.setTitle("测试分享的标题");
                            sp.setTitleUrl("http://sharesdk.cn"); // 标题的超链接
                            sp.setText("测试分享的文本");
                            sp.setImageUrl("http://f1.sharesdk.cn/imgs/2014/05/21/oESpJ78_533x800.jpg");//分享网络图片

                            Platform qq = ShareSDK.getPlatform(QQ.NAME);
                            qq.setPlatformActionListener(ScenicSpotsDetailsActivity.this); // 设置分享事件回调
                            // 执行分享
                            qq.share(sp);
                        } else {
                            Toast.makeText(ScenicSpotsDetailsActivity.this,"您点中了" + item.get("ItemText"), Toast.LENGTH_LONG).show();
                        }
                        shareDialog.dismiss();

                    }
                });*/
                share(scenicSpotsDetailsBean);
                break;
            case R.id.top_like:
                if (MyUtil.isFastClick()) {
                    return;
                } else {
                    if (MyApplication.IS_LOGIN) {

                        if (collection == 0) {
                            OkHttpUtils.post().url(Myconstant.user_senic_collect).
                                    addParams("uid", "" + MyApplication.USER_ID).addParams("senic_id", SENIC_ID).build().execute(new StringCallback() {
                                @Override
                                public void onError(Request request, Exception e) {
                                }

                                @Override
                                public void onResponse(String response) {
                            /*uid	必须	用户UID
                            senic_id	必须	景区ID*/
                                    if (response.contains("2000")) {
                                        collection++;
                                        top_like.setBackgroundResource(R.mipmap.in_like);
                                    } else {
                                        LogUtil.e(TAG, response);
                                        ToastUtil.showShortToast(ScenicSpotsDetailsActivity.this, "收藏失败");
                                    }
                                }
                            });
                        } else {
                      /*  uid	必须	用户UID
                        senic_id	必须	景区ID*/
                            OkHttpUtils.post().url(Myconstant.user_delect_collect).
                                    addParams("uid", "" + MyApplication.USER_ID).addParams("senic_id", SENIC_ID).build().execute(new StringCallback() {
                                @Override
                                public void onError(Request request, Exception e) {
                                }

                                @Override
                                public void onResponse(String response) {
                            /*uid	必须	用户UID
                            senic_id	必须	景区ID*/
                                    if (response.contains("2000")) {
                                        collection--;
                                        top_like.setBackgroundResource(R.drawable.like2);
                                    } else {
                                        LogUtil.e(TAG, response);
                                        ToastUtil.showShortToast(ScenicSpotsDetailsActivity.this, "取消收藏失败");
                                    }
                                }
                            });
                        }
                    } else {
                        LoginFast.TYPE = 2;
                        startActivity(new Intent(ScenicSpotsDetailsActivity.this, LoginFast.class));
                    }
                }
                break;

        }
        transaction.commit();
    }

    protected Fragment fragmentAtPosition(int position) {
        Fragment fragment = null;
        if (position == 1) {
            if (mvoice == null)
                mvoice = new VoiceIntroduce();
            VoiceIntroduce.IMGGE_URL = url1;
            VoiceIntroduce.SENIC_ID = SENIC_ID;
            VoiceIntroduce.SENIC_NAME = senic_name;
            //VoiceIntroduce.LONG_DESCROPTION=mData.getLong_description();
            fragment = mvoice;
        } else if (position == 2) {
            if (mhotel == null)
                mhotel = new HotelIntroduce();
            HotelIntroduce.SENCIC_ID = SENIC_ID;
            // HotelIntroduce.SENIC_ID=mData.getSenic_id();
            fragment = mhotel;
        }
        return fragment;
    }

    public void setColor(int pos) {
        switch (pos) {
            case 1:
                voiceRadiobutton.setTextColor(getResources().getColor(R.color.colorPrimary));
                hotelRadiobutton.setTextColor(getResources().getColor(R.color.colorPrimary));
                voiceRadiobutton1.setTextColor(getResources().getColor(R.color.colorPrimary));
                hotelRadiobutton1.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case 2:
                introduceRadiobutton.setTextColor(getResources().getColor(R.color.colorPrimary));
                hotelRadiobutton.setTextColor(getResources().getColor(R.color.colorPrimary));
                introduceRadiobutton1.setTextColor(getResources().getColor(R.color.colorPrimary));
                hotelRadiobutton1.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case 3:
                voiceRadiobutton.setTextColor(getResources().getColor(R.color.colorPrimary));
                introduceRadiobutton.setTextColor(getResources().getColor(R.color.colorPrimary));
                voiceRadiobutton1.setTextColor(getResources().getColor(R.color.colorPrimary));
                introduceRadiobutton1.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;

        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            searchLayoutTop = rlayout.getBottom();//获取searchLayout的顶部位置
        }
    }

    @Override
    public void onScroll(int scrollY) {
        int mBuyLayout2ParentTop = Math.max(scrollY, mRadio.getTop());
        mRadioTop.layout(0, mBuyLayout2ParentTop, mRadioTop.getWidth(), mBuyLayout2ParentTop + mRadioTop.getHeight());
    }

    @Override
    public void onCancel(Platform arg0, int arg1) {//回调的地方是子线程，进行UI操作要用handle处理
        handler.sendEmptyMessage(2);
    }
    @Override
    public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {//回调的地方是子线程，进行UI操作要用handle处理
        if (arg0.getName().equals(QQ.NAME)) {// 判断成功的平台是不是QQ
            handler.sendEmptyMessage(1);
        }
    }
    @Override
    public void onError(Platform arg0, int arg1, Throwable arg2) {//回调的地方是子线程，进行UI操作要用handle处理
        arg2.printStackTrace();
        Message msg = new Message();
        msg.what = 3;
        msg.obj = arg2.getMessage();
        handler.sendMessage(msg);
    }
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Toast.makeText(getApplicationContext(), "QQ分享成功", Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    Toast.makeText(getApplicationContext(), "取消分享", Toast.LENGTH_LONG).show();
                    break;
                case 3:
                    Toast.makeText(getApplicationContext(), "分享失败" + msg.obj, Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
        }

    };

private void showShare() {
    ShareSDK.initSDK(this);
    OnekeyShare oks = new OnekeyShare();
    //关闭sso授权
    oks.disableSSOWhenAuthorize();
// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
    //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
    // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
    oks.setTitle(getString(R.string.share));
    // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
    oks.setTitleUrl("http://sharesdk.cn");
    // text是分享文本，所有平台都需要这个字段
    oks.setText("我是分享文本");
    //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
    oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
    // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
    //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
    // url仅在微信（包括好友和朋友圈）中使用
    oks.setUrl("http://sharesdk.cn");
    // comment是我对这条分享的评论，仅在人人网和QQ空间使用
    oks.setComment("我是测试评论文本");
    // site是分享此内容的网站名称，仅在QQ空间使用
    oks.setSite(getString(R.string.app_name));
    // siteUrl是分享此内容的网站地址，仅在QQ空间使用
    oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
    oks.show(this);
}
    private void share(ScenicSpotsDetailsBean bean) {
        String content = "测试";
        if (content.length() > 128) {
            content = content.substring(0, 127);
        }
        ShareControl shareControl=new ShareControl(this);
        shareControl.setBack(new ShareControl.shareonCompleteBack() {
            @Override
            public void onComplete() {
                //ScenicSpotsDetailsActivity.sharecommOK();
            }
        });
        String msg="";
        ScenicSpotsDetailsBean.DataEntity data=bean.getData().get(0);
        if(data.getShort_description().equals("")){
            msg=data.getLong_description();
        }else{
            msg=data.getShort_description();
        }
        OnekeyShare onekeyShare=shareControl.getOnekeyShare(data.getName(),msg,null,Myconstant.BASE_URL+data.getImage(),null,null,data.getShare_app());
        //OnekeyShare onekeyShare=shareControl.getOnekeyShare(content,"contnet",null,"http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg",null,null,null);
        //OnekeyShare onekeyShare = shareControl.getOnekeyShare(content, "测试", bmp, commentEntity.getRelay_url(),bigimagerl,imagerl, null);
        onekeyShare.show(ScenicSpotsDetailsActivity.this);
    }
    private AMapLocationClient mLocationClient;
    private GeocodeSearch geocoderSearch;
    private double startLat;
    private double startLag;

    private void getLocationLat() {
        mLocationClient = new AMapLocationClient(mContext);
        AMapLocationClientOption option = new AMapLocationClientOption();
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        option.setOnceLocation(true);
        mLocationClient.setLocationOption(option);
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        //dismissDialog();
                        String city = aMapLocation.getCity();
                        String district = aMapLocation.getDistrict();
                        startLat=aMapLocation.getLatitude();
                        startLag=aMapLocation.getLongitude();
              /*          mStartPoint.setLatitude(startLat);
                        mStartPoint.setLongitude(startLag);*/
                        LogUtil.e("Latitude:",""+startLat);
                        LogUtil.e("Longitude:",""+startLag);
                        Log.e("onLocationChanged", "city: " + city);
                        Log.e("onLocationChanged", "district: " + district);
                        String location = StringUtils.extractLocation(city, district);
                        ToastUtil.showShortToast(mContext,"定位成功("+city+")，开始导航");
                        iscompelet=true;
                    } else {
                        //定位失败
                        ToastUtil.showShortToast(mContext,"定位失败，请检查相关网络");
                    }
                }
            }
        });
        mLocationClient.startLocation();
    }
  /*   private LatLonPoint mStartPoint = new LatLonPoint(30.589102, 104.054314);//起点，
    private LatLonPoint mEndPoint = new LatLonPoint(33.947568, 111.73278);//终点，

   *//**
     * 响应地理编码
     *//*
    public void getLatlon(String name) {
        LogUtil.e("result","0");
        geocoderSearch=new GeocodeSearch(mContext);
        // showDialog();
        geocoderSearch.setOnGeocodeSearchListener(this);
        GeocodeQuery query = new GeocodeQuery(name, "全国");// 第一个参数表示地址，第二个参数表示查询城市，中文或者中文全拼，citycode、adcode，
        geocoderSearch.getFromLocationNameAsyn(query);// 设置同步地理编码请求
    }

    *//**
     * 逆地理编码回调
     *//*
    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

    }
    *//**
     * 地理编码查询回调
     *//*
    @Override
    public void onGeocodeSearched(GeocodeResult result, int rCode) {
        LogUtil.e("result", "1");
        LogUtil.e("result", ""+rCode);
        if (rCode == 0) {
            if (result != null && result.getGeocodeAddressList() != null && result.getGeocodeAddressList().size() > 0) {
                GeocodeAddress address = result.getGeocodeAddressList().get(0);
                endLat = address.getLatLonPoint().getLatitude();
                endLag = address.getLatLonPoint().getLongitude();
                mEndPoint.setLatitude(endLat);
                mEndPoint.setLongitude(endLag);
                ToastUtil.showShortToast(mContext, "目的地设置成功 " + endLat + " :" + endLag);
                LogUtil.e("result","目的地设置成功 " + endLat + " :" + endLag);
                iscompelet1=true;
            } else {
                ToastUtil.showShortToast(mContext, "查询无结果");
            }
        } else if (rCode == 27) {
            LogUtil.e("result","网络错误 ");
            ToastUtil.showShortToast(mContext, "网络错误");
        } else if (rCode == 32) {
            LogUtil.e("result","错误的key ");
            ToastUtil.showShortToast(mContext, "错误的key");
        } else {
            LogUtil.e("result","其他错误 ");
            ToastUtil.showShortToast(mContext, "其他错误");
        }
    }*/
    public void getList(){
        PackageManager pckMan = getPackageManager();

        List<PackageInfo> packs = pckMan.getInstalledPackages(0);
       int count = packs.size();
        String name;
        int installedNum = 0;
        for(int i = 0; i < count; i++) {
            PackageInfo p = packs.get(i);
          LogUtil.e("info",p.packageName);
        }
    }
    private boolean isInstallByread(String packageName)
    {
        return new File("/data/data/" + packageName).exists();
    }
    private double endLat;
    private double endLag;
    private boolean isCom=false;
    private void getLoc(String name){
        OkHttpUtils.get().url(Myconstant.getGaoDe(name)).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
            }
            @Override
            public void onResponse(String response) {
                LogUtil.e("result",response);
                if(response!=null){
                    try {
                        JSONObject jsonObject=new JSONObject(response);
                        if(jsonObject.getString("info").equals("OK")){
                            JSONArray json=jsonObject.getJSONArray("geocodes");
                            JSONObject jsonObject1=json.getJSONObject(0);
                            String location=jsonObject1.getString("location");
                            double[] gps = new double[2];
                            String []loc=location.split(",");
                            gps[0] = Double.valueOf(loc[0]);
                            gps[1] = Double.valueOf(loc[1]);
                            System.out.println("gps: {}" + Arrays.toString(gps));
                            // return gps;
                            endLat=gps[1];
                            endLag=gps[0];
                            isCom=true;
                            LogUtil.e("result",""+endLat+"   "+endLag);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
