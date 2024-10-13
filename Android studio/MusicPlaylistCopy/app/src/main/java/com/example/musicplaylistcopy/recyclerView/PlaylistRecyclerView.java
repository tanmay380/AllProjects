package com.example.musicplaylistcopy.recyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplaylistcopy.R;

import java.util.ArrayList;

public class PlaylistRecyclerView extends RecyclerView.Adapter<PlaylistRecyclerView.ViewHolder> {

    ArrayList<Playlist> playlistArray = new ArrayList<>();
    ItemClickListener itemClickListener;


    public PlaylistRecyclerView(ArrayList<Playlist> playlist) {
        this.playlistArray = playlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.playlistview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv.setText(playlistArray.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return playlistArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.playtv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null){
                itemClickListener.onItemClick(v, getAdapterPosition());
            }
        }

    }

    public Playlist getItem(int id) {
        return playlistArray.get(id);
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View v, int position);
    }
}
