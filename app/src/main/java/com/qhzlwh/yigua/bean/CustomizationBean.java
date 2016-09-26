package com.qhzlwh.yigua.bean;

import java.util.List;

/**
 * 创建者：Administrator
 * 时间：2016/9/8
 * 功能描述：
 */
public class CustomizationBean {

    /**
     * retcode : 2000
     * msg : 分类数据获取成功
     * data : {"zs":[{"id":"1","title":"普通双标间","type":"1"},{"id":"2","title":"经济型","type":"1"},{"id":"3","title":"三星级","type":"1"},{"id":"5","title":"四星级","type":"1"},{"id":"6","title":"五星级","type":"1"}],"yc":[{"id":"7","title":"20-30元/人","type":"2"},{"id":"8","title":"30-50元/人","type":"2"},{"id":"9","title":"50元以上/人","type":"2"},{"id":"10","title":"无需安排","type":"2"}],"ld":[{"id":"11","title":"男领队","type":"3"},{"id":"12","title":"女领队","type":"3"},{"id":"13","title":"男女均可","type":"3"}]}
     */

    private int retcode;
    private String msg;
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
        /**
         * id : 1
         * title : 普通双标间
         * type : 1
         */

        private List<ZsEntity> zs;
        /**
         * id : 7
         * title : 20-30元/人
         * type : 2
         */

        private List<YcEntity> yc;
        /**
         * id : 11
         * title : 男领队
         * type : 3
         */

        private List<LdEntity> ld;

        public List<ZsEntity> getZs() {
            return zs;
        }

        public void setZs(List<ZsEntity> zs) {
            this.zs = zs;
        }

        public List<YcEntity> getYc() {
            return yc;
        }

        public void setYc(List<YcEntity> yc) {
            this.yc = yc;
        }

        public List<LdEntity> getLd() {
            return ld;
        }

        public void setLd(List<LdEntity> ld) {
            this.ld = ld;
        }

        public static class ZsEntity {
            private String id;
            private String title;
            private String type;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class YcEntity {
            private String id;
            private String title;
            private String type;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }

        public static class LdEntity {
            private String id;
            private String title;
            private String type;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }
        }
    }
}
