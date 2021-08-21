package com.supply.medium.socialmedium.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.supply.medium.socialmedium.R;
import com.supply.medium.socialmedium.model.BusinessModel;

import java.util.ArrayList;

/**
 * Created by nisha on 10/3/2017.
 */

public class ListCustomAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<BusinessModel> data;
    public Resources res;
    BusinessModel tempValues=null;
    LayoutInflater inflater;
    int type=1;
    public ListCustomAdapter(Context context, ArrayList<BusinessModel> objects,int type) {
        super();
        this.activity = (Activity)context;
        this.data = objects;
        this.type = type;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    // This funtion called for each row ( Called data.size() times )
    public View getCustomView(int position, View convertView, ViewGroup parent) {

        /********** Inflate spinner_rows.xml file for each row ( Defined below ) ************/

        LayoutInflater inflater = ((Activity) activity).getLayoutInflater();
        View row = inflater.inflate(R.layout.spinner_rows, parent, false);

        /***** Get each Model object from Arraylist ********/
        tempValues = null;
        tempValues = (BusinessModel) data.get(position);

        Log.v("data","data:"+data.size());

        TextView label        = (TextView)row.findViewById(R.id.name);
        TextView sub          = (TextView)row.findViewById(R.id._id);

        if(position==0){
            // Default selected Spinner item
            if(type==1) {
                label.setText("Please select Business Category");
            }else if(type==2) {
                label.setText("Please select Country");
            }else{
                label.setText("Please select Title");
            }
            sub.setText("");
        }
        else
        {
            //if(type==1 || type==2) {
                label.setText(tempValues.get_name());
                sub.setText(tempValues.get_id());
           // }
        }

        return row;
    }
}
