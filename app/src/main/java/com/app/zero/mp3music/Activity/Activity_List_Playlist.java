package com.app.zero.mp3music.Activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.zero.mp3music.Adapter.DanhsachplaylistAdapter;
import com.app.zero.mp3music.Model.Playlist;
import com.app.zero.mp3music.R;
import com.app.zero.mp3music.Service.APIService;
import com.app.zero.mp3music.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_List_Playlist extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    DanhsachplaylistAdapter danhsachplaylistAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_playlist);

        setID();
        init();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Playlist>> callback = dataservice.GetDanhsachPlaylist();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(@NonNull Call<List<Playlist>> call, @NonNull Response<List<Playlist>> response) {
                ArrayList<Playlist> mangplaylist = (ArrayList<Playlist>) response.body();
                danhsachplaylistAdapter = new DanhsachplaylistAdapter(Activity_List_Playlist.this, mangplaylist);
                recyclerView.setLayoutManager(new GridLayoutManager(Activity_List_Playlist.this, 2));
                recyclerView.setAdapter(danhsachplaylistAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<Playlist>> call, @NonNull Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Playlists");
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolbar_playlist));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setID() {
        toolbar = findViewById(R.id.toolbar_list_playlist);
        recyclerView = findViewById(R.id.recycler_list_Playlist);
    }
}
