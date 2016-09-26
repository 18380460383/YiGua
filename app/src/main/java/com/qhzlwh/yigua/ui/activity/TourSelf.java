package com.qhzlwh.yigua.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
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

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.application.MyApplication;
import com.qhzlwh.yigua.contral.ShareControl;
import com.qhzlwh.yigua.ui.fragment.RouteReviews;
import com.qhzlwh.yigua.ui.fragment.SingnUpKnow;
import com.qhzlwh.yigua.ui.fragment.TourselfInformation;
import com.qhzlwh.yigua.util.LogUtil;
import com.qhzlwh.yigua.util.Myconstant;
import com.qhzlwh.yigua.util.StringUtils;
import com.qhzlwh.yigua.util.ToastUtil;
import com.qhzlwh.yigua.view.CircularImage;
import com.qhzlwh.yigua.view.ListViewNoSrcoll;
import com.qhzlwh.yigua.view.MyScrollView;
import com.qhzlwh.yigua.view.MyScrollViewL;
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
import java.util.List;

import abslistview.CommonAdapter;
import abslistview.ViewHolder;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * 自驾游或者线路游 type=21?22
 */
public class TourSelf extends BaseActivity implements  View.OnClickListener, MyScrollView.OnScrollListener, MyScrollViewL.OnScrollListener {
    private RadioButton mRadioButton1;
    private RadioButton mRadioButton2;
    private RadioButton mRadioButton3;
    private RadioButton mRadioButton11;
    private RadioButton mRadioButton21;
    private RadioButton mRadioButton31;
    private RelativeLayout group2;
    private TourselfInformation mTourselfInformation;
    private SingnUpKnow mSingnUpKnow;
    private RouteReviews mRouteReviews;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private TextView signup_explain_message;
    private TextView mTextViewNext;
    private TextView mTextViewTitle;
    private ImageView imageView;
    private CircularImage mImageViewBak;
    private CircularImage mImageViewLike;
    private com.qhzlwh.yigua.bean.TourSelf.DataEntity mData;
    public static String SENIC_ID="127";
    public static String TYPE="22";
    private String url="http://qh.91chuanbo.cn/Api/Api/SenicDetailById/senic_id/";
    private TextView mtop_head_name;
    private TextView mShortTitle;
    private TextView mPrice;
    private TextView mPhone;
    private TextView mLocation;
    private TextView mLongTitle;
    private Button mSingn;
    private ImageView mTopHead;
    private ImageView type;
    private ViewPager mViewPage;
    private List<Fragment> fragmentList;
    private FragmentPagerAdapter mAdapter;
    private ListViewNoSrcoll item_listview;
    private RadioGroup mRadioGroup;
    private MyScrollViewL myScrollView;
    private int searchLayoutTop;
    private LinearLayout search01,search02;
    private LinearLayout mRadio,mRadioTop;
    private RelativeLayout rlayout;
    private static final String TAG="TourSelf";
    private Context mContext;
    private  String address="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_self);
        showProgressDialog("数据加载中。。。");
        mContext=this;
        initView();
    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.top_circul_back);
        mImageViewBak = (CircularImage) findViewById(R.id.top_bak);
        mImageViewLike = (CircularImage) findViewById(R.id.top_like);
        mTextViewTitle = (TextView) findViewById(R.id.top_title);
        mTextViewTitle.setText("路线游");
        imageView.setOnClickListener(this);
        mImageViewLike.setOnClickListener(this);
        mSingn = (Button) findViewById(R.id.signup);
        mSingn.setOnClickListener(this);
        mRadioButton1= (RadioButton) findViewById(R.id.radiobutton1);
        mRadioButton2= (RadioButton) findViewById(R.id.radiobutton2);
        mRadioButton3= (RadioButton) findViewById(R.id.radiobutton3);
        mRadioButton11= (RadioButton) findViewById(R.id.radiobutton11);
        mRadioButton21= (RadioButton) findViewById(R.id.radiobutton21);
        mRadioButton31= (RadioButton) findViewById(R.id.radiobutton31);

        mtop_head_name = (TextView) findViewById(R.id.top_head_name);
        mShortTitle = (TextView) findViewById(R.id.item_short_title);
        mLongTitle = (TextView) findViewById(R.id.item_long_title);
        mLocation = (TextView) findViewById(R.id.gather_location);
        mPhone = (TextView) findViewById(R.id.contact_way);
        mPrice = (TextView) findViewById(R.id.item_price);
        item_listview= (ListViewNoSrcoll) findViewById(R.id.item_listview);
       // signup_explain_message = (TextView) findViewById(R.id.signup_explain_message);
        mTopHead = (ImageView) findViewById(R.id.top_head_image);
        type = (ImageView) findViewById(R.id.tour_type_image);
       // mViewPage = (ViewPager) findViewById(R.id.id_stickynavlayout_viewpager);
        myScrollView = (MyScrollViewL) findViewById(R.id.myScrollView);
        mRadioGroup = (RadioGroup) findViewById(R.id.id_stickynavlayout_indicator);
        search01 = (LinearLayout)findViewById(R.id.search01);
        search02 = (LinearLayout)findViewById(R.id.search02);
        mRadio = (LinearLayout)findViewById(R.id.radio);
        mRadioTop = (LinearLayout)findViewById(R.id.radio_top);
        group2= (RelativeLayout) findViewById(R.id.group2);
        group2.setOnClickListener(this);
        mRadioButton1.setOnClickListener(this);
        mRadioButton2.setOnClickListener(this);
        mRadioButton3.setOnClickListener(this);
        mRadioButton11.setOnClickListener(this);
        mRadioButton21.setOnClickListener(this);
        mRadioButton31.setOnClickListener(this);
        myScrollView.setOnScrollListener(this);
        mImageViewBak.setOnClickListener(this);
        findViewById(R.id.parent_layout).getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //这一步很重要，使得上面的购买布局和下面的购买布局重合
                onScroll(myScrollView.getScrollY());
                System.out.println(myScrollView.getScrollY());
            }
        });
        manager=getSupportFragmentManager();
        initData();
    }

    private void initData() {
        Log.e("tourself",url+SENIC_ID);
        OkHttpUtils.get().url(Myconstant.SenicLikeOrCollect+"senic_id/"+SENIC_ID+"/uid/"+MyApplication.USER_ID).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                if (response.contains("\"is_collect\":1")) {
                    mImageViewLike.setBackgroundResource(R.mipmap.in_like);
                }
            }
        });
        OkHttpUtils.get().url(url+SENIC_ID).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }
            @Override
            public void onResponse(String response) {


      /*          //1.将两个经纬度点转成投影点

                MAMapPoint point1 = MAMapPointForCoordinate(CLLocationCoordinate2DMake(39.989612,116.480972));

                MAMapPoint point2 = MAMapPointForCoordinate(CLLocationCoordinate2DMake(39.990347,116.480441));
//2.计算距离
                CLLocationDistance distance = MAMetersBetweenMapPoints(point1,point2);*/
                Gson gson = new Gson();
                com.qhzlwh.yigua.bean.TourSelf tourSelf = gson.fromJson(response, com.qhzlwh.yigua.bean.TourSelf.class);
                mData = tourSelf.getData().get(0);

                address=mData.getAddress();
                getLoc(address);
                getLocationLat();
                mtop_head_name.setText(mData.getName());
                mTextViewTitle.setText(mData.getName());
                mShortTitle.setText(mData.getTour_name());
                mLongTitle.setText(mData.getLong_title());
                mPhone.setText(getResources().getString(R.string.contact_way) + mData.getTelephone());
                mPrice.setText("门票价格：");
                mPrice.append(getColorText("¥" + mData.getPrice() + "/人", "#ffea15"));
                // signup_explain_message.setText(Html.fromHtml(mData.getTicket()));
                if (TYPE.equals("22")) {
                    mLocation.setText(getResources().getString(R.string.gather) + mData.getAddress());
                } else {
                    mLocation.setText(getResources().getString(R.string.tour_name) + mData.getTour_name());
                    Glide.with(TourSelf.this).load(R.mipmap.tour).into(type);
                }
                item_listview.setAdapter(new CommonAdapter<com.qhzlwh.yigua.bean.TourSelf.DataEntity.TicketsEntity>(TourSelf.this, R.layout.tikect_listview_layout, tourSelf.getData().get(0).getTickets()) {
                    @Override
                    protected void convert(ViewHolder viewHolder, final com.qhzlwh.yigua.bean.TourSelf.DataEntity.TicketsEntity item, int position) {
                        if(item.getTicket_type().equals("2")){
                            viewHolder.setText(R.id.tikect_type,item.getTicket_name()+" ("+item.getTicket_type_name()+")");
                        }else{
                            viewHolder.setText(R.id.tikect_type, item.getTicket_name());
                        }
                        viewHolder
                                .setText(R.id.tikect_price, item.getTicket_yprice()+"/人")
                                .setText(R.id.tikect_leave, item.getTicket_number())
                                .setText(R.id.tikect_explan, item.getTicket_description())
                                .setText(R.id.tikect_charge, item.getTips());
                        Button button = viewHolder.getView(R.id.item_submit);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle bundle=new Bundle();
                                if (TYPE.equals("22")) {
                                    bundle.putString("order_type","4");
                                    bundle.putString("ORDER_TYPE","自驾游");
                               /*     OrderWrite.order_type="4";
                                    OrderWrite.ORDER_TYPE="自驾游";*/
                                }else{
                                    bundle.putString("order_type","3");
                                    bundle.putString("ORDER_TYPE","线路游");
                                   /* OrderWrite.order_type = "3";
                                    OrderWrite.ORDER_TYPE="线路游";*/
                                }
                                bundle.putString("SENIC_ID",mData.getSenic_id());
                                bundle.putString("TIKECT_ID",item.getTicket_id());
                                bundle.putFloat("PRICE",Float.valueOf(item.getTicket_yprice()));
                                bundle.putString("SENIC_NAME",mData.getName());
                                bundle.putString("SENIC_ADDRESS",mData.getAddress());
                                bundle.putString("TIKECT_TYPE",item.getTicket_name());
                                if(MyApplication.IS_LOGIN){
                                   /* if (item.getTicket_name().contains("儿童")) {
                                        // OrderWrite.PRICE_CHILD=Integer.valueOf(item.getTicket_yprice());
                                        OrderWrite.PRICE_CHILD = Float.parseFloat(item.getTicket_yprice());
                                    } else if (item.getTicket_name().contains("成人")) {
                                        OrderWrite.PRICE_ADULT = Float.parseFloat(item.getTicket_yprice());
                                    }*/
                                    Intent intent = new Intent(TourSelf.this, OrderWrite.class);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }else{
                                    LoginFast.TYPE=1;
                                    Intent intent = new Intent(TourSelf.this, LoginFast.class);
                                    startActivity(intent);
                                }
                            }
                        });
                    }
                });
                dismissProgressDialog();
                Glide.with(TourSelf.this).load(Myconstant.BASE_URL + mData.getImage()).into(mTopHead);
                transaction = manager.beginTransaction();
                transaction.replace(R.id.container, fragmentAtPosition(0));
                transaction.commit();
            }
        });
    }


    protected Fragment fragmentAtPosition(int position) {
        Fragment fragment = null;
        if (position == 0) {
            if (mTourselfInformation == null)
                mTourselfInformation = new TourselfInformation();

          /*  Bundle bundle=new Bundle();
            bundle.putString(TourselfInformation.SENIC_ID,mData.getSenic_id());
            mTourselfInformation.setArguments(bundle);*/
            mTourselfInformation.SENIC_ID=mData.getSenic_id();

            fragment = mTourselfInformation;
        } else if (position == 1) {
            if (mSingnUpKnow == null)
                mSingnUpKnow = new SingnUpKnow();

         /*   Bundle bundle=new Bundle();
            bundle.putString(SingnUpKnow.LONG_DESCROPTION,mData.getLong_description());
            mSingnUpKnow.setArguments(bundle);*/
            SingnUpKnow.LONG_DESCROPTION=mData.getLong_description();
            fragment = mSingnUpKnow;
        } else if (position == 2) {
            if (mRouteReviews == null)
                mRouteReviews = new RouteReviews();
         /*   Bundle bundle=new Bundle();
            bundle.putString(mRouteReviews.SENIC_ID,mData.getSenic_id());
            mRouteReviews.setArguments(bundle);*/
            mRouteReviews.SENIC_ID=mData.getSenic_id();
            fragment = mRouteReviews;
        }
        return fragment;
    }

    @Override
    public void onClick(View v) {
        transaction =manager.beginTransaction();
        switch (v.getId()){
            case R.id.radiobutton1:
                setColor(1);
                mRadioButton1.setTextColor(getResources().getColor(R.color.white));
                transaction.replace(R.id.container, fragmentAtPosition(0));
              /*  fragmentAtPosition(0);
                mViewPage.setCurrentItem(0);*/
                break;
            case R.id.radiobutton2:
                setColor(2);
                mRadioButton2.setTextColor(getResources().getColor(R.color.white));
                transaction.replace(R.id.container, fragmentAtPosition(1));
              /*  fragmentAtPosition(1);
                mViewPage.setCurrentItem(1);*/
                break;
            case R.id.radiobutton3:
                setColor(3);
                mRadioButton3.setTextColor(getResources().getColor(R.color.white));
                transaction.replace(R.id.container, fragmentAtPosition(2));
               /* fragmentAtPosition(2);
                mViewPage.setCurrentItem(2);*/
                break;
            case R.id.radiobutton11:
                setColor(1);
                mRadioButton11.setTextColor(getResources().getColor(R.color.white));
                transaction.replace(R.id.container, fragmentAtPosition(0));
              /*  fragmentAtPosition(0);
                mViewPage.setCurrentItem(0);*/
                break;
            case R.id.radiobutton21:
                setColor(2);
                mRadioButton21.setTextColor(getResources().getColor(R.color.white));
                transaction.replace(R.id.container, fragmentAtPosition(1));
              /*  fragmentAtPosition(1);
                mViewPage.setCurrentItem(1);*/
                break;
            case R.id.radiobutton31:
                setColor(3);
                mRadioButton31.setTextColor(getResources().getColor(R.color.white));
                transaction.replace(R.id.container, fragmentAtPosition(2));
               /* fragmentAtPosition(2);
                mViewPage.setCurrentItem(2);*/
                break;
            case R.id.top_circul_back:
                finish();
                break;
            case R.id.top_bak:
                //ToastUtil.showShortToast(TourSelf.this, "bar");
                share();
                break;
            case R.id.top_like:
               // ToastUtil.showShortToast(TourSelf.this,"like");
                if(MyApplication.IS_LOGIN){
                    OkHttpUtils.post().url(Myconstant.user_senic_collect).
                            addParams("uid", ""+MyApplication.USER_ID).addParams("senic_id", SENIC_ID).build().execute(new StringCallback() {
                        @Override
                        public void onError(Request request, Exception e) {

                        }

                        @Override
                        public void onResponse(String response) {
                            /*uid	必须	用户UID
                            senic_id	必须	景区ID*/
                            if (response.contains("2000")) {
                                mImageViewLike.setBackgroundResource(R.mipmap.in_like);
                            } else {
                                LogUtil.e(TAG, response);
                                ToastUtil.showShortToast(TourSelf.this, "收藏失败");
                            }
                        }
                    });
                }
                else{
                    LoginFast.TYPE=2;
                    startActivity(new Intent(TourSelf.this,LoginFast.class));
                }
                break;
            case R.id.signup:
               Intent intent=new Intent(TourSelf.this,OrderWrite.class);
                startActivity(intent);
                break;
            case R.id.group2:
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
                        XanderPanel.Builder mBuilder = new XanderPanel.Builder(mContext);
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
                                        ToastUtil.showShortToast(mContext,"取消");
                                    }
                                }
                        );
                        XanderPanel xanderPanel = mBuilder.create();
                        xanderPanel.show();
                    }
                }

                break;

        }
        transaction.commit();
    }
    public void setColor(int pos){
        switch (pos){
            case 1:
                mRadioButton2.setTextColor(getResources().getColor(R.color.colorPrimary));
                mRadioButton3.setTextColor(getResources().getColor(R.color.colorPrimary));
                mRadioButton21.setTextColor(getResources().getColor(R.color.colorPrimary));
                mRadioButton31.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case 2:
                mRadioButton1.setTextColor(getResources().getColor(R.color.colorPrimary));
                mRadioButton3.setTextColor(getResources().getColor(R.color.colorPrimary));
                mRadioButton11.setTextColor(getResources().getColor(R.color.colorPrimary));
                mRadioButton31.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case 3:
                mRadioButton2.setTextColor(getResources().getColor(R.color.colorPrimary));
                mRadioButton1.setTextColor(getResources().getColor(R.color.colorPrimary));
                mRadioButton21.setTextColor(getResources().getColor(R.color.colorPrimary));
                mRadioButton11.setTextColor(getResources().getColor(R.color.colorPrimary));

                break;
        }
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
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            //searchLayoutTop = rlayout.getBottom();//获取searchLayout的顶部位置
        }
    }

/*    @Override
    public void onScroll(int scrollY) {
        if(scrollY >= searchLayoutTop){
            if (mRadioGroup.getParent()!=search01) {
                search02.removeView(mRadioGroup);
                search01.addView(mRadioGroup);
            }
        }else{
            if (mRadioGroup.getParent()!=search02) {
                search01.removeView(mRadioGroup);
                search02.addView(mRadioGroup);
            }
        }
    }*/
   Handler mhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mRadio.setVisibility(View.VISIBLE);
            mRadioTop.setVisibility(View.VISIBLE);
        }
    };
    public void setVisiable(){
        Message message=new Message();
        message.what=1;
        mhandler.handleMessage(message);
    }

    @Override
    public void onScroll(int scrollY) {
        int mBuyLayout2ParentTop = Math.max(scrollY, mRadio.getTop());
        mRadioTop.layout(0, mBuyLayout2ParentTop, mRadioTop.getWidth(), mBuyLayout2ParentTop + mRadioTop.getHeight());
    }
    private void share() {
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
        com.qhzlwh.yigua.bean.TourSelf.DataEntity data=mData;
        if(data.getShort_description().equals("")){
            msg=data.getLong_description();
        }else{
            msg=data.getShort_description();
        }
        OnekeyShare onekeyShare=shareControl.getOnekeyShare(data.getName(),msg,null,Myconstant.BASE_URL+data.getImage(),null,Myconstant.BASE_URL+data.getImage(),data.getShare_app());
        //OnekeyShare onekeyShare=shareControl.getOnekeyShare(content,"contnet",null,"http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg",null,null,null);
        //OnekeyShare onekeyShare = shareControl.getOnekeyShare(content, "测试", bmp, commentEntity.getRelay_url(),bigimagerl,imagerl, null);
        onekeyShare.show(TourSelf.this);
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
    private boolean isInstallByread(String packageName)
    {
        return new File("/data/data/" + packageName).exists();
    }
    private double endLat;
    private double endLag;
    private boolean iscompelet=false;
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
