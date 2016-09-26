package com.qhzlwh.yigua.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.bean.LineOrTour;
import com.qhzlwh.yigua.ui.activity.TourSelf;
import com.qhzlwh.yigua.util.LogUtil;
import com.qhzlwh.yigua.util.Myconstant;
import com.qhzlwh.yigua.util.ToastUtil;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;


public class FragmentTranvel extends ListViewFragment {


    PullToRefreshListView tranvelRecyclerview;
    private String url = "http://qh.91chuanbo.cn/Api/Api/LineTourOrSelfDrivingList/attribute_id/22/page/1/number/10";
    private List<LineOrTour.DataBean> mList;
    TextView emptyView;
    private abslistview.CommonAdapter<LineOrTour.DataBean> adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_tranvel, container, false);
        //ButterKnife.bind(this, view);
        tranvelRecyclerview= (PullToRefreshListView) view.findViewById(R.id.tranvel_recyclerview);
        initView();
        return view;
    }

    private void initView() {
        mList=new ArrayList<>();
        adapter=new abslistview.CommonAdapter<LineOrTour.DataBean>(getActivity(), R.layout.tranvel_self_recylerview_item, mList) {
            @Override
            protected void convert(abslistview.ViewHolder holder, final LineOrTour.DataBean item, int position) {
                holder.setOnClickListener(R.id.item, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TourSelf.SENIC_ID = item.getSenic_id();
                        TourSelf.TYPE = "22";
                        Log.e("Tranvel", item.getSenic_id());
                        Intent intent = new Intent(getActivity(), TourSelf.class);
                        startActivity(intent);
                    }
                });
                holder.setImage(R.id.item_image, Myconstant.BASE_URL +item.getThumbnail())
                        .setText(R.id.item_short_title, item.getShort_title())
                        .setText(R.id.item_long_title, item.getLong_title())
                        .setText(R.id.item_location, item.getName())
                        .setText(R.id.item_leader, item.getTour_name())
                        .setText(R.id.item_price, item.getPrice())
                ;
            }
        };
        setmPullRefreshListView(tranvelRecyclerview, adapter);
        emptyView = new TextView(getActivity());
        emptyView.setGravity(Gravity.CENTER);
        emptyView.setVisibility(View.GONE);
        tranvelRecyclerview.getRefreshableView().setEmptyView(emptyView);
        tranvelRecyclerview.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        setADD();
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
    private void initData() {
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                ToastUtil.showShortToast(getActivity(), "网络加载错误");
                onrequestDone();
            }

            @Override
            public void onResponse(String response) {
              /*  try {

                    JSONObject json = new JSONObject(response);
                    JSONArray jsonarr = json.getJSONArray("data");
                    for (int i = 0; i < jsonarr.length(); i++) {
                        JSONObject jsonob1 = jsonarr.getJSONObject(i);
                        LineTourOrSelfDrivingList bean = new LineTourOrSelfDrivingList();
                        bean.setSenic_id(jsonob1.getString("senic_id"));
                        bean.setName(jsonob1.getString("name"));
                        bean.setShort_title(jsonob1.getString("short_title"));
                        bean.setThumbnail(jsonob1.getString("thumbnail"));
                        bean.setLong_title(jsonob1.getString("long_title"));
                        bean.setPrice(jsonob1.getString("price"));
                        bean.setTour_name(jsonob1.getString("tour_name"));
                        bean.setAttribute_id(jsonob1.getString("attribute_id"));
                        bean.setTemplete(jsonob1.getInt("templete"));
                        mList.add(bean);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
                Gson gson = new Gson();
                LineOrTour order = gson.fromJson(response, LineOrTour.class);
                LogUtil.e("tag",response);
                if(order.getRetcode()!=2000){
                    emptyView.setText("没有订单信息");
                    emptyView.setVisibility(View.VISIBLE);
                }else{
                mList.clear();
                mList.addAll(order.getData());
                }
                onrequestDone();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //ButterKnife.reset(this);
    }
}
