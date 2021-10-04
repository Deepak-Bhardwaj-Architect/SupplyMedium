package com.supply.medium.socialmedium;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.supply.medium.socialmedium.db.RestApiCall;
import com.supply.medium.socialmedium.db.RestApiData;
import com.supply.medium.socialmedium.util.AlertManager;
import com.supply.medium.socialmedium.util.Config;
import com.supply.medium.socialmedium.util.Internet;
import com.supply.medium.socialmedium.util.ResultGet;
import com.supply.medium.socialmedium.util.SharedPreferencesManager;

import org.json.JSONObject;

public class ChangeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,ResultGet {
        EditText curr_password,new_password,con_password;
        Button btnSignInSignIn;
        SharedPreferencesManager spm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_activity);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spm = new SharedPreferencesManager(ChangeActivity.this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        curr_password=(EditText)findViewById(R.id.curr_password);
        new_password=(EditText)findViewById(R.id.new_password);
        con_password=(EditText)findViewById(R.id.con_password);
        btnSignInSignIn=(Button)findViewById(R.id.btnSignInSignIn);
        btnSignInSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(con_password.getText().toString().equals(new_password.getText().toString())) {
                    if (Internet.hasInternet(ChangeActivity.this)) {
                        RestApiCall rac = new RestApiCall(ChangeActivity.this, ChangeActivity.this, Config.CHANGEPASSWORD,
                                RestApiData.changePassword(spm.getStringValues(Config.EMAIL), curr_password.getText().toString(),
                                        new_password.getText().toString()));
                        rac.execute();
                    } else AlertManager.messageDialog(ChangeActivity.this, "Alert!", "");
                }else{
                    Toast.makeText(ChangeActivity.this,R.string.same_password,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_00) { //Home
            Intent intentMain = new Intent(ChangeActivity.this, HomeActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_01) {//Post Your News Feed
            Intent intentMain = new Intent(ChangeActivity.this, PostActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_02) {//Notifications
            Intent intentMain = new Intent(ChangeActivity.this, NotificationActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_03) {//Search Vendor - Add
            Intent intentMain = new Intent(ChangeActivity.this, VendorSearchActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_04) {//Vendors List //////////////////////////////////////////////////////
            Intent intentMain = new Intent(ChangeActivity.this, VendorListActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_05) {//Sent Vendor Requests
            Intent intentMain = new Intent(ChangeActivity.this, VendorSentActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_06) {//Received Vendor Requests
            Intent intentMain = new Intent(ChangeActivity.this, VendorReceivedActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_07) {//Search Friend - Add //////////////////////////////////////////////////////
            Intent intentMain = new Intent(ChangeActivity.this, FriendSearchActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_08) {//Friends List
            Intent intentMain = new Intent(ChangeActivity.this, FriendListActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_09) {//Sent Friend Requests
            Intent intentMain = new Intent(ChangeActivity.this, FriendSentActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_10) {//Received Friend Requests
            Intent intentMain = new Intent(ChangeActivity.this, FriendReceivedActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_11) {//View Messages
            Intent intentMain = new Intent(ChangeActivity.this, MessegeViewActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_12) {//Send a Message
            Intent intentMain = new Intent(ChangeActivity.this, MessageSendActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_13) {//Invite A Friend
            Intent intentMain = new Intent(ChangeActivity.this, InviteActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_14) {//My Profile //////////////////////////////////////////////////////
            Intent intentMain = new Intent(ChangeActivity.this, MyProfileActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_15) {//Change Password //////////////////////////////////////////////////////
            Intent intentMain = new Intent(ChangeActivity.this, ChangeActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_16) {//Logout //////////////////////////////////////////////////////
            Intent intentMain = new Intent(ChangeActivity.this, LogoutActivity.class);
            startActivity(intentMain);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void pickdata(String result) {
        try {
            JSONObject obj = new JSONObject(result);
            if (obj.get("result").equals("SUCCESS")) {
                Toast.makeText(ChangeActivity.this,R.string.password_sucess,Toast.LENGTH_SHORT).show();
                Intent intentMain = new Intent(ChangeActivity.this, HomeActivity.class);
                startActivity(intentMain);
                finish();
            }else{
                Toast.makeText(ChangeActivity.this,R.string.password_fail,Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex){
            Toast.makeText(ChangeActivity.this,R.string.api_error,Toast.LENGTH_SHORT).show();
        }
    }
}
