package com.mobcent.discuz.module.user.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appbyme.dev.R;
import com.mobcent.discuz.activity.LoginUtils;
import com.mobcent.discuz.base.WebParamsMap;
import com.mobcent.discuz.module.user.adapter.UserPublishAdapter;
import com.mobcent.discuz.module.user.interFace.NetloadFinished;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import discuz.com.bean.me.Publish;
import discuz.com.net.service.DiscuzRetrofit;
import discuz.com.net.service.model.me.PublishResult;
import discuz.com.retrofit.library.HTTPSubscriber;

import static com.mobcent.discuz.module.user.activity.UserHomeActivity.from;
import static com.mobcent.discuz.module.user.activity.UserHomeActivity.uid_myfriendsSearch;


/**
 * Created by pangxiaomin on 16/11/20.
 * 我-个人-发表
 * @author 张春生
 */
public class UserHomePublishFragment extends BaseUserInnerScrollFragment  {
    private int listsize;
    private int UserHomeActivity=1;
    private TextView text_nothing;
    private RecyclerView mPublishRecyclerView;
    static NetloadFinished activity;
    UserPublishAdapter adapter;
    List<Publish> datas;

    public static UserHomePublishFragment newInstance(NetloadFinished netloadFinished) {
        activity = netloadFinished;
        return new UserHomePublishFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestUserPublishInfo();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_home_publish,container, false);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        mPublishRecyclerView = $(view,R.id.fragment_publish_recyclerview);
        text_nothing = $(view,R.id.fragment_public_nothing);
        mPublishRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //manager.setAutoMeasureEnabled(true);
        //mPublishRecyclerView.setLayoutManager(manager);
        if (from==true){
            Intent intent=getActivity().getIntent();
            String uid_SearchFriends=intent.getStringExtra("uid");
            DiscuzRetrofit.getUserInfoService(getActivity()).requestUserPublish(WebParamsMap.user_public(uid_myfriendsSearch)).subscribe(new HTTPSubscriber<PublishResult>() {
                @Override
                public void onSuccess(PublishResult userResult) {
                    listsize=userResult.list.size();
                    if(userResult!=null && userResult.list!=null){
                        if (userResult.list.size()!=0){
                            text_nothing.setVisibility(View.INVISIBLE);
                        }else {
                            text_nothing.setVisibility(View.VISIBLE);
                        }
                        datas.addAll(userResult.list);
                        adapter.notifyDataSetChanged();
                        activity.loadfinish(userResult.total_num);
                    }
                }

                @Override
                public void onFail(int httpCode, int errorUserCode, String message) {

                }
            });
            datas = new ArrayList<>();
            adapter = new UserPublishAdapter(getActivity(),datas,UserHomeActivity);
            mPublishRecyclerView.setAdapter(adapter);
        }else {
            DiscuzRetrofit.getUserInfoService(getActivity()).requestUserPublish(WebParamsMap.user_public(LoginUtils.getInstance().getUserId())).subscribe(new HTTPSubscriber<PublishResult>() {
                @Override
                public void onSuccess(PublishResult userResult) {
                    listsize=userResult.list.size();
                    if(userResult!=null && userResult.list!=null){
                        if (userResult.list.size()!=0){
                            text_nothing.setVisibility(View.INVISIBLE);
                        }else {
                            text_nothing.setVisibility(View.VISIBLE);
                        }
                        datas.addAll(userResult.list);
                        adapter.notifyDataSetChanged();
                        activity.loadfinish(userResult.total_num);
                    }
                }

                @Override
                public void onFail(int httpCode, int errorUserCode, String message) {

                }
            });
            datas = new ArrayList<>();
            adapter = new UserPublishAdapter(getActivity(),datas,UserHomeActivity);
            mPublishRecyclerView.setAdapter(adapter);
        }

    }


    @Override
    public boolean canScrollVertically(int direction) {
        return mPublishRecyclerView != null && mPublishRecyclerView.canScrollVertically(direction);
    }

    @Override
    public void onFlingOver(int y, long duration) {
        if (mPublishRecyclerView != null) {
            mPublishRecyclerView.smoothScrollBy(y, (int) duration);
        }
    }

    private void requestUserPublishInfo(){
        final HashMap<String,String> params = WebParamsMap.map();
        DiscuzRetrofit.getUserInfoService(getActivity()).requestUserPublish(WebParamsMap.map()).subscribe(new HTTPSubscriber<PublishResult>() {
            @Override
            public void onSuccess(PublishResult userResult) {
                if(userResult!=null && userResult.list!=null){
                    datas.addAll(userResult.list);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFail(int httpCode, int errorUserCode, String message) {
                Log.i("longquan","获取失败,失败原因:"+message);
            }
        });
    }

}
