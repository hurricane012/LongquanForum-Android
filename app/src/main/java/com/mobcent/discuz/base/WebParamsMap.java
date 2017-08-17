package com.mobcent.discuz.base;

import com.mobcent.discuz.activity.LoginUtils;

import java.util.HashMap;

import discuz.com.net.service.config.WebParamsKey;
import discuz.com.net.service.config.WebParamsValue;

/**
 * Created by pangxiaomin on 16/11/27.
 */
public class WebParamsMap {

    public static HashMap<String,String> baseMap(){
        HashMap<String,String> map = new HashMap<>();
        map.put(WebParamsKey.packageName, WebParamsValue.packageName);
        map.put(WebParamsKey.accessToken, WebParamsValue.accessToken);
        map.put(WebParamsKey.appName, WebParamsValue.appName);
        map.put(WebParamsKey.accessSecret,  WebParamsValue.accessSecret);
        map.put(WebParamsKey.sdkVersion, WebParamsValue.sdkVersion);
        map.put(WebParamsKey.forumKey,WebParamsValue.forumKey);
        map.put(WebParamsKey.imei, WebParamsValue.imei);
        map.put(WebParamsKey.imsi, WebParamsValue.imsi);
        map.put(WebParamsKey.forumType, WebParamsValue.forumType);
        map.put(WebParamsKey.platType, WebParamsValue.platType);
        map.put(WebParamsKey.egnVersion, WebParamsValue.egnVersion);
        return map;
    }

    public static HashMap<String,String> map(){
        HashMap<String,String> map = new HashMap<>();
        map.put(WebParamsKey.egnVersion, WebParamsValue.egnVersions);
        map.put(WebParamsKey.appName, WebParamsValue.appNames);
        map.put(WebParamsKey.sdkType, WebParamsValue.sdkTypes);
        map.put(WebParamsKey.packageName, WebParamsValue.packageNames);

        map.put(WebParamsKey.forumKey, WebParamsValue.forumKeys);
        map.put(WebParamsKey.accessSecret,  LoginUtils.getInstance().getAccessSecret());
        map.put(WebParamsKey.accessToken, LoginUtils.getInstance().getAccessToken());
        return map;
    }

    //用户发表
    public static HashMap<String,String> user_public(String uid){
        HashMap<String,String> map = new HashMap<>();
        map.put("longitude", "117");
        map.put("pageSize", "20");
        map.put("isImageList", "1");
        map.put("apphash",  "83837a08");
        map.put("latitude",  "40");
        map.put("uid", uid);
        map.put("type", "topic");
        map.put("page", "1");
        map.putAll(baseMap());
        return map;
    }

    public static HashMap<String,String> maps(String uid){
        HashMap<String,String> map = new HashMap<>();
        map.put("pageSize", "20");
        map.put("apphash", "d32cf2e2");
        map.put("uid", uid);
        map.put("type", "favorite");
        map.put("page", "1");
        map.putAll(baseMap());
        return map;
    }

    //注册
    public static HashMap<String,String> regist_map(String name,String pwd,String email) {
        HashMap<String, String> map = new HashMap<>();

        map.put(WebParamsKey.forumKey, "BW0L5ISVRsOTVLCTJx");
        map.put("isValidation","1");
        map.put("accessSecret","");
        map.put("accessToken","");

        map.put("sdkVersion", "2.4.0");
        map.put("username", name);
        map.put("password", pwd);
        map.put("email", email);
        map.put("apphash", "44c00ca2");
        map.put("forumType", "7");
        map.put("platType", "1");
        map.put("imsi", "460001001651621");
        map.put("egnVersion", "v2035.2");
        map.put("fastRegister", "0");
        map.put("packageName", "com.appbyme.app178470");
        return map;
    }

    //我的好友(搜索好友页面)
    public static HashMap<String,String> map_friend(String name,String page){
        HashMap<String,String> map = new HashMap<>();
        map.put("searchid", "0");
        map.put("pageSize", "10");
        map.put("apphash", "38b69752");
        map.put("keyword", name);
        map.put("page", page);
        map.putAll(baseMap());
        return map;
    }

    //我的好友(我的-我的好友)
    public static HashMap<String,String>  myfriends_homepage(String uid){
        HashMap<String,String> map = new HashMap<>();
        map.put("longitude", "117");
        map.put("pageSize", "20");
        map.put("apphash", "17f66603");
        map.put("latitude", "40");
        map.put("uid", uid);
        map.put("orderBy", "dateline");
        map.put("type", "friend");
        map.put("page", "1");
        map.putAll(baseMap());
        return map;
    }

    //我的好友详情页
    public static HashMap<String,String> userinfo(String uid){
        HashMap<String,String> map = new HashMap<>();
        map.put("userId", uid);
        map.put("apphash", "5bd6ff26");
        map.putAll(baseMap());
        return map;
    }

    //拉黑
    public static HashMap<String,String> block(String uid){
        HashMap<String,String> map = new HashMap<>();
        map.put("apphash", "ab970d53");
        map.put("uid", "196601");
        map.put("type", "black");
        map.putAll(baseMap());
        return map;
    }

    //取消拉黑
    public static HashMap<String,String> blockcancle(String uid){
        HashMap<String,String> map = new HashMap<>();
        map.put("apphash", "ab970d53");
        map.put("uid", "196601");
        map.put("type", "delblack");
        map.putAll(baseMap());
        return map;
    }

    //举报
    public static HashMap<String,String> report(String id,String message){
        HashMap<String,String> map = new HashMap<>();
        map.put("idType", "user");
        map.put("id", id);
        map.put("apphash", "ab970d53");
        map.put("message", message);
        map.putAll(baseMap());
        return map;
    }

    //我的发表
    public static HashMap<String,String> myPublic(String uid){
        HashMap<String,String> map = new HashMap<>();
        map.put("longitude", "117");
        map.put("pageSize", "20");
        map.put("isImageList", "1");
        map.put("apphash", "2a699579");
        map.put("latitude", "40");
        map.put("uid", uid);
        map.put("type", "topic");
        map.put("page", "1");
        map.putAll(baseMap());
        return map;
    }

    //我的发表(详情页)
    public static HashMap<String,String> myPublic_info(String uid){
        HashMap<String,String> map = new HashMap<>();
        map.put("longitude", "117");
        map.put("pageSize", "20");
        map.put("isImageList", "1");
        map.put("apphash", "3591506d");
        map.put("latitude", "40");
        map.put("uid", uid);
        map.put("type", "topic");
        map.put("page", "1");
        map.putAll(baseMap());
        return map;
    }

    //编辑页面
    public static HashMap<String,String> compile(){
        HashMap<String,String> map = new HashMap<>();
        map.put("r", "user/getprofilegroup");
        map.put("type", "userInfo");
        map.put("apphash", "c6fbc65a");
        //map.put("sdkType", "");
        map.putAll(baseMap());
        return map;
    }

    //编辑页面(个人信息(资料)编辑)
    public static HashMap<String,String> myselfInfoedit(String name,String appHash){
        HashMap<String,String> map = new HashMap<>();
        map.put("r", "user/updateuserinfo");
        map.put("type", "userInfo");
        map.put("userInfo", name);
        map.put("apphash", appHash);
        map.put("sdkType", "");
        map.putAll(baseMap());
        return map;
    }

    //编辑页面(个人信息(学历)
    public static HashMap<String,String> myselfInfoedits(String info){
        HashMap<String,String> map = new HashMap<>();
        map.put("r", "user/updateuserinfo");
        map.put("type", "userInfo");
        map.put("userInfo", info);
        map.put("apphash", "9a465bf4");
        map.put("sdkType", "");
        map.putAll(baseMap());
        return map;
    }


    //编辑页面(个性签名)
    public static HashMap<String,String> myselfInfoedited(String name,String appHash){
        HashMap<String,String> map = new HashMap<>();
        map.put("r", "user/updateusersign");
        map.put("sign", name);
        map.put("apphash", appHash);
        map.put("sdkType", "");
        map.putAll(baseMap());
        return map;
    }
    //编辑页面(添加关注)
    public static HashMap<String,String> addFollow(String uid){
        HashMap<String,String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("type", "follow");
        map.put("apphash", "06661a55");
        map.put("sdkType", "");
        map.putAll(baseMap());
        return map;
    }

    //编辑页面(取消关注)
    public static HashMap<String,String> unFollow(String uid){
        HashMap<String,String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("type", "unfollow");
        map.put("apphash", "06661a55");
        map.put("sdkType", "");
        map.putAll(baseMap());
        return map;
    }

//    //我的回复
//    public static HashMap<String,String> myReply(){
//        HashMap<String,String> map = new HashMap<>();
//        map.put("packageName", "com.appbyme.app178470");
//        map.put("forumType", "7");
//        map.put("longitude", "116.5892944");
//        map.put("pageSize", "20");
//        map.put("accessToken", "460cf9492dc93a6b3efdfd0736365");
//        map.put("appName", "龙泉论坛");
//        map.put("egnVersion", "v2102.5");
//        map.put("accessSecret", "1cc2c488c7de6f17528cf77ae7678");
//        map.put("sdkVersion", "2.5.0.0");
//        map.put("imei", "868029029800109");
//        map.put("apphash", "bfafdcda");
//        map.put("latitude", "39.91361618");
//        map.put("forumKey", "BW0L5ISVRsOTVLCTJx");
//        map.put("type", "reply");
//        map.put("page", "1");
//        map.put("platType", "1");
//        map.put("imsi", "460001001651621");
//        map.put("sdkType", "");
//
//        return map;
//    }
    //我的回复(备用)
    public static HashMap<String,String> myReply(String page){
        HashMap<String,String> map = new HashMap<>();
        map.put("longitude", "116.5892944");
        map.put("pageSize", "20");
        //map.put("apphash","bfafdcda");
        map.put("apphash","403edd50");
        map.put("latitude", "39.91361618");
        map.put("uid",LoginUtils.getInstance().getUserId());//222436
        map.put("type", "reply");
        map.put("page",page);
        map.put("sdkType", "");
        map.putAll(baseMap());
        return map;
    }
}
