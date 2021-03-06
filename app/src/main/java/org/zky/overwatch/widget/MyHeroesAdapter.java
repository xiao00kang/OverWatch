package org.zky.overwatch.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.zky.overwatch.R;
import org.zky.overwatch.utils.GetRes;

/**
 * 包:org.zky.overwatch
 * Created by zhangkun on 2016/11/14.
 */

public class MyHeroesAdapter extends BaseAdapter {
    private String[] data;

    private Context context;


    public MyHeroesAdapter(Context context, String[] data){
        this.data = data;
        this.context = context;
    }
    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView!=null){
            holder = (ViewHolder) convertView.getTag();
        }else {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.recycler_view_item_view, parent, false);
            holder.tv = (TextView) convertView.findViewById(R.id.tv_name);
            holder.iv = (ImageView) convertView.findViewById(R.id.iv_type);
            convertView.setTag(holder);
        }
        String hero = data[position];
        holder.tv.setText(GetRes.getString(GetRes.getName(hero)));
        holder.iv.setImageResource(GetRes.getTypeImageRes(hero));
        return convertView;
    }

    class ViewHolder{
        public TextView tv;
        public ImageView iv;
    }

}
