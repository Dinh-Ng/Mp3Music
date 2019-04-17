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
import com.app.zero.mp3music.Model.Album;
import com.app.zero.mp3music.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    Context context;
    ArrayList<Album> mangAlbum;

    public AlbumAdapter(Context context, ArrayList<Album> mangAlbum) {
        this.context = context;
        this.mangAlbum = mangAlbum;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Album album = mangAlbum.get(i);
        viewHolder.txtTenCasiAlbum.setText(album.getTenCaSiAlbum());
        viewHolder.txtTenAlbum.setText(album.getTenAlbum());
        Picasso.get().load(album.getHinhAnhAlbum()).into(viewHolder.imgHinhAlbum);
    }

    @Override
    public int getItemCount() {
        return mangAlbum.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgHinhAlbum;
        TextView txtTenAlbum, txtTenCasiAlbum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgHinhAlbum = itemView.findViewById(R.id.imgAlbum);
            txtTenAlbum = itemView.findViewById(R.id.txtTenAlbum);
            txtTenCasiAlbum = itemView.findViewById(R.id.txtTenCaSiAlbum);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, Activity_List_BaiHat.class);
                    intent.putExtra("album", mangAlbum.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
