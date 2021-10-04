package com.supply.medium.socialmedium.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.supply.medium.socialmedium.R;
import com.supply.medium.socialmedium.model.PostsModel;

import java.util.ArrayList;

/**
 * Created by nisha on 10/3/2017.
 */

public class ListPostAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<PostsModel> data;
    public Resources res;
    PostsModel tempValues=null;
    LayoutInflater inflater;
    public ListPostAdapter(Context context, ArrayList<PostsModel> objects) {
        super();
        this.activity = (Activity)context;
        this.data = objects;
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
        View row = inflater.inflate(R.layout.list_view_post, parent, false);

        /***** Get each Model object from Arraylist ********/
        tempValues = null;
        tempValues = (PostsModel) data.get(position);

        TextView post_comments= (TextView)row.findViewById(R.id.post_comments);
        TextView post_like          = (TextView)row.findViewById(R.id.post_like);
        TextView post_desc          = (TextView)row.findViewById(R.id.post_desc);
        TextView post_title          = (TextView)row.findViewById(R.id.post_title);

            // Set values for spinner each row
        post_comments.setText(tempValues.getPost_comments());
        post_like.setText(tempValues.getPost_like());
        post_desc.setText(tempValues.getPost_desc());
        post_title.setText(tempValues.getPost_title());
        return row;
    }
}
