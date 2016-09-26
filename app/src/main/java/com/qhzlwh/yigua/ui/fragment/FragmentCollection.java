package com.qhzlwh.yigua.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.application.MyApplication;
import com.qhzlwh.yigua.bean.SenicCollectionBean;
import com.qhzlwh.yigua.ui.activity.ScenicSpotsDetailsActivity;
import com.qhzlwh.yigua.ui.activity.TourSelf;
import com.qhzlwh.yigua.util.LogUtil;
import com.qhzlwh.yigua.util.Myconstant;
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
public class FragmentCollection extends Fragment {
    public static int TYPE=0;
    private String type="";
   // @BindView(R.id.fragment_listview)
    ListView fragmentListview;
    private List<SenicCollectionBean.DataEntity>mList;
    private FragmentCollectionDoRusult doresult;
    public static String TAG="FragmentCollection";
    public FragmentCollection() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_collection, container, false);

       // ButterKnife.bind(this, view);
        fragmentListview= (ListView) view.findViewById(R.id.fragment_listview);
        initView(view);
        return view;
    }

    private void initView(final View view) {
        final Bundle bundle=getArguments();
        if(bundle!=null){
            type=bundle.getString("type");
            LogUtil.e(TAG, Myconstant.get_user_collect + MyApplication.USER_ID + "/type/" + type);
            OkHttpUtils.get().url(Myconstant.get_user_collect+ MyApplication.USER_ID+"/type/"+type).build().execute(new StringCallback() {
                @Override
                public void onError(Request request, Exception e) {

                }

                @Override
                public void onResponse(String response) {
                    SenicCollectionBean collection=new SenicCollectionBean();
                    mList=new ArrayList<SenicCollectionBean.DataEntity>();
                    Gson gson=new Gson();
                    collection = gson.fromJson(response, SenicCollectionBean.class);
                    mList=collection.getData();
                    // ((MyCollection)getActivity()).getcount(mList.size(),1);
                    if(type.equals("1")){
                        fragmentListview.setAdapter(new CommonAdapter<SenicCollectionBean.DataEntity>(getActivity(), R.layout.customization_recylerview_item, mList) {
                            @Override
                            protected void convert(ViewHolder viewHolder, final SenicCollectionBean.DataEntity item, int position) {
                                viewHolder.setOnClickListener(R.id.item, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                       // ScenicSpotsDetailsActivity.SENIC_ID=item.getSenic_id();
                                        Bundle bundle1=new Bundle();
                                        bundle1.putString("SENIC_ID",item.getSenic_id());
                                        Intent intent=new Intent(getActivity(), ScenicSpotsDetailsActivity.class);
                                        intent.putExtras(bundle1);
                                        startActivity(intent);
                                    }
                                });
                                viewHolder.setImage(R.id.item_image, Myconstant.BASE_URL + item.getThumbnail())
                                        .setText(R.id.item_title_name, item.getName());
                            }

                        });
                    }else {//线路游
                        fragmentListview.setAdapter(new CommonAdapter<SenicCollectionBean.DataEntity>(getActivity(),R.layout.tranvel_self_recylerview_item,mList) {
                            @Override
                            protected void convert(ViewHolder viewHolder, final SenicCollectionBean.DataEntity item, int position) {
                                viewHolder.setOnClickListener(R.id.item, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                  /*  Bundle mBundle=new Bundle();
                                    mBundle.putString(TourSelf.SENIC_ID, lineTourOrSelfDrivingList.getSenic_id());
                                    //线路游
                                    mBundle.putString(TourSelf.TYPE,"21");*/
                                        TourSelf.SENIC_ID = item.getSenic_id();
                                        TourSelf.TYPE = "21";
                                        Intent intent = new Intent(getActivity(), TourSelf.class);
                                        //  intent.putExtras(mBundle);
                                        startActivity(intent);
                                        //startActivity(new Intent(Linetour.this, TourSelf.class));
                                    }
                                });
                                viewHolder.setImage(R.id.item_image, Myconstant.BASE_URL + mList.get(position).getThumbnail())
                                        .setText(R.id.item_short_title, item.getShort_title())
                                        .setText(R.id.item_long_title, item.getLong_title())
                                        .setText(R.id.item_location, item.getName())
                                        .setText(R.id.item_introduce, item.getShort_title())
                                        .setText(R.id.item_price, "￥"+item.getPrice())
                                        .setText(R.id.item_leader, "玩转" + item.getName())
                                ;
                                TextView price=viewHolder.getView(R.id.item_price);
                                if(type.equals("3")){
                                    price.setVisibility(View.GONE);
                                }
                            }
                        });

                    }

                }
            });
        }else{

        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    public interface FragmentCollectionDoRusult{
        void doCount(int count,int pos);
    }
}
