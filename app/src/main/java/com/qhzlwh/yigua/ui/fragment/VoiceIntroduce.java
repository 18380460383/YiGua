package com.qhzlwh.yigua.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.bean.SenicVoiceBean;
import com.qhzlwh.yigua.util.LogUtil;
import com.qhzlwh.yigua.util.Myconstant;
import com.qhzlwh.yigua.util.ToastUtil;
import com.qhzlwh.yigua.view.ListViewNoSrcoll;
import com.qhzlwh.yigua.view.Player;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import abslistview.CommonAdapter;
import abslistview.ViewHolder;

/**
 * A simple {@link Fragment} subclass.
 * 景区下的语音介绍
 */
public class VoiceIntroduce extends Fragment implements View.OnClickListener {
    public static String VOICE_URL="/Uploads/Download/2016-02-01/56aee9d8167ad.mp3";
    public static String IMGGE_URL="";
    private ImageView mPalay;
    public static String SENIC_NAME="青海";
    public static String SENIC_ID="18";
    private TextView mName;
    private TextView mTime;
    private Player player;
    private SeekBar seekBar;
    private boolean isPlay=false;
    private ListViewNoSrcoll voice_listview;
    private List<SenicVoiceBean.DataEntity>mList;
    private RelativeLayout loading;
    private RelativeLayout voice_gide;
    public VoiceIntroduce() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_voice_introduce, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mPalay = (ImageView) view.findViewById(R.id.voice_play);
        mName = (TextView) view.findViewById(R.id.voice_name);
        mTime = (TextView) view.findViewById(R.id.voice_time);
        seekBar = (SeekBar) view.findViewById(R.id.music_progress);
        voice_listview =(ListViewNoSrcoll)view.findViewById(R.id.voice_listview);
        loading = (RelativeLayout) view.findViewById(R.id.loading);
        voice_gide= (RelativeLayout) view.findViewById(R.id.voice_gide);
        mPalay.setOnClickListener(this);
        player=new Player(seekBar,mTime);
        initData();
    }

    private void initData() {
        LogUtil.e("voice",Myconstant.senic_sound+SENIC_ID);
        OkHttpUtils.get().url(Myconstant.senic_sound+SENIC_ID).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
            }
            @Override
            public void onResponse(String response) {
                Gson gson=new Gson();
                SenicVoiceBean voice=gson.fromJson(response,SenicVoiceBean.class);
                mList=new ArrayList<SenicVoiceBean.DataEntity>();
                mList=voice.getData();
                if(mList.size()==0){
                    voice_gide.setVisibility(View.GONE);
                }else{
                mName.setText(mList.get(0).getName());
                mTime.setText(mList.get(0).getTime());
                VOICE_URL=mList.get(0).getSound();
                loading.setVisibility(View.GONE);
               voice_listview.setAdapter(new CommonAdapter<SenicVoiceBean.DataEntity>(getActivity(),R.layout.voice_listview_layout,mList) {
                   @Override
                   protected void convert(final ViewHolder viewHolder, final SenicVoiceBean.DataEntity item, int position) {
                        if(item.getIs_senic()!=1){
                            viewHolder.setText(R.id.item_voice_name,item.getName())
                                    .setText(R.id.item_voice_time,item.getTime());
                            viewHolder.getView(R.id.voice_item_bg).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent=new Intent(getActivity(), com.qhzlwh.yigua.ui.activity.VoiceIntroduce.class);
                                    Bundle bundle=new Bundle();
                                    bundle.putString("image",IMGGE_URL);
                                    bundle.putString("senic_name",SENIC_NAME);
                                    bundle.putString("name",item.getName());
                                    bundle.putString("url",item.getSound());
                                    bundle.putString("time",item.getTime());
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });
                        }else{
                            viewHolder.getView(R.id.voice_item_bg).setVisibility(View.GONE);
                        }

                   }
               });
            }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.voice_play:
                if(!isPlay){
                    Glide.with(getActivity()).load(R.drawable.pause).into(mPalay);
                    if(VOICE_URL.equals("")){
                        ToastUtil.showShortToast(getActivity(),"无音乐数据");
                    }else{
                        new Thread(new Runnable() {

                            @Override
                            public void run() {
                                player.playUrl(Myconstant.BASE_URL+VOICE_URL);
                            }
                        }).start();
                    }
                }else{
                    Glide.with(getActivity()).load(R.drawable.play).into(mPalay);
                    player.pause();
                }
                isPlay=!isPlay;

                break;
        }
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        player.stop();
    }
}
