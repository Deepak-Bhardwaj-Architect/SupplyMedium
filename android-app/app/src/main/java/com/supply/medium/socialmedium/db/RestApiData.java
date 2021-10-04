package com.supply.medium.socialmedium.db;

import android.util.Log;

import java.net.URLEncoder;

/**
 * Created by varsha on 11/26/2016.
 */
public class RestApiData {

    public static String login(String email,String password){
        String data="";
        try {
            data=URLEncoder.encode("xcode", "UTF-8")
                    + "=" + URLEncoder.encode("1", "UTF-8");

            data += "&" + URLEncoder.encode("email", "UTF-8") + "="
                    + URLEncoder.encode(email, "UTF-8");

            data += "&" + URLEncoder.encode("password", "UTF-8")
                    + "=" + URLEncoder.encode(password, "UTF-8");
        }
        catch(Exception ex){
            Log.e("Error","Data for Api:"+ex.getMessage());
            return "";
        }
        return data;
    }

    public static String forgot(String email){
        String data="";
        try {
            data=URLEncoder.encode("xcode", "UTF-8")
                    + "=" + URLEncoder.encode("1", "UTF-8");

            data += "&" + URLEncoder.encode("email", "UTF-8") + "="
                    + URLEncoder.encode(email, "UTF-8");
        }
        catch(Exception ex){
            Log.e("Error","Data for Api:"+ex.getMessage());
            return "";
        }
        return data;
    }

    public static String commonList(){
        String data="";
        try {
            data=URLEncoder.encode("xcode", "UTF-8")
                    + "=" + URLEncoder.encode("1", "UTF-8");
        }
        catch(Exception ex){
            Log.e("Error","Data for Api:"+ex.getMessage());
            return "";
        }
        return data;
    }

    public static String registor(String sign_up_as, String company_name, String company_id, String b_id, String c_id,
                                  String title, String f_name, String l_name, String email, String password,String phone){
        String data="";
        try {
            data=URLEncoder.encode("xcode", "UTF-8")
                    + "=" + URLEncoder.encode("1", "UTF-8");
            data +="&" +URLEncoder.encode("registered_as", "UTF-8")
                    + "=" + URLEncoder.encode(sign_up_as, "UTF-8");
            data +="&" +URLEncoder.encode("registered_name", "UTF-8")
                    + "=" + URLEncoder.encode(company_name, "UTF-8");
            data +="&" +URLEncoder.encode("registered_id", "UTF-8")
                    + "=" + URLEncoder.encode(company_id, "UTF-8");
            data +="&" +URLEncoder.encode("category", "UTF-8")
                    + "=" + URLEncoder.encode(b_id, "UTF-8");
            data +="&" +URLEncoder.encode("country", "UTF-8")
                    + "=" + URLEncoder.encode(c_id, "UTF-8");
            data +="&" +URLEncoder.encode("title", "UTF-8")
                    + "=" + URLEncoder.encode(title, "UTF-8");
            data +="&" +URLEncoder.encode("fname", "UTF-8")
                    + "=" + URLEncoder.encode(f_name, "UTF-8");
            data +="&" +URLEncoder.encode("lname", "UTF-8")
                    + "=" + URLEncoder.encode(l_name, "UTF-8");
            data +="&" +URLEncoder.encode("mobile", "UTF-8")
                    + "=" + URLEncoder.encode(phone, "UTF-8");
            data +="&" +URLEncoder.encode("email", "UTF-8")
                    + "=" + URLEncoder.encode(email, "UTF-8");
            data +="&" +URLEncoder.encode("password", "UTF-8")
                    + "=" + URLEncoder.encode(password, "UTF-8");
        }
        catch(Exception ex){
            Log.e("Error","Data for Api:"+ex.getMessage());
            return "";
        }
        return data;
    }

    public static String commonFeedsList(String email){
        String data="";
        try {
            data=URLEncoder.encode("xcode", "UTF-8")
                    + "=" + URLEncoder.encode("1", "UTF-8");
            data +="&" +URLEncoder.encode("email", "UTF-8")
                    + "=" + URLEncoder.encode(email, "UTF-8");
        }
        catch(Exception ex){
            Log.e("Error","Data for Api:"+ex.getMessage());
            return "";
        }
        return data;
    }

    public static String feedsPost(String email,String desc){
        String data="";
        try {
            data=URLEncoder.encode("xcode", "UTF-8")
                    + "=" + URLEncoder.encode("1", "UTF-8");
            data +="&" +URLEncoder.encode("email", "UTF-8")
                    + "=" + URLEncoder.encode(email, "UTF-8");
            data +="&" +URLEncoder.encode("post_title", "UTF-8")
                    + "=" + URLEncoder.encode("Test", "UTF-8");
            data +="&" +URLEncoder.encode("post_description", "UTF-8")
                    + "=" + URLEncoder.encode(desc, "UTF-8");
        }
        catch(Exception ex){
            Log.e("Error","Data for Api:"+ex.getMessage());
            return "";
        }
        return data;
    }

    public static String invite(String email,String user_email){
        String data="";
        try {
            data=URLEncoder.encode("xcode", "UTF-8")
                    + "=" + URLEncoder.encode("1", "UTF-8");
            data +="&" +URLEncoder.encode("my_email", "UTF-8")
                    + "=" + URLEncoder.encode(email, "UTF-8");
            data +="&" +URLEncoder.encode("user_email", "UTF-8")
                    + "=" + URLEncoder.encode(user_email, "UTF-8");
        }
        catch(Exception ex){
            Log.e("Error","Data for Api:"+ex.getMessage());
            return "";
        }
        return data;
    }

    public static String changePassword(String email,String current,String newpassword){
        String data="";
        try {
            data=URLEncoder.encode("xcode", "UTF-8")
                    + "=" + URLEncoder.encode("1", "UTF-8");
            data +="&" +URLEncoder.encode("email", "UTF-8")
                    + "=" + URLEncoder.encode(email, "UTF-8");
            data +="&" +URLEncoder.encode("new_pass", "UTF-8")
                    + "=" + URLEncoder.encode(newpassword, "UTF-8");
            data +="&" +URLEncoder.encode("current_pass", "UTF-8")
                    + "=" + URLEncoder.encode(current, "UTF-8");
        }
        catch(Exception ex){
            Log.e("Error","Data for Api:"+ex.getMessage());
            return "";
        }
        return data;
    }

}
