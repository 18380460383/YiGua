package com.qhzlwh.yigua.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.bean.TourInformationBean;
import com.qhzlwh.yigua.ui.activity.TourSelf;
import com.qhzlwh.yigua.view.ListViewNoSrcoll;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import abslistview.CommonAdapter;
import abslistview.ViewHolder;

/**
 * A simple {@link Fragment} subclass.
 */
public class TourselfInformation extends Fragment {


    private ListViewNoSrcoll mListView;
    private List<TourInformationBean.DataEntity> mList;
    public static String SENIC_ID="123";
    private String url="http://qh.91chuanbo.cn/Api/Senic/trip/senic_id/";

    private RelativeLayout mLoading;
    private TextView mshow;
    private RelativeLayout information_bg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_tourself_information, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mListView = (ListViewNoSrcoll) view.findViewById(R.id.id_stickynavlayout_innerscrollview);
        information_bg =(RelativeLayout)view.findViewById(R.id.information_bg);
        mListView.setFocusable(false);
        mLoading= (RelativeLayout) view.findViewById(R.id.loading);
        mshow = (TextView) view.findViewById(R.id.show_message);
        initData();
    }

    private void initData() {
        mList = new ArrayList<>();
        OkHttpUtils.get().url(url+SENIC_ID).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                TourInformationBean scenicSpotsDetailsBean = gson.fromJson(response, TourInformationBean.class);
                mList=scenicSpotsDetailsBean.getData();
                mListView.setAdapter(new CommonAdapter<TourInformationBean.DataEntity>(getActivity(),R.layout.tourself_information_item,mList) {

                    @Override
                    protected void convert(ViewHolder viewHolder, TourInformationBean.DataEntity item, int position) {
                        viewHolder.setText(R.id.item_days,item.getTrip_day())
                        .setText(R.id.item_location_time,item.getLocation_time())
                        .setText(R.id.location_point,item.getLocation_point())
                        .setText(R.id.item_starting_time,item.getStarting_time())
                        .setText(R.id.item_destination_time,item.getDestination_time())
                        .setText(R.id.item_destination_point,item.getDestination_point())
                        .setText(R.id.trip_description,item.getTrip_description())
                        .setText(R.id.hotel_name,item.getHotel_name());
                    }
                });
                if(mList.size()>0){
                    mLoading.setVisibility(View.GONE);
                    ((TourSelf)getActivity()).setVisiable();
                }
                if(mList.size()==0){
                    information_bg.setVisibility(View.GONE);
                    mshow.setText("无数据！");

                }
            }
        });
    }


}
