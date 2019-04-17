package com.app.zero.mp3music.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.zero.mp3music.Activity.Activity_List_Theloai_theo_chude;
import com.app.zero.mp3music.Model.ChuDe;
import com.app.zero.mp3music.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachchudeAdapter extends RecyclerView.Adapter<DanhsachchudeAdapter.ViewHolder>{

    private Context context;
    private ArrayList<ChuDe> mangchude;

    public DanhsachchudeAdapter(Context context, ArrayList<ChuDe> mangchude) {
        this.context = context;
        this.mangchude = mangchude;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_chude, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ChuDe chuDe = mangchude.get(i);
        Picasso.get().load(chuDe.getHinhChuDe()).into(viewHolder.imgChude);
    }

    @Override
    public int getItemCount() {
        return mangchude.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgChude;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgChude = itemView.findViewById(R.id.img_dong_chude);
            imgChude.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, Activity_List_Theloai_theo_chude.class);
                    intent.putExtra("chude", mangchude.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
