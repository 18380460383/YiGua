package com.qhzlwh.yigua.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.application.MyApplication;
import com.qhzlwh.yigua.bean.SenicTicketChoice;
import com.qhzlwh.yigua.util.DateUtil;
import com.qhzlwh.yigua.util.Myconstant;
import com.qhzlwh.yigua.util.ToastUtil;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import abslistview.CommonAdapter;
import abslistview.ViewHolder;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 选择使用的票型
 */
public class ChoiceTikect extends BaseActivity {

    @Bind(R.id.top_back)
    ImageView topBack;
    @Bind(R.id.top_tilte_name)
    TextView topTilteName;
    @Bind(R.id.top_rigth_text)
    TextView topRigthText;
    @Bind(R.id.chioce_tikect)
    ListView chioceTikect;
    private List<SenicTicketChoice.DataBean.TicketsBean>mList;
    private String senic_id;
    private String is_senic;
    private String qrcode_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_tikect);
        ButterKnife.bind(this);
        initView();

   /*     Bundle bundle=new Bundle();
        SenicTicketChoice.DataBean bean=choice.getData().get(0);
        bundle.putString("SENIC_NAME",bean.getName());
        bundle.putString("SENIC_ID",bean.getSenic_id());
        bundle.putString("TICKET_TIME",bean.getCome_date());
        bundle.putString("TICKET_COUNT",bean.getNumber());
        bundle.putString("TICKET_TYPE",bean.getTicket_type_name());*/
    }

    private void initData() {
        OkHttpUtils.post().url(Myconstant.ScanQRCode)
                .addParams("uid", ""+ MyApplication.USER_ID)
                .addParams("qrcode_data",""+ qrcode_data).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                ToastUtil.showShortToast(ChoiceTikect.this, "查询错误");
            }
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                final SenicTicketChoice choice = gson.fromJson(response, SenicTicketChoice.class);
                mList = new ArrayList<SenicTicketChoice.DataBean.TicketsBean>();
                mList=choice.getData().getTickets();
                chioceTikect.setAdapter(new CommonAdapter<SenicTicketChoice.DataBean.TicketsBean>(ChoiceTikect.this, R.layout.tikect_listview_item, mList) {
                    @Override
                    protected void convert(ViewHolder viewHolder, final SenicTicketChoice.DataBean.TicketsBean item, int position) {
                        if (item.getTicket_type_name().equals("折扣票")) {
                            viewHolder.getView(R.id.tikect_bg).setBackgroundResource(R.drawable.tikect_item_background_pink);
                        }
                        viewHolder.setText(R.id.tikect_type, item.getTicket_type_name())
                                .setText(R.id.tikect_name, item.getName())
                                .setText(R.id.tikect_count, "门票数量：" + item.getNumber())
                                .setText(R.id.tikect_work, "有效期：" + DateUtil.timesOne(item.getCome_date()));
                        viewHolder.getView(R.id.tikcet_use).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle bundle = new Bundle();
                                SenicTicketChoice.DataBean.TicketsBean bean = item;
                                bundle.putString("SENIC_NAME", bean.getName());
                                bundle.putString("SENIC_ID", bean.getSenic_id());
                                bundle.putString("TICKET_TIME", bean.getCome_date());
                                bundle.putString("TICKET_COUNT", bean.getNumber());
                                bundle.putString("TICKET_TYPE", bean.getTicket_type_name());
                                bundle.putString("ticket_type",""+ bean.getTicket_type());
                                bundle.putString("ticket_id",""+ bean.getTicket_id());
                                bundle.putString("is_senic",""+ bean.getIs_senic());
                               // bundle.putString("tikect",bean.toString());
                                Intent intent = new Intent(ChoiceTikect.this, TikectUseSure.class);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }
                        });
                    }
                });
            }
        });
    }

    private void initView() {
        topTilteName.setText("选择使用的门票类型");
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            senic_id = bundle.getString("senic_id");
            is_senic = bundle.getString("is_senic");
            qrcode_data = bundle.getString("qrcode_data");
            initData();
        }

    }

    @OnClick(R.id.top_back)
    public void onClick() {
        finish();
    }
}
