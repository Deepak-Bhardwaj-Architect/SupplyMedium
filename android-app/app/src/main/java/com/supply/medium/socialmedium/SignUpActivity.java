package com.supply.medium.socialmedium;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.supply.medium.socialmedium.adapter.ListCustomAdapter;
import com.supply.medium.socialmedium.db.RestApiCall;
import com.supply.medium.socialmedium.db.RestApiData;
import com.supply.medium.socialmedium.model.BusinessModel;
import com.supply.medium.socialmedium.util.AlertManager;
import com.supply.medium.socialmedium.util.Config;
import com.supply.medium.socialmedium.util.Internet;
import com.supply.medium.socialmedium.util.ResultGet;
import com.supply.medium.socialmedium.util.SharedPreferencesManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SignUpActivity extends Activity implements View.OnClickListener,ResultGet {

    Button signIn,SignUp,forgot;
    Spinner sign_up_as,buss_cat,country,title;
    EditText company_name,company_id,f_name,l_name,p_home,email,password,c_password;
    int type=0;
    String b_id;
    String c_id;
    ArrayList<BusinessModel> title_data = new ArrayList<BusinessModel>();
    ListCustomAdapter spinnertitleArrayAdapter;
    String title_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        sign_up_as=(Spinner) findViewById(R.id.sign_up_as);
        buss_cat=(Spinner) findViewById(R.id.business_category);
        country=(Spinner) findViewById(R.id.country);
        title=(Spinner) findViewById(R.id.title);
        company_name=(EditText) findViewById(R.id.company_name);
        company_id=(EditText) findViewById(R.id.company_id);
        f_name=(EditText) findViewById(R.id.f_name);
        l_name=(EditText) findViewById(R.id.l_name);
        p_home=(EditText) findViewById(R.id.p_home);
        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        c_password=(EditText) findViewById(R.id.c_password);

        signIn=(Button)findViewById(R.id.btnSignUpSignIn);
        SignUp=(Button)findViewById(R.id.btnSignUpSignUp);
        forgot=(Button)findViewById(R.id.btnSignUpForgot);

        signIn.setOnClickListener(this);
        SignUp.setOnClickListener(this);
        forgot.setOnClickListener(this);
        type=1;
        loadData(1);
        loadItems();
        //title_data.clear();

        BusinessModel bmone = new BusinessModel();bmone.set_id("0");
        bmone.set_name("MRS");title_data.add(bmone);

        BusinessModel bm = new BusinessModel();bm.set_id("0");
        bm.set_name("MR");title_data.add(bm);

        BusinessModel bmtwo = new BusinessModel();bmtwo.set_id("0");
        bmtwo.set_name("MS");title_data.add(bmtwo);

        bmtwo = new BusinessModel();bmtwo.set_id("0");
        bmtwo.set_name("MRS");title_data.add(bmtwo);

        Log.v("sizes","size:"+title_data.size());

        spinnertitleArrayAdapter = new ListCustomAdapter(this,title_data,3);
        title.setAdapter(spinnertitleArrayAdapter);
    }

    private void loadItems() {
        buss_cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                b_id = ((TextView) v.findViewById(R.id._id)).getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            // your code here
            }
        });
        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {

                c_id = ((TextView) v.findViewById(R.id._id)).getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        title.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View v, int position, long id) {
                title_name = ((TextView) v.findViewById(R.id.name)).getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

    }

    private void loadData(int type) {
        if (Internet.hasInternet(SignUpActivity.this)) {
            RestApiCall rac;
            if(type==1) {
                rac = new RestApiCall(SignUpActivity.this, SignUpActivity.this, Config.BUSS_CAT, RestApiData.commonList());
            }
            else{
                rac = new RestApiCall(SignUpActivity.this, SignUpActivity.this, Config.COUNTRY, RestApiData.commonList());
            }
            rac.execute();
        } else AlertManager.messageDialog(SignUpActivity.this, "Alert!", "");

    }
    ArrayList<BusinessModel> busscat_data = new ArrayList<BusinessModel>();
    ArrayList<BusinessModel> country_data = new ArrayList<BusinessModel>();
    public void onClick(View v) {

        if (v.getId() == R.id.btnSignUpSignIn) {
            Intent intentMain = new Intent(SignUpActivity.this, SignInActivity.class);
            startActivity(intentMain);
            finish();
        }
        else if (v.getId() == R.id.btnSignUpSignUp) {
            type=3;
            if (Internet.hasInternet(SignUpActivity.this)) {
                RestApiCall rac = new RestApiCall(SignUpActivity.this, SignUpActivity.this, Config.SIGNUP, RestApiData.registor(
                        sign_up_as.getSelectedItem().toString(),company_name.getText().toString(),company_id.getText().toString(),
                        b_id,c_id,title_name,f_name.getText().toString(),
                        l_name.getText().toString(),email.getText().toString(),password.getText().toString(),p_home.getText().toString()));
                rac.execute();
            } else AlertManager.messageDialog(SignUpActivity.this, "Alert!", "");
        }
        else if (v.getId() == R.id.btnSignUpForgot) {
            Intent intentMain = new Intent(SignUpActivity.this, ForgotActivity.class);
            startActivity(intentMain);
            finish();
        }
    }
    ListCustomAdapter spinnerArrayAdapter;
    @Override
    public void pickdata(String result) {
    if(type==1 || type==2){
        try {
            JSONObject obj = new JSONObject(result);
            Log.v("data","data:.."+obj);
            if (obj.get("result").equals("SUCCESS")) {
                Log.v("data","data:.."+obj.get("data"));
                JSONArray data=obj.getJSONArray("data");

                for(int i=0;i<data.length();i++){
                    JSONObject jo=(JSONObject)data.get(i);
                    BusinessModel bm = new BusinessModel();
                    if(type==2){
                        bm.set_id(jo.getString("country_key"));
                        bm.set_name(jo.getString("country_name"));
                        country_data.add(bm);
                    }else {
                        bm.set_id(jo.getString("business_category_key"));
                        bm.set_name(jo.getString("business_category_name"));
                        busscat_data.add(bm);
                    }
                }
                if(type==2) {
                    spinnerArrayAdapter = new ListCustomAdapter(this,country_data,2);
                    country.setAdapter(spinnerArrayAdapter);
                }else{
                    spinnerArrayAdapter = new ListCustomAdapter(this,busscat_data,1);
                    buss_cat.setAdapter(spinnerArrayAdapter);
                }

            }else{
                Toast.makeText(SignUpActivity.this,R.string.api_error,Toast.LENGTH_SHORT).show();
            }
            if(type==1) {
                type = 2;
                loadData(2);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }else{
        try {
            JSONObject obj = new JSONObject(result);
            if (obj.get("result").equals("SUCCESS")) {
                spm = new SharedPreferencesManager(SignUpActivity.this);
                spm.saveStringValues(Config.EMAIL, email.getText().toString());
                Intent intentMain = new Intent(SignUpActivity.this, HomeActivity.class);
                startActivity(intentMain);
                finish();
            }else{
                Toast.makeText(SignUpActivity.this,R.string.registor_fail,Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    }
    SharedPreferencesManager spm;
}
