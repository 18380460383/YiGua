package com.qhzlwh.yigua.ui.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hxkj.alertviewlibrary.AlertView.AlertDialog;
import com.meiqia.core.callback.OnInitCallback;
import com.meiqia.meiqiasdk.util.MQConfig;
import com.meiqia.meiqiasdk.util.MQIntentBuilder;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.application.MyApplication;
import com.qhzlwh.yigua.ui.activity.LoginFast;
import com.qhzlwh.yigua.ui.activity.MyCollection;
import com.qhzlwh.yigua.ui.activity.MyComment;
import com.qhzlwh.yigua.ui.activity.OrderAll;
import com.qhzlwh.yigua.ui.activity.OrderNoComment;
import com.qhzlwh.yigua.ui.activity.OrderWaitOut;
import com.qhzlwh.yigua.ui.activity.OrderWaitPay;
import com.qhzlwh.yigua.ui.activity.UserInformation;
import com.qhzlwh.yigua.util.LogUtil;
import com.qhzlwh.yigua.util.Myconstant;
import com.qhzlwh.yigua.util.ToastUtil;
import com.qhzlwh.yigua.view.CircularImage;

import java.util.HashMap;

public class FragmentMe extends Fragment implements View.OnClickListener {

    ImageView imageFragmentMeSetting;
    CircularImage imageFragmentMeHead;
    TextView txtFragmentMeGrade;
    TextView txtFragmentMeName;
    ImageView imageView1;
    TextView txtFragmentComment;
    RelativeLayout orderAll;
    ImageView imageView3;
    TextView txtFragmentTravel;
    RelativeLayout orderWaitPay;
    ImageView imageView2;
    TextView txtFragmentYue;
    RelativeLayout orderWaitOut;
    ImageView imageView4;
    TextView txtFragmentShou;
    RelativeLayout orderWaitCharge;
    LinearLayout group1;
    LinearLayout group2;
    LinearLayout group3;
    LinearLayout group5;
    LinearLayout group4;
    LinearLayout group7;
    LinearLayout group6;
    private String name;
    private String avatar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_me, container, false);
       // ButterKnife.bind(this, view);
        imageFragmentMeSetting= (ImageView) view.findViewById(R.id.image_fragment_me_setting);
        imageFragmentMeHead= (CircularImage) view.findViewById(R.id.image_fragment_me_head);
        txtFragmentMeGrade= (TextView) view.findViewById(R.id.txt_fragment_me_grade);
        txtFragmentMeName= (TextView) view.findViewById(R.id.txt_fragment_me_name);
        imageView1= (ImageView) view.findViewById(R.id.imageView1);
        txtFragmentComment= (TextView) view.findViewById(R.id.txt_fragment_comment);
        orderAll= (RelativeLayout) view.findViewById(R.id.order_all);
        imageView3= (ImageView) view.findViewById(R.id.imageView3);
        txtFragmentTravel= (TextView) view.findViewById(R.id.txt_fragment_travel);
        orderWaitPay= (RelativeLayout) view.findViewById(R.id.order_wait_pay);
        imageView2= (ImageView) view.findViewById(R.id.imageView2);
        txtFragmentYue= (TextView) view.findViewById(R.id.txt_fragment_yue);
        orderWaitOut= (RelativeLayout) view.findViewById(R.id.order_wait_out);
        imageView4= (ImageView) view.findViewById(R.id.imageView4);
        txtFragmentShou= (TextView) view.findViewById(R.id.txt_fragment_shou);
        orderWaitCharge= (RelativeLayout) view.findViewById(R.id.order_wait_charge);
        group1= (LinearLayout) view.findViewById(R.id.group1);
        group2= (LinearLayout) view.findViewById(R.id.group2);
        group3= (LinearLayout) view.findViewById(R.id.group3);
        group4= (LinearLayout) view.findViewById(R.id.group4);
        group5= (LinearLayout) view.findViewById(R.id.group5);
        group6= (LinearLayout) view.findViewById(R.id.group6);
        group7= (LinearLayout) view.findViewById(R.id.group7);
        group1.setOnClickListener(this);
        group2.setOnClickListener(this);
        group3.setOnClickListener(this);
        group4.setOnClickListener(this);
        group5.setOnClickListener(this);
        group6.setOnClickListener(this);
        group7.setOnClickListener(this);
        txtFragmentMeGrade.setOnClickListener(this);
        orderAll.setOnClickListener(this);
        orderWaitCharge.setOnClickListener(this);
        orderWaitOut.setOnClickListener(this);
        orderWaitPay.setOnClickListener(this);
        initView();
        return view;
    }

    private void initView() {




        SharedPreferences sp= MyApplication.getInstance().getSp();
        if(MyApplication.IS_LOGIN){
            LogUtil.e("me",sp.getString("user_name",""));
            avatar=sp.getString("avatar","");
            Glide.with(getActivity()).load(avatar).into(imageFragmentMeHead);
            name=sp.getString("user_name","");
            txtFragmentMeGrade.setText(name);
        }else{
            LogUtil.e("me","未登录");
            Glide.with(getActivity()).load(R.mipmap.default_head_pic).into(imageFragmentMeHead);
            txtFragmentMeGrade.setText("未登录");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
      //  ButterKnife.reset(this);
    }

    public void onClick(View view) {
        Intent intent=null;

        switch (view.getId()) {
            case R.id.txt_fragment_me_grade:
                if(!MyApplication.IS_LOGIN){
                    intent=new Intent(getActivity(),LoginFast.class);
                }
                break;
            case R.id.order_all:
                if(!MyApplication.IS_LOGIN){
                    LoginFast.TYPE=2;
                    intent=new Intent(getActivity(), LoginFast.class);
                }else{
                intent=new Intent(getActivity(), OrderAll.class);}
                break;
            case R.id.order_wait_pay:
                if(!MyApplication.IS_LOGIN){
                    LoginFast.TYPE=2;
                    intent=new Intent(getActivity(), LoginFast.class);
                }else{
                intent=new Intent(getActivity(), OrderWaitPay.class);}
                break;
            case R.id.order_wait_out:
                if(!MyApplication.IS_LOGIN){
                    LoginFast.TYPE=2;
                    intent=new Intent(getActivity(), LoginFast.class);
                }else{
                intent=new Intent(getActivity(), OrderWaitOut.class);}
                break;
            case R.id.order_wait_charge:
                if(!MyApplication.IS_LOGIN){
                    LoginFast.TYPE=2;
                    intent=new Intent(getActivity(), LoginFast.class);
                }else{
                intent=new Intent(getActivity(), OrderNoComment.class);}
                break;
            case R.id.group1:
                if(!MyApplication.IS_LOGIN){
                    LoginFast.TYPE=2;
                    intent=new Intent(getActivity(), LoginFast.class);
                }else{
                intent=new Intent(getActivity(), MyComment.class);}
                break;
            case R.id.group2:
                if(!MyApplication.IS_LOGIN){
                    LoginFast.TYPE=2;
                    intent=new Intent(getActivity(), LoginFast.class);
                }else{
               intent=new Intent(getActivity(), UserInformation.class);}
               //ToastUtil.showShortToast(getActivity(),"该功能开发中");
                break;
            case R.id.group3:
                if(!MyApplication.IS_LOGIN){
                    LoginFast.TYPE=3;
                    intent=new Intent(getActivity(), LoginFast.class);
                }else{
               // if(MyApplication.IS_LOGIN){
                    intent=new Intent(getActivity(), MyCollection.class);}
             /*   }else{
                    LoginFast.TYPE=3;
                    intent=new Intent(getActivity(), LoginFast.class);
                }*/
                break;
            case R.id.group5:
              /*  if(!MyApplication.IS_LOGIN){
                    LoginFast.TYPE=2;
                    intent=new Intent(getActivity(), LoginFast.class);
                }else{}*/
                ToastUtil.showShortToast(getActivity(),"该功能开发中");
                break;
            case R.id.group4:
              /*  if(!MyApplication.IS_LOGIN){
                    LoginFast.TYPE=2;
                    intent=new Intent(getActivity(), LoginFast.class);
                }else{}*/
                ToastUtil.showShortToast(getActivity(),"该功能开发中");
                break;
            case R.id.group7:
                if(!MyApplication.IS_LOGIN){
                    showAlterDialog("当前未登录！",1);
                }else{
               // intent=new Intent(getActivity(), CommonTourUser.class);
                showAlterDialog("确定退出当前账号？",2);
                }
                break;
            case R.id.group6:
             /*   if(!MyApplication.IS_LOGIN){
                    LoginFast.TYPE=2;
                    intent=new Intent(getActivity(), LoginFast.class);
                }else{}*/
                //ToastUtil.showShortToast(getActivity(),"该功能开发中");
                if(MyApplication.IS_LOGIN){
                    MQConfig.init(getActivity(), Myconstant.MEIQIA_KEY, new OnInitCallback() {
                        @Override
                        public void onSuccess(String s) {
                            HashMap<String, String> clientInfo = new HashMap<>();
                            //User_For_pe peUser1 = AppContext.getInstance().getPEUser();
                            clientInfo.put("name",name);
                            clientInfo.put("avatar",Myconstant.BASE_URL+avatar);
                            clientInfo.put("gender", "");
                            clientInfo.put("tel", MyApplication.PHONE);
                            clientInfo.put("comment",""+MyApplication.USER_ID);
                            Intent intent = new MQIntentBuilder(getActivity()).setClientInfo(clientInfo).build();
                            startActivity(intent);
                        }
                        @Override
                        public void onFailure(int i, String s) {
                            LogUtil.e("kefu",s);
                            ToastUtil.showShortToast(getActivity(),"打开客服失败");
                        }
                    });
                }else{
                    MQConfig.init(getActivity(), Myconstant.MEIQIA_KEY, new OnInitCallback() {
                        @Override
                        public void onSuccess(String s) {
                            HashMap<String, String> clientInfo = new HashMap<>();
                            //User_For_pe peUser1 = AppContext.getInstance().getPEUser();
                            clientInfo.put("name","");
                            clientInfo.put("avatar","");
                            clientInfo.put("gender", "");
                            clientInfo.put("tel", "");
                            clientInfo.put("comment","");
                            Intent intent = new MQIntentBuilder(getActivity()).setClientInfo(clientInfo).build();
                            startActivity(intent);
                        }
                        @Override
                        public void onFailure(int i, String s) {
                            LogUtil.e("kefu",s);
                                ToastUtil.showShortToast(getActivity(),"打开客服失败");
                        }
                    });
                }
                //intent=new Intent(getActivity(), ChoiceTikect.class);}
                break;
        }
        if(intent!=null){
            startActivity(intent);
        }

    }
    public void showAlterDialog(String message,int type){
        final AlertDialog dialog=new AlertDialog(getActivity());
        dialog.setMessage(message);
        if(type==1){
            dialog.setPositiveButton("确定", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyApplication.IS_LOGIN=false;
                    MyApplication.USER_ID=-1;
                    ToastUtil.showShortToast(getActivity(),"退出成功");
                    SharedPreferences.Editor edit=MyApplication.getInstance().getSp().edit();
                    edit.putBoolean("islogin",false);
                    edit.commit();
                    dialog.dismiss();
                    initView();
                }
            });
        }else{
            dialog.setPositiveButton("确定", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyApplication.IS_LOGIN=false;
                    MyApplication.USER_ID=-1;
                    ToastUtil.showShortToast(getActivity(),"退出成功");
                    SharedPreferences.Editor edit=MyApplication.getInstance().getSp().edit();
                    edit.putBoolean("islogin",false);
                    edit.commit();
                    dialog.dismiss();
                    initView();
                }
            });
            dialog.setNegativeButton("取消", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }
    }
}
