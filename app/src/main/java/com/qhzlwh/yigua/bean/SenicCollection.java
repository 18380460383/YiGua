package com.qhzlwh.yigua.bean;

import java.util.List;

/**
 * 创建者：Administrator
 * 时间：2016/9/6
 * 功能描述：收藏的景区
 */
public class SenicCollection {

    /**
     * retcode : 2000
     * msg : 收藏的景区信息获取成功
     * data : [{"senic_id":"15","thumbnail":"/Uploads/Picture/2016-03-04/620fe469fc8.jpg","name":"嵖岈山","price":"70.00","level_name":"5A级景区","cate_name":"自然风光","templete":1},{"senic_id":"18","thumbnail":"/Uploads/Picture/2016-08-23/6516b876832.jpg","name":"重渡沟","price":"100.00","level_name":"4A级景区","cate_name":"自然风光","templete":1}]
     */

    private int retcode;
    private String msg;
    /**
     * senic_id : 15
     * thumbnail : /Uploads/Picture/2016-03-04/620fe469fc8.jpg
     * name : 嵖岈山
     * price : 70.00
     * level_name : 5A级景区
     * cate_name : 自然风光
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
