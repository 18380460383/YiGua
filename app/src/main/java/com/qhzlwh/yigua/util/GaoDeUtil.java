package com.qhzlwh.yigua.util;

import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by FuPei on 2016/9/26.
 */
public class GaoDeUtil {
    private static String API = "http://restapi.amap.com/v3/geocode/geo?key=<key>&s=rsv3&address=<address>";

    private static String KEY = "aa4a48297242d22d2b3fd6eddfe62217";

    private static Pattern pattern = Pattern.compile("\"location\":\"(\\d+\\.\\d+),(\\d+\\.\\d+)\"");

    static {
        init();
    }

    private static void init() {
   /*     System.out.println("高德地图工具类初始化");
        System.out.println("api: {}"+API);
        System.out.println("key: {}"+KEY);*/
        API = API.replaceAll("<key>", KEY);
    }

    public static double[] addressToGPS(String address) throws IOException {
            String requestUrl = API.replaceAll("<address>", URLEncoder.encode(address, "UTF-8"));
            System.out.println("请求地址: {}" + requestUrl);
            //requestUrl = HttpClientHelper.INSTANCE.get(requestUrl);
            OkHttpUtils.get().url(requestUrl).build().execute(new StringCallback() {
                @Override
                public void onError(Request request, Exception e) {

                }

                @Override
                public void onResponse(String response) {
                    if(response!=null){
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            if(jsonObject.getString("info").equals("OK")){
                                JSONObject jsonObject1=jsonObject.getJSONObject("geocodes");
                                String location=jsonObject1.getString("location");
                                double[] gps = new double[2];
                                String []loc=location.split(",");
                                gps[0] = Double.valueOf(loc[0]);
                                gps[1] = Double.valueOf(loc[1]);
                                System.out.println("gps: {}" + Arrays.toString(gps));
                               // return gps;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });
        return null;
    }
}
