package com.qhzlwh.yigua.bean;

/**
 * Created by Administrator on 2015/11/11.
 */
public class UpdateMsg {


    /**
     * retcode : 2000
     * msg : 获取成功
     * data : {"bate":"1","showversion":"1.0.0","download":null,"isqz":"0","qzcont":"一呱APP","not_version_alert":"已经是最新版本了！"}
     */

    private int retcode;
    private String msg;
    /**
     * bate : 1
     * showversion : 1.0.0
     * download : null
     * isqz : 0
     * qzcont : 一呱APP
     * not_version_alert : 已经是最新版本了！
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
        private String bate;
        private String showversion;
        private Object download;
        private String isqz;
        private String qzcont;
        private String not_version_alert;

        public String getBate() {
            return bate;
        }

        public void setBate(String bate) {
            this.bate = bate;
        }

        public String getShowversion() {
            return showversion;
        }

        public void setShowversion(String showversion) {
            this.showversion = showversion;
        }

        public Object getDownload() {
            return download;
        }

        public void setDownload(Object download) {
            this.download = download;
        }

        public String getIsqz() {
            return isqz;
        }

        public void setIsqz(String isqz) {
            this.isqz = isqz;
        }

        public String getQzcont() {
            return qzcont;
        }

        public void setQzcont(String qzcont) {
            this.qzcont = qzcont;
        }

        public String getNot_version_alert() {
            return not_version_alert;
        }

        public void setNot_version_alert(String not_version_alert) {
            this.not_version_alert = not_version_alert;
        }
    }
}
