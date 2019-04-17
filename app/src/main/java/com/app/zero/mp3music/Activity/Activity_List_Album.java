package com.app.zero.mp3music.Activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.zero.mp3music.Adapter.DanhsachAlbumAdapter;
import com.app.zero.mp3music.Model.Album;
import com.app.zero.mp3music.R;
import com.app.zero.mp3music.Service.APIService;
import com.app.zero.mp3music.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_List_Album extends AppCompatActivity {

    RecyclerView recyclerView;
    Toolbar toolbar;
    DanhsachAlbumAdapter danhsachAlbumAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_album);
        
        setID();
        getData();
    }

    private void getData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Album>> callback = dataservice.GetAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(@NonNull Call<List<Album>> call, @NonNull Response<List<Album>> response) {
                ArrayList<Album> mangalbum = (ArrayList<Album>) response.body();
                danhsachAlbumAdapter = new DanhsachAlbumAdapter(Activity_List_Album.this, mangalbum);
                recyclerView.setLayoutManager(new GridLayoutManager(Activity_List_Album.this, 2));
                recyclerView.setAdapter(danhsachAlbumAdapter);
            }
            @Override
            public void onFailure(@NonNull Call<List<Album>> call, @NonNull Throwable t) {
            }
        });
    }

    private void setID() {
        recyclerView = findViewById(R.id.recycler_list_Album);
        toolbar = findViewById(R.id.toolbar_list_album);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả các Album");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
