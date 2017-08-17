package com.mobcent.discuz.module.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.appbyme.dev.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.other.ProgressStyle;
import com.mobcent.discuz.activity.BasePopActivity;
import com.mobcent.discuz.activity.LoginUtils;
import com.mobcent.discuz.base.WebParamsMap;
import com.mobcent.discuz.module.user.adapter.collectionAdapter.CollectionRecycle_adapter;
import com.mobcent.discuz.module.user.adapter.myFriends_adapter.Myfriends_adapter;

import discuz.com.net.service.DiscuzRetrofit;
import discuz.com.net.service.model.bean.MyFriend.Lists;
import discuz.com.net.service.model.bean.MyFriend.MyFriends;
import discuz.com.net.service.model.me.UserResult;
import discuz.com.retrofit.library.HTTPSubscriber;

import static android.widget.Toast.makeText;
import static com.mobcent.discuz.module.user.adapter.myFriends_adapter.Myfriends_adapter.datas_myFriendsSearch;

public class MyFriendsSearchActivity extends BasePopActivity {
    java.util.List<Lists> list;
    private EditText edittext;
    String nickname;
    private Button search_button;
    Myfriends_adapter adapter;
    String page="1";
    int pages=1;
    XRecyclerView xRecycler;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAppActionBar().setTitle(R.string.mc_forum_role_user);
        //adapter=new Myfriends_adapter(MyFriendsSearchActivity.this,list);
        adapter=new Myfriends_adapter();
        xRecycler= (XRecyclerView) findViewById(R.id.xr_test_myfrieds_search);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //mRecyclerView.setAdapter(adapter);
//        View header = LayoutInflater.from(this).inflate(R.layout.item_myfriends_listviewhead, (ViewGroup)findViewById(android.R.id.content),false);
//        xRecycler.addHeaderView(header);
        edittext= (EditText)findViewById(R.id.myfriends_edittext);
        search_button= (Button)findViewById(R.id.myfriend_search_button);
//      xRecycler.setEmptyView(edittext);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page="1";
                pages=1;
                nickname=edittext.getText().toString();
                if (nickname.equals("")){
                    Toast.makeText(MyFriendsSearchActivity.this,R.string.error_search_null,Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    adapter.clear();
                }catch (NullPointerException e){

                }
                onRefreshs(nickname,page,false);
            }
        });
        //设置瀑布流管理器
        xRecycler.setLayoutManager(layoutManager);
        //添加分割线，注意位置是会从下拉那个Item的实际位置开始的，所以如果支持下拉需要屏蔽下拉和HeaderView
        //因为集成了下拉item，所以需要从下拉item之后开始计算分割
        //xRecycler.addItemDecoration(new DividerItemDecoration(dip2px(this, 10), DividerItemDecoration.LIST, 1));

        //是否屏蔽下拉
        xRecycler.setPullRefreshEnabled(false);
        //xRecycler.setRefreshHeader(new MyCustomRefreshHeader(this));
        /*xRecycler.setFootView(new CustomLoadMoreFooter(this));*/
        //上拉加载更多样式，也可以设置下拉
        xRecycler.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotatePulse);
        xRecycler.setRefreshProgressStyle(ProgressStyle.BallScaleRippleMultiple);
        //onRefreshs(nickname,page,false);



        //本身也支持设置空局部
        //xRecycler.setEmptyView(tv_no_data);
        xRecycler.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        makeText(MyFriendsSearchActivity.this,R.string.mc_forum_webview_refresh, Toast.LENGTH_SHORT).show();
                        xRecycler.refreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                xRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        makeText(MyFriendsSearchActivity.this,R.string.mc_forum_loadmore,Toast.LENGTH_SHORT).show();
                        pages++;
                        page=Integer.toString(pages);
                        onRefreshs(nickname,page,true);
                        xRecycler.loadMoreComplete();
                    }
                }, 1000);

            }
        });

    }

    private void onRefreshs(String nickname, String page, final Boolean isLoad) {
        DiscuzRetrofit.getUserInfoService(this).requestUserMyFriends(LoginUtils.getInstance().getUserId(), WebParamsMap.map_friend(nickname,page)).subscribe(new HTTPSubscriber<MyFriends>() {
            @Override
            public void onSuccess(MyFriends myFriends) {
                list=myFriends.getBody().getList();
                adapter.setMyfriends_adapter(MyFriendsSearchActivity.this,list);

                //设置点击事件
                adapter.setOnItemClickLitener(new CollectionRecycle_adapter.OnItemClickLitener() {
                    @Override
                    public void onitemclick(View view, int pos) {
                        final String uid=Integer.toString(datas_myFriendsSearch.get(pos).getUid());
                        DiscuzRetrofit.getUserInfoService(MyFriendsSearchActivity.this).requestUserInfo(LoginUtils.getInstance().getUserId(), WebParamsMap.userinfo(uid)).subscribe(new HTTPSubscriber<UserResult>() {
                            @Override
                            public void onSuccess(UserResult myFriends) {
                                Intent intent=new Intent(MyFriendsSearchActivity.this,UserHomeActivity.class);
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
                        makeText(MyFriendsSearchActivity.this,"长按 pos="+pos,Toast.LENGTH_SHORT).show();
                    }
                });


                if (isLoad){
                    adapter.notifyDataSetChanged();
                }else {
                    xRecycler.setAdapter(adapter);
                }

                xRecycler.refreshComplete();

            }

            @Override
            public void onFail(int httpCode, int errorUserCode, String message) {
                Log.i("TAG", "httpCode="+httpCode);
                Log.i("TAG", "errorUserCode="+errorUserCode);
                Log.i("TAG", "message="+message);
            }
        });
    }

    @Override
    protected Fragment initContentFragment() {
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            datas_myFriendsSearch.clear();
        } catch (NullPointerException e) {
        }
    }

    @Override
    public int initLayout() {
        return R.layout.activity_my_friends_search;
    }
}