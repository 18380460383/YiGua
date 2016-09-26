package com.qhzlwh.yigua.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.bean.LineTourOrSelfDrivingList;
import com.qhzlwh.yigua.util.Myconstant;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import abslistview.CommonAdapter;
import abslistview.ViewHolder;
import butterknife.ButterKnife;
import butterknife.Bind;

public class OrderMessage extends ListViewActivity {
    @Bind(R.id.top_back)
    ImageView topBack;
    @Bind(R.id.top_tilte_name)
    TextView topTilteName;
    @Bind(R.id.top_rigth_text)
    TextView topRigthText;
    @Bind(R.id.top)
    LinearLayout top;
    @Bind(R.id.actlist_lv)
    PullToRefreshListView actlistLv;
    private TextView emptyView;
    private String url="http://qh.91chuanbo.cn/Api/Api/SelectedList";
    private ArrayList<LineTourOrSelfDrivingList> mList;
    private CommonAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_message);
        ButterKnife.bind(this);
        emptyView = new TextView(this);
        emptyView.setGravity(Gravity.CENTER);
        emptyView.setVisibility(View.GONE);
        actlistLv.getRefreshableView().setEmptyView(emptyView);
        actlistLv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
        mList =new ArrayList<>();
        adapter=new CommonAdapter<LineTourOrSelfDrivingList>(OrderMessage.this, R.layout.customization_recylerview_item, mList) {

            @Override
            protected void convert(ViewHolder viewHolder, final LineTourOrSelfDrivingList item, int position) {
                viewHolder.setOnClickListener(R.id.item, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //ScenicSpotsDetailsActivity.SENIC_ID=item.getSenic_id();
                        Bundle bundle=new Bundle();
                        bundle.putString("SENIC_ID",item.getSenic_id());
                        Intent intent=new Intent(OrderMessage.this, ScenicSpotsDetailsActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                viewHolder.setImage(R.id.item_image, Myconstant.BASE_URL + item.getThumbnail())
                        .setText(R.id.item_title_name, item.getName());
            }
        };

        setmPullRefreshListView(actlistLv, adapter);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        getData();
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        super.onPullDownToRefresh(refreshView);

    }
    public void getData(){
        OkHttpUtils.get().url(url).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                try {
                    mList =new ArrayList<>();
                    JSONObject json=new JSONObject(response);
                    JSONArray jsonarr=json.getJSONArray("data");
                    for(int i=0;i<jsonarr.length();i++){
                        JSONObject jsonob1=jsonarr.getJSONObject(i);
                        LineTourOrSelfDrivingList bean=new LineTourOrSelfDrivingList();
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
                    adapter.notifyDataSetChanged();
                    onrequestDone();
                } catch (JSONException e) {
                    e.printStackTrace();
                    onrequestDone();
                }
            }
        });
    }

}
