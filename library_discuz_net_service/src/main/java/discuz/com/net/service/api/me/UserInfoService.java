package discuz.com.net.service.api.me;

import java.util.Map;

import discuz.com.net.service.model.bean.MyFriend.MyFriends;
import discuz.com.net.service.model.bean.addFollowBean.AddFollowBean;
import discuz.com.net.service.model.bean.block.Block;
import discuz.com.net.service.model.bean.collectionBean.ColoectionBean;
import discuz.com.net.service.model.bean.compileBeans.CompileBeans;
import discuz.com.net.service.model.bean.editInfoBean.EditInfoBean;
import discuz.com.net.service.model.bean.my_reply.MyReplyBean;
import discuz.com.net.service.model.bean.myfriendsHomepage.MyfriendsHomepage;
import discuz.com.net.service.model.bean.mypublicbean.MyPublicBean;
import discuz.com.net.service.model.bean.registbean.RegistBean;
import discuz.com.net.service.model.bean.searchfriendsinfo.SearchFriendsInfo;
import discuz.com.net.service.model.bean.userpublic.UserPublic;
import discuz.com.net.service.model.me.PublishResult;
import discuz.com.net.service.model.me.UserResult;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by pangxiaomin on 16/11/27.
 */
public interface UserInfoService {

    /**获取用户资料*/
    @FormUrlEncoded
    @POST("index.php?r=user/userinfo")
    Observable<UserResult> requestUserInfo(@Field("userId")String userId, @FieldMap Map<String,String> params);

    /**获取用户发表*/
    @FormUrlEncoded
    @POST("index.php?r=user/topiclist")
    Observable<PublishResult> requestUserPublish(@FieldMap Map<String,String> params);

    /**获取(查询)用户发表*/
    @FormUrlEncoded
    @POST("index.php?r=user/topiclist")
    Observable<UserPublic> requestUserPublishs(@FieldMap Map<String,String> params);

    /**获取用户收藏*/
    @FormUrlEncoded
    @POST("index.php?r=user/topiclist")
    Observable<ColoectionBean> requestUserCollection(@Field("userId")String userId, @FieldMap Map<String,String> params);

    /**注册*/
    @FormUrlEncoded
    @POST("index.php?r=user/register")
    Observable<RegistBean> userregist(@FieldMap Map<String,String> params);

    /**获取我的好友*/
    @FormUrlEncoded
    @POST("index.php?r=user/searchuser")
    Observable<MyFriends> requestUserMyFriends(@Field("userId")String userId, @FieldMap Map<String,String> params);

    /**拉黑*/
    @FormUrlEncoded
    @POST("index.php?r=user/useradmin")
    Observable<Block> block(@Field("userId")String userId, @FieldMap Map<String,String> params);

    /**取消拉黑*/
    @FormUrlEncoded
    @POST("index.php?r=user/useradmin")
    Observable<Block> blockcancle(@Field("userId")String userId, @FieldMap Map<String,String> params);

    /**举报*/
    @FormUrlEncoded
    @POST("index.php?r=user/report")
    Observable<Block> report(@Field("userId")String userId, @FieldMap Map<String,String> params);

    /**获取我的好友详情(资料)*/
    @FormUrlEncoded
    @POST("index.php?r=user/userinfo")
    Observable<SearchFriendsInfo> friendsinfo(@Field("userId")String userId, @FieldMap Map<String,String> params);

    /**我的好友(我的-我的好友页面)*/
    @FormUrlEncoded
    @POST("index.php?r=user/userlist")
    Observable<MyfriendsHomepage> friendList( @FieldMap Map<String,String> params);

    /**我的发表(我的-我的发表页面)*/
    @FormUrlEncoded
    @POST("index.php?r=user/topiclist")
    Observable<MyPublicBean> myPublic(@FieldMap Map<String,String> params);

    /**编辑页面(个人信息)*/
    @FormUrlEncoded
    @POST("index.php?r=user/getprofilegroup")
    Observable<CompileBeans> compile(@Field("userId")String userId, @FieldMap Map<String,String> params);

    /**编辑(个人信息编辑)*/
    @FormUrlEncoded
    @POST("index.php?r=user/updateuserinfo")
    Observable<EditInfoBean> myselfInfoedit(@FieldMap Map<String,String> params);

    /**编辑(个性签名)*/
    @FormUrlEncoded
    @POST("index.php?r=user/updateusersign")
    Observable<EditInfoBean> myselfInfoedited(@FieldMap Map<String,String> params);

    /**编辑(添加关注)*/
    @FormUrlEncoded
    @POST("index.php?r=user/useradmin")
    Observable<AddFollowBean> addFollow(@Field("userId")String userId,@FieldMap Map<String,String> params);

    /**编辑(取消关注)*/
    @FormUrlEncoded
    @POST("index.php?r=user/useradmin")
    Observable<AddFollowBean> unFollow(@Field("userId")String userId,@FieldMap Map<String,String> params);

    /**我的回复*/
    @FormUrlEncoded
    @POST("index.php?r=user/topiclist")
    Observable<MyReplyBean> partake(@Field("userId")String userId, @FieldMap Map<String,String> params);
}
