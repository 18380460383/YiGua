package com.qhzlwh.yigua.bean;

/**
 * 创建者：Administrator
 * 时间：2016/9/2
 * 功能描述：
 */
public class SenicOrderMakeBean {

    /**
     * retcode : 2000
     * msg : 订单创建成功
     * data : {"order_id":176,"price":345,"order_sn":"T20160902ADCEE44F9A7","amount":34500}
     */

    private int retcode;
    private String msg;
    /**
     * order_id : 176
     * price : 345
     * order_sn : T20160902ADCEE44F9A7
     * amount : 34500
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
        private int order_id;
        private int price;
        private String order_sn;
        private int amount;

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }
    }
}
