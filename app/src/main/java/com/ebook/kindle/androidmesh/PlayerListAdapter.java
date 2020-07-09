package com.ebook.kindle.androidmesh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<PlayerModel> playerModelArrayList;


    public PlayerListAdapter(Context ctx, ArrayList<PlayerModel> playerModelArrayList){
        inflater = LayoutInflater.from(ctx);
        this.playerModelArrayList = playerModelArrayList;
    }


    @Override
    public PlayerListAdapter.PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.player_item, parent, false);
        PlayerViewHolder playerHolder = new PlayerViewHolder(view);

        return playerHolder;
    }


    @Override
    public void onBindViewHolder(PlayerListAdapter.PlayerViewHolder holder, int position) {

        Picasso.get().load(playerModelArrayList.get(position).getImageURL()).into(holder.iv);
        holder.name.setText(playerModelArrayList.get(position).getName());
        holder.country.setText(playerModelArrayList.get(position).getCountry());
        holder.city.setText(playerModelArrayList.get(position).getCity());
    }


    @Override
    public int getItemCount() {
        return playerModelArrayList.size();
    }


    class PlayerViewHolder extends RecyclerView.ViewHolder{

        TextView country, name, city;
        ImageView iv;

        public PlayerViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.player_name_tv);
            country = itemView.findViewById(R.id.player_country_tv);
            city = itemView.findViewById(R.id.player_city_tv);
            iv = itemView.findViewById(R.id.player_image);
        }

    }

}
