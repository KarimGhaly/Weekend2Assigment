package com.example.admin.weekend2assigment;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 9/9/2017.
 */

public class CustomAdabter extends BaseAdapter {

    Context context;
    static LayoutInflater layoutInflater = null;
    List<Object> objectList = new ArrayList<>();

    public CustomAdabter(Context context, List<Object> objectList) {
        this.context = context;
        this.objectList = objectList;
    }

    @Override
    public int getCount() {
        return objectList.size();
    }

    @Override
    public Object getItem(int position) {
        return getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if(row == null)
        {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.list_layout,null);
        }

        TextView txtID = (TextView) row.findViewById(R.id.txtID);
        TextView txtName = (TextView) row.findViewById(R.id.txtName);
        TextView txtDesc = (TextView) row.findViewById(R.id.TxtDesc);

        txtID.setText(String.valueOf(objectList.get(position).getID()));
        txtName.setText(objectList.get(position).getName());
        txtDesc.setText(objectList.get(position).getDescription());


        return row;
    }
}
