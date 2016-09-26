package com.qhzlwh.yigua.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.application.MyApplication;
import com.qhzlwh.yigua.bean.SenicCommentBean;
import com.qhzlwh.yigua.util.LogUtil;
import com.qhzlwh.yigua.util.Myconstant;
import com.qhzlwh.yigua.view.GridViewNoScoll;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import abslistview.CommonAdapter;
import abslistview.ViewHolder;
import butterknife.ButterKnife;
import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我的点评
 */
public class MyComment extends ListViewActivity {

    @Bind(R.id.top_back)
    ImageView topBack;
    @Bind(R.id.top_tilte_name)
    TextView topTilteName;
    @Bind(R.id.top_rigth_text)
    TextView topRigthText;
    @Bind(R.id.top)
    LinearLayout top;
    @Bind(R.id.mycomment_listview)
    com.handmark.pulltorefresh.library.PullToRefreshListView mycommentListview;

    private List<SenicCommentBean.DataEntity> mList;
    private String TAG = "MyComment";
    private int page = 1;
    TextView emptyView;
    private CommonAdapter<SenicCommentBean.DataEntity>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_comment);
        ButterKnife.bind(this);
        initView();

    }

    private void initData() {
        LogUtil.e(TAG, Myconstant.my_comment + MyApplication.USER_ID);
        OkHttpUtils.get().url(Myconstant.my_comment + MyApplication.USER_ID).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                onrequestDone();
            }

            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                SenicCommentBean commentBean = gson.fromJson(response, SenicCommentBean.class);
                if(commentBean.getRetcode()!=2000){
                    emptyView.setText("没有点评信息");
                    emptyView.setVisibility(View.VISIBLE);
                }
                mList.clear();
                mList.addAll(commentBean.getData());
                onrequestDone();
            }
        });
    }    private void initData(int page) {
        LogUtil.e(TAG, Myconstant.my_comment + MyApplication.USER_ID);
        OkHttpUtils.get().url(Myconstant.my_comment + MyApplication.USER_ID+"/page/"+page).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                onrequestDone();
            }

            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                SenicCommentBean commentBean = gson.fromJson(response, SenicCommentBean.class);
              /*  if(commentBean.getRetcode()!=2000){
                    emptyView.setText("没有点评信息");
                    emptyView.setVisibility(View.VISIBLE);
                }*/
                mList.addAll(commentBean.getData());
                onrequestDone();
            }
        });
    }

    private void initView() {
        topTilteName.setText("我的点评");
        mList=new ArrayList<>();
        adapter=new CommonAdapter<SenicCommentBean.DataEntity>(MyComment.this, R.layout.mycomment_senic, mList) {
            @Override
            protected void convert(ViewHolder viewHolder, final SenicCommentBean.DataEntity item, int position) {
                GridViewNoScoll gridViewNoScoll = viewHolder.getView(R.id.item_reviews_gridview);
                LinearLayout line = viewHolder.getView(R.id.item_line);
                if (item.getAttribute_id().equals("0")) {//景区评论
                    line.setVisibility(View.GONE);
                    viewHolder.setText(R.id.item_username, item.getDateline() + " 点评")
                            .setText(R.id.item_comment_content, item.getDescription())
                            .setText(R.id.item_comment_senic_name, item.getName())
                            .setText(R.id.item_comment_senic_price, "￥" + item.getPrice() + "/人起")
                            .setImage(R.id.item_comment_senic_image, Myconstant.BASE_URL + item.getThumbnail());
                    viewHolder.getView(R.id.item_comment_senic_item).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //ScenicSpotsDetailsActivity.SENIC_ID = item.getSenic_id();
                            Bundle bundle=new Bundle();
                            bundle.putString("SENIC_ID",item.getSenic_id());
                            Intent intent=new Intent(MyComment.this, ScenicSpotsDetailsActivity.class);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                    if (item.getImages() != null) {
                        gridViewNoScoll.setAdapter(new CommonAdapter<SenicCommentBean.DataEntity.ImagesEntity>(MyComment.this, R.layout.gridview_whit_image, item.getImages()) {
                            @Override
                            protected void convert(ViewHolder viewHolder, SenicCommentBean.DataEntity.ImagesEntity item, int position) {
                                viewHolder.setImage(R.id.gridview_images, Myconstant.BASE_URL + item.getSmall());
                            }
                        });
                    }

                } else {//线路或者自驾游评论
                    gridViewNoScoll.setVisibility(View.GONE);
                    int i = Integer.valueOf(item.getStars());
                    viewHolder
                            .setText(R.id.item_username, item.getDateline() + " 点评")
                            .setText(R.id.item_comment_content, item.getDescription())
                            .setText(R.id.item_reviews_transform, item.getTraffic_stars().getName())
                            .setColor(R.id.item_reviews_transform, item.getTraffic_stars().getStars_color())
                            .setText(R.id.item_reviews_eat, item.getHotel_stars().getName())
                            .setColor(R.id.item_reviews_eat, item.getHotel_stars().getStars_color())
                            .setText(R.id.item_reviews_tourgide, item.getLeader_stars().getName())
                            .setColor(R.id.item_reviews_tourgide, item.getLeader_stars().getStars_color())
                            //  .setImage(R.id.item_reviews_imageview, Myconstant.BASE_URL+item.getAvatar())
                            .setText(R.id.item_reviews_rating_charge, "" + i + "分")
                            .setImage(R.id.item_comment_senic_image, Myconstant.BASE_URL + item.getThumbnail())
                            .setText(R.id.item_comment_senic_name, item.getName())
                            .setText(R.id.item_comment_senic_price, "￥" + item.getPrice() + "/人起")
                    ;
                    RatingBar ratingBar = viewHolder.getView(R.id.item_rating_charge);
                    ratingBar.setRating(i);
                    viewHolder.getView(R.id.item_comment_senic_item).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TourSelf.SENIC_ID = item.getSenic_id();
                            TourSelf.TYPE = item.getAttribute_id();
                            Intent intent = new Intent(MyComment.this, TourSelf.class);
                            //  intent.putExtras(mBundle);
                            startActivity(intent);
                        }
                    });
                }
            }
        };
        setmPullRefreshListView(mycommentListview, adapter);
        emptyView = new TextView(this);
        emptyView.setGravity(Gravity.CENTER);
        emptyView.setVisibility(View.GONE);
        mycommentListview.getRefreshableView().setEmptyView(emptyView);
        mycommentListview.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        setADD();
    }
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        initData();
    }
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
    page++;
        initData(page);
    }
    @Override
    public void setImageLoader(ImageLoader imageLoader) {
        super.setImageLoader(imageLoader);
    }
    @OnClick(R.id.top_back)
    public void onClick() {
        finish();
    }
}
