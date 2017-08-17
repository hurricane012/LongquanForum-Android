package com.mobcent.discuz.module.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.appbyme.dev.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.other.ProgressStyle;
import com.mobcent.discuz.activity.BasePopActivity;
import com.mobcent.discuz.activity.LoginUtils;
import com.mobcent.discuz.base.WebParamsMap;
import com.mobcent.discuz.module.user.adapter.PartakeAdapter.PartakeAdapter;
import com.mobcent.discuz.module.user.adapter.collectionAdapter.CollectionRecycle_adapter;

import java.util.List;

import discuz.com.net.service.DiscuzRetrofit;
import discuz.com.net.service.model.bean.my_reply.Lists;
import discuz.com.net.service.model.bean.my_reply.MyReplyBean;
import discuz.com.net.service.model.me.UserResult;
import discuz.com.retrofit.library.HTTPSubscriber;

import static android.widget.Toast.makeText;
import static com.mobcent.discuz.module.user.adapter.PartakeAdapter.PartakeAdapter.datas_PartakeMyReply;

/*
* 参与
*该类主要是点击"我的"里的参与按钮后进入到"我的回复"
* */
public class PartakeActivity extends BasePopActivity {
    XRecyclerView xRecycler;
    PartakeAdapter adapter;
    private int page=1;

    @Override
    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        getAppActionBar().setTitle(R.string.mc_forum_my_reply);
        getAppActionBar().setRightOptionGone();
        xRecycler= (XRecyclerView) findViewById(R.id.xr_partake);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        //设置瀑布流管理器
        xRecycler.setLayoutManager(layoutManager);
        xRecycler.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotatePulse);
        xRecycler.setRefreshProgressStyle(ProgressStyle.BallScaleRippleMultiple);


        xRecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        makeText(PartakeActivity.this,R.string.mc_forum_webview_refresh, Toast.LENGTH_SHORT).show();
                        xRecycler.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                xRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        makeText(PartakeActivity.this,R.string.mc_forum_loadmore,Toast.LENGTH_SHORT).show();
                        page++;
                        onRefreshs(page);
                        xRecycler.loadMoreComplete();
                    }
                }, 1000);

            }
        });
        onRefreshs(page);
    }

    private void onRefreshs(int page) {
        DiscuzRetrofit.getUserInfoService(this).partake(LoginUtils.getInstance().getUserId(), WebParamsMap.myReply(Integer.toString(page))).subscribe(new HTTPSubscriber<MyReplyBean>() {
            @Override
            public void onSuccess(MyReplyBean myReplyBean) {
                Log.i("TAG1", "getRs="+myReplyBean.getHead().getErrCode());
                if (myReplyBean.getHead().getErrCode().equals("00000000")){

                    List<Lists> list=myReplyBean.getList();

                    adapter=new PartakeAdapter(PartakeActivity.this,list);
                    //设置点击事件
                    adapter.setOnItemClickLitener(new CollectionRecycle_adapter.OnItemClickLitener() {
                        @Override
                        public void onitemclick(View view, int pos) {
                            final String uid=Integer.toString(datas_PartakeMyReply.get(pos).getUser_id());
                            DiscuzRetrofit.getUserInfoService(PartakeActivity.this).requestUserInfo(LoginUtils.getInstance().getUserId(), WebParamsMap.userinfo(uid)).subscribe(new HTTPSubscriber<UserResult>() {
                                @Override
                                public void onSuccess(UserResult myFriends) {
                                    Intent intent=new Intent(PartakeActivity.this,UserHomeActivity.class);
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
                            makeText(PartakeActivity.this,"长按 pos="+pos,Toast.LENGTH_SHORT).show();
                        }
                    });



                }else {
                    Toast.makeText(PartakeActivity.this,"请求错误",Toast.LENGTH_SHORT).show();
                }


                xRecycler.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                xRecycler.refreshComplete();

            }

            @Override
            public void onFail(int httpCode, int errorUserCode, String message) {
                Log.i("TAG1","httpCode="+httpCode);
                Log.i("TAG1","errorUserCode="+errorUserCode);
                Log.i("TAG1","message="+message);
            }

        });
    }

    @Override
    public int initLayout() {
        return R.layout.activity_partake;
    }

    @Override
    protected Fragment initContentFragment() {
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        datas_PartakeMyReply.clear();
    }
}
