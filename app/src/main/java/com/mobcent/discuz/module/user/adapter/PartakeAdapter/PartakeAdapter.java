package com.mobcent.discuz.module.user.adapter.PartakeAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appbyme.dev.R;
import com.bumptech.glide.Glide;
import com.mobcent.discuz.module.user.adapter.collectionAdapter.CollectionRecycle_adapter;
import com.mobcent.discuz.uitls.DateUtils;

import java.util.List;

import discuz.com.net.service.model.bean.my_reply.Lists;

/**
 * Created by $Createdbymynameon on 2017/8/7.
 */

public class PartakeAdapter extends RecyclerView.Adapter<PartakeAdapter.ViewHolder>  {
    public static List<Lists> datas_PartakeMyReply;
    private Context context;

    public PartakeAdapter(Context context, List<Lists> datas) {
        if (datas_PartakeMyReply==null){
            this.datas_PartakeMyReply= datas;
        }else {
            datas_PartakeMyReply.addAll(datas);
            notifyDataSetChanged();
        }
        this.context=context;
    }

    @Override
    public PartakeAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_collection_follow,viewGroup,false);
        PartakeAdapter.ViewHolder vh = new PartakeAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final PartakeAdapter.ViewHolder holder, int position) {
        int essence=datas_PartakeMyReply.get(position).getEssence();
        if (essence==0){
            holder.cream.setVisibility(View.GONE);
        }else {
            holder.cream.setVisibility(View.VISIBLE);
        }
        String date= DateUtils.stampToDate(datas_PartakeMyReply.get(position).getLast_reply_date());
        holder.title.setText(datas_PartakeMyReply.get(position).getTitle());
        holder.date.setText(date);
        holder.name.setText(datas_PartakeMyReply.get(position).getBoard_name());
        holder.readed.setText(datas_PartakeMyReply.get(position).getHits()+"阅读");
        Glide.with(context).load(datas_PartakeMyReply.get(position).getPic_path()).into(holder.head);

        //设置点击事件
        holder.total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getLayoutPosition();
                onItemClickLitener.onitemclick(holder.total, pos);
            }
        });

        //设置长按点击事件
        holder.total.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int pos = holder.getLayoutPosition();
                onItemClickLitener.onitemlongclick(holder.total, pos);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.i("TAG", "datas.size()="+datas_PartakeMyReply.size());
        return datas_PartakeMyReply.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout total;
        ImageView head,cream;
        TextView title,date,name,readed;
        public ViewHolder(View view){
            super(view);
            total = (RelativeLayout) view.findViewById(R.id.item_collection_total);
            title = (TextView) view.findViewById(R.id.item_collection_title);
            date = (TextView) view.findViewById(R.id.item_collection_date);
            name = (TextView) view.findViewById(R.id.item_collection_name);
            readed = (TextView) view.findViewById(R.id.item_collection_readed);
            cream = (ImageView) view.findViewById(R.id.item_collection_imageview_cream);
            head = (ImageView)view.findViewById(R.id.item_collection_imageview_head);
        }
    }

    private CollectionRecycle_adapter.OnItemClickLitener onItemClickLitener;

    public void setOnItemClickLitener(CollectionRecycle_adapter.OnItemClickLitener onItemClickLitener) {
        this.onItemClickLitener = onItemClickLitener;
    }
}
