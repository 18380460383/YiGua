package com.qhzlwh.yigua.bean;

import java.util.List;

/**
 * 创建者：Administrator
 * 时间：2016/9/5
 * 功能描述：全部订单信息
 */
public class SenicOrderBean {


    /**
     * retcode : 2000
     * msg : 订单信息获取成功
     * data : [{"order_id":"41","status":"0","order_sn":"T20160913ECD4A12A343","number":"3","contact_name":"123.","unit_price":"34.00","telephone":"18080808080","coupon_id":"0","come_date":"2016-09-30","dateline":"2016-09-13","platOrderNo":"","list_info":[{"come_date":"2016-09-30","name":"123.","telephone":"","id_card":"513033196802031234"}],"senic_id":"123","amount":10200,"price":"102.00","senic_name":"兰州-青海湖-塔尔班-成都","senic_address":"兰州市西津西路3号","travel_days":"0","coupon":[],"order_type":"3","type":"3","type_name":"线路游","status_name":"待支付"},{"order_id":"40","status":"0","order_sn":"T20160913A2E74DEA10C","number":"1","contact_name":"123","unit_price":"34.00","telephone":"18080808080","coupon_id":"0","come_date":"2016-10-01","dateline":"2016-09-13","platOrderNo":"","list_info":[{"come_date":"2016-10-01","name":"123","telephone":"","id_card":"513033196802031234"}],"senic_id":"123","amount":3400,"price":"34.00","senic_name":"兰州-青海湖-塔尔班-成都","senic_address":"兰州市西津西路3号","travel_days":"0","coupon":[],"order_type":"3","type":"3","type_name":"线路游","status_name":"待支付"},{"order_id":"39","status":"0","order_sn":"T20160913BF7690A2734","number":"1","contact_name":"热","unit_price":"60.00","telephone":"18080808080","coupon_id":"0","come_date":"2016-09-23","dateline":"2016-09-13","platOrderNo":"","list_info":[{"come_date":"2016-09-23","name":"热","telephone":"","id_card":"513033196802031234"}],"senic_id":"18","amount":6000,"price":"60.00","senic_name":"重渡沟","senic_address":" 洛阳市栾川县潭头镇西南部重渡沟风景区","coupon":[],"order_type":"1","type":"1","type_name":"门票","admission_way":"工作人员接待进入","status_name":"待支付"},{"order_id":"21","status":"0","order_sn":"T20160912E6D08054344","number":"2","contact_name":"","unit_price":"60.00","telephone":"18080808080","coupon_id":"0","come_date":"2016-10-08","dateline":"2016-09-12","platOrderNo":"","list_info":[{"come_date":"2016-10-08","name":"而已","telephone":"","id_card":"513033196907071234"}],"senic_id":"18","amount":12000,"price":"120.00","senic_name":"重渡沟","senic_address":" 洛阳市栾川县潭头镇西南部重渡沟风景区","coupon":[],"order_type":"1","type":"1","type_name":"门票","admission_way":"工作人员接待进入","status_name":"待支付"},{"order_id":"20","status":"0","order_sn":"T20160912486D97D30AF","number":"2","contact_name":"","unit_price":"60.00","telephone":"18080808080","coupon_id":"0","come_date":"2016-10-08","dateline":"2016-09-12","platOrderNo":"","list_info":[{"come_date":"2016-10-08","name":"而已","telephone":"","id_card":"513033196907071234"}],"senic_id":"18","amount":12000,"price":"120.00","senic_name":"重渡沟","senic_address":" 洛阳市栾川县潭头镇西南部重渡沟风景区","coupon":[],"order_type":"1","type":"1","type_name":"门票","admission_way":"工作人员接待进入","status_name":"待支付"},{"order_id":"19","status":"0","order_sn":"T20160912BC60ED5DF9C","number":"2","contact_name":"","unit_price":"60.00","telephone":"18080808080","coupon_id":"0","come_date":"2016-10-08","dateline":"2016-09-12","platOrderNo":"","list_info":[{"come_date":"2016-10-08","name":"而已","telephone":"","id_card":"513033196907071234"}],"senic_id":"18","amount":12000,"price":"120.00","senic_name":"重渡沟","senic_address":" 洛阳市栾川县潭头镇西南部重渡沟风景区","coupon":[],"order_type":"1","type":"1","type_name":"门票","admission_way":"工作人员接待进入","status_name":"待支付"},{"order_id":"16","status":"0","order_sn":"T20160912C36E28AEB57","number":"1","contact_name":"","unit_price":"60.00","telephone":"13594015206","coupon_id":"0","come_date":"2016-09-13","dateline":"2016-09-12","platOrderNo":"","list_info":[{"come_date":"2016-09-13","name":"23124","telephone":"13594015206","id_card":"510623199209234234"}],"senic_id":"18","amount":6000,"price":"60.00","senic_name":"重渡沟","senic_address":" 洛阳市栾川县潭头镇西南部重渡沟风景区","coupon":[],"order_type":"1","type":"1","type_name":"门票","admission_way":"工作人员接待进入","status_name":"待支付"},{"order_id":"14","status":"0","order_sn":"T201609120A40F010423","number":"1","contact_name":"","unit_price":"60.00","telephone":"13594015206","coupon_id":"0","come_date":"2016-09-13","dateline":"2016-09-12","platOrderNo":"","list_info":[{"come_date":"2016-09-13","name":"890809","telephone":"13594015206","id_card":"510623199209234234"}],"senic_id":"18","amount":6000,"price":"60.00","senic_name":"重渡沟","senic_address":" 洛阳市栾川县潭头镇西南部重渡沟风景区","coupon":[],"order_type":"1","type":"1","type_name":"门票","admission_way":"工作人员接待进入","status_name":"待支付"},{"order_id":"13","status":"0","order_sn":"T2016091230F3909DB3C","number":"2","contact_name":"","unit_price":"60.00","telephone":"18080808080","coupon_id":"0","come_date":"2016-09-30","dateline":"2016-09-12","platOrderNo":"","list_info":[{"come_date":"2016-09-30","name":"而已","telephone":"","id_card":"513033196907071234"}],"senic_id":"18","amount":12000,"price":"120.00","senic_name":"重渡沟","senic_address":" 洛阳市栾川县潭头镇西南部重渡沟风景区","coupon":[],"order_type":"1","type":"1","type_name":"门票","admission_way":"工作人员接待进入","status_name":"待支付"},{"order_id":"9","status":"0","order_sn":"T2016091264D7BB05B57","number":"3","contact_name":"","unit_price":"60.00","telephone":"13594015206","coupon_id":"0","come_date":"2016-09-13","dateline":"2016-09-12","platOrderNo":"","list_info":[{"come_date":"2016-09-13","name":"123123","telephone":"13594015206","id_card":"510623199209234234"}],"senic_id":"18","amount":18000,"price":"180.00","senic_name":"重渡沟","senic_address":" 洛阳市栾川县潭头镇西南部重渡沟风景区","coupon":[],"order_type":"1","type":"1","type_name":"门票","admission_way":"工作人员接待进入","status_name":"待支付"}]
     */

    private int retcode;
    private String msg;
    /**
     * order_id : 41
     * status : 0
     * order_sn : T20160913ECD4A12A343
     * number : 3
     * contact_name : 123.
     * unit_price : 34.00
     * telephone : 18080808080
     * coupon_id : 0
     * come_date : 2016-09-30
     * dateline : 2016-09-13
     * platOrderNo :
     * list_info : [{"come_date":"2016-09-30","name":"123.","telephone":"","id_card":"513033196802031234"}]
     * senic_id : 123
     * amount : 10200
     * price : 102.00
     * senic_name : 兰州-青海湖-塔尔班-成都
     * senic_address : 兰州市西津西路3号
     * travel_days : 0
     * coupon : []
     * order_type : 3
     * type : 3
     * type_name : 线路游
     * status_name : 待支付
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
        private String order_id;
        private String status;
        private String order_sn;
        private String number;
        private String contact_name;
        private String unit_price;
        private String telephone;
        private String coupon_id;
        private String come_date;
        private String dateline;
        private String platOrderNo;
        private String senic_id;
        private int amount;
        private String price;
        private String senic_name;
        private String senic_address;
        private String travel_days;
        private String order_type;
        private String type;
        private String type_name;
        private String status_name;
        private String admission_way;

        public String getAdmission_way() {
            return admission_way;
        }

        public void setAdmission_way(String admission_way) {
            this.admission_way = admission_way;
        }

        /**
         * come_date : 2016-09-30
         * name : 123.
         * telephone :
         * id_card : 513033196802031234
         */

        private List<ListInfoBean> list_info;
        private List<?> coupon;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getContact_name() {
            return contact_name;
        }

        public void setContact_name(String contact_name) {
            this.contact_name = contact_name;
        }

        public String getUnit_price() {
            return unit_price;
        }

        public void setUnit_price(String unit_price) {
            this.unit_price = unit_price;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getCoupon_id() {
            return coupon_id;
        }

        public void setCoupon_id(String coupon_id) {
            this.coupon_id = coupon_id;
        }

        public String getCome_date() {
            return come_date;
        }

        public void setCome_date(String come_date) {
            this.come_date = come_date;
        }

        public String getDateline() {
            return dateline;
        }

        public void setDateline(String dateline) {
            this.dateline = dateline;
        }

        public String getPlatOrderNo() {
            return platOrderNo;
        }

        public void setPlatOrderNo(String platOrderNo) {
            this.platOrderNo = platOrderNo;
        }

        public String getSenic_id() {
            return senic_id;
        }

        public void setSenic_id(String senic_id) {
            this.senic_id = senic_id;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSenic_name() {
            return senic_name;
        }

        public void setSenic_name(String senic_name) {
            this.senic_name = senic_name;
        }

        public String getSenic_address() {
            return senic_address;
        }

        public void setSenic_address(String senic_address) {
            this.senic_address = senic_address;
        }

        public String getTravel_days() {
            return travel_days;
        }

        public void setTravel_days(String travel_days) {
            this.travel_days = travel_days;
        }

        public String getOrder_type() {
            return order_type;
        }

        public void setOrder_type(String order_type) {
            this.order_type = order_type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getStatus_name() {
            return status_name;
        }

        public void setStatus_name(String status_name) {
            this.status_name = status_name;
        }

        public List<ListInfoBean> getList_info() {
            return list_info;
        }

        public void setList_info(List<ListInfoBean> list_info) {
            this.list_info = list_info;
        }

        public List<?> getCoupon() {
            return coupon;
        }

        public void setCoupon(List<?> coupon) {
            this.coupon = coupon;
        }

        public static class ListInfoBean {
            private String come_date;
            private String name;
            private String telephone;
            private String id_card;

            public String getCome_date() {
                return come_date;
            }

            public void setCome_date(String come_date) {
                this.come_date = come_date;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getId_card() {
                return id_card;
            }

            public void setId_card(String id_card) {
                this.id_card = id_card;
            }
        }
    }
}
