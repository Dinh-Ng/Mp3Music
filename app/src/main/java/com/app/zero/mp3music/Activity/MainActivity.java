package com.app.zero.mp3music.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.app.zero.mp3music.Adapter.MainViewPaperAdapter;
import com.app.zero.mp3music.Fragment.Fragment_Tim_Kiem;
import com.app.zero.mp3music.Fragment.Fragment_Trang_Chu;
import com.app.zero.mp3music.R;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isConnect()) {
            Toast.makeText(MainActivity.this, "Có kết nối mạng !", Toast.LENGTH_SHORT).show();
            setId();
            init();
        } else {
            Toast.makeText(MainActivity.this, "Không có kết nối mạng rồi, kiểm tra lại đi", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this, DisconnectActivity.class);
            startActivity(intent);
        }

    }

    private void init() {
        MainViewPaperAdapter mainViewPaperAdapter = new
                MainViewPaperAdapter(getSupportFragmentManager());
        mainViewPaperAdapter.addFragment(new Fragment_Trang_Chu(), "Trang Chủ");
        mainViewPaperAdapter.addFragment(new Fragment_Tim_Kiem(), "Tìm Kiếm");
        viewPager.setAdapter(mainViewPaperAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconsearch);
    }

    private void setId() {
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPaper);
    }

    //check network
    public boolean isConnect() {
        ConnectivityManager manager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }
}
