package com.qhzlwh.yigua.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.bean.SenicSpotsParms;
import com.qhzlwh.yigua.ui.activity.ScenicCommentMore;
import com.qhzlwh.yigua.util.FileUtil;
import com.qhzlwh.yigua.util.LogUtil;
import com.qhzlwh.yigua.util.Myconstant;
import com.qhzlwh.yigua.view.ListViewNoSrcoll;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import abslistview.CommonAdapter;
import abslistview.ViewHolder;

/**
 * 创建者：Administrator
 * 时间：2016/8/16
 * 功能描述：ScenicSpotsDetailsActivity 选择景区介绍所加载的Fragment
 */
public class ScenicSpotIntroduceFragment extends Fragment implements View.OnClickListener {
    public static final String OPEN="open";
    public static final String TRAFFIC="traffic";
    public static final String SHORT_DESCRIPTION="short_description";
        public static final String SPECIAL_TELL="short_description";
        public static final String TELPHONE="telphone";
    public static String SENIC_ID="18";
    private TextView shortDescriptionTextview;
    private TextView usefulInformationTextview;
    private TextView scenic_spot_detail_fragment_mustknow;
    private TextView scenic_spot_detail_fragment_notice;
    private ListViewNoSrcoll listRecommend;
    private ListViewNoSrcoll listComment;
    private List<SenicSpotsParms.DataEntity.RecommendSpotEntity> mRecommend;
    private List<SenicSpotsParms.DataEntity.SenicCommentEntity>mComment;
    private String TAG="ScenicSpotIntroduceFragment";
    private WebView mWebView;

    private TextView scenic_spot_detail_fragment_showall;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_spot_introduce, container, false);
        shortDescriptionTextview = (TextView) inflate.findViewById(R.id.short_description_textview);
        usefulInformationTextview = (TextView) inflate.findViewById(R.id.useful_information_textview);
        initView(inflate);
        Bundle arguments = getArguments();
        setData(arguments.getString(OPEN), arguments.getString(TRAFFIC), arguments.getString(SHORT_DESCRIPTION), arguments.getString(TELPHONE));
        return inflate;
    }

    private void initView(View view) {
        scenic_spot_detail_fragment_mustknow = (TextView) view.findViewById(R.id.scenic_spot_detail_fragment_mustknow);
        scenic_spot_detail_fragment_notice = (TextView) view.findViewById(R.id.scenic_spot_detail_fragment_notice);
        scenic_spot_detail_fragment_showall = (TextView) view.findViewById(R.id.scenic_spot_detail_fragment_showall);
        listRecommend = (ListViewNoSrcoll) view.findViewById(R.id.scenic_spot_detail_fragment_listview);
        listRecommend.setFocusable(false);

        listComment = (ListViewNoSrcoll) view.findViewById(R.id.scenic_spot_detail_fragment_listview_charge);
        mWebView = (WebView) view.findViewById(R.id.item_show_long);
        WebSettings settings=mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        listComment.setFocusable(false);
        scenic_spot_detail_fragment_showall.setOnClickListener(this);
        initData();
    }

    private void initData() {
        Log.e(TAG,Myconstant.SENIC_PARMS_URL+SENIC_ID);
        OkHttpUtils.get().url(Myconstant.SENIC_PARMS_URL+SENIC_ID).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Log.e(TAG,response);
                Gson gson=new Gson();
                SenicSpotsParms parms=gson.fromJson(response,SenicSpotsParms.class);
                mRecommend=new ArrayList<SenicSpotsParms.DataEntity.RecommendSpotEntity>();
                mComment=new ArrayList<SenicSpotsParms.DataEntity.SenicCommentEntity>();
                mRecommend=parms.getData().getRecommend_spot();
                mComment=parms.getData().getSenic_comment();
                scenic_spot_detail_fragment_mustknow.setText("游览须知：" + Html.fromHtml(parms.getData().getSeason()));
                scenic_spot_detail_fragment_notice.setText("游览须知：" + Html.fromHtml(parms.getData().getNotice()));
                String html= FileUtil.getFromRaw(getActivity(),R.raw.currencyhtml);
                String data=html.replace("%@",parms.getData().getLong_description());

                   // FileUtil.writeSDFile(data);
                try {
                    FileUtil.writeSDFile(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                LogUtil.e(TAG, "file:/" + Myconstant.PATH + Myconstant.fileName);
               // mWebView.loadUrl("file:/" + Myconstant.PATH + Myconstant.fileName);
                mWebView.loadUrl("file:///mnt/sdcard/"+File.separator+"rawHtml"+File.separator+ Myconstant.fileName);
                listRecommend.setAdapter(new CommonAdapter<SenicSpotsParms.DataEntity.RecommendSpotEntity>(getActivity(), R.layout.recomment_spot_listview_item, mRecommend) {
                    @Override
                    protected void convert(ViewHolder viewHolder, SenicSpotsParms.DataEntity.RecommendSpotEntity item, int position) {
                        viewHolder.setImage(R.id.item_image, Myconstant.BASE_URL + item.getThumbnail())
                                .setText(R.id.item_name, item.getName())
                                .setText(R.id.item_position, "" + (position+1))
                                .setTextHtml(R.id.item_descripte, item.getLong_description());

                    }
                });
                listComment.setAdapter(new CommonAdapter<SenicSpotsParms.DataEntity.SenicCommentEntity>(getActivity(),R.layout.comment_listview_item,mComment) {
                    @Override
                    protected void convert(ViewHolder viewHolder, SenicSpotsParms.DataEntity.SenicCommentEntity item, int position) {
                        Log.e(TAG,Myconstant.BASE_URL+"//"+item.getAvatar());

                        viewHolder.setImagecricle(R.id.item_user_image, Myconstant.BASE_URL+"//"+item.getAvatar())
                                .setText(R.id.item_user_name,item.getUser_name())
                                .setText(R.id.item_user_love,item.getStars()+"人喜欢")
                                .setText(R.id.item_user_time,item.getDateline())
                                .setText(R.id.item_user_content, item.getComment_description());

                    }
                });

            }
        });
    }

    private void setData(String open, String traffic, String short_description,String telphone) {
        shortDescriptionTextview.setText(short_description);
        usefulInformationTextview.setText(open + "\n" + traffic + "\n" + "\n" + "联系方式："+telphone);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.scenic_spot_detail_fragment_showall:
                ScenicCommentMore.SENIC_ID=SENIC_ID;
                Intent intent=new Intent(getActivity(), ScenicCommentMore.class);
                startActivity(intent);
                break;
        }
    }
}
