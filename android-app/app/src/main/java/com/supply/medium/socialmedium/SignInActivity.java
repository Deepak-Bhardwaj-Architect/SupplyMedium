package com.supply.medium.socialmedium;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class SignInActivity extends Activity implements View.OnClickListener,ResultGet {
    SharedPreferencesManager spm;
    Button signIn,SignUp,forgot;
    Context mcon;
    EditText password,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mcon=this;
        spm = new SharedPreferencesManager(mcon);
        signIn=(Button)findViewById(R.id.btnSignInSignIn);
        SignUp=(Button)findViewById(R.id.btnSignInSignUp);
        forgot=(Button)findViewById(R.id.btnSignInForgot);

        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);


        signIn.setOnClickListener(this);
        SignUp.setOnClickListener(this);
        forgot.setOnClickListener(this);
    }
    int type=0;
    public void onClick(View v) {

        if (v.getId() == R.id.btnSignInSignIn) {
            type=1;
            if (Internet.hasInternet(SignInActivity.this)) {
                RestApiCall rac=new RestApiCall(mcon,SignInActivity.this, Config.SIGNIN,RestApiData.login(email.getText().toString(),password.getText().toString()));
                rac.execute();
            } else AlertManager.messageDialog(SignInActivity.this, "Alert!", "");
        }
        else if (v.getId() == R.id.btnSignInSignUp) {
            type=2;
            Intent intentMain = new Intent(SignInActivity.this, SignUpActivity.class);
            startActivity(intentMain);
            finish();
        }
        else if (v.getId() == R.id.btnSignInForgot) {
            Intent intentMain = new Intent(SignInActivity.this, ForgotActivity.class);
            startActivity(intentMain);
            finish();
        }

    }

    @Override
    public void pickdata(String result) {
        Log.v("aass:","aass:"+result);
    try {
        JSONObject obj = new JSONObject(result);
        if (obj.get("result").equals("SUCCESS")) {
            if(type==1) {
                spm.saveStringValues(Config.EMAIL, email.getText().toString());
                Intent intentMain = new Intent(SignInActivity.this, HomeActivity.class);
                startActivity(intentMain);
                finish();
            }
        }else{
            Toast.makeText(SignInActivity.this,R.string.login_fail,Toast.LENGTH_SHORT).show();
        }
    }catch (Exception ex){
        Toast.makeText(SignInActivity.this,R.string.api_error,Toast.LENGTH_SHORT).show();
    }

    }
}
