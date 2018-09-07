package com.example.shubhammaheshwari.vyapar;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DataHolder extends RecyclerView.ViewHolder {
    public TextView mTxtPlayerName , mTxtPlayerDesc;
    public ImageView mImgPlayerPhoto;

    public DataHolder(View itemView) {
        super(itemView);
        mTxtPlayerName = itemView.findViewById(R.id.txt_player_name);
        mTxtPlayerDesc = itemView.findViewById(R.id.txt_player_desc);
        mImgPlayerPhoto = itemView.findViewById(R.id.img_player_photo);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mTxtPlayerName.getContext() ,mTxtPlayerName.getText() , Toast.LENGTH_LONG).show();
            }
        });
    }
}
