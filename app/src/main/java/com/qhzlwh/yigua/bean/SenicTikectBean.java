package com.qhzlwh.yigua.bean;

import java.util.List;

/**
 * 创建者：Administrator
 * 时间：2016/9/2
 * 功能描述：订单中的票信息
 */
public class SenicTikectBean {

    /**
     * retcode : 2000
     * msg : 门票信息获取成功！
     * data : {"ticket_id":"159","ticket_type":"1","ticket_number":"0","ticket_number_buy":"0","ticket_yprice":"115.00","ticket_price":"120.00","attribute_id":"0","order_type":1,"day_desc":"请选择指定日期使用，逾期作废。","day_use":"指定日期","day_arr":[{"is_buy":1,"day_stamp":1472745600,"day":"2016-09-02","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1472832000,"day":"2016-09-03","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1472918400,"day":"2016-09-04","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1473004800,"day":"2016-09-05","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1473091200,"day":"2016-09-06","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1473177600,"day":"2016-09-07","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1473264000,"day":"2016-09-08","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1473350400,"day":"2016-09-09","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1473436800,"day":"2016-09-10","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1473523200,"day":"2016-09-11","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1473609600,"day":"2016-09-12","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1473696000,"day":"2016-09-13","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1473782400,"day":"2016-09-14","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1473868800,"day":"2016-09-15","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1473955200,"day":"2016-09-16","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1474041600,"day":"2016-09-17","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1474128000,"day":"2016-09-18","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1474214400,"day":"2016-09-19","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1474300800,"day":"2016-09-20","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1474387200,"day":"2016-09-21","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1474473600,"day":"2016-09-22","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1474560000,"day":"2016-09-23","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1474646400,"day":"2016-09-24","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1474732800,"day":"2016-09-25","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1474819200,"day":"2016-09-26","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1474905600,"day":"2016-09-27","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1474992000,"day":"2016-09-28","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1475078400,"day":"2016-09-29","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1475164800,"day":"2016-09-30","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1475251200,"day":"2016-10-01","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1475337600,"day":"2016-10-02","ticket":999,"price":"115.00"}]}
     */

    private int retcode;
    private String msg;
    /**
     * ticket_id : 159
     * ticket_type : 1
     * ticket_number : 0
     * ticket_number_buy : 0
     * ticket_yprice : 115.00
     * ticket_price : 120.00
     * attribute_id : 0
     * order_type : 1
     * day_desc : 请选择指定日期使用，逾期作废。
     * day_use : 指定日期
     * day_arr : [{"is_buy":1,"day_stamp":1472745600,"day":"2016-09-02","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1472832000,"day":"2016-09-03","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1472918400,"day":"2016-09-04","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1473004800,"day":"2016-09-05","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1473091200,"day":"2016-09-06","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1473177600,"day":"2016-09-07","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1473264000,"day":"2016-09-08","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1473350400,"day":"2016-09-09","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1473436800,"day":"2016-09-10","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1473523200,"day":"2016-09-11","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1473609600,"day":"2016-09-12","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1473696000,"day":"2016-09-13","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1473782400,"day":"2016-09-14","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1473868800,"day":"2016-09-15","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1473955200,"day":"2016-09-16","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1474041600,"day":"2016-09-17","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1474128000,"day":"2016-09-18","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1474214400,"day":"2016-09-19","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1474300800,"day":"2016-09-20","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1474387200,"day":"2016-09-21","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1474473600,"day":"2016-09-22","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1474560000,"day":"2016-09-23","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1474646400,"day":"2016-09-24","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1474732800,"day":"2016-09-25","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1474819200,"day":"2016-09-26","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1474905600,"day":"2016-09-27","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1474992000,"day":"2016-09-28","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1475078400,"day":"2016-09-29","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1475164800,"day":"2016-09-30","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1475251200,"day":"2016-10-01","ticket":999,"price":"115.00"},{"is_buy":1,"day_stamp":1475337600,"day":"2016-10-02","ticket":999,"price":"115.00"}]
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
        private String ticket_id;
        private String ticket_type;
        private String ticket_number;
        private String ticket_number_buy;
        private String ticket_yprice;
        private String ticket_price;
        private String attribute_id;
        private int order_type;
        private String day_desc;
        private String day_use;
        /**
         * is_buy : 1
         * day_stamp : 1472745600
         * day : 2016-09-02
         * ticket : 999
         * price : 115.00
         */

        private List<DayArrEntity> day_arr;

        public String getTicket_id() {
            return ticket_id;
        }

        public void setTicket_id(String ticket_id) {
            this.ticket_id = ticket_id;
        }

        public String getTicket_type() {
            return ticket_type;
        }

        public void setTicket_type(String ticket_type) {
            this.ticket_type = ticket_type;
        }

        public String getTicket_number() {
            return ticket_number;
        }

        public void setTicket_number(String ticket_number) {
            this.ticket_number = ticket_number;
        }

        public String getTicket_number_buy() {
            return ticket_number_buy;
        }

        public void setTicket_number_buy(String ticket_number_buy) {
            this.ticket_number_buy = ticket_number_buy;
        }

        public String getTicket_yprice() {
            return ticket_yprice;
        }

        public void setTicket_yprice(String ticket_yprice) {
            this.ticket_yprice = ticket_yprice;
        }

        public String getTicket_price() {
            return ticket_price;
        }

        public void setTicket_price(String ticket_price) {
            this.ticket_price = ticket_price;
        }

        public String getAttribute_id() {
            return attribute_id;
        }

        public void setAttribute_id(String attribute_id) {
            this.attribute_id = attribute_id;
        }

        public int getOrder_type() {
            return order_type;
        }

        public void setOrder_type(int order_type) {
            this.order_type = order_type;
        }

        public String getDay_desc() {
            return day_desc;
        }

        public void setDay_desc(String day_desc) {
            this.day_desc = day_desc;
        }

        public String getDay_use() {
            return day_use;
        }

        public void setDay_use(String day_use) {
            this.day_use = day_use;
        }

        public List<DayArrEntity> getDay_arr() {
            return day_arr;
        }

        public void setDay_arr(List<DayArrEntity> day_arr) {
            this.day_arr = day_arr;
        }

        public static class DayArrEntity {
            private int is_buy;
            private int day_stamp;
            private String day;
            private int ticket;
            private String price;

            public int getIs_buy() {
                return is_buy;
            }

            public void setIs_buy(int is_buy) {
                this.is_buy = is_buy;
            }

            public int getDay_stamp() {
                return day_stamp;
            }

            public void setDay_stamp(int day_stamp) {
                this.day_stamp = day_stamp;
            }

            public String getDay() {
                return day;
            }

            public void setDay(String day) {
                this.day = day;
            }

            public int getTicket() {
                return ticket;
            }

            public void setTicket(int ticket) {
                this.ticket = ticket;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }
        }
    }
}
