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
import android.widget.Toast;

import com.app.zero.mp3music.Activity.Activity_Play_Nhac;
import com.app.zero.mp3music.Model.Baihat;
import com.app.zero.mp3music.R;
import com.app.zero.mp3music.Service.APIService;
import com.app.zero.mp3music.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaihatHotAdapter extends RecyclerView.Adapter<BaihatHotAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Baihat> baihatArrayList;

    public BaihatHotAdapter(Context context, ArrayList<Baihat> baihatArrayList) {
        this.context = context;
        this.baihatArrayList = baihatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_baihathot, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Baihat baihat = baihatArrayList.get(i);
        viewHolder.txtCasi.setText(baihat.getCasi());
        viewHolder.txtTen.setText(baihat.getTenbaihat());

        Picasso.get().load(baihat.getHinhbaihat()).into(viewHolder.imgHinh);

        DecimalFormat decimalFormat = new DecimalFormat("0,000");
        int a = Integer.parseInt(baihat.getLuotthich());
        viewHolder.txtSoluotthich.setText(decimalFormat.format(a));
    }

    @Override
    public int getItemCount() {
        return baihatArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTen, txtCasi, txtSoluotthich;
        ImageView imgHinh, imgLuotthich;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.txtTenBaihatHot);
            txtCasi = itemView.findViewById(R.id.txtCasiBaihatHot);
            txtSoluotthich = itemView.findViewById(R.id.txt_so_luot_thich);
            imgHinh = itemView.findViewById(R.id.imgBaihathot);
            imgLuotthich = itemView.findViewById(R.id.imgBaihathot_love);
            imgLuotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imgLuotthich.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice = APIService.getService();
                    Call<String> callback = dataservice.UpdateLuotThich("1", baihatArrayList.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                            String ketqua = response.body();
                            assert ketqua != null;
                            if (ketqua.equals("Thanh Cong")) {
                                Toast.makeText(context, "Đã thích", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Lỗi", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {

                        }
                    });
                    imgLuotthich.setEnabled(false);
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, Activity_Play_Nhac.class);
                    intent.putExtra("cakhuc", baihatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
