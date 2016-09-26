package com.qhzlwh.yigua.bean;

import java.util.List;

/**
 * 创建者：Administrator
 * 时间：2016/9/1
 * 功能描述：首页轮播图
 */
public class SenicHomeBannerBean {

    /**
     * retcode : 2000
     * msg : 获取成功
     * data : [{"id":"11","name":"老君山","path":"/Uploads/Picture/2016-01-31/56ad85725e901.jpg","url":"","type":"2","senic_id":"2","templete":1,"level_name":"5A级景区"},{"id":"10","name":"清明上河园","path":"/Uploads/Picture/2016-08-19/57b6843fe267c.jpg","url":"","type":"2","senic_id":"9","templete":1,"level_name":"5A级景区"},{"id":"9","name":"游呗","path":"/Uploads/Picture/2016-08-19/57b68418e9f9c.jpg","url":"https://www.baidu.com","type":"1","senic_id":0},{"id":"7","name":"重渡沟","path":"/Uploads/Picture/2016-08-19/57b683cf9abc2.jpg","url":"","type":"2","senic_id":"18","templete":1,"level_name":"4A级景区"}]
     */

    private int retcode;
    private String msg;
    /**
     * id : 11
     * name : 老君山
     * path : /Uploads/Picture/2016-01-31/56ad85725e901.jpg
     * url :
     * type : 2
     * senic_id : 2
     * templete : 1
     * level_name : 5A级景区
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
        private String id;
        private String name;
        private String path;
        private String url;
        private String type;
        private String senic_id;
        private int templete;
        private String level_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSenic_id() {
            return senic_id;
        }

        public void setSenic_id(String senic_id) {
            this.senic_id = senic_id;
        }

        public int getTemplete() {
            return templete;
        }

        public void setTemplete(int templete) {
            this.templete = templete;
        }

        public String getLevel_name() {
            return level_name;
        }

        public void setLevel_name(String level_name) {
            this.level_name = level_name;
        }
    }
}
