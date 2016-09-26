package com.qhzlwh.yigua.bean;

import java.util.List;

/**
 * 创建者：Administrator
 * 时间：2016/8/30
 * 功能描述：自驾游
 */
public class TourSelf {


    /**
     * retcode : 2000
     * msg : 获取成功！
     * data : [{"senic_id":"1","name":"白云山","long_title":"中国最美的地方","image":"/Uploads/Picture/2016-01-26/56a6e55455683.jpg","sound":"","price":"80.00","map":"","ticket":"成人票：80元/人 A.免费政策：1.4米以下免大门票（1.4米以下不占座位儿童免换乘票）；70岁以上的老人凭老年证免大门票；现役军人持军官证免大门票。 B.优惠政策：60岁\u201469周岁的老人持老年证购大门票40元/每人；嵩县居民凭有效身份证门票20元/每人、换乘观光车25元/每人（法定节假日、周末除外）。","traffic":"自驾： 郑州（洛界高速）－登封（郑少洛高速）伊川－－陆浑（嵩县）－白云山 郑州→登封→伊川→嵩县→栾川合峪镇→白云山 开封→洛阳→伊川→嵩县→栾川合峪镇→白云山 公交路线： 洛阳汽车站每天7：30分，13：30 ，各有一班直达车前往白云山风景区。票价：44元。车程约4.5小时。 另外也可以乘坐洛阳至车村的客车，票价31元，到白云山景区路口下车，然后公交或打车到景区门口。 返回洛阳市时景区内有两班车，12:30和13:30，票价45元。一般人比较多，提前到会比较好。","short_description":"洛阳白云山位于河南省洛阳市嵩县南部世界地质公园伏牛山腹地，是国家森林公园、国家级自然保护区、国家AAAAA级旅游景区、中国十佳休闲胜地，被誉为\u201c人间仙境\u201d、\u201c中原名山\u201d。2005年被评为\u201c中国最美的地方\u201d。2010年晋升世界地质公园。","open":"全年开放 08:00-18:00","address":"洛阳市嵩县车村镇","city_id":"1014","telephone":"0379-66590127","tour_name":"青年旅行社","long_description":"洛阳市嵩县车村镇","attribute_id":"21","time":1472614133,"level":"5A级景区","ticket_notice":"购票说明","tickets":[{"ticket_id":"12","ticket_name":"白云山门票","type":"成人票","ticket_price":"81.00","ticket_yprice":"0.00","tips":"","ticket_type":"1","ticket_number":"0","ticket_description":"","ticket_type_name":"折扣票"}]}]
     */

    private int retcode;
    private String msg;
    /**
     * senic_id : 1
     * name : 白云山
     * long_title : 中国最美的地方
     * image : /Uploads/Picture/2016-01-26/56a6e55455683.jpg
     * sound :
     * price : 80.00
     * map :
     * ticket : 成人票：80元/人 A.免费政策：1.4米以下免大门票（1.4米以下不占座位儿童免换乘票）；70岁以上的老人凭老年证免大门票；现役军人持军官证免大门票。 B.优惠政策：60岁—69周岁的老人持老年证购大门票40元/每人；嵩县居民凭有效身份证门票20元/每人、换乘观光车25元/每人（法定节假日、周末除外）。
     * traffic : 自驾： 郑州（洛界高速）－登封（郑少洛高速）伊川－－陆浑（嵩县）－白云山 郑州→登封→伊川→嵩县→栾川合峪镇→白云山 开封→洛阳→伊川→嵩县→栾川合峪镇→白云山 公交路线： 洛阳汽车站每天7：30分，13：30 ，各有一班直达车前往白云山风景区。票价：44元。车程约4.5小时。 另外也可以乘坐洛阳至车村的客车，票价31元，到白云山景区路口下车，然后公交或打车到景区门口。 返回洛阳市时景区内有两班车，12:30和13:30，票价45元。一般人比较多，提前到会比较好。
     * short_description : 洛阳白云山位于河南省洛阳市嵩县南部世界地质公园伏牛山腹地，是国家森林公园、国家级自然保护区、国家AAAAA级旅游景区、中国十佳休闲胜地，被誉为“人间仙境”、“中原名山”。2005年被评为“中国最美的地方”。2010年晋升世界地质公园。
     * open : 全年开放 08:00-18:00
     * address : 洛阳市嵩县车村镇
     * city_id : 1014
     * telephone : 0379-66590127
     * tour_name : 青年旅行社
     * long_description : 洛阳市嵩县车村镇
     * attribute_id : 21
     * time : 1472614133
     * level : 5A级景区
     * ticket_notice : 购票说明
     * tickets : [{"ticket_id":"12","ticket_name":"白云山门票","type":"成人票","ticket_price":"81.00","ticket_yprice":"0.00","tips":"","ticket_type":"1","ticket_number":"0","ticket_description":"","ticket_type_name":"折扣票"}]
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
        private String name;
        private String long_title;
        private String image;
        private String sound;
        private String price;
        private String map;
        private String ticket;
        private String traffic;
        private String short_description;
        private String open;
        private String address;
        private String city_id;
        private String telephone;
        private String tour_name;
        private String long_description;
        private String attribute_id;
        private int time;
        private String level;
        private String ticket_notice;
        private String share_app;

        public String getShare_app() {
            return share_app;
        }

        public void setShare_app(String share_app) {
            this.share_app = share_app;
        }

        /**
         * ticket_id : 12
         * ticket_name : 白云山门票
         * type : 成人票
         * ticket_price : 81.00
         * ticket_yprice : 0.00
         * tips :
         * ticket_type : 1
         * ticket_number : 0
         * ticket_description :
         * ticket_type_name : 折扣票
         */


        private List<TicketsEntity> tickets;

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

        public String getLong_title() {
            return long_title;
        }

        public void setLong_title(String long_title) {
            this.long_title = long_title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getSound() {
            return sound;
        }

        public void setSound(String sound) {
            this.sound = sound;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getMap() {
            return map;
        }

        public void setMap(String map) {
            this.map = map;
        }

        public String getTicket() {
            return ticket;
        }

        public void setTicket(String ticket) {
            this.ticket = ticket;
        }

        public String getTraffic() {
            return traffic;
        }

        public void setTraffic(String traffic) {
            this.traffic = traffic;
        }

        public String getShort_description() {
            return short_description;
        }

        public void setShort_description(String short_description) {
            this.short_description = short_description;
        }

        public String getOpen() {
            return open;
        }

        public void setOpen(String open) {
            this.open = open;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getTour_name() {
            return tour_name;
        }

        public void setTour_name(String tour_name) {
            this.tour_name = tour_name;
        }

        public String getLong_description() {
            return long_description;
        }

        public void setLong_description(String long_description) {
            this.long_description = long_description;
        }

        public String getAttribute_id() {
            return attribute_id;
        }

        public void setAttribute_id(String attribute_id) {
            this.attribute_id = attribute_id;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getTicket_notice() {
            return ticket_notice;
        }

        public void setTicket_notice(String ticket_notice) {
            this.ticket_notice = ticket_notice;
        }

        public List<TicketsEntity> getTickets() {
            return tickets;
        }

        public void setTickets(List<TicketsEntity> tickets) {
            this.tickets = tickets;
        }

        public static class TicketsEntity {
            private String ticket_id;
            private String ticket_name;
            private String type;
            private String ticket_price;
            private String ticket_yprice;
            private String tips;
            private String ticket_type;
            private String ticket_number;
            private String ticket_description;
            private String ticket_type_name;

            public String getTicket_id() {
                return ticket_id;
            }

            public void setTicket_id(String ticket_id) {
                this.ticket_id = ticket_id;
            }

            public String getTicket_name() {
                return ticket_name;
            }

            public void setTicket_name(String ticket_name) {
                this.ticket_name = ticket_name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTicket_price() {
                return ticket_price;
            }

            public void setTicket_price(String ticket_price) {
                this.ticket_price = ticket_price;
            }

            public String getTicket_yprice() {
                return ticket_yprice;
            }

            public void setTicket_yprice(String ticket_yprice) {
                this.ticket_yprice = ticket_yprice;
            }

            public String getTips() {
                return tips;
            }

            public void setTips(String tips) {
                this.tips = tips;
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

            public String getTicket_description() {
                return ticket_description;
            }

            public void setTicket_description(String ticket_description) {
                this.ticket_description = ticket_description;
            }

            public String getTicket_type_name() {
                return ticket_type_name;
            }

            public void setTicket_type_name(String ticket_type_name) {
                this.ticket_type_name = ticket_type_name;
            }
        }
    }
}
