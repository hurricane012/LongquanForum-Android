package com.mobcent.discuz.module.user.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.appbyme.dev.R;
import com.mobcent.discuz.module.user.adapter.viewholder.ItemPublishViewHolder;

import java.util.List;

import discuz.com.bean.me.Publish;

/**
 * Created by pangxiaomin on 16/11/20.
 * 我的发表
 * @author 张春生
 */
public class UserPublishAdapter extends RecyclerView.Adapter {

    private Context context;
    public List<Publish> datas;
    private int from;

    public UserPublishAdapter(Context context,List<Publish> datas,int from) {
        this.context = context;
        this.datas = datas;
        this.from = from;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemPublishViewHolder(View.inflate(context, R.layout.item_recycler_publish,null),from);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ItemPublishViewHolder holerData = (ItemPublishViewHolder) holder;
        holerData.bindConent(datas.get(position),context,position,datas);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

}
