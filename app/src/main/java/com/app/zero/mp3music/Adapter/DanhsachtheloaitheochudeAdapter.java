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
import com.app.zero.mp3music.Model.TheLoai;
import com.app.zero.mp3music.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachtheloaitheochudeAdapter extends RecyclerView.Adapter<DanhsachtheloaitheochudeAdapter.ViewHolder>{

    Context context;
    ArrayList<TheLoai> theLoaiArrayList;

    public DanhsachtheloaitheochudeAdapter(Context context, ArrayList<TheLoai> theLoaiArrayList) {
        this.context = context;
        this.theLoaiArrayList = theLoaiArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_theloai_theo_chude, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TheLoai theLoai = theLoaiArrayList.get(i);
        Picasso.get().load(theLoai.getHinhTheLoai()).into(viewHolder.imgHinhnen);
        viewHolder.txtTentheloai.setText(theLoai.getTenTheLoai());
        viewHolder.txtTencasi.setText(theLoai.getTenCaSi());
    }

    @Override
    public int getItemCount() {
        return theLoaiArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgHinhnen;
        TextView txtTentheloai, txtTencasi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhnen = itemView.findViewById(R.id.img_theloai_theo_chude);
            txtTentheloai = itemView.findViewById(R.id.txt_ten_theloai_theo_chude);
            txtTencasi = itemView.findViewById(R.id.txt_casi_theloai_theo_chude);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, Activity_List_BaiHat.class);
                    intent.putExtra("idtheloai", theLoaiArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
