package com.ebook.kindle.androidmesh.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ebook.kindle.androidmesh.R;
import com.ebook.kindle.androidmesh.model.PlayerModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlayerListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<PlayerModel> playerModelArrayList;


    public PlayerListAdapter(Context context, ArrayList<PlayerModel> playerModelArrayList) {
        this.context = context;
        this.playerModelArrayList = playerModelArrayList;
    }


    @Override
    public int getViewTypeCount() {
        return getCount();
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public int getCount() {
        return playerModelArrayList.size();
    }


    @Override
    public Object getItem(int position) {
        return playerModelArrayList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PlayerViewHolder holder;

        if (convertView == null) {

            holder = new PlayerViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.player_item, null, true);

            holder.iv = convertView.findViewById(R.id.player_image);
            holder.tvname = convertView.findViewById(R.id.player_name_tv);
            holder.tvcountry = convertView.findViewById(R.id.player_country_tv);
            holder.tvcity = convertView.findViewById(R.id.player_city_tv);

            convertView.setTag(holder);

        }else {

            // the getTag returns the viewHolder object set as a tag to the view
            holder = (PlayerViewHolder)convertView.getTag();

        }


        Picasso.get().load(playerModelArrayList.get(position).getImageURL()).into(holder.iv);
        holder.tvname.setText(playerModelArrayList.get(position).getName());
        holder.tvcountry.setText("Country: "+playerModelArrayList.get(position).getCountry());
        holder.tvcity.setText("City: "+playerModelArrayList.get(position).getCity());


        return convertView;
    }


    private static class PlayerViewHolder {
        protected TextView tvname, tvcountry, tvcity;
        protected ImageView iv;
    }


}
