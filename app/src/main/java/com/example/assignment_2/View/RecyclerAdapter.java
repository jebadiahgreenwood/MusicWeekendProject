package com.example.assignment_2.View;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.assignment_2.Model.MusicDetailsPojo;
import com.example.assignment_2.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final String TAG = "RecyclerAdapter";

    List<MusicDetailsPojo> dataSet;
    public void setDataSet(List<MusicDetailsPojo> data) {
        this.dataSet = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.rv_item_layout,
                viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolderRock, int i) {
        MusicDetailsPojo musicDetails = dataSet.get(i);
        try{
            viewHolderRock.tv_author.setText(musicDetails.artistName);
            viewHolderRock.tv_title.setText(musicDetails.trackName);
            if (musicDetails.trackPrice != null)
                viewHolderRock.tv_price.setText(
                        "USD "+musicDetails.trackPrice.toString());
            if (musicDetails.artworkUrl60 != null)
                Picasso.get()
                        .load(musicDetails.artworkUrl60)
                        .into(viewHolderRock.iv_cover);
            viewHolderRock.songUri = musicDetails.previewUrl;
        }catch (Exception e)
        {
            Log.d(TAG, e.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return dataSet != null ? dataSet.size() : 0;
    }
}
