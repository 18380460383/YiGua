package com.qhzlwh.yigua.util;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.geocoder.GeocodeAddress;
import com.amap.api.services.geocoder.GeocodeQuery;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeResult;

/**
 * Created by FuPei on 2016/9/23.
 */
public class naviUtil implements GeocodeSearch.OnGeocodeSearchListener {
    private LatLonPoint mStartPoint = new LatLonPoint(30.589102, 104.054314);//起点，
    private LatLonPoint mEndPoint = new LatLonPoint(33.947568, 111.73278);//终点，
    private double startLat;
    private double startLag;
    private double endLat;
    private double endLag;
    private Context mContext;
    private AMapLocationClient mLocationClient;
    private GeocodeSearch geocoderSearch;
    public naviUtil(Context mContext) {
        this.mContext = mContext;
    }

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
                        mStartPoint.setLatitude(startLat);
                        mStartPoint.setLongitude(startLag);
                        LogUtil.e("Latitude:",""+startLat);
                        LogUtil.e("Longitude:",""+startLag);
                        Log.e("onLocationChanged", "city: " + city);
                        Log.e("onLocationChanged", "district: " + district);
                        String location = StringUtils.extractLocation(city, district);
                        ToastUtil.showShortToast(mContext,"定位成功("+city+")，开始导航");
                        setmStartPoint(mStartPoint);
                    } else {
                        //定位失败
                        ToastUtil.showShortToast(mContext,"定位失败，请检查相关网络");
                    }
                }
            }
        });
        mLocationClient.startLocation();
    }

    public LatLonPoint getmStartPoint() {
        return mStartPoint;
    }

    public void setmStartPoint(LatLonPoint mStartPoint) {
        this.mStartPoint = mStartPoint;
    }

    public LatLonPoint getmEndPoint() {
        return mEndPoint;
    }

    public void setmEndPoint(LatLonPoint mEndPoint) {
        this.mEndPoint = mEndPoint;
    }

    public double getStartLat() {
        return startLat;
    }

    public void setStartLat(double startLat) {
        this.startLat = startLat;
    }

    public double getStartLag() {
        return startLag;
    }

    public void setStartLag(double startLag) {
        this.startLag = startLag;
    }

    public double getEndLat() {
        return endLat;
    }

    public void setEndLat(double endLat) {
        this.endLat = endLat;
    }

    public double getEndLag() {
        return endLag;
    }

    public void setEndLag(double endLag) {
        this.endLag = endLag;
    }


    /**
     * 响应地理编码
     */
    public void getLatlon(String name,String cityId) {
        LogUtil.e("result","0");
        geocoderSearch=new GeocodeSearch(mContext);
        // showDialog();
        geocoderSearch.setOnGeocodeSearchListener(this);
        GeocodeQuery query = new GeocodeQuery(name, cityId);// 第一个参数表示地址，第二个参数表示查询城市，中文或者中文全拼，citycode、adcode，
        geocoderSearch.getFromLocationNameAsyn(query);// 设置同步地理编码请求
    }
    /**
     * 逆地理编码回调
     */
    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {

    }

    /**
     * 地理编码查询回调
     */
    @Override
    public void onGeocodeSearched(GeocodeResult result, int rCode) {
        LogUtil.e("result", "1");
        if (rCode == 0) {
            if (result != null && result.getGeocodeAddressList() != null && result.getGeocodeAddressList().size() > 0) {
                GeocodeAddress address = result.getGeocodeAddressList().get(0);
                endLat = address.getLatLonPoint().getLatitude();
                endLag = address.getLatLonPoint().getLongitude();
                mEndPoint.setLatitude(endLat);
                mEndPoint.setLongitude(endLag);
                setmEndPoint(mEndPoint);
                ToastUtil.showShortToast(mContext, "目的地设置成功 " + endLat + " :" + endLag);
            } else {
                ToastUtil.showShortToast(mContext, "查询无结果");
            }
        } else if (rCode == 27) {
            ToastUtil.showShortToast(mContext, "网络错误");
        } else if (rCode == 32) {
            ToastUtil.showShortToast(mContext, "错误的key");
        } else {
            ToastUtil.showShortToast(mContext, "其他错误");
        }
    }
}
