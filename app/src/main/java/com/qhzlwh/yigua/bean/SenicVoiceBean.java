package com.qhzlwh.yigua.bean;

import java.util.List;

/**
 * 创建者：Administrator
 * 时间：2016/9/5
 * 功能描述：
 */
public class SenicVoiceBean {

    /**
     * retcode : 2000
     * msg : 推荐景点获取成功
     * data : [{"name":"重渡沟","sound":"http://qh.91chuanbo.cn//Uploads/Download/2016-02-01/56aee9d8167ad.mp3","time":"1:59","is_senic":1},{"name":"飞虹瀑布","sound":"http://qh.91chuanbo.cn//Uploads/Download/2016-02-01/56af6acff303c.mp3","time":"1:22","is_senic":0},{"name":"\u200b剑插泉","sound":"http://qh.91chuanbo.cn//Uploads/Download/2016-02-01/56af6c2347ea2.mp3","time":"0:40","is_senic":0},{"name":"马海明纪念园","sound":"http://qh.91chuanbo.cn//Uploads/Download/2016-02-01/56af6d2b0dd8f.mp3","time":"0:51","is_senic":0},{"name":"农耕村","sound":"http://qh.91chuanbo.cn//Uploads/Download/2016-02-01/56af748f54303.mp3","time":"0:59","is_senic":0},{"name":"菩提树","sound":"http://qh.91chuanbo.cn//Uploads/Download/2016-02-01/56af750b6e182.mp3","time":"0:36","is_senic":0},{"name":"水帘仙宫","sound":"http://qh.91chuanbo.cn//Uploads/Download/2016-02-01/56af7563c0ed3.mp3","time":"0:30","is_senic":0},{"name":"五彩莲池","sound":"http://qh.91chuanbo.cn//Uploads/Download/2016-02-01/56af75bef100a.mp3","time":"0:23","is_senic":0},{"name":"泄愤崖瀑布","sound":"http://qh.91chuanbo.cn//Uploads/Download/2016-02-01/56af75fd4d1c8.mp3","time":"0:47","is_senic":0},{"name":"震天雷瀑布","sound":"http://qh.91chuanbo.cn//Uploads/Download/2016-02-01/56af766a478a4.mp3","time":"0:35","is_senic":0},{"name":"迎宾瀑布","sound":"http://qh.91chuanbo.cn//Uploads/Download/2016-02-05/56b4239c99899.mp3","time":"0:41","is_senic":0},{"name":"革面潭","sound":"http://qh.91chuanbo.cn//Uploads/Download/2016-02-05/56b4240b36873.mp3","time":"0:20","is_senic":0},{"name":"竹林长廊","sound":"http://qh.91chuanbo.cn//Uploads/Download/2016-02-05/56b4241c9d8d5.mp3","time":"0:48","is_senic":0},{"name":"农耕村","sound":"http://qh.91chuanbo.cn//Uploads/Download/2016-02-01/56af748f54303.mp3","time":"0:59","is_senic":0},{"name":"水帘仙宫","sound":"http://qh.91chuanbo.cn//Uploads/Download/2016-02-17/56c41e06477ce.mp3","time":"1:19","is_senic":0},{"name":"五彩莲池","sound":"http://qh.91chuanbo.cn//Uploads/Download/2016-02-01/56af75bef100a.mp3","time":"0:23","is_senic":0},{"name":"玉如意瀑布","sound":"http://qh.91chuanbo.cn//Uploads/Download/2016-02-17/56c41f8888f0f.mp3","time":"0:26","is_senic":0},{"name":"震天雷瀑布","sound":"http://qh.91chuanbo.cn//Uploads/Download/2016-02-01/56af766a478a4.mp3","time":"0:35","is_senic":0},{"name":"飞虹瀑布","sound":"http://qh.91chuanbo.cn//Uploads/Download/2016-02-01/56af6acff303c.mp3","time":"1:22","is_senic":0},{"name":"歇二歇","sound":"http://qh.91chuanbo.cn//Uploads/Download/2016-02-17/56c4298c90c32.mp3","time":"0:28","is_senic":0},{"name":"歇三歇","sound":"http://qh.91chuanbo.cn//Uploads/Download/2016-02-17/56c429bae0f17.mp3","time":"0:37","is_senic":0},{"name":"奥斯卡四维影城","sound":"http://qh.91chuanbo.cn//Uploads/Download/2016-02-17/56c42aad5f9f5.mp3","time":"0:18","is_senic":0},{"name":"大停车场","sound":"http://qh.91chuanbo.cn//Uploads/Download/2016-02-17/56c42aed54473.mp3","time":"0:30","is_senic":0},{"name":"导服中心","sound":"http://qh.91chuanbo.cn//Uploads/Download/2016-02-17/56c42b5435ebe.mp3","time":"0:24","is_senic":0},{"name":"风波桥","sound":"http://qh.91chuanbo.cn//Uploads/Download/2016-02-17/56c42b8689ec6.mp3","time":"0:53","is_senic":0},{"name":"风波桥","sound":"http://qh.91chuanbo.cn//Uploads/Download/2016-02-17/56c42b8689ec6.mp3","time":"0:53","is_senic":0},{"name":"关公湖","sound":"http://qh.91chuanbo.cn//Uploads/Download/2016-02-17/56c4402cb0b35.mp3","time":"0:26","is_senic":0},{"name":"观光车站","sound":"http://qh.91chuanbo.cn//Uploads/Download/2016-02-17/56c440676c20b.mp3","time":"0:11","is_senic":0}]
     */

    private int retcode;
    private String msg;
    /**
     * name : 重渡沟
     * sound : http://qh.91chuanbo.cn//Uploads/Download/2016-02-01/56aee9d8167ad.mp3
     * time : 1:59
     * is_senic : 1
     */

    private List<DataEntity> data;

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

    public List<DataEntity> getData() {
        return data;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public static class DataEntity {
        private String name;
        private String sound;
        private String time;
        private int is_senic;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSound() {
            return sound;
        }

        public void setSound(String sound) {
            this.sound = sound;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getIs_senic() {
            return is_senic;
        }

        public void setIs_senic(int is_senic) {
            this.is_senic = is_senic;
        }
    }
}
