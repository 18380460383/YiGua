package com.qhzlwh.yigua.util;

import android.os.Environment;

import java.io.File;

/**
 * Created by peter on 2016/8/27.
 */
public class Myconstant {
    public static String MEIQIA_KEY = "35070755a8aaea9ed589b2c627a42ae0";
    public static String fileName = "currencyHtml.html";
    public static String PATH= Environment.getExternalStorageDirectory()+ File.separator+"rawHtml"+File.separator;
    public static String BASE_URL="http://qh.91chuanbo.cn/";
    public static String BASE_OUT_URL="http://qh.91chuanbo.cn/Api/";
    public static String PUICTUR_BASE_URL="http://qh.91chuanbo.cn/";
    //内网地址
    public static String BASE_OFFICE_URL="http://ipx.app.qhzlwh.com/Api/";

    //首页轮播图
    public static String BANNER_URL=BASE_OUT_URL+"Api/GetBanner";
    // 景区下的评论
    public static String COMMENT_URL=BASE_OUT_URL+"Senic/get_senic_comment/senic_id/";
    //获得城市下的景区
    public static String SENIC_URL=BASE_OUT_URL+"Users/get_senic_by_cityid/city_id/";
    //获得验证码
    public static String GETCODE_URL=BASE_OUT_URL+"Api/sendVerCode";
    //快捷登录
    public static String SENIC_LOGIN=BASE_OUT_URL+"Users/login_kj";
    //获取概况
    public static String SENIC_PARMS_URL=BASE_OUT_URL+"Senic/get_senic_parms_all/senic_id/";
    //获得景区下的更多评论
    public static String SENIC_COMMENT_URL=BASE_OUT_URL+"Senic/get_senic_comment/senic_id/";
    //获取景区下的美食和特产
    public static String SENIC_GOOD_URL=BASE_OUT_URL+"Goods/get_product/senic_id/";
    public static String SENIC_TIKECT_INFORMATION=BASE_OUT_URL+"Ticket/get_one_ticket/";
    //获得门票信息
    public static String getSenic_Tikect(String senic_id,String ticket_id,String uid){
        return SENIC_TIKECT_INFORMATION+"/senic_id/"+senic_id+"/ticket_id/"+ticket_id+"/uid/"+uid;
    }
    //获得订购中的门票信息
    public static String SENIC_TIKECT_MAKE=BASE_OUT_URL+"Ticket/ticket_order";
    //获取ping++支付凭证 charge
    public static String SENIC_PING=BASE_OUT_URL+"Ping/get_charge";
    //获取用户的所有订单
    public static String GET_ORDER_LIST=BASE_OUT_URL+"Users/get_order_list/uid/";
    //获取景区下所有语音文件
    public static String senic_sound=BASE_OUT_URL+"Senic/senic_sound/senic_id/";
    //获取用户收藏的景区
    public static String get_user_collect=BASE_OUT_URL+"Users/get_user_collect/uid/";
    //用户收藏景区
    public static String user_senic_collect=BASE_OUT_URL+"Users/user_senic_collect";
    //用户删除收藏的景区（删除单条记录）
    public static String user_delect_collect=BASE_OUT_URL+"Users/user_delect_collect";
    //用户是否去过景区，是否收藏了景区
    public static String SenicLikeOrCollect=BASE_OUT_URL+"Api/SenicLikeOrCollect/";
    //获取我的已评论（我的页面）
    public static String my_comment=BASE_OUT_URL+"Users/my_comment/uid/";
    //定制游报名
    public static String add_custom_tour=BASE_OUT_URL+"Type/add_custom_tour";
    //获取定制游标准分类名称
    public static String get_type_list=BASE_OUT_URL+"Type/get_type_list";
    //用户发布景区评论
    public static String senic_comment=BASE_OUT_URL+"Senic/senic_comment";
    //图片上传
    public static String uploadPicture=BASE_OUT_URL+"Api/uploadPicture";
    //获取为评论订单
    public static String my_uncomment=BASE_OUT_URL+"Users/my_uncomment/uid/";
    //取消订单
    public static String cancel_order=BASE_OUT_URL+"Order/cancel_order";
    //用户完善、修改资料
    public static String userEdit=BASE_OUT_URL+"Users/userEdit";
    //获取一个登录用户的会员信息
    public static String getUserInfo=BASE_OUT_URL+"Users/getUserInfo/user_id/";
    //扫描二维码获取购票列表
    public static String ScanQRCode=BASE_OUT_URL+"Senic/ScanQRCode";
    //检查APP版本
    public static String getAppBate=BASE_OUT_URL+"Api/getAppBate/type/";
    //景区门票使用
    public static String useSenicTicket=BASE_OUT_URL+"Senic/useSenicTicket";
    //获取城市列表
    public static String getCityList=BASE_OUT_URL+"Api/getCityList";
  /*  public static String cancel_order(String UID,String ORDER_ID,String order_type){
        return BASE_OUT_URL+"Order/cancel_order/"+ORDER_ID+"/uid/"+UID+"/order_type/"+order_type;
    }*/
  public static String API = "http://restapi.amap.com/v3/geocode/geo?key=";

    public static String KEY = "aa4a48297242d22d2b3fd6eddfe62217";
    public static String getGaoDe(String name){
        return API+KEY+"&s=rsv3&address="+name;
    }
}
