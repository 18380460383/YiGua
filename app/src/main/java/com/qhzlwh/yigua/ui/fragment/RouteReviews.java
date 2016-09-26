package com.qhzlwh.yigua.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.bean.TourCommentBean;
import com.qhzlwh.yigua.util.LogUtil;
import com.qhzlwh.yigua.util.Myconstant;
import com.qhzlwh.yigua.view.GridViewNoScoll;
import com.qhzlwh.yigua.view.ListViewNoSrcoll;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import abslistview.CommonAdapter;
import abslistview.ViewHolder;

/**
 * 路线点评.
 */
public class RouteReviews extends Fragment {

    ListViewNoSrcoll routereviewsListview;
    private List<TourCommentBean.DataEntity> list;
    public static String SENIC_ID="123";
    private RelativeLayout mLoading;
    private TextView mshow;


    public RouteReviews() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_route_reviews, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView( View view) {
        routereviewsListview= (ListViewNoSrcoll) view.findViewById(R.id.id_stickynavlayout_innerscrollview);
        mLoading = (RelativeLayout) view.findViewById(R.id.loading);
        mshow = (TextView) view.findViewById(R.id.show_message);

    }

    private void initData() {
        Bundle bundle=getArguments();
        if(bundle!=null){
            SENIC_ID=bundle.getString(SENIC_ID);
        }

        list = new ArrayList<>();
        LogUtil.e("Route", "" + Myconstant.COMMENT_URL+SENIC_ID);
        OkHttpUtils.get().url(Myconstant.COMMENT_URL+SENIC_ID).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                TourCommentBean bean=gson.fromJson(response,TourCommentBean.class);
                list=bean.getData();
                LogUtil.e("Route", "" + list.size());
                routereviewsListview.setAdapter(new CommonAdapter<TourCommentBean.DataEntity>(getActivity(),R.layout.routereview_listview_item,list) {
                    @Override
                    protected void convert(ViewHolder viewHolder, TourCommentBean.DataEntity item, int position) {
                        int i=Integer.valueOf(item.getStars());

                        viewHolder.setText(R.id.item_username,item.getUser_name())
                                .setText(R.id.item_reviews_time,item.getDateline())
                                .setText(R.id.item_reviews_content,item.getComment_description())
                                .setText(R.id.item_reviews_transform, item.getTraffic_stars().getName())
                               .setColor(R.id.item_reviews_transform, item.getTraffic_stars().getStars_color())
                                .setText(R.id.item_reviews_eat, item.getHotel_stars().getName())
                                .setColor(R.id.item_reviews_eat,item.getHotel_stars().getStars_color())
                                .setText(R.id.item_reviews_tourgide, item.getLeader_stars().getName())
                                        .setColor(R.id.item_reviews_tourgide,item.getLeader_stars().getStars_color())
                                        //  .setImage(R.id.item_reviews_imageview, Myconstant.BASE_URL+item.getAvatar())
                                .setText(R.id.item_reviews_rating_charge, "" + i + "分")
                        ;
                      RatingBar ratingBar= viewHolder.getView(R.id.item_rating_charge);
                        ratingBar.setRating(i);
                        List<TourCommentBean.DataEntity.ImagesEntity>listImage=new ArrayList<TourCommentBean.DataEntity.ImagesEntity>();
                        listImage=item.getImages();
                        if(listImage!=null){
                            LogUtil.e("Route",""+list.size());
                            ((GridViewNoScoll)viewHolder.getView(R.id.item_reviews_gridview)).setAdapter(new CommonAdapter<TourCommentBean.DataEntity.ImagesEntity>(getActivity(),R.layout.comment_image_item,listImage) {
                                @Override
                                protected void convert(ViewHolder viewHolder, TourCommentBean.DataEntity.ImagesEntity item, int position) {
                                    viewHolder.setImage(R.id.item_image,Myconstant.BASE_URL+item.getSmall());
                                }
                            });
                        }
                    }
                });
                if(list.size()>0){
                    mLoading.setVisibility(View.GONE);
                }
                if(list.size()==0){
                    mshow.setText("无数据！");
                }
            }
        });

    }

}
