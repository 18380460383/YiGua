package com.qhzlwh.yigua.ui.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.bean.TourCommentBean;
import com.qhzlwh.yigua.util.Myconstant;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import abslistview.CommonAdapter;
import butterknife.ButterKnife;
import butterknife.Bind;

public class ScenicCommentMore extends ListViewActivity implements View.OnClickListener {
    @Bind(R.id.tranvel_recyclerview)
    PullToRefreshListView tranvelRecyclerview;
    private String url = "http://qh.91chuanbo.cn/Api/Api/SelectedList";
    private TextView mTextViewNext;
    private TextView mTextViewTitle;
    private ImageView imageView;
    public static String SENIC_ID = "18";
    private List<TourCommentBean.DataEntity>mList;
    private TextView emptyView;
    private CommonAdapter<TourCommentBean.DataEntity> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senic_comment_more);
        ButterKnife.bind(this);
        initView();
    }
    private void initView() {
        imageView = (ImageView) findViewById(R.id.top_back);
        mTextViewTitle = (TextView) findViewById(R.id.top_tilte_name);
        mTextViewNext = (TextView) findViewById(R.id.top_rigth_text);
        mTextViewTitle.setText("更多评论");
        imageView.setOnClickListener(this);
        mList=new ArrayList<>();
        adapter = new CommonAdapter<TourCommentBean.DataEntity>(ScenicCommentMore.this, R.layout.comment_listview_item,mList) {
            @Override
            protected void convert(abslistview.ViewHolder viewHolder, TourCommentBean.DataEntity item, int position) {
                viewHolder.setImagecricle(R.id.item_user_image, Myconstant.BASE_URL + "//" + item.getAvatar())
                        .setText(R.id.item_user_name, item.getUser_name())
                        .setText(R.id.item_user_love, item.getStars() + "人喜欢")
                        .setText(R.id.item_user_time, item.getDateline())
                        .setText(R.id.item_user_content, item.getComment_description());
            }
        };
        setmPullRefreshListView(tranvelRecyclerview, adapter);
        emptyView = new TextView(this);
        emptyView.setGravity(Gravity.CENTER);
        emptyView.setVisibility(View.GONE);
        tranvelRecyclerview.getRefreshableView().setEmptyView(emptyView);
        tranvelRecyclerview.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        setADD();
        //initData();
    }
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        initData();
    }
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        initData();
    }
    @Override
    public void setImageLoader(ImageLoader imageLoader) {
        super.setImageLoader(imageLoader);
    }
    @Override
    public void setPullToRefreshListView() {
        super.setPullToRefreshListView();
    }
    private void initData() {
        OkHttpUtils.get().url(Myconstant.SENIC_COMMENT_URL + SENIC_ID).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                onrequestDone();
            }
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                TourCommentBean commentBean = gson.fromJson(response, TourCommentBean.class);
                if(commentBean.getRetcode()!=2000){
                    emptyView.setText("无更多评论");
                    emptyView.setVisibility(View.VISIBLE);
                }else{
                    mList.clear();
                    mList.addAll(commentBean.getData());
                }
                onrequestDone();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_back:
                finish();
                break;
        }
    }
}
