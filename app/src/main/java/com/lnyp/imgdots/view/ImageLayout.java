package com.lnyp.imgdots.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lnyp.imgdots.bean.PointSimple;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.ui.activity.HSencicPoint;
import com.qhzlwh.yigua.util.Myconstant;

import java.util.ArrayList;

public class ImageLayout extends FrameLayout implements View.OnClickListener {

    ArrayList<PointSimple> points;

    FrameLayout layouPoints;

    ImageView imgBg;

    Context mContext;
    private String URL;
    private String TAG="ImageLayout";

    public ImageLayout(Context context) {
        this(context, null);
    }

    public ImageLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImageLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context, attrs);
    }


    private void initView(Context context, AttributeSet attrs) {

        mContext = context;

        View imgPointLayout = inflate(context, R.layout.layout_imgview_point, this);

        imgBg = (ImageView) imgPointLayout.findViewById(R.id.imgBg);
        layouPoints = (FrameLayout) imgPointLayout.findViewById(R.id.layouPoints);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public void setImgBg(int width, int height, Integer imgUrl) {

        ViewGroup.LayoutParams lp = imgBg.getLayoutParams();
        lp.width = width;
        lp.height = height;

        imgBg.setLayoutParams(lp);

        ViewGroup.LayoutParams lp1 = layouPoints.getLayoutParams();
        lp1.width = width;
        lp1.height = height;

        layouPoints.setLayoutParams(lp1);

        Glide.with(mContext).load(imgUrl).asBitmap().into(imgBg);

        addPoints(width, height);

    }

    public void setPoints(ArrayList<PointSimple> points) {

        this.points = points;
    }

    private void addPoints(int width, int height) {

        layouPoints.removeAllViews();

        for (int i = 0; i < points.size(); i++) {

            double width_scale = points.get(i).width_scale;
            double height_scale = points.get(i).height_scale;


            LinearLayout view = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_img_point, this, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.imgPoint);
            TextView textview=(TextView)view.findViewById(R.id.textPoint);
            imageView.setTag(i);
            textview.setTag(i);
            view.setTag(points.get(i).showText);
            textview.setText(points.get(i).showText);
           /* AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
            animationDrawable.start();*/

            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();

            layoutParams.leftMargin = (int) (width * width_scale);
            layoutParams.topMargin = (int) (height * height_scale);

           // imageView.setOnClickListener(this);
            view.setOnClickListener(this);

            layouPoints.addView(view, layoutParams);

        }
    }


    @Override
    public void onClick(View view) {
        String pos = (String) view.getTag();
        String url="";
        if(pos.contains("海西")){
            url="2192";
        }
        if(pos.contains("海北")){
            url="2174";
        }
        if(pos.contains("海南")){
            url="2186";
        }
        if(pos.contains("海东")){
            url="2179";
        }
        if(pos.contains("黄南")){
            url="2198";
        }
        if(pos.contains("果洛")){
            url="2167";
        }
        if(pos.contains("玉树")){
            url="2203";
        }
        HSencicPoint.URL= getURL(url);
        HSencicPoint.NAME=pos;
        getContext().startActivity(new Intent(getContext(), HSencicPoint.class));
       //Toast.makeText(getContext(), "" + pos, Toast.LENGTH_SHORT).show();
    }

    public String getURL(String id) {
        Log.e(TAG, Myconstant.SENIC_URL+id);

        return Myconstant.SENIC_URL+id;
    }
}
