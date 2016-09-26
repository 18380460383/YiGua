package com.qhzlwh.yigua.bean;

import java.util.List;

/**
 * 创建者：Administrator
 * 时间：2016/9/2
 * 功能描述：景区下的美食 特产
 */
public class SenicGoodBean {

    /**
     * retcode : 2000
     * msg : 景区特产信息获取成功
     * data : {"product":[{"product_id":"1","cate_id":"6","product_name":"皮蛋","short_description":"一般也叫变蛋或者是皮蛋，是修武县的特产。","long_description":"","thumbnail":"http://qh.91chuanbo.cn//Uploads/Picture/2016-01-30/d33acc3e809.png","image":"http://qh.91chuanbo.cn//Uploads/Picture/2016-01-30/56ac6cf15c4d6.png","price":"8.00","unit":"个","fee":"10.00","cate_name":" 绿色环保"}],"shop":[{"cate_id":"9","short_description":"焦作柿饼","long_description":"","thumbnail":"http://qh.91chuanbo.cn//Uploads/Picture/2016-01-30/c681740248a.png","image":"http://qh.91chuanbo.cn//Uploads/Picture/2016-01-30/56ac6dc0447b2.png","cate_name":"风味小吃","product_name":"焦作柿饼","product_id":"11","price":0,"unit":"","fee":0}]}
     */

    private int retcode;
    private String msg;
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
        /**
         * product_id : 1
         * cate_id : 6
         * product_name : 皮蛋
         * short_description : 一般也叫变蛋或者是皮蛋，是修武县的特产。
         * long_description :
         * thumbnail : http://qh.91chuanbo.cn//Uploads/Picture/2016-01-30/d33acc3e809.png
         * image : http://qh.91chuanbo.cn//Uploads/Picture/2016-01-30/56ac6cf15c4d6.png
         * price : 8.00
         * unit : 个
         * fee : 10.00
         * cate_name :  绿色环保
         */

        private List<ProductEntity> product;
        /**
         * cate_id : 9
         * short_description : 焦作柿饼
         * long_description :
         * thumbnail : http://qh.91chuanbo.cn//Uploads/Picture/2016-01-30/c681740248a.png
         * image : http://qh.91chuanbo.cn//Uploads/Picture/2016-01-30/56ac6dc0447b2.png
         * cate_name : 风味小吃
         * product_name : 焦作柿饼
         * product_id : 11
         * price : 0
         * unit :
         * fee : 0
         */

        private List<ShopEntity> shop;

        public List<ProductEntity> getProduct() {
            return product;
        }

        public void setProduct(List<ProductEntity> product) {
            this.product = product;
        }

        public List<ShopEntity> getShop() {
            return shop;
        }

        public void setShop(List<ShopEntity> shop) {
            this.shop = shop;
        }

        public static class ProductEntity {
            private String product_id;
            private String cate_id;
            private String product_name;
            private String short_description;
            private String long_description;
            private String thumbnail;
            private String image;
            private String price;
            private String unit;
            private String fee;
            private String cate_name;

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getCate_id() {
                return cate_id;
            }

            public void setCate_id(String cate_id) {
                this.cate_id = cate_id;
            }

            public String getProduct_name() {
                return product_name;
            }

            public void setProduct_name(String product_name) {
                this.product_name = product_name;
            }

            public String getShort_description() {
                return short_description;
            }

            public void setShort_description(String short_description) {
                this.short_description = short_description;
            }

            public String getLong_description() {
                return long_description;
            }

            public void setLong_description(String long_description) {
                this.long_description = long_description;
            }

            public String getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getFee() {
                return fee;
            }

            public void setFee(String fee) {
                this.fee = fee;
            }

            public String getCate_name() {
                return cate_name;
            }

            public void setCate_name(String cate_name) {
                this.cate_name = cate_name;
            }
        }

        public static class ShopEntity {
            private String cate_id;
            private String short_description;
            private String long_description;
            private String thumbnail;
            private String image;
            private String cate_name;
            private String product_name;
            private String product_id;
            private int price;
            private String unit;
            private int fee;

            public String getCate_id() {
                return cate_id;
            }

            public void setCate_id(String cate_id) {
                this.cate_id = cate_id;
            }

            public String getShort_description() {
                return short_description;
            }

            public void setShort_description(String short_description) {
                this.short_description = short_description;
            }

            public String getLong_description() {
                return long_description;
            }

            public void setLong_description(String long_description) {
                this.long_description = long_description;
            }

            public String getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getCate_name() {
                return cate_name;
            }

            public void setCate_name(String cate_name) {
                this.cate_name = cate_name;
            }

            public String getProduct_name() {
                return product_name;
            }

            public void setProduct_name(String product_name) {
                this.product_name = product_name;
            }

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public int getFee() {
                return fee;
            }

            public void setFee(int fee) {
                this.fee = fee;
            }
        }
    }
}
