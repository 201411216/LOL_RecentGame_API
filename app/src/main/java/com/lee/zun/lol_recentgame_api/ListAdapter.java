package com.lee.zun.lol_recentgame_api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lee.zun.lol_recentgame_api.data.recent.GameDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by A150 on 2015-01-22.
 */
public class ListAdapter extends BaseAdapter {
    private Context mContext;
    private List<GameDto> mDataset;
    public String TAG = "ListAdapter";

    public ListAdapter(Context tContext) {
        mDataset = new ArrayList<GameDto>();
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
            viewHolder.champion0 = (ImageView) convertView.findViewById(R.id.champion_id);
            viewHolder.item0 = (ImageView) convertView.findViewById(R.id.item0);
            viewHolder.item1 = (ImageView) convertView.findViewById(R.id.item1);
            viewHolder.item2 = (ImageView) convertView.findViewById(R.id.item2);
            viewHolder.item3 = (ImageView) convertView.findViewById(R.id.item3);
            viewHolder.item4 = (ImageView) convertView.findViewById(R.id.item4);
            viewHolder.item5 = (ImageView) convertView.findViewById(R.id.item5);
            viewHolder.item6 = (ImageView) convertView.findViewById(R.id.item6);
            viewHolder.spell1 = (ImageView) convertView.findViewById(R.id.spell1);
            viewHolder.spell2 = (ImageView) convertView.findViewById(R.id.spell2);
            viewHolder.gametype_result = (TextView) convertView.findViewById(R.id.game_result);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
//            GameDto data = mDataset.get(position);
//            viewHolder.gametype_result.setText(data.getGameType());
//            viewHolder.gametype_result.append(data.get...);
//            viewHolder.kda_score.setText(String.valueOf(data.getStats().getChampionsKilled()));
//            viewHolder.kda_score.append(String.valueOf(data.getStats().getAssists()));
//            viewHolder.kda_score.append(String.valueOf(data.getStats().getNumDeaths()));
//            viewHolder.gold_ward_creepscore.setText(String.valueOf(data.getStats().getGold()));
//            viewHolder.gold_ward_creepscore.append(String.valueOf(data.getStats().getWardPlaced()));
//            viewHolder.gold_ward_creepscore.append(String.valueOf(data.getStats().getMinionsKilled()+data.getStats().getNeutralMinionsKilled()));

        }
        return convertView;
    }

    public void addItem(GameDto data) {
        mDataset.add(data);
        notifyDataSetChanged();
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
    }
}
