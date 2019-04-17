package com.app.zero.mp3music.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.zero.mp3music.Activity.Activity_List_BaiHat;
import com.app.zero.mp3music.Activity.Activity_List_Chude;
import com.app.zero.mp3music.Activity.Activity_List_Theloai_theo_chude;
import com.app.zero.mp3music.Model.ChuDe;
import com.app.zero.mp3music.Model.ChuDeTheLoai;
import com.app.zero.mp3music.Model.TheLoai;
import com.app.zero.mp3music.R;
import com.app.zero.mp3music.Service.APIService;
import com.app.zero.mp3music.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Chu_de_The_Loai extends Fragment{

    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtXemthemchude;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chu_de_the_loai, container, false);
        horizontalScrollView = view.findViewById(R.id.horintalScrallview);
        txtXemthemchude = view.findViewById(R.id.txtXemthem);
        txtXemthemchude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Activity_List_Chude.class);
                startActivity(intent);
            }
        });
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<ChuDeTheLoai> callback = dataservice.GetChuDeDay();
        callback.enqueue(new Callback<ChuDeTheLoai>() {
            @Override
            public void onResponse(@NonNull Call<ChuDeTheLoai> call, @NonNull Response<ChuDeTheLoai> response) {
                ChuDeTheLoai chuDeTheLoai = response.body();

                final ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();
                chuDeArrayList.addAll(chuDeTheLoai.getChuDe());

                final ArrayList<TheLoai> theLoaiArrayList = new ArrayList<>();
                theLoaiArrayList.addAll(chuDeTheLoai.getTheLoai());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(580, 250);
                layout.setMargins(10,20,10,30);
                for (int i = 0 ; i < (chuDeArrayList.size()); i++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (chuDeArrayList.get(i).getHinhChuDe() != null) {
                        Picasso.get().load(chuDeArrayList.get(i).getHinhChuDe()).into(imageView);
                    }
                    LinearLayout.LayoutParams layout1 = new LinearLayout.LayoutParams(580, 250);
                    layout1.setMargins(10,20,10,30);

                    cardView.setLayoutParams(layout1);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    final int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), Activity_List_Theloai_theo_chude.class);
                            intent.putExtra("chude", chuDeArrayList.get(finalI));
                            startActivity(intent);
                        }
                    });
                }
                for (int j = 0 ; j < (theLoaiArrayList.size()); j++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (theLoaiArrayList.get(j).getHinhTheLoai() != null) {
                        Picasso.get().load(theLoaiArrayList.get(j).getHinhTheLoai()).into(imageView);
                    }
                    LinearLayout.LayoutParams layout2 = new LinearLayout.LayoutParams(250, 250);
                    layout2.setMargins(10,20,10,30);

                    cardView.setLayoutParams(layout2);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), Activity_List_BaiHat.class);
                            intent.putExtra("idtheloai", theLoaiArrayList.get(finalJ));
                            startActivity(intent);
                        }
                    });
                }
                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(@NonNull Call<ChuDeTheLoai> call, @NonNull Throwable t) {

            }
        });
    }
}
