package com.qhzlwh.yigua.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.adapter.CityListAdapter;
import com.qhzlwh.yigua.adapter.ResultListAdapter;
import com.qhzlwh.yigua.db.DBManager;
import com.qhzlwh.yigua.model.City;
import com.qhzlwh.yigua.model.LocateState;
import com.qhzlwh.yigua.util.CharacterParser;
import com.qhzlwh.yigua.util.FileUtil;
import com.qhzlwh.yigua.util.LogUtil;
import com.qhzlwh.yigua.util.Myconstant;
import com.qhzlwh.yigua.util.StringUtils;
import com.qhzlwh.yigua.util.ToastUtil;
import com.qhzlwh.yigua.util.ToastUtils;
import com.qhzlwh.yigua.view.SideLetterBar;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * author zaaach on 2016/1/26.
 */
public class CityPickerActivity extends BaseActivity implements View.OnClickListener {
    public static final int REQUEST_CODE_PICK_CITY = 2333;
    public static final String KEY_PICKED_CITY = "picked_city";

    private ListView mListView;
    private ListView mResultListView;
    private SideLetterBar mLetterBar;
    private EditText searchBox;
    private ImageView clearBtn;
    private ImageView backBtn;
    private ViewGroup emptyView;

    private CityListAdapter mCityAdapter;
    private ResultListAdapter mResultAdapter;
    private List<City> mAllCities;
    private List<City> mHotCities;
    private DBManager dbManager;
    private TextView title;
    private String TAG="CityPickerActivity";
    private AMapLocationClient mLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        showProgressDialog("数据加载中。。。");
        mAllCities=new ArrayList<>();
        mHotCities=new ArrayList<>();

        initView();
        initLocation();
        initDataLoacl();
        initData();
    }

    private void initDataLoacl() {
        dismissProgressDialog();
       String data= FileUtil.read(CityPickerActivity.this,"citylist");
        if(!TextUtils.isEmpty(data)){
            try {
                JSONArray jsonArray=new JSONArray(data);
              //  JSONArray jsonArray=jsonObject.getJSONArray("data");
                for (int i = 0; i <jsonArray.length() ; i++) {
                    City city=new City();
                    JSONObject js=jsonArray.getJSONObject(i);
                    int isHot=js.getInt("ishot");
                    city.setId(js.getString("city_id"));
                    String cityName=js.getString("name");
                    city.setName(cityName);
                    String ping= CharacterParser.getSelling(cityName);
                    //LogUtil.e(TAG,"name"+cityName+"PinyinUtils:"+ping);
                    city.setPinyin(ping);
                    if(isHot==1){
                        mHotCities.add(city);
                    }
                    mAllCities.add(city);
                }
            /*    LogUtil.e(TAG,"hot:"+mHotCities.size());
                LogUtil.e(TAG,"all:"+mAllCities.size());*/
                Collections.sort(mAllCities, new CityComparator());
                Collections.sort(mHotCities, new Comparator<City>() {
                    @Override
                    public int compare(City lhs, City rhs) {
                        String a = lhs.getPinyin().substring(0, 1);
                        String b = rhs.getPinyin().substring(0, 1);
                        return a.compareTo(b);
                    }
                });
                mCityAdapter = new CityListAdapter(CityPickerActivity.this, mAllCities,mHotCities);
                mCityAdapter.setOnCityClickListener(new CityListAdapter.OnCityClickListener() {
                    @Override
                    public void onCityClick(String name, String id) {
                        back(name,id);
                    }
                    @Override
                    public void onLocateClick() {
                        Log.e("onLocateClick", "重新定位...");
                        mCityAdapter.updateLocateState(LocateState.LOCATING, null);
                        //mLocationClient.startLocation();
                    }
                });

                mListView.setAdapter(mCityAdapter);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        }



    private void initLocation() {
        mLocationClient = new AMapLocationClient(this);
        AMapLocationClientOption option = new AMapLocationClientOption();
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        option.setOnceLocation(true);
        mLocationClient.setLocationOption(option);
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation != null) {
                    if (aMapLocation.getErrorCode() == 0) {
                        String city = aMapLocation.getCity();
                        String district = aMapLocation.getDistrict();
                        LogUtil.e("Latitude:",""+aMapLocation.getLatitude());
                        LogUtil.e("Longitude:",""+aMapLocation.getLongitude());
                        Log.e("onLocationChanged", "city: " + city);
                        Log.e("onLocationChanged", "district: " + district);
                        String location = StringUtils.extractLocation(city, district);
                        mCityAdapter.updateLocateState(LocateState.SUCCESS, location);
                    } else {
                        //定位失败
                        mCityAdapter.updateLocateState(LocateState.FAILED, null);
                    }
                }
            }
        });
        mLocationClient.startLocation();
    }


    private void initData() {

        OkHttpUtils.get().url(Myconstant.getCityList).build().execute(new StringCallback() {
            @Override
            public void onError(Request request, Exception e) {
                ToastUtil.showShortToast(CityPickerActivity.this,"城市获取错误");
                dismissProgressDialog();
            }

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    int code=jsonObject.getInt("retcode");
                    String data=jsonObject.getString("data");
                    FileUtil.save(CityPickerActivity.this,"citylist",data);
                   // FileUtil.read(CityPickerActivity.this,"citylist");
                    if(code!=2000){
                        ToastUtil.showShortToast(CityPickerActivity.this,jsonObject.getString("msg"));
                    }else{
                        FileUtil.save(CityPickerActivity.this,"citylist",data);
                    }
                    //dismissProgressDialog();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    /*    dbManager = new DBManager(this);
        dbManager.copyDBFile();*/

    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listview_all_city);


        TextView overlay = (TextView) findViewById(R.id.tv_letter_overlay);
        mLetterBar = (SideLetterBar) findViewById(R.id.side_letter_bar);
        mLetterBar.setOverlay(overlay);
        mLetterBar.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                int position = mCityAdapter.getLetterPosition(letter);
                mListView.setSelection(position);
            }
        });

        searchBox = (EditText) findViewById(R.id.et_search);
        searchBox.clearFocus();
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String keyword = s.toString();
                LogUtil.e(TAG,keyword);
                if (TextUtils.isEmpty(keyword)) {
                    clearBtn.setVisibility(View.GONE);
                    emptyView.setVisibility(View.GONE);
                    mResultListView.setVisibility(View.GONE);
                } else {
                    clearBtn.setVisibility(View.VISIBLE);
                    mResultListView.setVisibility(View.VISIBLE);
                    List<City> result =new ArrayList<City>();
                    //result=getResultByPinYin(keyword);
                    if(isLetter(keyword.substring(0,1))){
                        result=getResultByPinYin(keyword);
                    }else{
                        result=getResultByName(keyword);
                    }
                    if (result == null || result.size() == 0) {
                        emptyView.setVisibility(View.VISIBLE);
                    } else {
                        emptyView.setVisibility(View.GONE);
                        //mResultAdapter.changeData(result);
                        mResultAdapter = new ResultListAdapter(CityPickerActivity.this, result);
                        mResultListView.setAdapter(mResultAdapter);
                        mResultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                back(mResultAdapter.getItem(position).getName(),mResultAdapter.getItem(position).getId());
                            }
                        });
                    }
                }
            }
        });

        emptyView = (ViewGroup) findViewById(R.id.empty_view);
        mResultListView = (ListView) findViewById(R.id.listview_search_result);


        clearBtn = (ImageView) findViewById(R.id.iv_search_clear);
        backBtn = (ImageView) findViewById(R.id.top_back);
        title= (TextView) findViewById(R.id.top_tilte_name);
        title.setText("选择城市");
        clearBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);
    }
    private  boolean isLetter(String str){

        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(str);
        if(m.matches() ){
            return false;
        }
        p=Pattern.compile("[a-zA-Z]");
        m=p.matcher(str);
        if(m.matches()){
        return true;
        }
        p=Pattern.compile("[\u4e00-\u9fa5]");
        m=p.matcher(str);
        if(m.matches()){
            return false;
        }
        return false;
    }
    public static boolean isHave(String strs,String s){
        for (int i = 0; i < strs.length()-1; i++) {
            for (int j = i+1; j < strs.length(); j++) {
                if(s.contains(strs.substring(i, j))){
                    return true;
                }
            }
        }
        return false;
    }
    private List<City> getResultByName(String cityName){
        List<City> result=new ArrayList<>();
        for (int i = 0; i <mAllCities.size() ; i++) {
            if(isHave(cityName,mAllCities.get(i).getName())){
                result.add(mAllCities.get(i));
            }
           /* if(mAllCities.get(i).getName().contains(cityName)){
                result.add(mAllCities.get(i));
            }*/
        }
        return result;
    }
    private List<City> getResultByPinYin(String cityName){
        List<City> result=new ArrayList<>();
        for (int i = 0; i <mAllCities.size() ; i++) {
           /* if(isHave(cityName,mAllCities.get(i).getPinyin())){
                result.add(mAllCities.get(i));
            }*/
            boolean ishave=false;
            LogUtil.e(TAG,mAllCities.get(i).getPinyin()+"   :"+cityName.length());
            for(int j=0;j<cityName.length();j++){
               // LogUtil.e(TAG,mAllCities.get(i).getPinyin()+"   :"+cityName.substring(j,j+1));
              if(mAllCities.get(i).getPinyin().contains(cityName.substring(j,j+1))){
                    ishave=true;
              }else{
                  ishave=false;
                  break;
              }
            }
            if(ishave){
                result.add(mAllCities.get(i));
            }
        }

        return result;
    }
    private void back(String city,String id){
        ToastUtils.showToast(this, "点击的城市：" + city);
//        Intent data = new Intent();
//        data.putExtra(KEY_PICKED_CITY, city);
//        setResult(RESULT_OK, data);
//        finish();
        //数据是使用Intent返回
        Intent intent = new Intent();
        Bundle bundle=new Bundle();
        bundle.putString("city",city);
        bundle.putString("id",id);
        //把返回数据存入Intent
        intent.putExtras(bundle);
        //设置返回数据
        CityPickerActivity.this.setResult(RESULT_OK, intent);
        //关闭Activity
        CityPickerActivity.this.finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_search_clear:
                searchBox.setText("");
                clearBtn.setVisibility(View.GONE);
                emptyView.setVisibility(View.GONE);
                mResultListView.setVisibility(View.GONE);
                break;
            case R.id.top_back:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // mLocationClient.stopLocation();
    }
    /**
     * a-z排序
     */
    private class CityComparator implements Comparator<City> {
        @Override
        public int compare(City lhs, City rhs) {
            String a = lhs.getPinyin().substring(0, 1);
            String b = rhs.getPinyin().substring(0, 1);
            return a.compareTo(b);
        }
    }
    public static String getSpells(String characters) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < characters.length(); i++) {

            char ch = characters.charAt(i);
            if ((ch >> 7) == 0) {
                // 判断是否为汉字，如果左移7为为0就不是汉字，否则是汉字
            } else {
                char spell = getFirstLetter(ch);
                buffer.append(String.valueOf(spell));
            }
        }
        return buffer.toString();
    }

    // 获取一个汉字的首字母
    public static Character getFirstLetter(char ch) {

        byte[] uniCode = null;
        try {
            uniCode = String.valueOf(ch).getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        if (uniCode[0] < 128 && uniCode[0] > 0) { // 非汉字
            return null;
        } else {
            return convert(uniCode);
        }
    }
    static final int GB_SP_DIFF = 160;
    static final int[] secPosValueList = { 1601, 1637, 1833, 2078, 2274, 2302,
            2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858, 4027,
            4086, 4390, 4558, 4684, 4925, 5249, 5600 };
    // 存放国标一级汉字不同读音的起始区位码对应读音
    static final char[] firstLetter = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'w', 'x',
            'y', 'z' };
    /**
     * 获取一个汉字的拼音首字母。 GB码两个字节分别减去160，转换成10进制码组合就可以得到区位码
     * 例如汉字“你”的GB码是0xC4/0xE3，分别减去0xA0（160）就是0x24/0x43
     * 0x24转成10进制就是36，0x43是67，那么它的区位码就是3667，在对照表中读音为‘n’
     */
    static char convert(byte[] bytes) {
        char result = '-';
        int secPosValue = 0;
        int i;
        for (i = 0; i < bytes.length; i++) {
            bytes[i] -= GB_SP_DIFF;
        }
        secPosValue = bytes[0] * 100 + bytes[1];
        for (i = 0; i < 23; i++) {
            if (secPosValue >= secPosValueList[i]
                    && secPosValue < secPosValueList[i + 1]) {
                result = firstLetter[i];
                break;
            }
        }
        return result;
    }
}
