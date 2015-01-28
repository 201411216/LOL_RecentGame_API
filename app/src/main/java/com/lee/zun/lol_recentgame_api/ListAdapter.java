package com.lee.zun.lol_recentgame_api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lee.zun.lol_recentgame_api.data.recent.GameDto;

import java.util.List;

/**
 * Created by A150 on 2015-01-22.
 */
public class ListAdapter extends BaseAdapter {
    private Context mContext;
    private List<GameDto> mDataset;
    public String TAG = "ListAdapter";


    public ListAdapter(Context tContext, List<GameDto> dataset) {
        mDataset = dataset;
        mContext = tContext;
    }

    @Override
    public int getCount() {
        return mDataset.size();
    }

    @Override
    public GameDto getItem(int position) {
        return mDataset.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_recent_item, parent, false);
            viewHolder.champion0 = (ImageView) convertView.findViewById(R.id.champion0);
            viewHolder.item0 = (ImageView) convertView.findViewById(R.id.item0);
            viewHolder.item1 = (ImageView) convertView.findViewById(R.id.item1);
            viewHolder.item2 = (ImageView) convertView.findViewById(R.id.item2);
            viewHolder.item3 = (ImageView) convertView.findViewById(R.id.item3);
            viewHolder.item4 = (ImageView) convertView.findViewById(R.id.item4);
            viewHolder.item5 = (ImageView) convertView.findViewById(R.id.item5);
            viewHolder.item6 = (ImageView) convertView.findViewById(R.id.item6);
            viewHolder.spell1 = (ImageView) convertView.findViewById(R.id.spell1);
            viewHolder.spell2 = (ImageView) convertView.findViewById(R.id.spell2);
            viewHolder.gametype_result = (TextView) convertView.findViewById(R.id.gametype_result);
            viewHolder.kda_score = (TextView) convertView.findViewById(R.id.kda_score);
            viewHolder.gold_ward_creepscore = (TextView) convertView.findViewById(R.id.gold_ward_creepscore);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }


        return convertView;
    }


    public static class ViewHolder {
        public ImageView champion0;
        public ImageView item0;
        public ImageView item1;
        public ImageView item2;
        public ImageView item3;
        public ImageView item4;
        public ImageView item5;
        public ImageView item6;
        public ImageView spell1;
        public ImageView spell2;
        public TextView gametype_result;
        public TextView kda_score;
        public TextView gold_ward_creepscore;
    }
}
