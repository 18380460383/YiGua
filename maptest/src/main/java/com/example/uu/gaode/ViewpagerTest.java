package com.example.uu.gaode;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.uu.gaode.viewpager.RotateYTransformer;
import com.example.uu.gaode.viewpager.ScaleInTransformer;

import java.util.ArrayList;
import java.util.List;

public class ViewpagerTest extends AppCompatActivity {
    ViewPager viewPager;
    private List<Integer>list;
    private int[] imageIDs;
    RecyclerView mRecylerview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_test);
        initView();
    }

    private void initView() {
        LinearLayout lin= (LinearLayout) findViewById(R.id.container);
        viewPager= (ViewPager) findViewById(R.id.viewpager);
        mRecylerview= (RecyclerView) findViewById(R.id.viewpagertest_recylerview);
        imageIDs = new int[] { R.drawable.beautiful_001,
                R.drawable.beautiful_002, R.drawable.beautiful_003,
                R.drawable.beautiful_004, R.drawable.beautiful_005,
                R.drawable.beautiful_006, R.drawable.beautiful_007,
                R.drawable.beautiful_008 };
        // 1.设置幕后item的缓存数目
        viewPager.setOffscreenPageLimit(3);
// 2.设置页与页之间的间距
        viewPager.setPageMargin(10);

// 3.将父类的touch事件分发至viewPgaer，否则只能滑动中间的一个view对象
        lin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewPager.dispatchTouchEvent(event);
            }
        });
        list=new ArrayList<>();
        list.add(R.drawable.icon_002_cover);
        list.add(R.drawable.icon_013_cover);
        list.add(R.drawable.icon_007_cover);
        list.add(R.drawable.icon_010_cover);
        list.add(R.drawable.icon_012_cover);
        list.add(R.drawable.icon_018_cover);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imageIDs.length;
            }
            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView image=new ImageView(ViewpagerTest.this);
                image.setBackgroundResource(imageIDs[position]);
                container.addView(image);
                return image;
            }
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public float getPageWidth(int position) {
                return 1f;
            }
        });
        viewPager.setPageTransformer(true, new
                RotateYTransformer(new ScaleInTransformer()));
        viewPager.setCurrentItem(1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecylerview.setLayoutManager(linearLayoutManager);
        mRecylerview.setAdapter(new MyRecAdapter());

    }
    class MyRecAdapter extends RecyclerView.Adapter<MyRecAdapter.MyViewHolder>{

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(ViewpagerTest.this).inflate(R.layout.recylerview_item,null));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.imageview.setImageResource(imageIDs[position]);
        }

        @Override
        public int getItemCount() {
            return imageIDs.length;
        }

        class MyViewHolder extends RecyclerView.ViewHolder{
            ImageView imageview;
            public MyViewHolder(View itemView) {
                super(itemView);
                imageview= (ImageView) itemView.findViewById(R.id.imageview);
            }
        }
    }
}
