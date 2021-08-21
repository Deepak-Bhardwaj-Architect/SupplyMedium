package com.supply.medium.socialmedium;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import org.json.JSONObject;

public class ForgotActivity extends Activity implements View.OnClickListener,ResultGet {

    Button signIn,SignUp,forgot;
    EditText email;
    Context mcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mcon=this;

        signIn=(Button)findViewById(R.id.btnForgotSignIn);
        SignUp=(Button)findViewById(R.id.btnForgotSignUp);
        forgot=(Button)findViewById(R.id.btnForgotForgot);

        email=(EditText)findViewById(R.id.email);

        signIn.setOnClickListener(this);
        SignUp.setOnClickListener(this);
        forgot.setOnClickListener(this);

    }

    public void onClick(View v) {

        if (v.getId() == R.id.btnForgotSignIn) {
            Intent intentMain = new Intent(ForgotActivity.this, SignInActivity.class);
            startActivity(intentMain);
            finish();
        }
        else if (v.getId() == R.id.btnForgotSignUp) {
            Intent intentMain = new Intent(ForgotActivity.this, SignUpActivity.class);
            startActivity(intentMain);
            finish();
        }
        else if (v.getId() == R.id.btnForgotForgot) {
            if (Internet.hasInternet(ForgotActivity.this)) {
                if (isValid()) {
                    RestApiCall rac = new RestApiCall(mcon, ForgotActivity.this, Config.FORGOT, RestApiData.forgot(email.getText().toString()));
                    rac.execute();
                }
            } else AlertManager.messageDialog(ForgotActivity.this, "Alert!", "");
        }

    }

    private boolean isValid() {
        if (email.getText().toString().trim().length() == 0) {
            showMessage("Alert!", "Please enter you email/username");
            return false;
        } else if (email.getText().toString().contains("@") && !isEmailValid(email.getText().toString().trim())) {
            showMessage("Alert!", "Invalid email! Please enter valid email.");
            return false;
        }
        return true;
    }
    private void showMessage(String title, String message) {
        AlertManager.validationDialog(this, title, message);
    }
    public final static boolean isEmailValid(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    @Override
    public void pickdata(String result) {
        try {
            JSONObject obj = new JSONObject(result);
            if (obj.get("result").equals("SUCCESS EMAIL SENT")) {
                    Intent intentMain = new Intent(ForgotActivity.this, SignInActivity.class);
                    startActivity(intentMain);
                    finish();
            }else{
               Toast.makeText(ForgotActivity.this,R.string.forgot_email_fail,Toast.LENGTH_SHORT).show();
            }
        }catch (Exception ex){
            Toast.makeText(ForgotActivity.this,R.string.api_error,Toast.LENGTH_SHORT).show();
        }

    }
}
