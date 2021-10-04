package com.supply.medium.socialmedium;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.supply.medium.socialmedium.util.Config;
import com.supply.medium.socialmedium.util.SharedPreferencesManager;

public class MainActivity extends Activity {
    SharedPreferencesManager spm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spm = new SharedPreferencesManager(MainActivity.this);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                if(spm.getStringValues(Config.EMAIL).equals("")) {
                    Intent intentMain = new Intent(MainActivity.this, SignInActivity.class);
                    startActivity(intentMain);
                    finish();
                }else{
                    Intent intentMain = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intentMain);
                    finish();
                }
            }
        }, 3000);
    }
}
