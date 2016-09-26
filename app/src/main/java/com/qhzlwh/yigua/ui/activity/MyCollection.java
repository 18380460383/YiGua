package com.qhzlwh.yigua.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.ui.fragment.FragmentCollection;

import butterknife.ButterKnife;
import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我的收藏
 */
public class MyCollection extends BaseActivity {

    @Bind(R.id.top_back)
    ImageView topBack;
    @Bind(R.id.top_tilte_name)
    TextView topTilteName;
    @Bind(R.id.top_rigth_text)
    TextView topRigthText;
    @Bind(R.id.top)
    LinearLayout top;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.info_viewpager)
    ViewPager infoViewpager;



    private String[] titles=new String[]{"景点","线路","自驾游"};
    MyFragmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        topTilteName.setText("我的收藏");
        adapter=new MyFragmentAdapter(getSupportFragmentManager());
        adapter.setTitles(titles);


        infoViewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(infoViewpager);


        //设置Tablayout
        //设置TabLayout模式 -该使用Tab数量比较多的情况
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        //设置自定义Tab--加入图标的demo
        for(int i=0;i<3;i++){
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(adapter.getTabView(i));
        }
    }

    @OnClick(R.id.top_back)
    public void onClick() {
        finish();
    }
    public class MyFragmentAdapter extends FragmentStatePagerAdapter{
        private String[] titles;
        private LayoutInflater mInflater;

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            FragmentCollection collection=new FragmentCollection();
            Bundle bundle=null;
           switch (position){
               case 0:
                   bundle=new Bundle();
                   bundle.putString("type","1");
                   break;
               case 1:
                    bundle=new Bundle();
                   bundle.putString("type","2");
                   break;
               case 2:
                    bundle=new Bundle();
                   bundle.putString("type","3");
                   break;
           }
            collection.setArguments(bundle);
            return collection;
        }

        @Override
        public int getCount() {
            return 3;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment=null;
            try {
                fragment=(Fragment)super.instantiateItem(container,position);
            }catch (Exception e){

            }
            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }
  /*      //此方法用来显示tab上的名字
        @Override
        public CharSequence getPageTitle(int position) {

            return titles[position % titles.length];
        }*/


        /**
         * 添加getTabView的方法，来进行自定义Tab的布局View
         * @param position
         * @return
         */
        public View getTabView(int position){
            mInflater=LayoutInflater.from(MyCollection.this);
            View view=mInflater.inflate(R.layout.tab_item_layout,null);
            TextView tv= (TextView) view.findViewById(R.id.textView);
            tv.setText(titles[position]);
            ImageView img = (ImageView) view.findViewById(R.id.imageView);
            if(position==0){
                Glide.with(getApplicationContext()).load(R.mipmap.senic).into(img);
            }else if(position==1){
                Glide.with(getApplicationContext()).load(R.mipmap.line).into(img);
            }else{
                Glide.with(getApplicationContext()).load(R.mipmap.actioncolor).into(img);
            }
            return view;
        }
        public void setTitles(String[] titles) {
            this.titles = titles;
        }

    }

}
