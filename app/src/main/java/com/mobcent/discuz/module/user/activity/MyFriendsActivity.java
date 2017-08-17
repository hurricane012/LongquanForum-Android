package com.mobcent.discuz.module.user.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appbyme.dev.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.other.ProgressStyle;
import com.mobcent.discuz.activity.BasePopActivity;
import com.mobcent.discuz.activity.LoginActivity;
import com.mobcent.discuz.activity.LoginUtils;
import com.mobcent.discuz.base.WebParamsMap;
import com.mobcent.discuz.module.user.adapter.collectionAdapter.CollectionRecycle_adapter;
import com.mobcent.discuz.module.user.adapter.myFriends_adapter.Myfriends_homepage_adapter;

import discuz.com.net.service.DiscuzRetrofit;
import discuz.com.net.service.model.bean.myfriendsHomepage.Lists;
import discuz.com.net.service.model.bean.myfriendsHomepage.MyfriendsHomepage;
import discuz.com.net.service.model.me.UserResult;
import discuz.com.retrofit.library.HTTPSubscriber;

public class MyFriendsActivity extends BasePopActivity {
    private java.util.List<Lists> list;
    private Myfriends_homepage_adapter adapter;
    TextView btn_myfriends;
    XRecyclerView xRecycler;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAppActionBar().setTitle(R.string.mc_forum_my_friends);
        if (!LoginUtils.getInstance().isLogin()){
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
        xRecycler= (XRecyclerView) findViewById(R.id.xr_test_myfrieds);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        View header = LayoutInflater.from(this).inflate(R.layout.item_myfriendearch_head, (ViewGroup)findViewById(android.R.id.content),false);
        xRecycler.addHeaderView(header);
        btn_myfriends= (TextView) header.findViewById(R.id.myfriends_button);
        btn_myfriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyFriendsActivity.this,MyFriendsSearchActivity.class);
                startActivity(intent);
            }
        });
        //设置瀑布流管理器
        xRecycler.setLayoutManager(layoutManager);
        xRecycler.setPullRefreshEnabled(true);
        xRecycler.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotatePulse);
        xRecycler.setRefreshProgressStyle(ProgressStyle.BallScaleRippleMultiple);
        newWork();
    }

    @Override
    protected Fragment initContentFragment() {
        return null;
    }

    @Override
    public int initLayout() {
        return R.layout.activity_my_friend;
    }

    private void newWork() {
        String uid=LoginUtils.getInstance().getUserId();
        DiscuzRetrofit.getUserInfoService(this).friendList(WebParamsMap.myfriends_homepage(uid)).subscribe(new HTTPSubscriber<MyfriendsHomepage>(){
            @Override
            public void onSuccess(MyfriendsHomepage myfriendsHomepage) {
                list=myfriendsHomepage.getList();
                adapter=new Myfriends_homepage_adapter(MyFriendsActivity.this,list);
                //设置点击事件
                adapter.setOnItemClickLitener(new CollectionRecycle_adapter.OnItemClickLitener() {
                    @Override
                    public void onitemclick(View view, int pos) {
                        final String uid=Integer.toString(list.get(pos-2).getUid());
                        DiscuzRetrofit.getUserInfoService(MyFriendsActivity.this).requestUserInfo(LoginUtils.getInstance().getUserId(), WebParamsMap.userinfo(uid)).subscribe(new HTTPSubscriber<UserResult>() {
                            @Override
                            public void onSuccess(UserResult myFriends) {
                                Intent intent=new Intent(MyFriendsActivity.this,UserHomeActivity.class);
                                intent.putExtra("uid",uid);
                                intent.putExtra("from",true);
                                startActivity(intent);
                            }

                            @Override
                            public void onFail(int httpCode, int errorUserCode, String message) {

                            }
                        });
                    }

                    @Override
                    public void onitemlongclick(View view, int pos) {
                        //makeText(MyFriendsActivity.this,"长按 pos="+pos,Toast.LENGTH_SHORT).show();

                    }
                });

                xRecycler.setAdapter(adapter);
            }

            @Override
            public void onFail(int httpCode, int errorUserCode, String message) {
                Log.i("TAG", "失败");
            }
        });
    }


}
