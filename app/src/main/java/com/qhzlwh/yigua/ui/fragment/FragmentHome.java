package com.qhzlwh.yigua.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.karics.library.zxing.android.CaptureActivity;
import com.lnyp.imgdots.bean.ImgSimple;
import com.lnyp.imgdots.bean.PointSimple;
import com.lnyp.imgdots.view.ImageLayout;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.application.MyApplication;
import com.qhzlwh.yigua.bean.SenicHomeBannerBean;
import com.qhzlwh.yigua.ui.activity.CustomizationTour;
import com.qhzlwh.yigua.ui.activity.Linetour;
import com.qhzlwh.yigua.ui.activity.LoginFast;
import com.qhzlwh.yigua.ui.activity.ScenicSpots;
import com.qhzlwh.yigua.ui.activity.ScenicSpotsDetailsActivity;
import com.qhzlwh.yigua.util.DisplayUtil;
import com.qhzlwh.yigua.util.Myconstant;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private ImgSimple imgSimple1;
    private int width;
    private LinearLayout button1;
    private LinearLayout button2;
    private LinearLayout button3;

    private ViewPager mViewPager;
    private List<SenicHomeBannerBean.DataEntity>list;
    private int count=Integer.MAX_VALUE/2;


    private static final int REQUEST_CODE_SCAN = 0x0000;

    private static final String DECODED_CONTENT_KEY = "codedContent";
    private static final String DECODED_BITMAP_KEY = "codedBitmap";

    /**
     * 装点点的ImageView数组
     */
    private ImageView[] tips;
    private ViewGroup group;
    private ImageView scanle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_fragment_home, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        group = (ViewGroup)view.findViewById(R.id.viewpager_poite);
        ImageLayout layoutContent = (ImageLayout) view.findViewById(R.id.fragment_home_imagelayout);
        mViewPager= (ViewPager) view.findViewById(R.id.top_viewpagr_banner);
        scanle =(ImageView)view.findViewById(R.id.home_scanle);

        //设置监听，主要是设置点点的背景
        mViewPager.addOnPageChangeListener(this);
        initData();
        try {
            WindowManager wm = (WindowManager) getActivity()
                    .getSystemService(Context.WINDOW_SERVICE);

            width = wm.getDefaultDisplay().getWidth();

            Integer imgUrl = imgSimple1.url;
            float scale =imgSimple1.scale;
            ArrayList<PointSimple> pointSimples = imgSimple1.pointSimples;

            layoutContent.setPoints(pointSimples);

            int height = (int) (width * scale)+ DisplayUtil.dip2px(getActivity(),50);

            layoutContent.setImgBg(width, height, imgUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        button1 = (LinearLayout) view.findViewById(R.id.test1);
        button2 = (LinearLayout) view.findViewById(R.id.test2);
        button3 = (LinearLayout) view.findViewById(R.id.test3);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        scanle.setOnClickListener(this);
    }

    private void initData() {

        imgSimple1 = new ImgSimple();
        imgSimple1.url = R.drawable.homebg;
         imgSimple1.scale = 1.0f;

        ArrayList<PointSimple> pointSimples = new ArrayList<>();
        PointSimple pointSimple1 = new PointSimple();
        pointSimple1.width_scale = 0.4f;
        pointSimple1.height_scale = 0.33f;
        pointSimple1.showText="玉树藏族自治州";

        PointSimple pointSimple2 = new PointSimple();
        pointSimple2.width_scale = 0.4f;
        pointSimple2.height_scale = 0.13f;
        pointSimple2.showText="海西蒙古藏族自治州";

        PointSimple pointSimple3 = new PointSimple();
        pointSimple3.width_scale = 0.65;
        pointSimple3.height_scale = 0.29;
        pointSimple3.showText="果洛藏族自治州";

        PointSimple pointSimple4 = new PointSimple();
        pointSimple4.width_scale = 0.658f;
        pointSimple4.height_scale = 0.14f;
        pointSimple4.showText="海南藏族自治州";

        PointSimple pointSimple5 = new PointSimple();
        pointSimple5.width_scale = 0.67f;
        pointSimple5.height_scale = 0.06;
        pointSimple5.showText="海北藏族自治州";

        PointSimple pointSimple6 = new PointSimple();
        pointSimple6.width_scale = 0.8f;
        pointSimple6.height_scale = 0.23f;
        pointSimple6.showText="黄南藏族自治州";

        PointSimple pointSimple7 = new PointSimple();
        pointSimple7.width_scale = 0.8f;
        pointSimple7.height_scale = 0.09f;
        pointSimple7.showText="海西藏族自治州";

        pointSimples.add(pointSimple1);
        pointSimples.add(pointSimple2);
        pointSimples.add(pointSimple3);
        pointSimples.add(pointSimple4);
        pointSimples.add(pointSimple5);
        pointSimples.add(pointSimple6);
        pointSimples.add(pointSimple7);

        imgSimple1.pointSimples = pointSimples;
        OkHttpUtils.get().url(Myconstant.BANNER_URL).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                SenicHomeBannerBean banner=gson.fromJson(response,SenicHomeBannerBean.class);
                list=banner.getData();
                mViewPager.setAdapter(new MyViewPager());
                //将点点加入到ViewGroup中
                tips = new ImageView[list.size()];
                for(int i=0; i<tips.length; i++){
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setLayoutParams(new LayoutParams(10,10));
                    tips[i] = imageView;
                    if(i == 0){
                        tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
                    }else{
                        tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
                    }

                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,
                            LayoutParams.WRAP_CONTENT));
                    layoutParams.leftMargin = 5;
                    layoutParams.rightMargin = 5;
                    group.addView(imageView, layoutParams);
                }
               // mhander.sendEmptyMessageDelayed(1,1000);
                handler.sendEmptyMessageDelayed(msgWhat, 3000);
            }
        });

    }
    int msgWhat = 0;
    private Handler handler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);//收到消息，指向下一个页面
            handler.sendEmptyMessageDelayed(msgWhat, 3000);//2S后在发送一条消息，由于在handleMessage()方法中，造成死循环。
        }
    };

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeMessages(msgWhat);
    }

    @Override
    public void onStart() {
        super.onStart();
        handler.removeMessages(msgWhat);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int arg0) {
        setImageBackground(arg0 % list.size());
    }

    /**
     * 设置选中的tip的背景
     * @param selectItems
     */
    private void setImageBackground(int selectItems){
        for(int i=0; i<tips.length; i++){
            if(i == selectItems){
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            }else{
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
        }
    }

    private class MyViewPager extends PagerAdapter{
        public MyViewPager() {
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
           // LogUtil.e("home", "" + Myconstant.BASE_URL + list.get(position % list.size()).getPath());
            ImageView imageView = new ImageView(getActivity());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
             Glide.with(getActivity()).load(Myconstant.BASE_URL+list.get(position%list.size()).
                   getPath())
                   .placeholder(R.drawable.banner2)
                   .crossFade()
                   .into(imageView);
            //imageView.setImageResource(R.drawable.banner2);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // ScenicSpotsDetailsActivity.SENIC_ID=list.get(position % list.size()).getSenic_id();
                    Bundle bundle=new Bundle();
                    bundle.putString("SENIC_ID",list.get(position % list.size()).getSenic_id());
                    Intent intent=new Intent(getActivity(), ScenicSpotsDetailsActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    //ToastUtils.showToast(getActivity(),""+position);
                }
            });
            container.addView(imageView, 0);
            return imageView;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()){
            case R.id.test1:
                intent=new Intent(getActivity(), Linetour.class);
                startActivity(intent);
                break;
            case R.id.test2:
                /*Bundle mBundle=new Bundle();
                mBundle.putString(TourSelf.SENIC_ID, lineTourOrSelfDrivingList.getSenic_id());
                intent=new Intent(getActivity(), TourSelf.class);
                intent.putExtras(mBundle);
                startActivity(intent);*/
                intent=new Intent(getActivity(), ScenicSpots.class);
                startActivity(intent);
                break;
            case R.id.test3:
               //intent=new Intent(getActivity(), CommonTourUser.class);
              // intent=new Intent(getActivity(), OrderMessage.class);
                //  intent=new Intent(getActivity(), OrderWrite.class);
                if(MyApplication.IS_LOGIN){
                    intent=new Intent(getActivity(), CustomizationTour.class);
                    //intent=new Intent(getActivity(), Test.class);
                    startActivity(intent);
                }else{
                    intent=new Intent(getActivity(), LoginFast.class);
                    //intent=new Intent(getActivity(), Test.class);
                    startActivity(intent);
                }

                break;
            case R.id.home_scanle:
                if(MyApplication.IS_LOGIN){
                    //startActivityForResult(intent, REQUEST_CODE_SCAN);
                    intent=new Intent(getActivity(),CaptureActivity.class);
                }else{
                    intent=new Intent(getActivity(),LoginFast.class);
                }
                startActivity(intent);
                break;
        }
    }
/*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == -1) {
            if (data != null) {
                String content = data.getStringExtra(DECODED_CONTENT_KEY);
                Bitmap bitmap = data.getParcelableExtra(DECODED_BITMAP_KEY);
                //MyDialog.showMsgOnlySure(getActivity(), content,"","");
                //qrCoded.setText("解码结果： \n" + content);
                //qrCodeImage.setImageBitmap(bitmap);
                Random random=new Random();
                int ran=random.nextInt(2);
                Intent intent=null;
                if(ran==0){
                    intent=new Intent(getActivity(), ChoiceTikect.class);
                }else{
                    MyDialog.showMsgOnlySure(getActivity(), "确定使用门票\n"+content,"","");
                }
                if(intent!=null){
                    startActivity(intent);
                }
            }
        }
    }*/
}
