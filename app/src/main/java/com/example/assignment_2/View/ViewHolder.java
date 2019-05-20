package com.example.assignment_2.View;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.preference.PreferenceScreen;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.assignment_2.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    public ImageView iv_cover;
    public TextView tv_author;
    public TextView tv_title;
    public TextView tv_price;
    public String songUri = "";
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_author = itemView.findViewById(R.id.tv_songAuthor);
        iv_cover = itemView.findViewById(R.id.iv_albumCover);
        tv_title = itemView.findViewById(R.id.tv_songTitle);
        tv_price = itemView.findViewById(R.id.tv_songPrice);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (songUri != "")
                {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(songUri), "audio/*");
                    v.getContext().startActivity(intent);
                }
            }
        });

    }


}
