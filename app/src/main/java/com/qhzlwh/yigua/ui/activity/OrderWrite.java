package com.qhzlwh.yigua.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.application.MyApplication;
import com.qhzlwh.yigua.bean.Peoples;
import com.qhzlwh.yigua.bean.SenicOrderMakeBean;
import com.qhzlwh.yigua.bean.SenicTikectBean;
import com.qhzlwh.yigua.util.DateUtil;
import com.qhzlwh.yigua.util.LogUtil;
import com.qhzlwh.yigua.util.Myconstant;
import com.qhzlwh.yigua.util.RegularUtil;
import com.qhzlwh.yigua.util.ToastUtil;
import com.qhzlwh.yigua.view.GridViewNoScoll;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import abslistview.CommonAdapter;
import abslistview.ViewHolder;

/**
 * 订单填写
 */
public class OrderWrite extends BaseActivity implements View.OnClickListener {
    private TextView mTextViewNext;
    private TextView mTextViewTitle;
    private ImageView imageView;


    private TextView order_write_tikecttype;
    private TextView price_count;
    private TextView up_left;
    private TextView up_right;
    private TextView down_left;
    private TextView down_right;
    private TextView mid_up;
    private TextView mid_down;
    private TextView price;
    private TextView price2;
    private TextView item_tikect_count;
    private RelativeLayout item_submit;
    private EditText item_name;
    private EditText item_number;
    private EditText item_phone;
    private int countAdult=1;
    private int countChild=0;
    public  float PRICE_ADULT=500;
    public  float PRICE_CHILD=200;
    public  float PRICE=0;
    public  String ORDER_TYPE="门票";
    public  String SENIC_NAME="景区名称";
    public  String SENIC_ADDRESS="景区地址";
    public  String TIKECT_TYPE="成人票";
    public  String SENIC_ID="18";
    public  String TIKECT_ID="159";
    private PopupWindow popupWindow;
    private List<SenicTikectBean.DataEntity.DayArrEntity>mList;
    private  String dayTikect;
    private  String priceTikect;
    public  String order_type="1";
    private String name;
    private String number;
    private String phone;
    private RadioButton order_tomorray;
    private RadioButton order_today;
    private RadioButton order_chioce;
    private int week;
    private TextView textViewcount;
    private int positionLoc=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_write);
        initView();
    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.top_back);
        mTextViewTitle = (TextView) findViewById(R.id.top_tilte_name);
        mTextViewNext = (TextView) findViewById(R.id.top_rigth_text);
        mTextViewTitle.setText("订单填写");
        imageView.setOnClickListener(this);
        order_write_tikecttype= (TextView) findViewById(R.id.order_write_tikecttype);
        price_count= (TextView) findViewById(R.id.price_count);
        up_left= (TextView) findViewById(R.id.item_up_left1);
        up_right= (TextView) findViewById(R.id.item_up_right1);
        down_left= (TextView) findViewById(R.id.item_up_left2);
        down_right= (TextView) findViewById(R.id.item_up_right2);
        mid_up= (TextView) findViewById(R.id.item_count1);
        mid_down= (TextView) findViewById(R.id.item_count2);
        item_tikect_count= (TextView) findViewById(R.id.item_tikect_count);
        item_submit= (RelativeLayout) findViewById(R.id.item_submit);
        price= (TextView) findViewById(R.id.order_price);
        price2= (TextView) findViewById(R.id.order_price2);
        item_name= (EditText) findViewById(R.id.item_name);
        item_number= (EditText) findViewById(R.id.item_number);
        item_phone= (EditText) findViewById(R.id.item_phone);
        order_chioce =(RadioButton)findViewById(R.id.ra_choice);
        order_today =(RadioButton)findViewById(R.id.ra_today);
        order_tomorray =(RadioButton)findViewById(R.id.ra_tomorrow);
        order_write_tikecttype.setText(TIKECT_TYPE);

        order_today.setOnClickListener(this);
        order_tomorray.setOnClickListener(this);
        order_chioce.setOnClickListener(this);
        up_left.setOnClickListener(this);
        up_right.setOnClickListener(this);
        down_left.setOnClickListener(this);
        down_right.setOnClickListener(this);
        item_submit.setOnClickListener(this);

   /*     bundle1.putString("ORDER_TYPE","门票");
        bundle1.putString("SENIC_ID",scenicSpotsDetailsBean.getData().get(0).getSenic_id());
        bundle1.putString("TIKECT_TYPE",item.getTicket_name());
        bundle1.putString("SENIC_NAME",scenicSpotsDetailsBean.getData().get(0).getName());
        bundle1.putString("SENIC_ADDRESS",scenicSpotsDetailsBean.getData().get(0).getAddress());
        bundle1.putString("PRICE",""+Float.valueOf(item.getTicket_yprice()));
        bundle1.putString("order_type","1");
        bundle1.putString("TIKECT_ID",item.getTicket_id());*/
        Bundle bundle=getIntent().getExtras();
        SENIC_ID=bundle.getString("SENIC_ID");
        TIKECT_TYPE=bundle.getString("TIKECT_TYPE");
        SENIC_NAME=bundle.getString("SENIC_NAME");
        SENIC_ADDRESS=bundle.getString("SENIC_ADDRESS");
        PRICE=bundle.getFloat("PRICE");
        order_type=bundle.getString("order_type");
        TIKECT_ID=bundle.getString("TIKECT_ID");
        mid_up.setText(""+countAdult);
        price.setText(""+PRICE);
        price_count.setText(""+PRICE);

        initData();
    }

    private void initData() {
        String url=Myconstant.getSenic_Tikect(SENIC_ID,TIKECT_ID,MyApplication.USER_ID+"");
        LogUtil.e("orderwrite",url);
        OkHttpUtils.get().url(Myconstant.getSenic_Tikect(SENIC_ID,TIKECT_ID,MyApplication.USER_ID+"")).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if(jsonObject.getInt("retcode")!=2000){
                        ToastUtil.showShortToast(OrderWrite.this,jsonObject.getString("msg"));
                    }else{
                        Gson gson=new Gson();
                        SenicTikectBean tikect=gson.fromJson(response,SenicTikectBean.class);
                        order_chioce.setText(tikect.getData().getDay_use());
                        mList=new ArrayList<SenicTikectBean.DataEntity.DayArrEntity>();
                        mList=tikect.getData().getDay_arr();
                        order_today.setText("今天："+ DateUtil.timesDate(""+mList.get(0).getDay_stamp()));
                        order_tomorray.setText("明天："+ DateUtil.timesDate(""+mList.get(1).getDay_stamp()));
                        week = DateUtil.getWeek(mList.get(0).getDay());
                        LogUtil.e("week",""+week);
                        for (int i = 0; i <week ; i++) {
                            SenicTikectBean.DataEntity.DayArrEntity day=new SenicTikectBean.DataEntity.DayArrEntity();
                            day.setDay("");
                            day.setPrice("");
                            mList.add(0,day);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }
    public void setColor(int pos){
        switch (pos){
            case 1:
                order_tomorray.setTextColor(getResources().getColor(R.color.colorPrimary));
                order_chioce.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case 2:
                order_today.setTextColor(getResources().getColor(R.color.colorPrimary));
                order_chioce.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
            case 3:
                order_today.setTextColor(getResources().getColor(R.color.colorPrimary));
                order_tomorray.setTextColor(getResources().getColor(R.color.colorPrimary));
                break;
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ra_today:
                setColor(1);
                dayTikect=""+mList.get(0).getDay_stamp();
                order_today.setTextColor(getResources().getColor(R.color.white));
                break;
            case R.id.ra_tomorrow:
                setColor(2);
                dayTikect=""+mList.get(1).getDay_stamp();
                order_tomorray.setTextColor(getResources().getColor(R.color.white));
                break;

            case R.id.top_back:
                finish();
                break;
            case R.id.item_up_left1:
                if(countAdult>=1){
                    countAdult--;
                }
                break;
            case R.id.item_up_right1:
                countAdult++;
                break;
            case R.id.item_up_left2:
                if(countChild>=1){
                    countChild--;
                }
                break;
            case R.id.item_up_right2:
                countChild++;
                break;
            case R.id.ra_choice:
                setColor(3);
                order_chioce.setTextColor(getResources().getColor(R.color.white));
              //  showPopupWindow(v);
                menuWindow = new timepicker(OrderWrite.this);
                menuWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
                //显示窗口
                menuWindow.showAtLocation(v, Gravity.CENTER, 0, 0); //设置layout在PopupWindow中显示的位置
                break;
            case R.id.item_submit:
                name = item_name.getText().toString();
                number = item_number.getText().toString();
                phone = item_phone.getText().toString();
                if(name.equals("")){
                    ToastUtil.showShortToast(OrderWrite.this,"姓名不能为空");
                }
                else if(number.isEmpty()){
                    ToastUtil.showShortToast(OrderWrite.this,"身份证不能为空");
                }
                else if(phone.equals("")){
                    ToastUtil.showShortToast(OrderWrite.this,"电话号码不能为空");
                }else if(!RegularUtil.isMobileNO(phone)){
                    ToastUtil.showShortToast(getApplicationContext(),"手机号码不合法");
                }else if(!RegularUtil.personIdValidation(number)){
                    ToastUtil.showShortToast(getApplicationContext(),"身份证不合法");
                }
                else{
                    AsyncHttpClient client=new AsyncHttpClient();
                    RequestParams params=new RequestParams();
                 /*   ticket_id	必须	门票ID
                    uid	必须	用户UID
                    number	必须	门票数量
                    coupon_id	必须	优惠券ID，如果没有使用优惠券，此字段传入0
                    telephone	必须	购买人联系电话
                    come_date	可选	游园时间，时间戳格式（除了活动票其他为必传）
                    order_type	必须	订单类型 1|景区 2|土特产 3|线路游 4|自驾游
                    peoples	必须	购票人员详情，是个二维数组，数组中的数组应包含名称（name）和身份证号（id_card）,请参考请求数据类型*/
                    Peoples people=new Peoples();
                    people.setId_card(number);
                    people.setName(name);
                    List<Map<String,String>>list=new ArrayList<>();
                    Map<String,String>map=new HashMap<>();
                    map.put("name", name);
                    map.put("id_card", number);
                    list.add(map);
                    params.add("ticket_id",TIKECT_ID);
                    params.add("uid",""+MyApplication.USER_ID);
                    params.add("number",""+countAdult);
                    params.add("coupon_id","0");
                    params.add("telephone", phone);
                    params.add("come_date",dayTikect);
                    params.add("order_type",order_type);
                    params.put("peoples",list);
                    client.post(OrderWrite.this, Myconstant.SENIC_TIKECT_MAKE, params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int i, Header[] headers, byte[] bytes) {
                            String data=new String(bytes);
                            Log.e("client",""+data);
                            try {
                                Gson gson=new Gson();
                                JSONObject jsonObject=new JSONObject(data);
                                if(jsonObject.getInt("retcode")!=2000){
                                    ToastUtil.showShortToast(OrderWrite.this,jsonObject.getString("msg"));
                                }
                                else{
                                    SenicOrderMakeBean order=gson.fromJson(data,SenicOrderMakeBean.class);
                                 /*OrderInformation.ORDER_TYPE=item.getType_name();
                                 OrderInformation.SENIC_WAY=item.getAdmission_way();*/
                          /*         OrderInformation.ORDER_TYPE=ORDER_TYPE;
                                    OrderInformation.STAUS_NAME="待付款";
                                    OrderInformation.SENIC_WAY="工作人员接待入内";
                                OrderInformation.SENIC_NAME=SENIC_NAME;
                                OrderInformation.SENIC_ADDRESS=SENIC_ADDRESS;
                                OrderInformation.TIKECT_TIME=dayTikect;
                                OrderInformation.ORDER_NUMBER=order.getData().getOrder_sn();
                                OrderInformation.TIKECT_PRICE=""+PRICE;
                                OrderInformation.TIKECT_PRICE_COUNT=""+order.getData().getPrice();
                                OrderInformation.ORDER_USER_IDCARD=number;
                                OrderInformation.ORDER_USER_NAME=name;
                                OrderInformation.ORDER_USER_PHONE=phone;
                                OrderInformation.ORDER_ID=""+order.getData().getOrder_id();
                                Intent intent=new Intent(OrderWrite.this,OrderInformation.class);
                                startActivity(intent);
*/
                                    Bundle bundle = new Bundle();
                                    bundle.putString("订单id",""+order.getData().getOrder_id());
                                    bundle.putString("订单编号", order.getData().getOrder_sn());
                                    bundle.putString("景区名称", SENIC_NAME);
                                    bundle.putString("出发时间", dayTikect);
                                    bundle.putString("联系人", name);
                                    bundle.putString("单价", ""+PRICE);
                                    bundle.putString("联系电话", phone);
                                    bundle.putString("出游人数",""+countAdult);
                                    bundle.putString("订单金额", ""+order.getData().getPrice());

                                    if(ORDER_TYPE.equals("门票")){
                                        bundle.putString("订单类型", "1");
                                        OrderPay.order_type="1";
                                    }else if(ORDER_TYPE.equals("线路游")){
                                        bundle.putString("订单类型", "3");
                                        OrderPay.order_type="3";
                                    }else if(ORDER_TYPE.equals("自驾游")){
                                        bundle.putString("订单类型", "4");
                                        OrderPay.order_type="4";
                                    }
                                    Intent intent = new Intent(OrderWrite.this, OrderPay.class);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                            catch (Exception e){
                                //ToastUtil.showShortToast(OrderWrite.this,"创建订单错误");
                            }
                        }
                        //}
                        @Override
                        public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                            Log.e("client","错误");
                        }
                    });
                }


                break;
        }
        mid_up.setText(""+countAdult);
        mid_down.setText(""+countChild);
        if(countAdult>=1){
            price.setTextColor(getResources().getColor(R.color.yellow_dark));
        }else{
            price.setTextColor(getResources().getColor(R.color.gloomy));
        }
        if(countChild>=1){
            price2.setTextColor(getResources().getColor(R.color.yellow_dark));
        }else{
            price2.setTextColor(getResources().getColor(R.color.gloomy));
        }
        price.setText("￥"+countAdult*PRICE);
        price2.setText("￥" + countChild * PRICE_CHILD);
       // item_tikect_count.setText(countAdult+"成人 "+countChild+"儿童");
        item_tikect_count.setText(countAdult + " " + TIKECT_TYPE);
        float count=countAdult*PRICE;
        if(count>0){
            price_count.setVisibility(View.VISIBLE);
        }else{
            price_count.setVisibility(View.GONE);
        }
        price_count.setText("￥ " + count + " 元");
    }
    private void showPopupWindow(final View view) {

        // 一个自定义的布局，作为显示的内容
        View contentView = LayoutInflater.from(OrderWrite.this).inflate(
                R.layout.popu_getonetikect_date, null);
        contentView.getBackground().setAlpha(100);
        popupWindow = new PopupWindow(contentView,
               ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        GridViewNoScoll mGridView= (GridViewNoScoll) contentView.findViewById(R.id.popu_gridview);
        List<String>list=new ArrayList<>();
        for (int i = 0; i <31 ; i++) {
            list.add("price"+i);
        }
        mGridView.setAdapter(new CommonAdapter<String>(OrderWrite.this,R.layout.senic_getoneticket_gridview_item,list) {
            @Override
            protected void convert(ViewHolder viewHolder, String item, int position) {
                viewHolder.setText(R.id.item_price,item);
                final LinearLayout bg=viewHolder.getView(R.id.item_background);
                bg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bg.setBackgroundResource(R.drawable.button_retangle_solid);
                        popupWindow.dismiss();
                    }
                });
            }
        });
        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("mengdd", "onTouch : ");
                return false;
            }
        });

        popupWindow.setOutsideTouchable(true); //设置非PopupWindow区域是否可触摸
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
     /*   popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.selectmenu_bg_downward));*/
        // 设置好参数之后再show

        popupWindow.setFocusable(true);
       // popupWindow.showAsDropDown(view);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

    }
    //自定义的弹出框类
    timepicker menuWindow;
/*    @Override
    public void onBackPressed() {
        if(popupWindow.isShowing()){
            popupWindow.dismiss();
        }
    }*/

    /**
     * 创建者：peng
     * 时间：2016/9/2
     * 功能描述：弹出选择日期
     */
    public class timepicker extends PopupWindow {
        private View mMenuView;

        public timepicker(Activity context) {
            super(context);
            final LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mMenuView = inflater.inflate(R.layout.popu_getonetikect_date, null);

            //设置按钮监听
            //设置SelectPicPopupWindow的View
            this.setContentView(mMenuView);
            //设置SelectPicPopupWindow弹出窗体的宽
            this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            //设置SelectPicPopupWindow弹出窗体的高
            this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
            //设置SelectPicPopupWindow弹出窗体可点击
            this.setFocusable(true);
            //设置SelectPicPopupWindow弹出窗体动画效果
            //实例化一个ColorDrawable颜色为半透明
            ColorDrawable dw = new ColorDrawable(0xb0000000);
            //设置SelectPicPopupWindow弹出窗体的背景
            this.setBackgroundDrawable(dw);
            //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
            mMenuView.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    timepicker.this.dismiss();
                    return true;
                }
            });

            GridViewNoScoll mGridView = (GridViewNoScoll) mMenuView.findViewById(R.id.popu_gridview);
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 31; i++) {
                list.add("price" + i);
            }
            mGridView.setAdapter(new CommonAdapter<SenicTikectBean.DataEntity.DayArrEntity>(context, R.layout.senic_getoneticket_gridview_item, mList) {
                @Override
                protected void convert(ViewHolder viewHolder, final SenicTikectBean.DataEntity.DayArrEntity item, final int position) {
                    if(position<week){
                        viewHolder.setText(R.id.item_price, item.getPrice())
                                .setText(R.id.item_time,"");
                    }else{
                        viewHolder.setText(R.id.item_price, item.getPrice())
                                .setText(R.id.item_time, DateUtil.timesDay(item.getDay_stamp()+""));
                    }
                    final LinearLayout bg = viewHolder.getView(R.id.item_background);
                    bg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(position<week){
                            }else{
                            bg.setBackgroundResource(R.drawable.button_retangle_solid);
                            timepicker.this.dismiss();
                            PRICE=Float.valueOf(item.getPrice());
                            dayTikect=""+item.getDay_stamp();
                            order_chioce.setText(DateUtil.timesDate(""+item.getDay_stamp()));
                                positionLoc=position;
                            }
                        }
                    });
                    if(position!=-1&&position==positionLoc){
                        viewHolder.getView(R.id.item_background).setBackgroundResource(R.drawable.item_bg_colorpri);
                    }
                }
            });


        }
    }
}
