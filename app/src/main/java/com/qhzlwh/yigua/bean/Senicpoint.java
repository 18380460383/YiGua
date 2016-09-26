package com.qhzlwh.yigua.bean;

import java.util.List;

/**
 * 创建者：Administrator
 * 时间：2016/8/31
 * 功能描述：城市下的景点
 */
public class Senicpoint {

    /**
     * retcode : 2000
     * msg : 景区信息获取成功
     * data : [{"senic_id":"125","name":" 茶卡盐湖","short_title":" \u2022观看云朵和山川等","long_title":"茶卡盐湖可以乘坐小火车进人到深处，顺着铁轨在湖边走走，平静的","thumbnail":"/Uploads/Picture/2016-08-19/b172a29b265.jpg","price":"567.00"}]
     */

    private int retcode;
    private String msg;
    /**
     * senic_id : 125
     * name :  茶卡盐湖
     * short_title :  •观看云朵和山川等
     * long_title : 茶卡盐湖可以乘坐小火车进人到深处，顺着铁轨在湖边走走，平静的
     * thumbnail : /Uploads/Picture/2016-08-19/b172a29b265.jpg
     * price : 567.00
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
        private String senic_id;
        private String name;
        private String short_title;
        private String long_title;
        private String thumbnail;
        private String price;

        public String getSenic_id() {
            return senic_id;
        }

        public void setSenic_id(String senic_id) {
            this.senic_id = senic_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getShort_title() {
            return short_title;
        }

        public void setShort_title(String short_title) {
            this.short_title = short_title;
        }

        public String getLong_title() {
            return long_title;
        }

        public void setLong_title(String long_title) {
            this.long_title = long_title;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
