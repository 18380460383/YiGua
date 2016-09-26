package com.qhzlwh.yigua;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qhzlwh.yigua.contral.UpgradeControl;
import com.qhzlwh.yigua.ui.activity.BaseActivity;
import com.qhzlwh.yigua.ui.fragment.FragmentAction;
import com.qhzlwh.yigua.ui.fragment.FragmentHome;
import com.qhzlwh.yigua.ui.fragment.FragmentMe;
import com.qhzlwh.yigua.ui.fragment.FragmentTranvel;
import com.qhzlwh.yigua.view.PagerTab;
import com.qhzlwh.yigua.view.TourKitViewPager;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements PagerTab.TabDataSource {

    protected PagerTab tabs;
    protected TourKitViewPager pager;
    protected TourPagerAdapter adapter;

    protected FragmentHome fragment_home;
    protected FragmentAction fragment_travel;
    protected FragmentTranvel fragment_find;
    protected FragmentMe fragment_me;

    protected ArrayList<String> tab_titles;
    protected ArrayList<Integer> tab_icons;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   /*     getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);*/
        setContentView(R.layout.activity_main);
        //版本控制器  待完善
        UpgradeControl.getUpgradeControl(this).update();
        init();
        //版本升级
        //getUpgradeControl();
        /////
    }

    public void showMsg(String title, String msg1, String msg2) {
        String str = title;
        if (null !=msg1 && msg1.length() != 0) {
            str += "\n" + msg1;
        }
        if (null !=msg2 && msg2.length() != 0) {
            str += "\n" + msg2;
        }
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(MainActivity.this);
        builder.setMessage(str);
        builder.setTitle("提示");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                pDialog=new ProgressDialog(MainActivity.this);
                pDialog.setTitle("正在下载");
                pDialog.setMessage("请稍候...");
                pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
    private void init() {
        tab_titles = new ArrayList<String>();
        Resources recources = getResources();
        tab_titles.add(recources.getString(R.string.tab_text_home));
        tab_titles.add(recources.getString(R.string.tab_text_travel));
        tab_titles.add(recources.getString(R.string.tab_text_find));
        tab_titles.add(recources.getString(R.string.tab_text_me));

        tab_icons = new ArrayList<Integer>();
        tab_icons.add(R.drawable.btn_home_background);
        tab_icons.add(R.drawable.btn_travel_background);
        tab_icons.add(R.drawable.btn_activity_background);
        tab_icons.add(R.drawable.btn_me_background);

        tabs = (PagerTab) findViewById(R.id.tabs);
        tabs.setTextSize(12);

        pager = (TourKitViewPager) this.findViewById(R.id.pager);
        pager.setOffscreenPageLimit(2);
        adapter = new TourPagerAdapter(getSupportFragmentManager());
//
        pager.setAdapter(adapter);
        pager.setNoScroll(true);
        tabs.setViewPager(pager, this);
        ViewGroup vg = (ViewGroup) findViewById(R.id.main_bg);
       // MyUtil.setStatusBarTranslucent(vg, this);
        setImmerseLayout();
    }



    public class TourPagerAdapter extends FragmentPagerAdapter {
        public TourPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tab_titles.get(position);
        }

        @Override
        public int getCount() {
            return tab_titles.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentAtPosition(position);
        }
    }

    protected Fragment fragmentAtPosition(int position) {

        Fragment fragment = null;
        if (position == 0) {
            if (fragment_home == null)
                fragment_home = new FragmentHome();

            fragment = fragment_home;
        } else if (position == 1) {
            if (fragment_travel == null)
                fragment_travel = new FragmentAction();

            fragment = fragment_travel;
        } else if (position == 2) {
            if (fragment_find == null)
                fragment_find = new FragmentTranvel();
            fragment = fragment_find;

        } else if (position == 3) {
            if (fragment_me == null)
                fragment_me = new FragmentMe();
            fragment = fragment_me;
        }
        return fragment;
    }
    @Override
    public int getCount() {
        return tab_titles.size();
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position) {
        LayoutInflater inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.tabbar_item, null);
        if (view != null) {
            ImageView image = (ImageView) view
                    .findViewById(R.id.id_tabbar_icon);
            image.setImageResource(tab_icons.get(position));

            TextView text = (TextView) view.findViewById(R.id.id_tabbar_text);
            text.setText(tab_titles.get(position));
        }
        return view;
    }

  /*  @Override
    public void onBackPressed() {
        super.onBackPressed();
       // AppManager.getAppManager().finishAllActivity();
        System.exit(0);
    }*/
    class down extends AsyncTask<String,Integer,String>{

        @Override
        protected String doInBackground(String... params) {

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }
}
