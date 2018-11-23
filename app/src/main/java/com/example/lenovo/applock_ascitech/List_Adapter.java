package com.example.lenovo.applock_ascitech;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Lenovo on 11/21/2018.
 */

public class List_Adapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<AppList> listStorage;
    PackageManager manager;

    Context context;

    public List_Adapter(Context context, List<AppList> customizedListView) {
        this.context=context;
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listStorage = customizedListView;

    }

    @Override
    public int getCount() {
        return listStorage.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder listViewHolder;
        if(convertView == null){
            listViewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.list, parent, false);

            listViewHolder.textView = (TextView)convertView.findViewById(R.id.app_name);
            listViewHolder.imageView = (ImageView)convertView.findViewById(R.id.app_icon);

            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (ViewHolder)convertView.getTag();
        }
        listViewHolder.textView.setText(listStorage.get(position).getName());
        listViewHolder.imageView.setImageDrawable(listStorage.get(position).getIcon());

        return convertView;
    }

    static class ViewHolder{

        TextView textView;
        ImageView imageView;
        CheckBox checkBox;
    }
}
