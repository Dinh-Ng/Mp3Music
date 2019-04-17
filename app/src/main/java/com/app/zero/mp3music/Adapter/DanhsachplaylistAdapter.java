package com.app.zero.mp3music.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.zero.mp3music.Activity.Activity_List_BaiHat;
import com.app.zero.mp3music.Model.Playlist;
import com.app.zero.mp3music.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachplaylistAdapter extends RecyclerView.Adapter<DanhsachplaylistAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Playlist> mangPlaylist;

    public DanhsachplaylistAdapter(Context context, ArrayList<Playlist> mangPlaylist) {
        this.context = context;
        this.mangPlaylist = mangPlaylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_playlist, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Playlist playlist = mangPlaylist.get(i);
        Picasso.get().load(playlist.getIcon()).into(viewHolder.imgHinh);
        viewHolder.txtTen.setText(playlist.getTen());
        viewHolder.txtCasi.setText(playlist.getCasi());
    }

    @Override
    public int getItemCount() {
        return mangPlaylist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgHinh;
        TextView txtTen, txtCasi;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinh = itemView.findViewById(R.id.imgDanhsachPlaylist);
            txtTen = itemView.findViewById(R.id.txtDanhSachPlaylist);
            txtCasi = itemView.findViewById(R.id.txtCasiPlaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, Activity_List_BaiHat.class);
                    intent.putExtra("itemplaylist", mangPlaylist.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
