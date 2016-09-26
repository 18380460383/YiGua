package com.qhzlwh.yigua.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.bean.SenicGoodBean;
import com.qhzlwh.yigua.util.Myconstant;
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
 * 景区下的酒店介绍
 */
public class HotelIntroduce extends Fragment {


    private ListViewNoSrcoll eatListView;
    private ListViewNoSrcoll goodListView;

   public static String SENCIC_ID="18";
    private List<SenicGoodBean.DataEntity.ProductEntity>productList;
    private List<SenicGoodBean.DataEntity.ShopEntity>shopList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_hotel_introduce, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        eatListView = (ListViewNoSrcoll) view.findViewById(R.id.hotel_listview_eat);
        goodListView = (ListViewNoSrcoll) view.findViewById(R.id.hotel_listview_good);
        initData();
    }

    private void initData() {
        OkHttpUtils.get().url(Myconstant.SENIC_GOOD_URL+SENCIC_ID).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                SenicGoodBean good=gson.fromJson(response,SenicGoodBean.class);
                productList=new ArrayList<SenicGoodBean.DataEntity.ProductEntity>();
                shopList=new ArrayList<SenicGoodBean.DataEntity.ShopEntity>();
                productList=good.getData().getProduct();
                shopList=good.getData().getShop();
                eatListView.setAdapter(new CommonAdapter<SenicGoodBean.DataEntity.ProductEntity>(getActivity(),R.layout.senic_good_listview_item,productList) {
                    @Override
                    protected void convert(ViewHolder viewHolder, SenicGoodBean.DataEntity.ProductEntity item, int position) {
                        viewHolder.setImage(R.id.good_listview_item_image,item.getThumbnail())
                                .setText(R.id.good_listview_item_name,item.getProduct_name())
                                .setTextHtml(R.id.good_listview_item_content,item.getLong_description());

                    }
                });
                goodListView.setAdapter(new CommonAdapter<SenicGoodBean.DataEntity.ShopEntity>(getActivity(),R.layout.senic_good_listview_item,shopList) {
                    @Override
                    protected void convert(ViewHolder viewHolder, SenicGoodBean.DataEntity.ShopEntity item, int position) {
                        viewHolder.setImage(R.id.good_listview_item_image,item.getThumbnail())
                                .setText(R.id.good_listview_item_name,item.getProduct_name())
                              .setTextHtml(R.id.good_listview_item_content,item.getLong_description())
                                .setText(R.id.good_item_price,"￥"+item.getPrice());
                        TextView price=viewHolder.getView(R.id.good_item_price);
                        price.setVisibility(View.VISIBLE);
                    }
                });

            }
        });
    }

}
