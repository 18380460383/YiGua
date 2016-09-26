package com.qhzlwh.yigua.bean;

import java.util.List;

/**
 * 创建者：Administrator
 * 时间：2016/8/30
 * 功能描述：
 */
public class TourCommentBean {

    /**
     * retcode : 2000
     * msg : 鏅尯璇勮鑾峰彇鎴愬姛
     * data : [{"comment_id":"43","stars":"5","comment_description":"回","user_id":"19","dateline":"2016年03月16日 10:21","traffic_stars":{"name":"一般","stars_type":1,"stars_color":"#ff9933"},"hotel_stars":{"name":"一般","stars_type":1,"stars_color":"#ff9933"},"leader_stars":{"name":"一般","stars_type":1,"stars_color":"#ff9933"},"images":[],"user_name":"devli","avatar":"Uploads/Picture/2016-03-18/20160318113045541.jpg"},{"comment_id":"42","stars":"5","comment_description":"不","user_id":"19","dateline":"2016年03月16日 10:15","traffic_stars":{"name":"一般","stars_type":1,"stars_color":"#ff9933"},"hotel_stars":{"name":"一般","stars_type":1,"stars_color":"#ff9933"},"leader_stars":{"name":"一般","stars_type":1,"stars_color":"#ff9933"},"images":[],"user_name":"devli","avatar":"Uploads/Picture/2016-03-18/20160318113045541.jpg"},{"comment_id":"41","stars":"5","comment_description":"呵呵","user_id":"19","dateline":"2016年03月16日 10:13","traffic_stars":{"name":"一般","stars_type":1,"stars_color":"#ff9933"},"hotel_stars":{"name":"一般","stars_type":1,"stars_color":"#ff9933"},"leader_stars":{"name":"一般","stars_type":1,"stars_color":"#ff9933"},"images":[],"user_name":"devli","avatar":"Uploads/Picture/2016-03-18/20160318113045541.jpg"},{"comment_id":"40","stars":"5","comment_description":"好吧","user_id":"19","dateline":"2016年03月16日 10:11","traffic_stars":{"name":"一般","stars_type":1,"stars_color":"#ff9933"},"hotel_stars":{"name":"一般","stars_type":1,"stars_color":"#ff9933"},"leader_stars":{"name":"一般","stars_type":1,"stars_color":"#ff9933"},"images":[],"user_name":"devli","avatar":"Uploads/Picture/2016-03-18/20160318113045541.jpg"},{"comment_id":"22","stars":"5","comment_description":"我试试这个","user_id":"23","dateline":"2016年02月04日 21:40","traffic_stars":{"name":"一般","stars_type":1,"stars_color":"#ff9933"},"hotel_stars":{"name":"一般","stars_type":1,"stars_color":"#ff9933"},"leader_stars":{"name":"一般","stars_type":1,"stars_color":"#ff9933"},"images":[],"user_name":"五噢","avatar":"Uploads/Picture/2016-03-08/20160308165644660.png"}]
     */

    private int retcode;
    private String msg;
    /**
     * comment_id : 43
     * stars : 5
     * comment_description : 回
     * user_id : 19
     * dateline : 2016年03月16日 10:21
     * traffic_stars : {"name":"一般","stars_type":1,"stars_color":"#ff9933"}
     * hotel_stars : {"name":"一般","stars_type":1,"stars_color":"#ff9933"}
     * leader_stars : {"name":"一般","stars_type":1,"stars_color":"#ff9933"}
     * images : []
     * user_name : devli
     * avatar : Uploads/Picture/2016-03-18/20160318113045541.jpg
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
        private String comment_id;
        private String stars;
        private String comment_description;
        private String user_id;
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
        private String user_name;
        private String avatar;
        private List<ImagesEntity> images;

        public String getComment_id() {
            return comment_id;
        }

        public void setComment_id(String comment_id) {
            this.comment_id = comment_id;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public String getComment_description() {
            return comment_description;
        }

        public void setComment_description(String comment_description) {
            this.comment_description = comment_description;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
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

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
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
