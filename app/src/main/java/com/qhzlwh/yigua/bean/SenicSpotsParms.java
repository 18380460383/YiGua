package com.qhzlwh.yigua.bean;

import java.util.List;

/**
 * 创建者：Administrator
 * 时间：2016/9/1
 * 功能描述：景区介绍
 */
public class SenicSpotsParms {

    /**
     * retcode : 2000
     * msg : 景区信息获取成功
     * data : {"long_description":"","telephone":"028-8888-8888","open":"早9：00至晚18：00","ticket":"门票价格：免费","services":"服务项目后台添加","address":"兰州市西津西路3号","traffic":"公交 1.乘坐5路、13路、43路、47路等公交车在【金河路】站下车 2.乘坐62路、70路、93路、163路、340路等公交车在【长顺上街】站下车。","season":"无","notice":"参观须知参观须知参观须知参观须知参观须知","attribute_id":"21","recommend_spot":[{"spot_id":"116","name":"宽窄巷子景点","thumbnail":"/Uploads/Picture/2016-08-05/e499c416b7a.jpg","image":"/Uploads/Picture/2016-08-05/57a4473fa4af4.jpg","short_description":"1.乘坐5路、13路、43路、47路等公交车在【金河路】站下车 2.乘坐62路、70路、93路、163路、340路等公交车在【长顺上街】站下车。"}],"senic_comment":[{"comment_id":"8","stars":"5","comment_description":"好好好","user_id":"15","dateline":"2016年02月03日","user_name":"我是谁","avatar":"Uploads/Picture/2016-02-22/20160222232214622.png"},{"comment_id":"9","stars":"4","comment_description":"好","user_id":"18","dateline":"2016年02月03日","user_name":"傻子","avatar":"Uploads/Picture/2016-02-29/20160229134535364.png"}]}
     */

    private int retcode;
    private String msg;
    /**
     * long_description :
     * telephone : 028-8888-8888
     * open : 早9：00至晚18：00
     * ticket : 门票价格：免费
     * services : 服务项目后台添加
     * address : 兰州市西津西路3号
     * traffic : 公交 1.乘坐5路、13路、43路、47路等公交车在【金河路】站下车 2.乘坐62路、70路、93路、163路、340路等公交车在【长顺上街】站下车。
     * season : 无
     * notice : 参观须知参观须知参观须知参观须知参观须知
     * attribute_id : 21
     * recommend_spot : [{"spot_id":"116","name":"宽窄巷子景点","thumbnail":"/Uploads/Picture/2016-08-05/e499c416b7a.jpg","image":"/Uploads/Picture/2016-08-05/57a4473fa4af4.jpg","short_description":"1.乘坐5路、13路、43路、47路等公交车在【金河路】站下车 2.乘坐62路、70路、93路、163路、340路等公交车在【长顺上街】站下车。"}]
     * senic_comment : [{"comment_id":"8","stars":"5","comment_description":"好好好","user_id":"15","dateline":"2016年02月03日","user_name":"我是谁","avatar":"Uploads/Picture/2016-02-22/20160222232214622.png"},{"comment_id":"9","stars":"4","comment_description":"好","user_id":"18","dateline":"2016年02月03日","user_name":"傻子","avatar":"Uploads/Picture/2016-02-29/20160229134535364.png"}]
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
        private String long_description;
        private String telephone;
        private String open;
        private String ticket;
        private String services;
        private String address;
        private String traffic;
        private String season;
        private String notice;
        private String attribute_id;
        /**
         * spot_id : 116
         * name : 宽窄巷子景点
         * thumbnail : /Uploads/Picture/2016-08-05/e499c416b7a.jpg
         * image : /Uploads/Picture/2016-08-05/57a4473fa4af4.jpg
         * short_description : 1.乘坐5路、13路、43路、47路等公交车在【金河路】站下车 2.乘坐62路、70路、93路、163路、340路等公交车在【长顺上街】站下车。
         */

        private List<RecommendSpotEntity> recommend_spot;
        /**
         * comment_id : 8
         * stars : 5
         * comment_description : 好好好
         * user_id : 15
         * dateline : 2016年02月03日
         * user_name : 我是谁
         * avatar : Uploads/Picture/2016-02-22/20160222232214622.png
         */

        private List<SenicCommentEntity> senic_comment;

        public String getLong_description() {
            return long_description;
        }

        public void setLong_description(String long_description) {
            this.long_description = long_description;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getOpen() {
            return open;
        }

        public void setOpen(String open) {
            this.open = open;
        }

        public String getTicket() {
            return ticket;
        }

        public void setTicket(String ticket) {
            this.ticket = ticket;
        }

        public String getServices() {
            return services;
        }

        public void setServices(String services) {
            this.services = services;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTraffic() {
            return traffic;
        }

        public void setTraffic(String traffic) {
            this.traffic = traffic;
        }

        public String getSeason() {
            return season;
        }

        public void setSeason(String season) {
            this.season = season;
        }

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        public String getAttribute_id() {
            return attribute_id;
        }

        public void setAttribute_id(String attribute_id) {
            this.attribute_id = attribute_id;
        }

        public List<RecommendSpotEntity> getRecommend_spot() {
            return recommend_spot;
        }

        public void setRecommend_spot(List<RecommendSpotEntity> recommend_spot) {
            this.recommend_spot = recommend_spot;
        }

        public List<SenicCommentEntity> getSenic_comment() {
            return senic_comment;
        }

        public void setSenic_comment(List<SenicCommentEntity> senic_comment) {
            this.senic_comment = senic_comment;
        }

        public static class RecommendSpotEntity {
            private String spot_id;
            private String name;
            private String thumbnail;
            private String image;
            private String short_description;
            private String long_description;

            public String getSpot_id() {
                return spot_id;
            }

            public void setSpot_id(String spot_id) {
                this.spot_id = spot_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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
        }

        public static class SenicCommentEntity {
            private String comment_id;
            private String stars;
            private String comment_description;
            private String user_id;
            private String dateline;
            private String user_name;
            private String avatar;

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
        }
    }
}
