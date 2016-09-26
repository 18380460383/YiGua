package com.qhzlwh.yigua.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.util.FileUtil;
import com.qhzlwh.yigua.util.LogUtil;
import com.qhzlwh.yigua.util.Myconstant;

import java.io.File;
import java.io.IOException;

/**
*购票须知
 */
public class SingnUpKnow extends Fragment {

    public static  String LONG_DESCROPTION="long_description";
    private TextView mMessage;
    private WebView mWebView;
    private String TAG="SingnUpKnow";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.signup_know, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mMessage = (TextView) view.findViewById(R.id.signup_know_message);
        mWebView = (WebView) view.findViewById(R.id.item_show_long);
        WebSettings settings=mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        initData();

    }

    private void initData() {
        String html= FileUtil.getFromRaw(getActivity(), R.raw.currencyhtml);
        String data=html.replace("%@",LONG_DESCROPTION);

        // FileUtil.writeSDFile(data);
        try {
            FileUtil.writeSDFile(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LogUtil.e(TAG, "file:/" + Myconstant.PATH + Myconstant.fileName);
        // mWebView.loadUrl("file:/" + Myconstant.PATH + Myconstant.fileName);
        mWebView.loadUrl("file:///mnt/sdcard/"+ File.separator+"rawHtml"+File.separator+ Myconstant.fileName);
    }
}
