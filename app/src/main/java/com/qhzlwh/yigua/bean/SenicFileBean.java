package com.qhzlwh.yigua.bean;

/**
 * 创建者：Administrator
 * 时间：2016/9/9
 * 功能描述：
 */
public class SenicFileBean {

    /**
     * retcode : 2000
     * msg : 上传成功
     * data : {"file":{"id":"500","path":"/Uploads/Picture/2016-09-09/57d263d3b41b1.jpg","url":"","md5":"3bc66e76ba7258aefae98009c0f51a4b","sha1":"88d9e3434152771d0814abc71faa8dbffa5ef048","status":"1","create_time":"1473405907"}}
     */

    private int retcode;
    private String msg;
    /**
     * file : {"id":"500","path":"/Uploads/Picture/2016-09-09/57d263d3b41b1.jpg","url":"","md5":"3bc66e76ba7258aefae98009c0f51a4b","sha1":"88d9e3434152771d0814abc71faa8dbffa5ef048","status":"1","create_time":"1473405907"}
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
        /**
         * id : 500
         * path : /Uploads/Picture/2016-09-09/57d263d3b41b1.jpg
         * url :
         * md5 : 3bc66e76ba7258aefae98009c0f51a4b
         * sha1 : 88d9e3434152771d0814abc71faa8dbffa5ef048
         * status : 1
         * create_time : 1473405907
         */

        private FileEntity file;

        public FileEntity getFile() {
            return file;
        }

        public void setFile(FileEntity file) {
            this.file = file;
        }

        public static class FileEntity {
            private String id;
            private String path;
            private String url;
            private String md5;
            private String sha1;
            private String status;
            private String create_time;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getMd5() {
                return md5;
            }

            public void setMd5(String md5) {
                this.md5 = md5;
            }

            public String getSha1() {
                return sha1;
            }

            public void setSha1(String sha1) {
                this.sha1 = sha1;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }
        }
    }
}
