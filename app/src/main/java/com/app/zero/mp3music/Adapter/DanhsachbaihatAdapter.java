package com.app.zero.mp3music.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.zero.mp3music.Activity.Activity_Play_Nhac;
import com.app.zero.mp3music.Model.Baihat;
import com.app.zero.mp3music.R;
import com.app.zero.mp3music.Service.APIService;
import com.app.zero.mp3music.Service.Dataservice;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachbaihatAdapter extends RecyclerView.Adapter<DanhsachbaihatAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Baihat> mangBaihat;

    public DanhsachbaihatAdapter(Context context, ArrayList<Baihat> mangBaihat) {
        this.context = context;
        this.mangBaihat = mangBaihat;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_bai_hat, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Baihat baihat = mangBaihat.get(i);
        holder.txtCasi.setText(baihat.getCasi());
        holder.txtBaihat.setText(baihat.getTenbaihat());
        holder.txtIndex.setText(i + 1 + "");
    }

    @Override
    public int getItemCount() {
        return mangBaihat.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtIndex, txtCasi, txtBaihat;
        ImageView imgThich;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIndex = itemView.findViewById(R.id.txtDanhSachIndex);
            txtCasi = itemView.findViewById(R.id.txtTenCaSi);
            txtBaihat = itemView.findViewById(R.id.txtTenBaihat);
            imgThich = itemView.findViewById(R.id.img_luot_thich_bai_hat);
            imgThich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgThich.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.UpdateLuotThich("1", mangBaihat.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                            String ketqua = response.body();
                            assert ketqua != null;
                            if (ketqua.equals("ThanhCong")) {
                                Toast.makeText(context, "Đã thích", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {

                        }
                    });
                    imgThich.setEnabled(false);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, Activity_Play_Nhac.class);
                    intent.putExtra("cakhuc", mangBaihat.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
