package com.mobcent.discuz.module.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appbyme.dev.R;
import com.mobcent.discuz.activity.BasePopActivity;
import com.mobcent.discuz.activity.LoginUtils;
import com.mobcent.discuz.base.WebParamsMap;

import discuz.com.net.service.DiscuzRetrofit;
import discuz.com.net.service.model.bean.block.Block;
import discuz.com.retrofit.library.HTTPSubscriber;

/**
 * 举报功能页面
 */
public class ReportActivity extends BasePopActivity {
    private String uid;
    private ImageView img;
    private TextView reason;
    private EditText edittext_reason;
    @Override
    public void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        Intent intent=getIntent();
        uid=intent.getStringExtra("uid");
        reason= (TextView) findViewById(R.id.report_reason);
        edittext_reason= (EditText) findViewById(R.id.report_edittext_reason);
        img= (ImageView) findViewById(R.id.img_report_pull);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ReportActivity.this,MainWeixinTitleRightActivity.class);
                intent.putExtra("from","ReportActivity");
                startActivityForResult(intent,101);
            }
        });
    }

    @Override
    public int initLayout() {
        return R.layout.activity_report;
    }

    @Override
    protected Fragment initContentFragment() {
        return null;
    }

    @Override
    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);

        String intent= null;
        try {
            intent = paramIntent.getStringExtra("reason");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        reason.setText(intent);
    }
    public void submit(View view){
        String text=edittext_reason.getText().toString();
        DiscuzRetrofit.getUserInfoService(this).report(LoginUtils.getInstance().getUserId(), WebParamsMap.report(uid,text)).subscribe(new HTTPSubscriber<Block>() {


            @Override
            public void onSuccess(Block block) {
                if (block.getErrcode().equals("举报成功")){
                    Toast.makeText(ReportActivity.this,R.string.vp_option_report_success,Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(ReportActivity.this,R.string.error_type_error,Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFail(int httpCode, int errorUserCode, String message) {

            }
        });
    }
}
