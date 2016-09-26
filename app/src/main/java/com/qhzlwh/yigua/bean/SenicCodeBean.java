package com.qhzlwh.yigua.bean;

/**
 * 创建者：Administrator
 * 时间：2016/9/2
 * 功能描述：
 */
public class SenicCodeBean {

    /**
     * retcode : 2000
     * msg : 发送成功!
     * data : {"code":2671}
     */

    private int retcode;
    private String msg;
    /**
     * code : 2671
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
        private int code;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }
}
