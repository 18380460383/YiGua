package com.qhzlwh.yigua.bean;

import java.util.List;

/**
 * 创建者：Administrator
 * 时间：2016/9/1
 * 功能描述：我的评论
 */
public class SenicCommentBean {

    /**
     * retcode : 2000
     * msg : 评论信息获取成功
     * data : [{"name":"重渡沟","long_title":"既有重渡沟 何须下江南","short_title":"水乡竹韵","attribute_id":"0","price":"100.00","thumbnail":"/Uploads/Picture/2016-08-23/6516b876832.jpg","senic_id":"18","stars":"5","description":"玩得很开心","dateline":"2016年02月06日 23:44","traffic_stars":{"name":"一般","stars_type":1,"stars_color":"#ff9933"},"hotel_stars":{"name":"一般","stars_type":1,"stars_color":"#ff9933"},"leader_stars":{"name":"一般","stars_type":1,"stars_color":"#ff9933"},"images":[]},{"name":"兰州-青海湖-塔尔班-成都","long_title":"兰州 青海湖 茶卡 敦煌 嘉峪关 张掖 西宁双飞7日跟团游","short_title":"双飞九日跟团游","attribute_id":"21","price":"10.00","thumbnail":"/Uploads/Picture/2016-08-26/b25fc9f34ba.jpg","senic_id":"123","stars":"5","description":"好好干,兰州 青海湖 茶卡 敦煌 嘉峪关 张掖 西宁双飞7日跟团游","dateline":"2016年02月26日 14:08","traffic_stars":{"name":"一般","stars_type":1,"stars_color":"#ff9933"},"hotel_stars":{"name":"一般","stars_type":1,"stars_color":"#ff9933"},"leader_stars":{"name":"一般","stars_type":1,"stars_color":"#ff9933"},"images":[]}]
     */

    private int retcode;
    private String msg;
    /**
     * name : 重渡沟
     * long_title : 既有重渡沟 何须下江南
     * short_title : 水乡竹韵
     * attribute_id : 0
     * price : 100.00
     * thumbnail : /Uploads/Picture/2016-08-23/6516b876832.jpg
     * senic_id : 18
     * stars : 5
     * description : 玩得很开心
     * dateline : 2016年02月06日 23:44
     * traffic_stars : {"name":"一般","stars_type":1,"stars_color":"#ff9933"}
     * hotel_stars : {"name":"一般","stars_type":1,"stars_color":"#ff9933"}
     * leader_stars : {"name":"一般","stars_type":1,"stars_color":"#ff9933"}
     * images : []
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
        private String name;
        private String long_title;
        private String short_title;
        private String attribute_id;
        private String price;
        private String thumbnail;
        private String senic_id;
        private String stars;
        private String description;
        private String dateline;
        /**
         * name : 一般
         * stars_type : 1
         * stars_color : #ff9933
         */

        private TrafficStarsEntity traffic_stars;
        /**
         * name : 一般
         * stars_type : 1
         * stars_color : #ff9933
         */

        private HotelStarsEntity hotel_stars;
        /**
         * name : 一般
         * stars_type : 1
         * stars_color : #ff9933
         */

        private LeaderStarsEntity leader_stars;
        private List<ImagesEntity> images;

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

        public String getShort_title() {
            return short_title;
        }

        public void setShort_title(String short_title) {
            this.short_title = short_title;
        }

        public String getAttribute_id() {
            return attribute_id;
        }

        public void setAttribute_id(String attribute_id) {
            this.attribute_id = attribute_id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getSenic_id() {
            return senic_id;
        }

        public void setSenic_id(String senic_id) {
            this.senic_id = senic_id;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDateline() {
            return dateline;
        }

        public void setDateline(String dateline) {
            this.dateline = dateline;
        }

        public TrafficStarsEntity getTraffic_stars() {
            return traffic_stars;
        }

        public void setTraffic_stars(TrafficStarsEntity traffic_stars) {
            this.traffic_stars = traffic_stars;
        }

        public HotelStarsEntity getHotel_stars() {
            return hotel_stars;
        }

        public void setHotel_stars(HotelStarsEntity hotel_stars) {
            this.hotel_stars = hotel_stars;
        }

        public LeaderStarsEntity getLeader_stars() {
            return leader_stars;
        }

        public void setLeader_stars(LeaderStarsEntity leader_stars) {
            this.leader_stars = leader_stars;
        }

        public List<ImagesEntity> getImages() {
            return images;
        }

        public void setImages(List<ImagesEntity> images) {
            this.images = images;
        }
        public static class ImagesEntity{
            private String big;
            private String small;

            public String getBig() {
                return big;
            }

            public void setBig(String big) {
                this.big = big;
            }

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }
        }
        public static class TrafficStarsEntity {
            private String name;
            private int stars_type;
            private String stars_color;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getStars_type() {
                return stars_type;
            }

            public void setStars_type(int stars_type) {
                this.stars_type = stars_type;
            }

            public String getStars_color() {
                return stars_color;
            }

            public void setStars_color(String stars_color) {
                this.stars_color = stars_color;
            }
        }

        public static class HotelStarsEntity {
            private String name;
            private int stars_type;
            private String stars_color;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getStars_type() {
                return stars_type;
            }

            public void setStars_type(int stars_type) {
                this.stars_type = stars_type;
            }

            public String getStars_color() {
                return stars_color;
            }

            public void setStars_color(String stars_color) {
                this.stars_color = stars_color;
            }
        }

        public static class LeaderStarsEntity {
            private String name;
            private int stars_type;
            private String stars_color;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getStars_type() {
                return stars_type;
            }

            public void setStars_type(int stars_type) {
                this.stars_type = stars_type;
            }

            public String getStars_color() {
                return stars_color;
            }

            public void setStars_color(String stars_color) {
                this.stars_color = stars_color;
            }
        }
    }
}
