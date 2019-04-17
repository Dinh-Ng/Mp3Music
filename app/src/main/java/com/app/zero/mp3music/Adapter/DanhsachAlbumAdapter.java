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

import com.app.zero.mp3music.Activity.Activity_List_Album;
import com.app.zero.mp3music.Activity.Activity_List_BaiHat;
import com.app.zero.mp3music.Model.Album;
import com.app.zero.mp3music.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachAlbumAdapter extends RecyclerView.Adapter<DanhsachAlbumAdapter.ViewHolder>{

    Context context;
    ArrayList<Album> albumArrayList;

    public DanhsachAlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_all_album, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Album album = albumArrayList.get(i);
        Picasso.get().load(album.getHinhAnhAlbum()).into(viewHolder.imgAlbum);
        viewHolder.txtAlbum.setText(album.getTenAlbum());
        viewHolder.txtCasi.setText(album.getTenCaSiAlbum());
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAlbum;
        TextView txtAlbum, txtCasi;
        ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgAlbum = itemView.findViewById(R.id.imgDanhsachAlbum);
            txtAlbum = itemView.findViewById(R.id.txtDanhSachAlbum);
            txtCasi  = itemView.findViewById(R.id.txtCasiAlbum);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, Activity_List_BaiHat.class);
                    intent.putExtra("album", albumArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
