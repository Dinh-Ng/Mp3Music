package com.app.zero.mp3music.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.app.zero.mp3music.Adapter.DanhsachtheloaitheochudeAdapter;
import com.app.zero.mp3music.Model.ChuDe;
import com.app.zero.mp3music.Model.TheLoai;
import com.app.zero.mp3music.R;
import com.app.zero.mp3music.Service.APIService;
import com.app.zero.mp3music.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_List_Theloai_theo_chude extends AppCompatActivity {

    ChuDe chuDe;
    RecyclerView recyclerView_theloai;
    Toolbar toolbar;
    DanhsachtheloaitheochudeAdapter danhsachtheloaitheochudeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_theloai_theo_chude);

        GetIntent();
        setID();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<TheLoai>> callback = dataservice.GetTheLoaiTheoChuDe(chuDe.getIdChuDe());
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(@NonNull Call<List<TheLoai>> call, @NonNull Response<List<TheLoai>> response) {
                ArrayList<TheLoai> mangtheloai = (ArrayList<TheLoai>) response.body();
                danhsachtheloaitheochudeAdapter = new DanhsachtheloaitheochudeAdapter(Activity_List_Theloai_theo_chude.this, mangtheloai);
                recyclerView_theloai.setLayoutManager(new GridLayoutManager(Activity_List_Theloai_theo_chude.this, 2));
                recyclerView_theloai.setAdapter(danhsachtheloaitheochudeAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<TheLoai>> call, @NonNull Throwable t) {

            }
        });
    }

    private void setID() {
        recyclerView_theloai = findViewById(R.id.recycler_list_theloai_theo_chude);
        toolbar = findViewById(R.id.toolbar_list_theloai_theo_chude);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChuDe());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void GetIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("chude")) {
            chuDe = (ChuDe) intent.getSerializableExtra("chude");
        }
    }
}
