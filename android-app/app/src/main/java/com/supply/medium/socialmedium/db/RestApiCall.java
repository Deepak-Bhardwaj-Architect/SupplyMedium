package com.supply.medium.socialmedium.db;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.supply.medium.socialmedium.util.Config;
import com.supply.medium.socialmedium.util.ResultGet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class RestApiCall extends AsyncTask<String, String, String>  {
    Context mcon;
    ProgressDialog pdialog;
    ResultGet rg;
    String urlpage;
    String urldata;
    public RestApiCall(Context mcon, ResultGet rg, String urlpage, String urldata){
        //set context variables if required
        this.mcon=mcon;
        this.rg=rg;
        this.urlpage=urlpage;
        this.urldata=urldata;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pdialog=new ProgressDialog(mcon);
        pdialog.setMessage("Loading...");
        pdialog.show();
    }


    @Override
    protected String doInBackground(String... params) {
        BufferedReader reader=null;

        String text="";
        try
        {
            URL url = new URL(Config.APIURL+urlpage);
            // Send POST data request
            URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(urldata);
            wr.flush();
            // Get the server response
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line = null;
            // Read Server Response
            while((line = reader.readLine()) != null)
            {
                // Append server response in string
                sb.append(line + "\n");
            }


            text = sb.toString();
        }
        catch(Exception ex)
        {
            Log.v("Error:","Error:."+ex.getMessage());
        }
        finally
        {
            try
            {

                reader.close();
            }

            catch(Exception ex) {Log.v("Error:","Error 2:."+ex.getMessage());}
        }

        // Show response on activity



        return text;
    }


    @Override
    protected void onPostExecute(String result) {

        //Update the UI
        rg.pickdata(result);
        pdialog.dismiss();
    }

}