package com.qhzlwh.yigua.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.qhzlwh.yigua.R;
import com.qhzlwh.yigua.application.MyApplication;
import com.qhzlwh.yigua.bean.SenicFileBean;
import com.qhzlwh.yigua.bean.UserInfo;
import com.qhzlwh.yigua.util.FileUtil;
import com.qhzlwh.yigua.util.LogUtil;
import com.qhzlwh.yigua.util.Myconstant;
import com.qhzlwh.yigua.util.ToastUtil;
import com.rey.material.app.DatePickerDialog;
import com.rey.material.app.Dialog;
import com.rey.material.app.DialogFragment;
import com.rey.material.app.SimpleDialog;
import com.rey.material.app.ThemeManager;
import com.rey.material.widget.EditText;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Bind;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

/**
 * 用户资料管理
 */
public class UserInformation extends BaseActivity {

    private String TAG="UserInformation";
    @Bind(R.id.top_back)
    ImageView topBack;
    @Bind(R.id.top_tilte_name)
    TextView topTilteName;
    @Bind(R.id.top_rigth_text)
    TextView topRigthText;
    @Bind(R.id.top)
    LinearLayout top;
    @Bind(R.id.user_image)
    ImageView userImage;
    @Bind(R.id.user_image_choice)
    ImageView userImageChoice;
    @Bind(R.id.user_nic)
    TextView userNic;

    @Bind(R.id.user_name)
    TextView userName;

    @Bind(R.id.user_phone)
    TextView userPhone;
    @Bind(R.id.user_birth)
    TextView userBirth;

    @Bind(R.id.user_sex)
    TextView userSex;

    @Bind(R.id.submit)
    Button submit;
    @Bind(R.id.user_nic_input)
    LinearLayout userNicInput;
    @Bind(R.id.user_name_input)
    LinearLayout userNameInput;
    @Bind(R.id.user_birth_choice)
    LinearLayout userBirthChoice;
    @Bind(R.id.sex_choice)
    LinearLayout sexChoice;

    private String user_name;
    private String gender;
    private String avatar;
    private String truename;
    private String birthday;
    private static final int REQUEST_IMAGE = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        topTilteName.setText("我的信息");
        userPhone.setText(MyApplication.PHONE);
        initData();
    }

    private void initData() {
        SharedPreferences sp = MyApplication.getInstance().getSp();
        user_name = sp.getString("user_name", "");
        gender = sp.getString("gender", "");
        avatar = sp.getString("avatar", "");
        truename = sp.getString("truename", "");
        birthday = sp.getString("birthday", "");
        if(TextUtils.isEmpty(user_name)) {
            OkHttpUtils.get().url(Myconstant.getUserInfo + MyApplication.USER_ID).build().execute(new StringCallback() {
                @Override
                public void onError(Request request, Exception e) {

                }

                @Override
                public void onResponse(String response) {
                    Gson gson = new Gson();
                    UserInfo userInfo = gson.fromJson(response, UserInfo.class);
                    if (userInfo.getRetcode() != 2000) {
                        ToastUtil.showShortToast(UserInformation.this, userInfo.getMsg());
                    } else {
                        user_name=userInfo.getData().getUser_name();
                        gender=userInfo.getData().getGender();
                        avatar=Myconstant.BASE_URL + userInfo.getData().getAvatar();
                        truename=userInfo.getData().getTruename();
                        birthday=userInfo.getData().getBirthday();
                        SharedPreferences.Editor editor= MyApplication.getInstance().getSp().edit();
                        editor.putString("user_name",user_name);
                        editor.putString("gender",gender);
                        editor.putString("avatar",avatar);
                        editor.putString("truename",truename);
                        editor.putString("birthday",birthday);
                        editor.commit();
                        Glide.with(UserInformation.this).load(avatar).into(userImage);
                        userNic.setText(user_name);
                        userName.setText(truename);
                        userBirth.setText(birthday);
                        if (gender.equals("1")) {
                            userSex.setText("男");
                        } else {
                            userSex.setText("女");
                        }
                    }
                }
            });
        }
        else{
            userNic.setText(user_name);
            userName.setText(truename);
            userBirth.setText(birthday);
            if (gender.equals("1")) {
                userSex.setText("男");
            } else {
                userSex.setText("女");
            }
        }
        if (avatar.isEmpty()) {
            Glide.with(UserInformation.this).load(R.mipmap.publish_add).into(userImage);

        } else {
            Glide.with(UserInformation.this).load(Myconstant.BASE_URL + avatar).into(userImage);
        }
    }

    @OnClick(R.id.top_back)
    public void onClick() {
        finish();
    }

    @OnClick({R.id.top_back, R.id.user_image, R.id.user_image_choice, R.id.user_nic_input, R.id.user_name_input, R.id.user_birth_choice, R.id.sex_choice, R.id.submit})
    public void onClick(View view) {
        Dialog.Builder builder = null;

        boolean isLightTheme = ThemeManager.getInstance().getCurrentTheme() == 0;
        switch (view.getId()) {
            case R.id.top_back:
                finish();
                break;
            case R.id.user_image:
                choicePicture();
                break;
            case R.id.user_image_choice:
                choicePicture();
                break;
            case R.id.user_nic_input:
                builder = new SimpleDialog.Builder(isLightTheme ? R.style.SimpleDialogLight : R.style.SimpleDialog) {

                    @Override
                    protected void onBuildDone(Dialog dialog) {
                        dialog.layoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    }

                    @Override
                    public void onPositiveActionClicked(DialogFragment fragment) {
                        EditText et_pass = (EditText) fragment.getDialog().findViewById(R.id.custom_et_password);
                        Toast.makeText(UserInformation.this, "昵称" + et_pass.getText().toString(), Toast.LENGTH_SHORT).show();
                        user_name = et_pass.getText().toString();
                        userNic.setText(et_pass.getText().toString());
                        super.onPositiveActionClicked(fragment);
                    }

                    @Override
                    public void onNegativeActionClicked(DialogFragment fragment) {
                        Toast.makeText(UserInformation.this, "Cancelled", Toast.LENGTH_SHORT).show();
                        super.onNegativeActionClicked(fragment);
                    }
                };

                builder.title("请输入昵称")
                        .positiveAction("确定")
                        .negativeAction("取消")
                        .contentView(R.layout.layout_dialog_custom);
                break;
            case R.id.user_name_input:
                builder = new SimpleDialog.Builder(isLightTheme ? R.style.SimpleDialogLight : R.style.SimpleDialog) {

                    @Override
                    protected void onBuildDone(Dialog dialog) {
                        dialog.layoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    }

                    @Override
                    public void onPositiveActionClicked(DialogFragment fragment) {
                        EditText et_pass = (EditText) fragment.getDialog().findViewById(R.id.custom_et_password);
                        Toast.makeText(UserInformation.this, "姓名" + et_pass.getText().toString(), Toast.LENGTH_SHORT).show();
                        truename = et_pass.getText().toString();
                        userName.setText(et_pass.getText().toString());
                        super.onPositiveActionClicked(fragment);
                    }

                    @Override
                    public void onNegativeActionClicked(DialogFragment fragment) {
                        Toast.makeText(UserInformation.this, "Cancelled", Toast.LENGTH_SHORT).show();
                        super.onNegativeActionClicked(fragment);
                    }
                };

                builder.title("请输入您的真实姓名")
                        .positiveAction("确定")
                        .negativeAction("取消")
                        .contentView(R.layout.layout_dialog_custom);
                break;
            case R.id.user_birth_choice:
                builder = new DatePickerDialog.Builder(isLightTheme ? R.style.Material_App_Dialog_DatePicker_Light : R.style.Material_App_Dialog_DatePicker) {
                    @Override
                    public void onPositiveActionClicked(DialogFragment fragment) {
                        DatePickerDialog dialog = (DatePickerDialog) fragment.getDialog();
                        String date = dialog.getFormattedDate(new SimpleDateFormat("yyyy-MM-dd"));
                        Toast.makeText(UserInformation.this, "Date is " + date, Toast.LENGTH_SHORT).show();
                        userBirth.setText(date);
                        birthday = date;
                        // startDay=date;
                        super.onPositiveActionClicked(fragment);
                    }

                    @Override
                    public void onNegativeActionClicked(DialogFragment fragment) {
                        Toast.makeText(UserInformation.this, "Cancelled", Toast.LENGTH_SHORT).show();
                        super.onNegativeActionClicked(fragment);
                    }
                };

                builder.positiveAction("确定")
                        .negativeAction("取消");
                break;
            case R.id.sex_choice:
                builder = new SimpleDialog.Builder(isLightTheme ? R.style.SimpleDialogLight : R.style.SimpleDialog) {
                    @Override
                    public void onPositiveActionClicked(DialogFragment fragment) {
                        Toast.makeText(UserInformation.this, "选择了 " + getSelectedValue() + " ", Toast.LENGTH_SHORT).show();
                        String sex = (String) getSelectedValue();
                        if (sex.equals("男")) {
                            gender = "1";
                        } else {
                            gender = "0";
                        }
                        userSex.setText(getSelectedValue());
                        super.onPositiveActionClicked(fragment);
                    }

                    @Override
                    public void onNegativeActionClicked(DialogFragment fragment) {
                        Toast.makeText(UserInformation.this, "Cancelled", Toast.LENGTH_SHORT).show();
                        super.onNegativeActionClicked(fragment);
                    }
                };

                ((SimpleDialog.Builder) builder).items(new String[]{"男", "女"}, 0)
                        .title("选择性别")
                        .positiveAction("确定")
                        .negativeAction("取消");
                break;
            case R.id.submit:
          /*      user_id	必须	用户UID
                user_name	必须	用户昵称
                gender	必须	性别
                avatar	必须	头像
                truename	可选	真实姓名
                birthday	可选	生日 | 日期格式*/
               /* if(avatar.isEmpty()){*/
                if (user_name.isEmpty() || gender.isEmpty() || avatar.isEmpty() || truename.isEmpty() || birthday.isEmpty()) {
                    ToastUtil.showShortToast(UserInformation.this, "资料不完整");
                } else {
                    SharedPreferences.Editor edit = MyApplication.getInstance().getSp().edit();
                    edit.putString("user_name", user_name);
                    edit.putString("gender", gender);
                    edit.putString("avatar", avatar);
                    edit.putString("truename", truename);
                    edit.putString("birthday", birthday);
                    edit.commit();


                    SharedPreferences sp = MyApplication.getInstance().getSp();

                    LogUtil.e(TAG,"user_name="+sp.getString("user_name", "")+
                            "gender="+sp.getString("gender", "")+
                            "avatar="+sp.getString("avatar", "")+
                            "truename="+sp.getString("truename", "")+
                            "birthday="+sp.getString("birthday", "")
                           );
                    OkHttpUtils.post().url(Myconstant.userEdit).addParams("user_id", "" + MyApplication.USER_ID)
                            .addParams("user_name", user_name)
                            .addParams("gender", gender)
                            .addParams("avatar", avatar)
                            .addParams("truename", truename)
                            .addParams("birthday", birthday).build().execute(new StringCallback() {
                        @Override
                        public void onError(Request request, Exception e) {
                            ToastUtil.showShortToast(UserInformation.this, "资料修改错误");
                        }

                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject js = new JSONObject(response);
                                //  int code=js.getInt("");
                                String data = js.getString("msg");
                                ToastUtil.showShortToast(UserInformation.this, data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }

               /* }else{
                    ToastUtil.showShortToast(UserInformation.this,"图片上传未完成");
                }*/
                LogUtil.e(TAG, MyApplication.USER_ID + "  " + gender + "   " + avatar + "     " + user_name);
                break;
        }
        if (builder != null) {
            DialogFragment fragment = DialogFragment.newInstance(builder);
            fragment.show(getSupportFragmentManager(), null);
        }

    }

    public void choicePicture() {
        Intent intent = new Intent(UserInformation.this, MultiImageSelectorActivity.class);
        // 是否显示调用相机拍照
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
        // 最大图片选择数量
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 1);
        // 设置模式 (支持 单选/MultiImageSelectorActivity.MODE_SINGLE 或者 多选/MultiImageSelectorActivity.MODE_MULTI)
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    private boolean iscomplete = false;
    private String list;

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                // 获取返回的图片列表
                iscomplete = false;
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                Glide.with(UserInformation.this).load(path.get(0)).into(userImage);
                OkHttpUtils.post().url(Myconstant.uploadPicture).
                        addFile("file", FileUtil.getFileName(path.get(0)), new File(path.get(0))).build().execute(new StringCallback() {
                    @Override
                    public void onError(Request request, Exception e) {
                        ToastUtil.showShortToast(UserInformation.this, "图片上传错误");
                    }

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject js = new JSONObject(response);
                            int code = js.getInt("retcode");
                            String data = js.getString("msg");
                            LogUtil.e(TAG, response);
                            ToastUtil.showShortToast(UserInformation.this, data);
                            Gson gson = new Gson();
                            SenicFileBean bean = gson.fromJson(response, SenicFileBean.class);
                            avatar = bean.getData().getFile().getPath();
                            iscomplete = true;
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }
}
