package com.qhzlwh.yigua.util;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;




/**
 * Created by peter on 2016/8/27.
 */
public class HttpUtil {
    public static final String BASE_URL_OFFICE = "http://bicheng.scapp123.com/index.php/ApiA/";
    public static final String BASE_OUT_URL="http://qh.91chuanbo.cn/Api/";
    //    public static final String BASE_URL = "http://192.168.3.14/bc/index.php/ApiA/";//本地测试服务器

/*    public static synchronized Retrofit getRetrofit() {
//        if (retrofit == null) {
        retrofit = new Retrofit.Builder().baseUrl(BASE_OUT_URL).addConverterFactory(ScalarsConverterFactory.create()).build();
//        }
        return retrofit;
    }*/

    public HttpUtil() {
    }
    private static final String STATUS_LOGIN_SUCCESS = "SUCCESS";
    private static final String APPKEY = "f819a5d294fc451989eccc7f909b8107";

    private static AsyncHttpClient mHttpClient;

    private static HttpUtil mUtil;

    private static String mToken;

    private static String mUserKey;



    public static HttpUtil getInstance() {
        if(mHttpClient == null) {
            mHttpClient = new AsyncHttpClient();
            mHttpClient.setTimeout(5 * 1000);
        }
        if(mUtil == null) {
            mUtil = new HttpUtil();
        }
        return mUtil;
    }
/*    public void post(final Context context, String[] url, RequestParams params, final AesListener2 listener) {
        initParams(params, url);
        getClient(context).post(context, EnConstants.URL_API, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                if (bytes != null && i == 200) {
                    String text = new String(bytes);
                    try {
                        JSONObject json = new JSONObject(text);
                        String errorCode = json.getString("errcode");
                        String errorMsg = json.getString("errmsg");
                        String data = AESUtils.decrypt(json.getString("data"));
                        listener.onSuccess(errorCode, errorMsg, data);
                    } catch (JSONException e) {
                      //  EshareLoger.logI("jsonException:" + text);
                        listener.onFail("服务器发生未知错误");
                    }
                } else {
                    listener.onFail(new String(bytes));
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
               // EToastUtil.show(context, "无法连接网络");
                listener.onFail("无法连接网络");
            }
        });
    }
    private void initParams(RequestParams params, String[] url) {
//        if(!url.equals(EnConstants.URL_GET_WRITER)) {
        PublicParameter.getPublicParameter(AppContext.getInstance()).getRequestParams(params);
//        }
        params.add("controller", url[0]);
        params.add("func", url[1]);
        params.put("public_source", "fc5caa024640c1c559dec6bd885e2f36");
    }*/
    /**
     * 取消当前context网络连接的请求
     * @param context
     */
    public void cancelRequest(Context context) {
        mHttpClient.cancelRequests(context, true);
    }

    public interface AesListener{
        void onSuccess(String result);
        void onFail(int code, String result);
        void onWebError();
    }

    public interface AesListener2{
        void onSuccess(String errorCode, String errorMsg, String data);
        void onFail(String result);
    }
}
