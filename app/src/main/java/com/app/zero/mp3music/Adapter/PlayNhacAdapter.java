package com.app.zero.mp3music.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.zero.mp3music.Model.Baihat;
import com.app.zero.mp3music.R;

import java.util.ArrayList;

public class PlayNhacAdapter extends RecyclerView.Adapter<PlayNhacAdapter.ViewHolder> {

    Context context;
    ArrayList<Baihat> mangbaihat;

    public PlayNhacAdapter(Context context, ArrayList<Baihat> mangbaihat) {
        this.context = context;
        this.mangbaihat = mangbaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_play_baihat, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Baihat baihat = mangbaihat.get(i);
        viewHolder.txtIndex.setText(i +1 + "");
        viewHolder.txtTencasi.setText(baihat.getCasi());
        viewHolder.txtTenbaihat.setText(baihat.getTenbaihat());
    }

    @Override
    public int getItemCount() {
        return mangbaihat.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtIndex, txtTenbaihat, txtTencasi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIndex = itemView.findViewById(R.id.txt_nhac_index);
            txtTenbaihat = itemView.findViewById(R.id.txt_play_nhac_ten_baihat);
            txtTencasi = itemView.findViewById(R.id.txt_play_nhac_ten_casi);
        }
    }
}

