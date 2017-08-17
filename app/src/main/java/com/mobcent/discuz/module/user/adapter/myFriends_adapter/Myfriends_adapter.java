package com.mobcent.discuz.module.user.adapter.myFriends_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.appbyme.dev.R;
import com.bumptech.glide.Glide;
import com.mobcent.discuz.module.user.adapter.collectionAdapter.CollectionRecycle_adapter;
import com.mobcent.discuz.uitls.DateUtils;

import java.util.List;

import discuz.com.net.service.model.bean.MyFriend.Lists;


/**
 * Created by $Createdbymynameon on 2017/4/7.
 */

public class Myfriends_adapter extends RecyclerView.Adapter<Myfriends_adapter.ViewHolder> {
    public static List<Lists> datas_myFriendsSearch;
    private Context context;
    private EditText edittext;
    private Button search_button;
    private String nickname;
    public void setMyfriends_adapter(Context context, List datass) {
        if (datas_myFriendsSearch==null){
            this.datas_myFriendsSearch = datass;
        }else {
            datas_myFriendsSearch.addAll(datass);
            notifyDataSetChanged();
        }
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_myfriends,viewGroup,false);
        Myfriends_adapter.ViewHolder vh = new Myfriends_adapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String date= DateUtils.stampToDate(datas_myFriendsSearch.get(position).getDateline());
        String signture=datas_myFriendsSearch.get(position).getSignture();
        holder.nickname.setText(datas_myFriendsSearch.get(position).getName());
        holder.grade.setText(datas_myFriendsSearch.get(position).getUserTitle());
        if (!signture.equals("")){
            holder.other.setText(datas_myFriendsSearch.get(position).getSignture());
        }

        holder.date.setText(date);
        Glide.with(context).load(datas_myFriendsSearch.get(position).getIcon()).into(holder.head);
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
        return datas_myFriendsSearch.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView head;
        TextView nickname,grade,other,date;
        LinearLayout total;
        public ViewHolder(View view){
            super(view);
            total= (LinearLayout) view.findViewById(R.id.linearlayout_myfriends_search_total);
            nickname= (TextView) view.findViewById(R.id.item_myfriends_nickname);
            grade= (TextView) view.findViewById(R.id.item_myfriends_grade);
            other= (TextView) view.findViewById(R.id.item_myfriends_other);
            date= (TextView) view.findViewById(R.id.item_myfriends_date);
            head = (ImageView)view.findViewById(R.id.item_myfriends_header);
        }
    }
    public void clear(){
        datas_myFriendsSearch.clear();
    }
    public void addAll(List<Lists> list, boolean isClear){
        if(isClear){
            datas_myFriendsSearch.clear();
        }
        datas_myFriendsSearch.addAll(list);
        notifyDataSetChanged();
    }

    private CollectionRecycle_adapter.OnItemClickLitener onItemClickLitener;

    public void setOnItemClickLitener(CollectionRecycle_adapter.OnItemClickLitener onItemClickLitener) {
        this.onItemClickLitener = onItemClickLitener;
    }
}
