package com.mobcent.discuz.module.user.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.appbyme.dev.R;
import com.mobcent.discuz.activity.LoginUtils;
import com.mobcent.discuz.base.WebParamsMap;

import discuz.com.net.service.DiscuzRetrofit;
import discuz.com.net.service.model.bean.block.Block;
import discuz.com.retrofit.library.HTTPSubscriber;

public class MainWeixinTitleRightActivity extends Activity
{
	private TextView block;
	private String uid;
	private TextView adult,politics,coarse,other,reason;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Intent intent=getIntent();
		String from=intent.getStringExtra("from");
		uid=intent.getStringExtra("uid");
		if (from.equals("ReportActivity")){
			setContentView(R.layout.item_report_pull);
			initialReportActivity();

		}else if (from.equals("UserHomeActivity")){
			setContentView(R.layout.item_myfriends_other);
			initialUserHomeActivity();

		}
	}

	private void initialUserHomeActivity() {
		block= (TextView) findViewById(R.id.myfriend_block);
		TextView report= (TextView) findViewById(R.id.myfriend_report);
	}

	private void initialReportActivity() {
		adult= (TextView) findViewById(R.id.report_adult);
		politics= (TextView) findViewById(R.id.report_politics);
		coarse= (TextView) findViewById(R.id.report_coarse);
		other= (TextView) findViewById(R.id.report_other);
		adult.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//finish();
				Intent mIntent = new Intent();
				mIntent.putExtra("reason",R.string.mc_forum_report_adult_content);
				MainWeixinTitleRightActivity.this.setResult(101, mIntent);
				finish();
			}
		});
		politics.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent mIntent = new Intent();
				mIntent.putExtra("reason", R.string.mc_forum_polity_sensi_content);
				MainWeixinTitleRightActivity.this.setResult(101, mIntent);
				finish();
			}
		});
		coarse.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent mIntent = new Intent();
				mIntent.putExtra("reason",R.string.mc_forum_vulgar_speech);
				MainWeixinTitleRightActivity.this.setResult(101, mIntent);
				finish();
			}
		});
		other.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent mIntent = new Intent();
				mIntent.putExtra("reason",R.string.mc_forum_other_reason);
				MainWeixinTitleRightActivity.this.setResult(101, mIntent);
				finish();
			}
		});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		finish();
		overridePendingTransition(0, 0);
		return true;
	}

	public void block(View view)
	{
		String text= (String) block.getText();
		if (text.equals("拉黑")){
			block();
			block.setText(R.string.pickerview_block_cancel);
		}else {
			blockcancle();
			block.setText(R.string.pickerview_block);
		}

	}

	private void blockcancle() {
		DiscuzRetrofit.getUserInfoService(this).blockcancle(LoginUtils.getInstance().getUserId(), WebParamsMap.blockcancle("")).subscribe(new HTTPSubscriber<Block>() {


			@Override
			public void onSuccess(Block block) {
				Toast.makeText(MainWeixinTitleRightActivity.this, R.string.pickerview_block_cancel, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onFail(int httpCode, int errorUserCode, String message) {

			}
		});
	}

	private void block() {
		DiscuzRetrofit.getUserInfoService(this).block(LoginUtils.getInstance().getUserId(), WebParamsMap.block("")).subscribe(new HTTPSubscriber<Block>() {


			@Override
			public void onSuccess(Block block) {
				Toast.makeText(MainWeixinTitleRightActivity.this, R.string.pickerview_block, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onFail(int httpCode, int errorUserCode, String message) {

			}
		});

	}
	public void report(View view)
	{
		Toast.makeText(this, R.string.vp_option_report, Toast.LENGTH_SHORT).show();
		Intent intent=new Intent(this,ReportActivity.class);
		intent.putExtra("uid",uid);
		startActivity(intent);
		finish();
	}

}
