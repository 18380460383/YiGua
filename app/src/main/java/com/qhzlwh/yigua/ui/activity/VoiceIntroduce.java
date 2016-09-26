package com.qhzlwh.yigua.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.util.ToastUtil;
import com.qhzlwh.yigua.view.Player;

import butterknife.ButterKnife;
import butterknife.Bind;
import butterknife.OnClick;

/**
 * 语音介绍
 */
public class VoiceIntroduce extends BaseActivity {

    @Bind(R.id.top_bg)
    ImageView topBg;
    @Bind(R.id.top_back)
    ImageView topBack;
    @Bind(R.id.top_title_name)
    TextView topTitleName;
    @Bind(R.id.item_voice_play)
    ImageView itemVoicePlay;
    @Bind(R.id.item_voice_name)
    TextView itemVoiceName;
    @Bind(R.id.item_voice_time)
    TextView itemVoiceTime;
    @Bind(R.id.voice_item_bg)
    RelativeLayout voiceItemBg;
    boolean isplay = false;
    @Bind(R.id.item_voice_progress)
    SeekBar itemVoiceProgress;
    private Player player;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImmerseLayout();
        setContentView(R.layout.activity_voice_introduce);
        ButterKnife.bind(this);

        player = new Player(itemVoiceProgress);
        initData();
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            itemVoiceName.setText(bundle.getString("name"));
            url = bundle.getString("url");
            topTitleName.setText(bundle.getString("senic_name"));
            itemVoiceTime.setText(bundle.getString("time"));
            Glide.with(this).load(bundle.get("image")).into(topBg);
        }
    }

    @OnClick({R.id.top_back, R.id.item_voice_play})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.top_back:
                player.stop();
                finish();
                break;
            case R.id.item_voice_play:
                if(!isplay){
                    Glide.with(VoiceIntroduce.this).load(R.drawable.play).into(itemVoicePlay);
                    if(url.equals("")){
                        ToastUtil.showShortToast(VoiceIntroduce.this, "无音乐数据");
                    }else{
                        new Thread(new Runnable() {

                            @Override
                            public void run() {
                                player.playUrl(url);
                            }
                        }).start();
                    }
                }else{
                    Glide.with(VoiceIntroduce.this).load(R.drawable.pause).into(itemVoicePlay);
                    player.pause();
                }
                isplay=!isplay;
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.stop();
    }

}
