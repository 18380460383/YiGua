package com.qhzlwh.yigua.bean;

import java.util.List;

/**
 * Created by FuPei on 2016/9/14.
 */
public class SenicTicketChoice {


    /**
     * retcode : 2000
     * msg : 获取成功
     * data : {"senic_id":18,"tickets":[{"ticket_id":"160","senic_id":"18","uid":"27","name":"重渡沟","number":"1","ticket_type":"1","scan_num":"0","senic_scan_num":"6","come_date":"1473782400","ticket_type_name":"折扣票","bgcolor":"fc5aa0","is_senic":1}]}
     */

    private int retcode;
    private String msg;
    /**
     * senic_id : 18
     * tickets : [{"ticket_id":"160","senic_id":"18","uid":"27","name":"重渡沟","number":"1","ticket_type":"1","scan_num":"0","senic_scan_num":"6","come_date":"1473782400","ticket_type_name":"折扣票","bgcolor":"fc5aa0","is_senic":1}]
     */

    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int senic_id;
        /**
         * ticket_id : 160
         * senic_id : 18
         * uid : 27
         * name : 重渡沟
         * number : 1
         * ticket_type : 1
         * scan_num : 0
         * senic_scan_num : 6
         * come_date : 1473782400
         * ticket_type_name : 折扣票
         * bgcolor : fc5aa0
         * is_senic : 1
         */

        private List<TicketsBean> tickets;

        public int getSenic_id() {
            return senic_id;
        }

        public void setSenic_id(int senic_id) {
            this.senic_id = senic_id;
        }

        public List<TicketsBean> getTickets() {
            return tickets;
        }

        public void setTickets(List<TicketsBean> tickets) {
            this.tickets = tickets;
        }

        public static class TicketsBean {
            private String ticket_id;
            private String senic_id;
            private String uid;
            private String name;
            private String number;
            private String ticket_type;
            private String scan_num;
            private String senic_scan_num;
            private String come_date;
            private String ticket_type_name;
            private String bgcolor;
            private int is_senic;

            public String getTicket_id() {
                return ticket_id;
            }

            public void setTicket_id(String ticket_id) {
                this.ticket_id = ticket_id;
            }

            public String getSenic_id() {
                return senic_id;
            }

            public void setSenic_id(String senic_id) {
                this.senic_id = senic_id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getTicket_type() {
                return ticket_type;
            }

            public void setTicket_type(String ticket_type) {
                this.ticket_type = ticket_type;
            }

            public String getScan_num() {
                return scan_num;
            }

            public void setScan_num(String scan_num) {
                this.scan_num = scan_num;
            }

            public String getSenic_scan_num() {
                return senic_scan_num;
            }

            public void setSenic_scan_num(String senic_scan_num) {
                this.senic_scan_num = senic_scan_num;
            }

            public String getCome_date() {
                return come_date;
            }

            public void setCome_date(String come_date) {
                this.come_date = come_date;
            }

            public String getTicket_type_name() {
                return ticket_type_name;
            }

            public void setTicket_type_name(String ticket_type_name) {
                this.ticket_type_name = ticket_type_name;
            }

            public String getBgcolor() {
                return bgcolor;
            }

            public void setBgcolor(String bgcolor) {
                this.bgcolor = bgcolor;
            }

            public int getIs_senic() {
                return is_senic;
            }

            public void setIs_senic(int is_senic) {
                this.is_senic = is_senic;
            }
        }
    }
}
