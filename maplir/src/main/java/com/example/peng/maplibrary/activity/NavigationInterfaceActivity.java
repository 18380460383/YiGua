package com.example.peng.maplibrary.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.AMapNaviView;
import com.amap.api.navi.AMapNaviViewListener;
import com.amap.api.navi.enums.PathPlanningStrategy;
import com.amap.api.navi.model.AMapLaneInfo;
import com.amap.api.navi.model.AMapNaviCross;
import com.amap.api.navi.model.AMapNaviInfo;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AMapNaviPath;
import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.navi.model.NaviLatLng;
import com.amap.api.services.core.LatLonPoint;
import com.autonavi.tbt.TrafficFacilityInfo;
import com.example.peng.maplibrary.R;
import com.example.peng.maplibrary.util.CarLatLngBean;
import com.example.peng.maplibrary.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 导航界面
 */
public class NavigationInterfaceActivity extends Activity implements AMapNaviListener, AMapNaviViewListener{
    private ProgressDialog progDialog = null;
    private NaviLatLng mStartLatlng;
    private NaviLatLng mEndLatlng;
    private AMapNaviView mAMapNaviView;
    private AMapNavi mAMapNavi;
    //private TTSController mTtsManager;//语音
    private List<NaviLatLng> mStartList = new ArrayList<>();
    private List<NaviLatLng> mEndList = new ArrayList<>();
    private LatLonPoint mStartPoint = new LatLonPoint(30.589102,104.054314);//起点，
    private LatLonPoint mEndPoint = new LatLonPoint(33.947568,111.73278);//终点，
    private List<NaviLatLng> mWayPointList;
    private double startLat;
    private double startLag;
    private double endLat;
    private double endLag;
    private  Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_navigation_interface);
        mAMapNaviView = (AMapNaviView) findViewById(R.id.navi_view);
        mAMapNaviView.onCreate(savedInstanceState);
        mAMapNaviView.setAMapNaviViewListener(this);
        progDialog=new ProgressDialog(this);
        //initData();
        initView();
    }
    /**
     * 显示进度条对话框
     */
    public void showDialog() {
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setIndeterminate(false);
        progDialog.setCancelable(true);
        progDialog.setMessage("导航数据加载中");
        progDialog.show();
    }

    /**
     * 隐藏进度条对话框
     */
    public void dismissDialog() {
        if (progDialog != null) {
            progDialog.dismiss();
        }
    }


    private void initView() {

        dismissDialog();
        bundle=new Bundle();
        bundle=getIntent().getExtras();
        if(bundle!=null){
            startLat=bundle.getDouble("startLat");
            startLag=bundle.getDouble("startLag");
            endLat=bundle.getDouble("endLat");
            endLag=bundle.getDouble("endLag");
        }else{
            startLat = mStartPoint.getLatitude();
            startLag = mStartPoint.getLongitude();
            endLat = mEndPoint.getLatitude();
            endLag = mEndPoint.getLongitude();
        }
        float speed = 0;
        int sp = (int)speed;
        mStartLatlng = new NaviLatLng(startLat, startLag);
        mEndLatlng = new NaviLatLng(endLat, endLag);
        Log.e(">>>>>>.", "" + mStartLatlng);
        Log.e("mEndLatlng", "" + mEndLatlng);
        //为了尽最大可能避免内存泄露问题，建议这么写
        try{
            mAMapNavi = AMapNavi.getInstance(getApplicationContext());
            Log.e("mAMapNavi", "" + mAMapNavi);
            mAMapNavi.addAMapNaviListener(this);
         /*   if (sp == 0) {
                mAMapNavi.setEmulatorNaviSpeed(150);
            } else {
                mAMapNavi.setEmulatorNaviSpeed(sp);
            }*/
            mAMapNavi.setEmulatorNaviSpeed(sp);
        }catch (Exception e){
           ToastUtil.show(this,e.toString());
            finish();
        }

    }

    /**
     * 如果使用无起点算路，请这样写
     */
    private void noStartCalculate() {
        //无起点算路须知：
        //AMapNavi在构造的时候，会startGPS，但是GPS启动需要一定时间
        //在刚构造好AMapNavi类之后立刻进行无起点算路，会立刻返回false
        //给人造成一种等待很久，依然没有算路成功 算路失败回调的错觉
        //因此，建议，提前获得AMapNavi对象实例，并判断GPS是否准备就绪
        if (mAMapNavi.isGpsReady())
            mAMapNavi.calculateDriveRoute(mEndList, mWayPointList, PathPlanningStrategy.DRIVING_DEFAULT);
    }

    @Override
    protected void onResume() {
        super.onResume();
/*        mAMapNaviView.onResume();
        mStartList.add(mStartLatlng);
        mEndList.add(mEndLatlng);*/
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            mAMapNaviView.onPause();
        }
        catch (Exception e){

        }
//        仅仅是停止你当前在说的这句话，一会到新的路口还是会再说的
        //mTtsManager.stopSpeaking();
//
//        停止导航之后，会触及底层stop，然后就不会再有回调了，但是讯飞当前还是没有说完的半句话还是会说完
//        mAMapNavi.stopNavi();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try{
            mAMapNaviView.onDestroy();
            //since 1.6.0 不再在naviview destroy的时候自动执行AMapNavi.stopNavi();
            //请自行执行
            mAMapNavi.stopNavi();
            mAMapNavi.destroy();
        }
        catch (Exception e){

        }

      //  mTtsManager.destroy();
    }

    @Override
    public void onInitNaviFailure() {
        Toast.makeText(this, "init navi Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInitNaviSuccess() {
        mAMapNavi.calculateDriveRoute(mStartList, mEndList, mWayPointList, PathPlanningStrategy.DRIVING_DEFAULT);
    }

    @Override
    public void onStartNavi(int type) {

    }

    @Override
    public void onTrafficStatusUpdate() {

    }

    @Override
    public void onLocationChange(AMapNaviLocation location) {

    }

    @Override
    public void onGetNavigationText(int type, String text) {

    }

    @Override
    public void onEndEmulatorNavi() {
    }

    @Override
    public void onArriveDestination() {
    }

    @Override
    public void onCalculateRouteSuccess() {
        Log.e("ASDAS>F","asdgasgdsaasdf");
        mAMapNavi.startNavi(AMapNavi.EmulatorNaviMode);
//        mAMapNavi.startNavi(AMapNavi.GPSNaviMode);
        AMapNaviPath naviPath = mAMapNavi.getNaviPath();
        if (naviPath == null) {
            return;
        }
    }

    @Override
    public void onCalculateRouteFailure(int errorInfo) {
    }

    @Override
    public void onReCalculateRouteForYaw() {

    }

    @Override
    public void onReCalculateRouteForTrafficJam() {

    }

    @Override
    public void onArrivedWayPoint(int wayID) {

    }

    @Override
    public void onGpsOpenStatus(boolean enabled) {
    }

    @Override
    public void onNaviSetting() {
    }

    @Override
    public void onNaviMapMode(int isLock) {

    }

    @Override
    public void onNaviCancel() {
        finish();
    }


    @Override
    public void onNaviTurnClick() {

    }

    @Override
    public void onNextRoadClick() {

    }


    @Override
    public void onScanViewButtonClick() {
    }

    @Override
    public void onNaviInfoUpdated(AMapNaviInfo naviInfo) {

    }

    private ArrayList<CarLatLngBean> list = new ArrayList<>();
    @Override
    public void onNaviInfoUpdate(NaviInfo naviinfo) {
     /*   CarLatLngBean bean = new CarLatLngBean();
        bean.setLatitude(naviinfo.getCoord().getLatitude());
        bean.setLongitude(naviinfo.getCoord().getLongitude());
        list.add(bean);
        MainActivity.instance.initRoadData(list);
        if (list.size() > 2) {
            MainActivity.instance.moveLooper();
        } else {
            return;
        }*/
    }

    @Override
    public void OnUpdateTrafficFacility(TrafficFacilityInfo trafficFacilityInfo) {

    }

    @Override
    public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo aMapNaviTrafficFacilityInfo) {

    }

    @Override
    public void showCross(AMapNaviCross aMapNaviCross) {
    }

    @Override
    public void hideCross() {
    }

    @Override
    public void showLaneInfo(AMapLaneInfo[] laneInfos, byte[] laneBackgroundInfo, byte[] laneRecommendedInfo) {

    }

    @Override
    public void hideLaneInfo() {

    }

    @Override
    public void onCalculateMultipleRoutesSuccess(int[] ints) {

    }

    @Override
    public void notifyParallelRoad(int i) {

    }


    @Override
    public void onLockMap(boolean isLock) {
    }

    @Override
    public void onNaviViewLoaded() {

    }

    @Override
    public boolean onNaviBackClick() {
        return false;
    }


}
