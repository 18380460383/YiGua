package com.qhzlwh.yigua.bean;

import java.util.List;

/**
 * 创建者：Administrator
 * 时间：2016/9/7
 * 功能描述：收藏
 */
public class SenicCollectionBean {

    /**
     * retcode : 2000
     * msg : 收藏的自驾游信息获取成功
     * data : [{"senic_id":"127","thumbnail":"/Uploads/Picture/2016-08-19/5b59926fc28.jpg","name":"黑马河","price":"5.00","long_title":"西宁贵德青海湖环湖黑马河茶卡盐湖双卧6日跟团游","short_title":"让旅游回归本质，自由行的感觉，","level_name":"5A级景区","cate_name":"人文古迹","templete":1}]
     */

    private int retcode;
    private String msg;
    /**
     * senic_id : 127
     * thumbnail : /Uploads/Picture/2016-08-19/5b59926fc28.jpg
     * name : 黑马河
     * price : 5.00
     * long_title : 西宁贵德青海湖环湖黑马河茶卡盐湖双卧6日跟团游
     * short_title : 让旅游回归本质，自由行的感觉，
     * level_name : 5A级景区
     * cate_name : 人文古迹
     * templete : 1
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
        private String thumbnail;
        private String name;
        private String price;
        private String long_title;
        private String short_title;
        private String level_name;
        private String cate_name;
        private int templete;

        public String getSenic_id() {
            return senic_id;
        }

        public void setSenic_id(String senic_id) {
            this.senic_id = senic_id;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getLong_title() {
            return long_title;
        }

        public void setLong_title(String long_title) {
            this.long_title = long_title;
        }

        public String getShort_title() {
            return short_title;
        }

        public void setShort_title(String short_title) {
            this.short_title = short_title;
        }

        public String getLevel_name() {
            return level_name;
        }

        public void setLevel_name(String level_name) {
            this.level_name = level_name;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }

        public int getTemplete() {
            return templete;
        }

        public void setTemplete(int templete) {
            this.templete = templete;
        }
    }
}
