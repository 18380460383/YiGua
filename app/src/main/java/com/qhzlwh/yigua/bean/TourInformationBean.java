package com.qhzlwh.yigua.bean;

import java.util.List;

/**
 * 创建者：Administrator
 * 时间：2016/8/30
 * 功能描述：线程游行程
 */
public class TourInformationBean {
    private int retcode;
    private String msg;
    private List<DataEntity> data;

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getRetcode() {
        return retcode;
    }

    public String getMsg() {
        return msg;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {

        /**
         * trip_id : 1
         * trip_day : 第一天
         * location_point : 兰州汽车客运中心集合
         * starting_point : 兰州
         * destination_point : 成都
         * location_time : 8:30
         * starting_time : 9:30
         * destination_time : 15:30
         * trip_description : 行驶：约5公里/约5小时10分钟 游览时间2小时
         * hotel_id : 9
         * hotel_name : 禅修国际饭店
         */

        private String trip_id;
        private String trip_day;
        private String location_point;
        private String starting_point;
        private String destination_point;
        private String location_time;
        private String starting_time;
        private String destination_time;
        private String trip_description;
        private String hotel_id;
        private String hotel_name;

        public String getTrip_id() {
            return trip_id;
        }

        public void setTrip_id(String trip_id) {
            this.trip_id = trip_id;
        }

        public String getTrip_day() {
            return trip_day;
        }

        public void setTrip_day(String trip_day) {
            this.trip_day = trip_day;
        }

        public String getLocation_point() {
            return location_point;
        }

        public void setLocation_point(String location_point) {
            this.location_point = location_point;
        }

        public String getStarting_point() {
            return starting_point;
        }

        public void setStarting_point(String starting_point) {
            this.starting_point = starting_point;
        }

        public String getDestination_point() {
            return destination_point;
        }

        public void setDestination_point(String destination_point) {
            this.destination_point = destination_point;
        }

        public String getLocation_time() {
            return location_time;
        }

        public void setLocation_time(String location_time) {
            this.location_time = location_time;
        }

        public String getStarting_time() {
            return starting_time;
        }

        public void setStarting_time(String starting_time) {
            this.starting_time = starting_time;
        }

        public String getDestination_time() {
            return destination_time;
        }

        public void setDestination_time(String destination_time) {
            this.destination_time = destination_time;
        }

        public String getTrip_description() {
            return trip_description;
        }

        public void setTrip_description(String trip_description) {
            this.trip_description = trip_description;
        }

        public String getHotel_id() {
            return hotel_id;
        }

        public void setHotel_id(String hotel_id) {
            this.hotel_id = hotel_id;
        }

        public String getHotel_name() {
            return hotel_name;
        }

        public void setHotel_name(String hotel_name) {
            this.hotel_name = hotel_name;
        }
    }
}
