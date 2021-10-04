package com.supply.medium.socialmedium;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.supply.medium.socialmedium.util.Config;
import com.supply.medium.socialmedium.util.SharedPreferencesManager;

public class LogoutActivity extends AppCompatActivity

         {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        SharedPreferencesManager spm = new SharedPreferencesManager(LogoutActivity.this);
        spm.saveStringValues(Config.EMAIL,"");
        finish();
    }
}
