package com.supply.medium.socialmedium;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import com.supply.medium.socialmedium.adapter.ListPostAdapter;
import com.supply.medium.socialmedium.db.RestApiCall;
import com.supply.medium.socialmedium.db.RestApiData;
import com.supply.medium.socialmedium.model.PostsModel;
import com.supply.medium.socialmedium.util.AlertManager;
import com.supply.medium.socialmedium.util.Config;
import com.supply.medium.socialmedium.util.Internet;
import com.supply.medium.socialmedium.util.ResultGet;
import com.supply.medium.socialmedium.util.SharedPreferencesManager;

import org.json.JSONObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,ResultGet {
    SharedPreferencesManager spm;
    ArrayList<PostsModel> arraypm=new ArrayList<>();
    ListView post_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        spm = new SharedPreferencesManager(HomeActivity.this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMain = new Intent(HomeActivity.this, PostActivity.class);
                startActivity(intentMain);
                finish();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        post_list=(ListView)findViewById(R.id.post_list);
        if (Internet.hasInternet(HomeActivity.this)) {
            RestApiCall rac=new RestApiCall(HomeActivity.this,HomeActivity.this, Config.FEEDSDISPLAY, RestApiData.commonFeedsList(spm.getStringValues(Config.EMAIL)));
            rac.execute();
        } else AlertManager.messageDialog(HomeActivity.this, "Alert!", "");
    }
    @Override
    public void pickdata(String result) {
        Log.v("result","result::"+result);
        try {
            JSONObject obj = new JSONObject(result);
           if (obj.get("result").equals("SUCCESS")) {
              JSONObject objdata=new JSONObject(obj.getString("data"));

               JSONObject data=new JSONObject(objdata.getString("data"));

               for(int i=1;i<=Integer.parseInt(objdata.getString("size"));i++){
                   JSONObject jodata=data.getJSONObject(String.valueOf(i));
                   Log.v("log","data:.."+jodata);
                   PostsModel pm = new PostsModel();
                   pm.setPost_comments(jodata.getString("comment_count"));
                   pm.setPost_desc(jodata.getString("post_detail"));
                   pm.setPost_like(jodata.getString("like_count"));
                   pm.setPost_title(jodata.getString("post_title"));
                   arraypm.add(pm);
               }
               ListPostAdapter adapter = new ListPostAdapter(this,arraypm);
               post_list.setAdapter(adapter);
            }else{
               Toast.makeText(HomeActivity.this,R.string.api_error,Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

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
            Intent intentMain = new Intent(HomeActivity.this, HomeActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_01) {//Post Your News Feed
            Intent intentMain = new Intent(HomeActivity.this, PostActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_02) {//Notifications
            Intent intentMain = new Intent(HomeActivity.this, NotificationActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_03) {//Search Vendor - Add
            Intent intentMain = new Intent(HomeActivity.this, VendorSearchActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_04) {//Vendors List //////////////////////////////////////////////////////
            Intent intentMain = new Intent(HomeActivity.this, VendorListActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_05) {//Sent Vendor Requests
            Intent intentMain = new Intent(HomeActivity.this, VendorSentActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_06) {//Received Vendor Requests
            Intent intentMain = new Intent(HomeActivity.this, VendorReceivedActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_07) {//Search Friend - Add //////////////////////////////////////////////////////
            Intent intentMain = new Intent(HomeActivity.this, FriendSearchActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_08) {//Friends List
            Intent intentMain = new Intent(HomeActivity.this, FriendListActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_09) {//Sent Friend Requests
            Intent intentMain = new Intent(HomeActivity.this, FriendSentActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_10) {//Received Friend Requests
            Intent intentMain = new Intent(HomeActivity.this, FriendReceivedActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_11) {//View Messages
            Intent intentMain = new Intent(HomeActivity.this, MessegeViewActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_12) {//Send a Message
            Intent intentMain = new Intent(HomeActivity.this, MessageSendActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_13) {//Invite A Friend
            Intent intentMain = new Intent(HomeActivity.this, InviteActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_14) {//My Profile //////////////////////////////////////////////////////
            Intent intentMain = new Intent(HomeActivity.this, MyProfileActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_15) {//Change Password //////////////////////////////////////////////////////
            Intent intentMain = new Intent(HomeActivity.this, ChangeActivity.class);
            startActivity(intentMain);
            finish();

        } else if (id == R.id.nav_16) {//Logout //////////////////////////////////////////////////////
            Intent intentMain = new Intent(HomeActivity.this, LogoutActivity.class);
            startActivity(intentMain);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
