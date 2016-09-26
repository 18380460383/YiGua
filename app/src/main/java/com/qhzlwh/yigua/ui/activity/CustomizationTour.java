package com.qhzlwh.yigua.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.gghl.view.wheelview.JudgeDate;
import com.gghl.view.wheelview.ScreenInfo;
import com.gghl.view.wheelview.WheelMain;
import com.google.gson.Gson;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.application.MyApplication;
import com.qhzlwh.yigua.bean.CustomizationBean;
import com.qhzlwh.yigua.util.LogUtil;
import com.qhzlwh.yigua.util.Myconstant;
import com.qhzlwh.yigua.util.RegularUtil;
import com.qhzlwh.yigua.util.ToastUtil;
import com.qhzlwh.yigua.view.MyAlertDialog;
import com.rey.material.app.DatePickerDialog1;
import com.rey.material.app.Dialog;
import com.rey.material.app.DialogFragment;
import com.rey.material.app.ThemeManager;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.ButterKnife;
import butterknife.Bind;
import butterknife.OnClick;

/**
 * 定制游
 */
public class CustomizationTour extends BaseActivity {
    @Bind(R.id.startday_choice)
    LinearLayout startdayChoice;
    @Bind(R.id.startcity_choice)
    LinearLayout startcityChoice;
    private String TAG = "CustomizationTour";
    @Bind(R.id.top_back)
    ImageView topBack;
    @Bind(R.id.top_tilte_name)
    TextView topTilteName;
    @Bind(R.id.top_rigth_text)
    TextView topRigthText;
    @Bind(R.id.top)
    LinearLayout top;
    @Bind(R.id.custom_start_location)
    TextView customStartLocation;
    @Bind(R.id.custom_location)
    EditText customLocation;
    @Bind(R.id.custom_choice_startday)
    ImageView customChoiceStartday;
    @Bind(R.id.custom_day)
    TextView customDay;
    @Bind(R.id.custom_adult)
    EditText customAdult;
    @Bind(R.id.custom_child)
    EditText customChild;
    @Bind(R.id.custom_precount)
    EditText customPrecount;
    @Bind(R.id.custom_person)
    RadioGroup customPerson;
    @Bind(R.id.custom_phone)
    EditText customPhone;
    @Bind(R.id.custom_mail)
    EditText customMail;
    @Bind(R.id.custom_submit)
    Button customSubmit;
    @Bind(R.id.custom_rab_man)
    RadioButton customRabMan;
    @Bind(R.id.custom_rab_woman)
    RadioButton customRabWoman;
    @Bind(R.id.custom_contact_person)
    EditText customContactPerson;
    @Bind(R.id.city_choice)
    ImageView cityChoice;
    @Bind(R.id.custom_startday)
    TextView customStartday;
    @Bind(R.id.hotel_text)
    TextView hotelText;
    @Bind(R.id.hotel_choice)
    LinearLayout hotelChoice;
    @Bind(R.id.eat_text)
    TextView eatText;
    @Bind(R.id.eat_choice)
    LinearLayout eatChoice;
    @Bind(R.id.leader_text)
    TextView leaderText;
    @Bind(R.id.leader_choice)
    LinearLayout leaderChoice;
    @Bind(R.id.day_choice)
    LinearLayout dayChoice;
    @Bind(R.id.adult_choice)
    LinearLayout adultChoice;
    @Bind(R.id.child_choice)
    LinearLayout childChoice;
    @Bind(R.id.custom_other)
    EditText customOther;
    private AlertDialog.Builder choice;
    private CustomizationBean.DataEntity data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customization_tour);
        ButterKnife.bind(this);
        showProgressDialog("正在加载信息。。。");
        initView();
        initData();
    }

    private void initData() {
        OkHttpUtils.get().url(Myconstant.get_type_list).build().execute(new StringCallback() {

            @Override
            public void onError(Request request, Exception e) {
            }

            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getInt("retcode") != 2000) {
                        ToastUtil.showShortToast(CustomizationTour.this, jsonObject.getString("msg"));
                    } else {
                        dismissProgressDialog();
                        CustomizationBean bean = gson.fromJson(response, CustomizationBean.class);
                        data = bean.getData();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
              /*  for (int i = 0; i < bean.getData().getZs().size(); i++) {
                    RadioButton tempButton = new RadioButton(CustomizationTour.this);
                    tempButton.setText(bean.getData().getZs().get(i).getTitle());
                    tempButton.setTag(bean.getData().getZs().get(i).getId());
                    customHotel.addView(tempButton, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                }
                customHotel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton tempButton = (RadioButton) findViewById(checkedId);
                        hotel = (String) tempButton.getTag();
                    }
                });
                for (int j = 0; j < bean.getData().getYc().size(); j++) {
                    RadioButton tempButton = new RadioButton(CustomizationTour.this);
                    tempButton.setText(bean.getData().getYc().get(j).getTitle());
                    tempButton.setTag(bean.getData().getYc().get(j).getId());
                    customEat.addView(tempButton, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                }
                customEat.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton tempButton = (RadioButton) findViewById(checkedId);
                        eat = (String) tempButton.getTag();
                    }
                });
                for (int z = 0; z < bean.getData().getLd().size(); z++) {
                    RadioButton tempButton = new RadioButton(CustomizationTour.this);
                    tempButton.setText(bean.getData().getLd().get(z).getTitle());
                    tempButton.setTag(bean.getData().getLd().get(z).getId());
                    customLeader.addView(tempButton, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                }
                customLeader.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton tempButton = (RadioButton) findViewById(checkedId);
                        leader = (String) tempButton.getTag();
                    }
                });*/
            }
        });
    }

    private void initView() {
        topTilteName.setText("填写定制需求");
        choice = new AlertDialog.Builder(this);
    }

    private String startLocation = "";
    private String address_name = "";
    private String endLocation = "";
    private String startDay = "2016-09-18 15:17:23";
    private String playCount = "";
    private String Adult = "";
    private String child = "";
    private String priceCount = "";
    private String hotel = "";
    private String eat = "";
    private String leader = "";
    private String other = "";
    private String contactPerson = "";
    private String contactPersonSex = "0";
    private String contactPhone = "";
    private String mail = "";
    private String[] hotel1;
    private String[] hotel2;
    private String[] eat1;
    private String[] eat2;
    private String[] leader1;
    private String[] leader2;
    private String[] day;

    private void choice(CustomizationBean.DataEntity bean, final int type) {
        choice.setTitle("请选择行业");
        choice.setSingleChoiceItems(toArea(bean, type), -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (type == 1) {
                    hotelText.setText(hotel2[which]);
                    hotel = hotel1[which];
                } else if (type == 2) {
                    eatText.setText(eat2[which]);
                    eat = eat1[which];
                } else if (type == 3) {
                    leaderText.setText(leader2[which]);
                    leader = leader1[which];
                }
                dialog.dismiss();
            }
        });
        choice.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        choice.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        choice.show();
    }

    /**
     * @param title
     * @param type  1选择游玩天数 2 选择成人  3 选择小孩
     */
    private void dayChoice(String title, final int type) {
        // 固定写死数组提高效率
        day = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        choice.setTitle(title);
        choice.setSingleChoiceItems(day, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            /*    age.setText(detail[which]);
                mUserDetailInfo.age = Integer.parseInt(detail[which]);*/
                if (type == 1) {
                    playCount = day[which];
                    customDay.setText(day[which]);
                } else if (type == 2) {
                    customAdult.setText("成人： " + day[which]);
                    Adult = day[which];
                } else if (type == 3) {
                    customChild.setText("小孩： " + day[which]);
                    child = day[which];
                }
                dialog.dismiss();
            }
        });
        choice.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        choice.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        choice.show();
    }

    WheelMain wheelMain;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private void choiceDay() {
        LayoutInflater inflater1 = LayoutInflater.from(CustomizationTour.this);
        final View timepickerview1 = inflater1.inflate(R.layout.timepicker,
                null);
        ScreenInfo screenInfo1 = new ScreenInfo(CustomizationTour.this);
        wheelMain = new WheelMain(timepickerview1);
        wheelMain.screenheight = screenInfo1.getHeight();
        Calendar calendar = Calendar.getInstance();
        String time1 = calendar.get(Calendar.YEAR) + "-"
                + (calendar.get(Calendar.MONTH) + 1) + "-"
                + calendar.get(Calendar.DAY_OF_MONTH) + "";
        Calendar calendar1 = Calendar.getInstance();
        if (JudgeDate.isDate(time1, "yyyy-MM-dd")) {
            try {
                calendar1.setTime(dateFormat.parse(time1));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        int year1 = calendar1.get(Calendar.YEAR);
        int month1 = calendar1.get(Calendar.MONTH);
        int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
        wheelMain.initDateTimePicker(year1, month1, day1);
        final MyAlertDialog dialog = new MyAlertDialog(CustomizationTour.this)
                .builder()
                .setTitle("选择日期")
                // .setMsg("再连续登陆15天，就可变身为QQ达人。退出QQ可能会使你现有记录归零，确定退出？")
                // .setEditText("1111111111111")
                .setView(timepickerview1)
                .setNegativeButton("取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
        dialog.setPositiveButton("保存", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        wheelMain.getTime(), Toast.LENGTH_SHORT).show();
                String datepick = wheelMain.getTime();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                startDay = dateFormat.format(datepick);
                customStartday.setText(startDay);
            }
        });
        dialog.show();
    }

    @OnClick({R.id.startday_choice, R.id.top_back, R.id.custom_choice_startday, R.id.custom_submit, R.id.custom_rab_man, R.id.custom_rab_woman, R.id.startcity_choice
            , R.id.hotel_choice, R.id.eat_choice, R.id.leader_choice, R.id.day_choice, R.id.adult_choice, R.id.child_choice})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.day_choice:
                dayChoice("请选择游玩天数", 1);
                break;
        /*    case R.id.adult_choice:
                dayChoice("请选择成人人数", 2);
                break;
            case R.id.child_choice:
                dayChoice("请选择小孩人数", 3);
                break;*/

            case R.id.hotel_choice:
                choice(data, 1);
                break;
            case R.id.eat_choice:
                choice(data, 2);
                break;
            case R.id.leader_choice:
                choice(data, 3);
                break;
            case R.id.top_back:
                finish();
                break;
            case R.id.startday_choice:
                // choiceDay();
                Dialog.Builder builder = null;
                boolean isLightTheme = ThemeManager.getInstance().getCurrentTheme() == 0;
                builder = new DatePickerDialog1.Builder(isLightTheme ? R.style.Material_App_Dialog_DatePicker_Light : R.style.Material_App_Dialog_DatePicker) {
                    @Override
                    public void onPositiveActionClicked(DialogFragment fragment) {
                        DatePickerDialog1 dialog = (DatePickerDialog1) fragment.getDialog();

                        String date = dialog.getFormattedDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                        //  Toast.makeText(mActivity, "Date is " + date, Toast.LENGTH_SHORT).show();
                        // Toast.makeText(CustomizationTour.this, "Time is " + date, Toast.LENGTH_SHORT).show();
                        startDay = date;
                        customStartday.setText(startDay);
                        super.onPositiveActionClicked(fragment);
                    }

                    @Override
                    public void onNegativeActionClicked(DialogFragment fragment) {
                        Toast.makeText(CustomizationTour.this, "Cancelled", Toast.LENGTH_SHORT).show();
                        super.onNegativeActionClicked(fragment);

                    }
                };

                builder.positiveAction("确定")
                        .negativeAction("取消");
                DialogFragment fragment = DialogFragment.newInstance(builder);
                fragment.show(getSupportFragmentManager(), null);
                break;
            case R.id.custom_submit:
                //startLocation = customStartLocation.getText().toString();
                endLocation = customLocation.getText().toString();
                // startDay=customDay.getText().toString();
                //playCount = customDay.getText().toString();
                Adult = customAdult.getText().toString();
                child = customChild.getText().toString();
                priceCount = customPrecount.getText().toString();
                other = customOther.getText().toString();
                contactPerson = customContactPerson.getText().toString();
                contactPhone = customPhone.getText().toString();
                mail = customMail.getText().toString();
            /*    if (startLocation.isEmpty() || endLocation.isEmpty() || startDay.isEmpty() || playCount.isEmpty() || Adult.isEmpty() || priceCount.isEmpty() ||
                        contactPerson.isEmpty() || contactPhone.isEmpty() || mail.isEmpty()) {
                    ToastUtil.showShortToast(CustomizationTour.this, "资料不完整！");
                } else {*/
                    if (!RegularUtil.isMobileNO(contactPhone)) {
                        ToastUtil.showShortToast(CustomizationTour.this, "电话号码不合法！");
                    } else if (!RegularUtil.isEmail(mail)) {
                        ToastUtil.showShortToast(CustomizationTour.this, "邮箱不合法！");
                    } else {
/*                       address_id	必须	出发地ID
                       destination	必须	目的地名称
                       departure_time	必须	出发日期（年月日格式：2016-08-11 15:17:23）
                       play_day	必须	游玩天数（整数）
                       travel_adult_count	必须	出行人数【成人】（整数）
                       travel_children_count	必须	出行人数【儿童】（整数）
                       budget_money	必须	出游预算费用/人
                       zsbz_id	必须	住宿标准id
                       ycbz_id	必须	用餐标准id
                       ldyq_id	必须	领队要求id
                       other_requirements	可选	其他要求
                       sex	必须	性别(0未知1男2女)
                       contacts	必须	联系人
                       tel	必须	联系电话
                       email	必须	邮箱
                       user_id	必须	用户ID*/
                        OkHttpUtils.post().url(Myconstant.add_custom_tour)
                                .addParams("address_id", startLocation)
                                .addParams("address_name", address_name)
                                .addParams("destination", endLocation)
                                .addParams("departure_time", startDay)
                                .addParams("play_day", playCount)
                                .addParams("travel_adult_count", Adult)
                                .addParams("travel_children_count", child)
                                .addParams("budget_money", priceCount)
                                .addParams("zsbz_id", hotel)
                                .addParams("ycbz_id", eat)
                                .addParams("ldyq_id", leader)
                                .addParams("other_requirements", other)
                                .addParams("sex", contactPersonSex)
                                .addParams("contacts", contactPerson)
                                .addParams("tel", contactPhone)
                                .addParams("email", mail)
                                .addParams("user_id", "" + MyApplication.USER_ID)
                                .build().execute(new StringCallback() {
                            @Override
                            public void onError(Request request, Exception e) {
                                ToastUtil.showShortToast(CustomizationTour.this, "定制失败");
                            }

                            @Override
                            public void onResponse(String response) {
                                LogUtil.e("custom", response);
                                try {
                                    JSONObject js = new JSONObject(response);
                                    String msg = js.getString("msg");
                                    int retcode = js.getInt("retcode");
                                    if (retcode == 2000) {
                                        ToastUtil.showShortToast(CustomizationTour.this, "定制成功");
                                        finish();
                                    } else {
                                        LogUtil.e(TAG, msg);
                                        ToastUtil.showShortToast(CustomizationTour.this, msg);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                    }
                //}
                LogUtil.e("custom", "id: "+startLocation+" address_name:"+address_name+"destination:"+endLocation+" hotel:" + hotel + "eat:" + eat + "leader:" + leader + "sex" + contactPersonSex);

                break;
            case R.id.custom_rab_man:
                contactPersonSex = "1";
                break;
            case R.id.custom_rab_woman:
                contactPersonSex = "2";
                break;
           // case R.id.city_choice:
            case R.id.startcity_choice:
                contactPersonSex = "2";
                Intent intent = new Intent(CustomizationTour.this, CityPickerActivity.class);
                startActivityForResult(intent, 1);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                startLocation = bundle.getString("id");
                address_name = bundle.getString("city");
                customStartLocation.setText(bundle.getString("city"));
            }
        }
    }

    public String[] toArea(CustomizationBean.DataEntity msg, int type) {//type=1 2 3
        if (msg == null) {
            return new String[0];
        }
        String[] arr = new String[0];
        if (type == 1) {//zs
            arr = new String[msg.getZs().size()];
            hotel1 = new String[msg.getZs().size()];
            hotel2 = new String[msg.getZs().size()];
            for (int i = 0; i < msg.getZs().size(); i++) {
                arr[i] = msg.getZs().get(i).getTitle();
                hotel1[i] = msg.getZs().get(i).getId();
                hotel2[i] = msg.getZs().get(i).getTitle();
            }
        } else if (type == 2) {//yc
            arr = new String[msg.getYc().size()];
            eat1 = new String[msg.getYc().size()];
            eat2 = new String[msg.getYc().size()];
            for (int i = 0; i < msg.getYc().size(); i++) {
                arr[i] = msg.getYc().get(i).getTitle();
                eat1[i] = msg.getYc().get(i).getId();
                eat2[i] = msg.getYc().get(i).getTitle();
            }
        } else if (type == 3) {//ld
            arr = new String[msg.getLd().size()];
            leader1 = new String[msg.getLd().size()];
            leader2 = new String[msg.getLd().size()];
            for (int i = 0; i < msg.getLd().size(); i++) {
                arr[i] = msg.getLd().get(i).getTitle();
                leader1[i] = msg.getLd().get(i).getId();
                leader2[i] = msg.getLd().get(i).getTitle();
            }
        }

        return arr;
    }


}
