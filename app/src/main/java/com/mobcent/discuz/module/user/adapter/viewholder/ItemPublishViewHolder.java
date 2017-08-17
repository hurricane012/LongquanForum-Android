package com.mobcent.discuz.module.user.adapter.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.appbyme.dev.R;
import com.bumptech.glide.Glide;
import com.mobcent.discuz.base.UIJumper;
import com.mobcent.discuz.uitls.DateUtils;

import java.util.ArrayList;
import java.util.List;

import discuz.com.bean.me.Publish;


/**
 * Created by pangxiaomin on 16/11/20.
 * 我的发表的item数据显示
 */
public class ItemPublishViewHolder extends RecyclerView.ViewHolder {
    private int from;
    public void bindConent(Publish publish, final Context ctx, final int position, final List<Publish> datas){
        List<ImageView> imageList=new ArrayList<ImageView>(){};
        imageList.add(image01);
        imageList.add(image02);
        imageList.add(image03);
        if(publish!=null){
            if (from==1){
                item_publish_bottom1.setVisibility(View.VISIBLE);
                item_publish_bottom2.setVisibility(View.GONE);
                Glide.with(ctx)
                        .load(publish.getUserAvatar())
                        .into(item_publish_avatar);
                item_publish_name.setText(publish.getUser_nick_name());
                //item_publish_time.setText(publish.getLast_reply_date()+"");
                item_publish_time.setText(DateUtils.stampToDate(String.valueOf(publish.getLast_reply_date())));
                tv_content.setText(publish.getTitle());
                tv_look_num.setText(Integer.toString(publish.getHits()));
                item_publish_reply.setText(Integer.toString(publish.getReplies()));
            }else if (from==2){
                item_publish_bottom1.setVisibility(View.GONE);
                item_publish_bottom2.setVisibility(View.VISIBLE);
                item_publish_head.setVisibility(View.GONE);
                tv_content.setText(publish.getTitle());
                item_publish_bottom2_readed.setText(Integer.toString(publish.getHits())+ctx.getResources().getString(R.string.mc_forum_topic_list_read));
                item_publish_bottom2_time.setText(DateUtils.stampToDate(String.valueOf(publish.getLast_reply_date())));
                item_publish_bottom2_title.setText(publish.getUser_nick_name());
                if (publish.getEssence()==0){
                    item_publish_essence.setVisibility(View.GONE);
                }else {
                    item_publish_essence.setVisibility(View.VISIBLE);
                }
                if (publish.getHot()==0){
                    item_mypublic_text_hot.setVisibility(View.GONE);
                }else {
                    item_mypublic_text_hot.setVisibility(View.VISIBLE);
                }
            }
            int num=publish.getImageList().size();
            if (num>0){
                visible();
                for (int i=0;i<=2;i++){
                    //publish.getImageList().get(i);
                    try {
                        Glide.with(ctx)
                                .load(publish.getImageList().get(i))
                                .into(imageList.get(i));
                    } catch (IndexOutOfBoundsException e) {
                        switch (i){
                            case 1:
                                image02.setVisibility(View.INVISIBLE);
                                image03.setVisibility(View.INVISIBLE);
                                break;
                            case 2:
                                image03.setVisibility(View.INVISIBLE);
                                break;
                        }
                    }
                }
            }else {
                invisible();
            }
        }
        item_publish_total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic_id=datas.get(position).getTopic_id();
                UIJumper.jumpTopic(ctx,Long.parseLong(topic_id));
            }
        });
    }

    private void invisible() {
        image01.setVisibility(View.GONE);
        image02.setVisibility(View.GONE);
        image03.setVisibility(View.GONE);
    }

    private void visible() {
        image01.setVisibility(View.VISIBLE);
        image02.setVisibility(View.VISIBLE);
        image03.setVisibility(View.VISIBLE);
    }

    LinearLayout item_publish_total;
    RelativeLayout item_publish_bottom1;
    LinearLayout item_publish_bottom2;
    RelativeLayout item_publish_head;
    ImageView item_publish_avatar;
    ImageView item_publish_essence;
    TextView item_publish_name;
    TextView item_publish_time;
    TextView tv_content;
    TextView tv_look_num;
    TextView item_publish_reply;
    TextView item_mypublic_text_hot;

    TextView item_publish_bottom2_time;
    TextView item_publish_bottom2_title;
    TextView item_publish_bottom2_readed;
    ImageView image01,image02,image03;

    public ItemPublishViewHolder(View view,int from) {
        super(view);
        this.from=from;
        item_publish_total = (LinearLayout) view.findViewById(R.id.item_publish_total);
        item_publish_head = (RelativeLayout) view.findViewById(R.id.item_public_relative_head);
        item_publish_bottom1 = (RelativeLayout) view.findViewById(R.id.item_public_relative_bottom1);
        item_publish_bottom2 = (LinearLayout) view.findViewById(R.id.item_public_relative_bottom2);
        item_publish_avatar = (ImageView) view.findViewById(R.id.item_publish_avatar);
        item_publish_essence = (ImageView) view.findViewById(R.id.item_mypublic_image_essence);
        item_mypublic_text_hot = (TextView) view.findViewById(R.id.item_mypublic_text_hot);
        item_publish_name = (TextView) view.findViewById(R.id.item_publish_name);
        item_publish_time = (TextView) view.findViewById(R.id.item_publish_time);
        tv_content = (TextView) view.findViewById(R.id.tv_content);
        tv_look_num = (TextView) view.findViewById(R.id.tv_look_num);
        item_publish_reply = (TextView) view.findViewById(R.id.item_publish_reply);
        item_publish_bottom2_time = (TextView) view.findViewById(R.id.item_publish_bottom2_time);
        item_publish_bottom2_title = (TextView) view.findViewById(R.id.item_publish_bottom2_title);
        item_publish_bottom2_readed = (TextView) view.findViewById(R.id.item_publish_bottom2_readed);
        image01 = (ImageView) view.findViewById(R.id.item_image_myfriend_infoImage1);
        image02 = (ImageView) view.findViewById(R.id.item_image_myfriend_infoImage2);
        image03 = (ImageView) view.findViewById(R.id.item_image_myfriend_infoImage3);
    }
}
