package com.qhzlwh.yigua.bean;

import java.util.List;

/**
 * Created by FuPei on 2016/9/13.
 */
public class LineOrTour {

    /**
     * retcode : 2000
     * msg : 获取数据成功！
     * data : [{"senic_id":"128","name":"青海湖","short_title":"骑行游青海湖，徒步进天空之境","thumbnail":"/Uploads/Picture/2016-08-19/2e03eaf2c15.jpg","long_title":"西宁青海湖二郎剑、茶卡盐湖6日跟团游","price":"1850.00","tour_name":"张先","attribute_id":"22","templete":1},{"senic_id":"127","name":"黑马河","short_title":"让旅游回归本质，自由行的感觉，","thumbnail":"/Uploads/Picture/2016-08-19/5b59926fc28.jpg","long_title":"西宁贵德青海湖环湖黑马河茶卡盐湖双卧6日跟团游","price":"5.00","tour_name":"55","attribute_id":"22","templete":1}]
     */

    private int retcode;
    private String msg;
    /**
     * senic_id : 128
     * name : 青海湖
     * short_title : 骑行游青海湖，徒步进天空之境
     * thumbnail : /Uploads/Picture/2016-08-19/2e03eaf2c15.jpg
     * long_title : 西宁青海湖二郎剑、茶卡盐湖6日跟团游
     * price : 1850.00
     * tour_name : 张先
     * attribute_id : 22
     * templete : 1
     */

    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String senic_id;
        private String name;
        private String short_title;
        private String thumbnail;
        private String long_title;
        private String price;
        private String tour_name;
        private String attribute_id;
        private int templete;

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

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getLong_title() {
            return long_title;
        }

        public void setLong_title(String long_title) {
            this.long_title = long_title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getTour_name() {
            return tour_name;
        }

        public void setTour_name(String tour_name) {
            this.tour_name = tour_name;
        }

        public String getAttribute_id() {
            return attribute_id;
        }

        public void setAttribute_id(String attribute_id) {
            this.attribute_id = attribute_id;
        }

        public int getTemplete() {
            return templete;
        }

        public void setTemplete(int templete) {
            this.templete = templete;
        }
    }
}
