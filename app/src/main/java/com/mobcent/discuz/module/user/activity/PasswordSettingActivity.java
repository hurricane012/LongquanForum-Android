package com.mobcent.discuz.module.user.activity;

import android.os.Bundle;
import android.app.Activity;

import com.appbyme.dev.R;

public class PasswordSettingActivity extends Activity {
    /**
     * 找回密码
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_setting_fragment);
    }

}
