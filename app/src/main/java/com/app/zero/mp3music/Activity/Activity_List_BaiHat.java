package com.app.zero.mp3music.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.zero.mp3music.Adapter.DanhsachbaihatAdapter;
import com.app.zero.mp3music.Model.Album;
import com.app.zero.mp3music.Model.Baihat;
import com.app.zero.mp3music.Model.Playlist;
import com.app.zero.mp3music.Model.Quangcao;
import com.app.zero.mp3music.Model.TheLoai;
import com.app.zero.mp3music.R;
import com.app.zero.mp3music.Service.APIService;
import com.app.zero.mp3music.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_List_BaiHat extends AppCompatActivity {

    Quangcao quangcao;
    Playlist playlist;
    TheLoai theLoai;
    Album album;

    ImageView imgdanhsachcakhuc;
    ArrayList<Baihat> mangBaihat;

    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewDanhSachBaiHat;
    FloatingActionButton floatingActionButton;
    DanhsachbaihatAdapter danhsachbaihatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bai_hat);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        DataIntent();
        setID();
        init();

        if (quangcao != null && !quangcao.getTenBaiHat().equals("")) {
            setValueInView(quangcao.getTenBaiHat(), quangcao.getHinhBaiHat());
            GetDataQuangcao(quangcao.getIdQuangCao());
        }

        if (playlist != null && !playlist.getTen().equals("")) {
            setValueInView(playlist.getTen(), playlist.getIcon());
            GetDataPlaylist(playlist.getIdPlayList());
        }
        if (theLoai != null && !theLoai.getTenTheLoai().equals("")) {
            setValueInView(theLoai.getTenTheLoai(), theLoai.getHinhTheLoai());
            GetDataTheLoai(theLoai.getIdTheLoai());
        }
        if (album != null && !album.getTenAlbum().equals("")) {
            setValueInView(album.getTenAlbum(), album.getHinhAnhAlbum());
            GetDataAlbum(album.getIdAlbum());
        }
    }
    private void setValueInView(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        Picasso.get().load(hinh).into(imgdanhsachcakhuc);

        /*
        try {
            URL url = new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                collapsingToolbarLayout.setBackground(bitmapDrawable);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }
    private void GetDataAlbum(String idAlbum) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetBaiHatTheoAlbum(idAlbum);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(@NonNull Call<List<Baihat>> call, @NonNull Response<List<Baihat>> response) {
                mangBaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(Activity_List_BaiHat.this, mangBaihat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(Activity_List_BaiHat.this));
                recyclerViewDanhSachBaiHat.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(@NonNull Call<List<Baihat>> call, @NonNull Throwable t) {

            }
        });
    }

    private void GetDataTheLoai(String idtheloai) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetBaiHatTheoTheLoai(idtheloai);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(@NonNull Call<List<Baihat>> call, @NonNull Response<List<Baihat>> response) {
                mangBaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(Activity_List_BaiHat.this, mangBaihat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(Activity_List_BaiHat.this));
                recyclerViewDanhSachBaiHat.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(@NonNull Call<List<Baihat>> call, @NonNull Throwable  t) {

            }
        });
    }

    private void GetDataPlaylist(String idplaylist) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetBaiHatTheoPlaylist(idplaylist);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(@NonNull Call<List<Baihat>> call, @NonNull Response<List<Baihat>> response) {
                mangBaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(Activity_List_BaiHat.this, mangBaihat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(Activity_List_BaiHat.this));
                recyclerViewDanhSachBaiHat.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(@NonNull Call<List<Baihat>> call, @NonNull Throwable t) {

            }
        });
    }

    private void GetDataQuangcao(String idquangcao) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.GetBaiHatTheoQuangCao(idquangcao);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(@NonNull Call<List<Baihat>> call, @NonNull Response<List<Baihat>> response) {
                mangBaihat = (ArrayList<Baihat>) response.body();
                danhsachbaihatAdapter = new DanhsachbaihatAdapter(Activity_List_BaiHat.this, mangBaihat);
                recyclerViewDanhSachBaiHat.setLayoutManager(new LinearLayoutManager(Activity_List_BaiHat.this));
                recyclerViewDanhSachBaiHat.setAdapter(danhsachbaihatAdapter);
                eventClick();
            }

            @Override
            public void onFailure(@NonNull Call<List<Baihat>> call, @NonNull Throwable t) {

            }
        });

    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.BLACK);
        floatingActionButton.setEnabled(false);
    }

    private void setID() {
        coordinatorLayout          = findViewById(R.id.coordinatorLayout);
        collapsingToolbarLayout    = findViewById(R.id.collasingToolbar);
        toolbar                    = findViewById(R.id.toolbarDanhsach);
        recyclerViewDanhSachBaiHat = findViewById(R.id.recyclerDanhSachBaihat);
        floatingActionButton       = findViewById(R.id.floatingActionButton);
        imgdanhsachcakhuc          = findViewById(R.id.imgDanhsachBaihat);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("banner")) {
                quangcao = (Quangcao) intent.getSerializableExtra("banner");
                Toast.makeText(this, quangcao.getTenBaiHat(), Toast.LENGTH_SHORT).show();
            }
            if (intent.hasExtra("itemplaylist")) {
                playlist = (Playlist) intent.getSerializableExtra("itemplaylist");
            }
            if (intent.hasExtra("idtheloai")) {
                theLoai = (TheLoai) intent.getSerializableExtra("idtheloai");
            }
            if (intent.hasExtra("album")) {
                album = (Album) intent.getSerializableExtra("album");
            }
        }
    }

    private void eventClick() {
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_List_BaiHat.this, Activity_Play_Nhac.class);
                intent.putExtra("cacbaihat", mangBaihat);
                startActivity(intent);
            }
        });
    }
}
