package com.qhzlwh.yigua.bean;

/**
 * Created by FuPei on 2016/9/14.
 */
public class UserInfo {

    /**
     * retcode : 2000
     * msg : 获取会员信息成功！
     * data : {"user_id":"27","truename":"peng","user_name":"ha","password":"824148019d54f88b8e68bcb8111d8c9f","gender":"1","avatar":"/Uploads/Picture/2016-09-09/57d25f7b5f796.jpg","mobile":"18380460383","email":"","register_date":"1472788222","last_login":"1473666690","online_time":"0","login_times":"0","article_count":"0","comment_count":"0","action_count":"0","point":"19534","blance":"0.00","status":"1","device_token":"ffffffff-daed-2e15-d4e8-335a0033","sign":"","residence_add":"","common_add":"","birthday":"2010-09-16","visit_count":"3","r_token":"f431b4d2f1eed96acae0ce7dae43dc1b","region_id":"2643"}
     */

    private int retcode;
    private String msg;
    /**
     * user_id : 27
     * truename : peng
     * user_name : ha
     * password : 824148019d54f88b8e68bcb8111d8c9f
     * gender : 1
     * avatar : /Uploads/Picture/2016-09-09/57d25f7b5f796.jpg
     * mobile : 18380460383
     * email :
     * register_date : 1472788222
     * last_login : 1473666690
     * online_time : 0
     * login_times : 0
     * article_count : 0
     * comment_count : 0
     * action_count : 0
     * point : 19534
     * blance : 0.00
     * status : 1
     * device_token : ffffffff-daed-2e15-d4e8-335a0033
     * sign :
     * residence_add :
     * common_add :
     * birthday : 2010-09-16
     * visit_count : 3
     * r_token : f431b4d2f1eed96acae0ce7dae43dc1b
     * region_id : 2643
     */

    private DataBean data;

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String user_id;
        private String truename;
        private String user_name;
        private String password;
        private String gender;
        private String avatar;
        private String mobile;
        private String email;
        private String register_date;
        private String last_login;
        private String online_time;
        private String login_times;
        private String article_count;
        private String comment_count;
        private String action_count;
        private String point;
        private String blance;
        private String status;
        private String device_token;
        private String sign;
        private String residence_add;
        private String common_add;
        private String birthday;
        private String visit_count;
        private String r_token;
        private String region_id;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRegister_date() {
            return register_date;
        }

        public void setRegister_date(String register_date) {
            this.register_date = register_date;
        }

        public String getLast_login() {
            return last_login;
        }

        public void setLast_login(String last_login) {
            this.last_login = last_login;
        }

        public String getOnline_time() {
            return online_time;
        }

        public void setOnline_time(String online_time) {
            this.online_time = online_time;
        }

        public String getLogin_times() {
            return login_times;
        }

        public void setLogin_times(String login_times) {
            this.login_times = login_times;
        }

        public String getArticle_count() {
            return article_count;
        }

        public void setArticle_count(String article_count) {
            this.article_count = article_count;
        }

        public String getComment_count() {
            return comment_count;
        }

        public void setComment_count(String comment_count) {
            this.comment_count = comment_count;
        }

        public String getAction_count() {
            return action_count;
        }

        public void setAction_count(String action_count) {
            this.action_count = action_count;
        }

        public String getPoint() {
            return point;
        }

        public void setPoint(String point) {
            this.point = point;
        }

        public String getBlance() {
            return blance;
        }

        public void setBlance(String blance) {
            this.blance = blance;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDevice_token() {
            return device_token;
        }

        public void setDevice_token(String device_token) {
            this.device_token = device_token;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getResidence_add() {
            return residence_add;
        }

        public void setResidence_add(String residence_add) {
            this.residence_add = residence_add;
        }

        public String getCommon_add() {
            return common_add;
        }

        public void setCommon_add(String common_add) {
            this.common_add = common_add;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getVisit_count() {
            return visit_count;
        }

        public void setVisit_count(String visit_count) {
            this.visit_count = visit_count;
        }

        public String getR_token() {
            return r_token;
        }

        public void setR_token(String r_token) {
            this.r_token = r_token;
        }

        public String getRegion_id() {
            return region_id;
        }

        public void setRegion_id(String region_id) {
            this.region_id = region_id;
        }
    }
}
