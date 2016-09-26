package com.qhzlwh.yigua.bean;

/**
 * 创建者：Administrator
 * 时间：2016/9/2
 * 功能描述：
 */
public class LoginUserBean {

    /**
     * retcode : 2000
     * msg : 登录成功！
     * data : {"user_id":27,"r_token":"f431b4d2f1eed96acae0ce7dae43dc1b"}
     */

    private int retcode;
    private String msg;
    /**
     * user_id : 27
     * r_token : f431b4d2f1eed96acae0ce7dae43dc1b
     */

    private DataEntity data;

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

    public DataEntity getData() {
        return data;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public static class DataEntity {
        private int user_id;
        private String r_token;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getR_token() {
            return r_token;
        }

        public void setR_token(String r_token) {
            this.r_token = r_token;
        }
    }
}
