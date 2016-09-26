package com.qhzlwh.yigua.bean;

/**
 * Created by peter on 2016/8/27.
 */
public class LineTourOrSelfDrivingList {

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
