package com.zayh.test.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zayh.test.R;
import com.zayh.test.bean.ActivityBrean;

import java.util.List;

/**
 * Created by guozhk on 2017/6/13.
 */

public class MainAdapter extends BaseAdapter {
    private List<ActivityBrean> activityBreens;
    private LayoutInflater mInflater;
    private Context context;

    public MainAdapter(Context context, List<ActivityBrean> activityBreens) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.activityBreens = activityBreens;
    }

    public void toSkip(int pos) {
        Intent intent = new Intent(context, activityBreens.get(pos).getCl());
        context.startActivity(intent);
    }

    @Override
    public int getCount() {
        return activityBreens.size();
    }

    @Override
    public Object getItem(int position) {
        return activityBreens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        HodlerView hodlerView = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_test, null);
            hodlerView = new HodlerView();
            hodlerView.tvDre = (TextView) convertView.findViewById(R.id.ls_home_tv);

            convertView.setTag(hodlerView);
        } else {
            hodlerView = (HodlerView) convertView.getTag();
        }
        hodlerView.tvDre.setText(activityBreens.get(position).getDre() + "");

        return convertView;
    }

    static class HodlerView {

        public TextView tvDre;


    }
}
