package com.example.shubhammaheshwari.vyapar;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataHolder> {
    private List<Player> mPlayers;
    private Context mContext;

    public DataAdapter(List<Player> players , Context context)
    {
        mPlayers = players;
        mContext = context;
    }
    public DataAdapter(Context context)
    {
        mContext = context;
    }
    public void setData(List<Player> players)
    {
        mPlayers = players;
    }
    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_player,parent, false);
        return new DataHolder(view);
    }

    @Override
    public void onBindViewHolder(DataHolder holder, int position) {
        Player player = mPlayers.get(position);
        holder.mTxtPlayerName.setText(player.playerName);
        holder.mTxtPlayerDesc.setText(player.playerDesc);
        Glide.with(mContext).load(player.playerImg).into(holder.mImgPlayerPhoto);
    }

    @Override
    public int getItemCount() {
        if(mPlayers == null)return 0;
        return mPlayers.size();
    }
}
