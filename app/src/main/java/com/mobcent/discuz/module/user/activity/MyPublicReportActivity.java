package com.mobcent.discuz.module.user.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;

import com.appbyme.dev.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.other.ProgressStyle;
import com.mobcent.discuz.activity.BasePopActivity;
import com.mobcent.discuz.activity.LoginUtils;
import com.mobcent.discuz.activity.PublishTopicActivity;
import com.mobcent.discuz.base.WebParamsMap;
import com.mobcent.discuz.module.user.adapter.UserPublishAdapter;

import java.util.List;

import discuz.com.bean.me.Publish;
import discuz.com.net.service.DiscuzRetrofit;
import discuz.com.net.service.model.me.PublishResult;
import discuz.com.retrofit.library.HTTPSubscriber;

/**
 * 我的发表
 */
public class MyPublicReportActivity extends BasePopActivity {
    private int myPublicReportActivity=2;
    RelativeLayout nothingReport;
    XRecyclerView xRecycler;
    UserPublishAdapter adapter;
    List <Publish>list;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_public_report);
        xRecycler= (XRecyclerView) findViewById(R.id.xr_my_public);
        nothingReport= (RelativeLayout) findViewById(R.id.activity_my_public_nothingReport);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //设置瀑布流管理器
        xRecycler.setLayoutManager(layoutManager);
        xRecycler.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotatePulse);
        xRecycler.setRefreshProgressStyle(ProgressStyle.BallScaleRippleMultiple);
        newWork();
    }



    @Override
    protected Fragment initContentFragment() {
        return null;
    }

    private void newWork() {
        DiscuzRetrofit.getUserInfoService(this).requestUserPublish(WebParamsMap.user_public(LoginUtils.getInstance().getUserId())).subscribe(new HTTPSubscriber<PublishResult>() {
            @Override
            public void onSuccess(PublishResult userResult) {
                if (userResult.getHead().getErrCode().equals("00000000")){
                    if (userResult.list.size()!=0){
                        list=userResult.list;
                        xRecycler.setVisibility(View.VISIBLE);
                        nothingReport.setVisibility(View.GONE);
                        adapter = new UserPublishAdapter(MyPublicReportActivity.this,list,myPublicReportActivity);
                        xRecycler.setAdapter(adapter);
                    }else {
                        xRecycler.setVisibility(View.GONE);
                        nothingReport.setVisibility(View.VISIBLE);
                    }

                }else {
                    xRecycler.setVisibility(View.GONE);
                    nothingReport.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFail(int httpCode, int errorUserCode, String message) {

            }
        });
    }

    //点击
    public void mypublic(View view){
        Intent intent_publish=new Intent(this, PublishTopicActivity.class);
        intent_publish.putExtra("Type", "0");
        startActivity(intent_publish);
    }

}
