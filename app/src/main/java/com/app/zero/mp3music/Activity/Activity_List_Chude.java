package com.app.zero.mp3music.Activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.app.zero.mp3music.Adapter.DanhsachchudeAdapter;
import com.app.zero.mp3music.Model.ChuDe;
import com.app.zero.mp3music.R;
import com.app.zero.mp3music.Service.APIService;
import com.app.zero.mp3music.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_List_Chude extends AppCompatActivity {

    RecyclerView recyclerView_list_chude;
    Toolbar toolbar;
    DanhsachchudeAdapter danhsachchudeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_chude);

        setID();
        getData();
    }

    private void getData() {
        Dataservice dataservice = APIService.getService();
        Call<List<ChuDe>> callback = dataservice.GetDanhsachChuDe();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(@NonNull Call<List<ChuDe>> call, @NonNull Response<List<ChuDe>> response) {
                ArrayList<ChuDe> mangChude = (ArrayList<ChuDe>) response.body();
                danhsachchudeAdapter = new DanhsachchudeAdapter(Activity_List_Chude.this, mangChude);
                GridLayoutManager manager = new GridLayoutManager(Activity_List_Chude.this, 2);
                recyclerView_list_chude.setLayoutManager(manager);
                recyclerView_list_chude.setAdapter(danhsachchudeAdapter);
            }
            @Override
            public void onFailure(@NonNull Call<List<ChuDe>> call, @NonNull Throwable t) {

            }
        });
    }

    private void setID() {
        recyclerView_list_chude = findViewById(R.id.recycler_list_Chude);
        toolbar = findViewById(R.id.toolbar_list_chude);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Chủ Đề");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
