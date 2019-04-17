package com.app.zero.mp3music.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.zero.mp3music.R;

public class DisconnectActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disconnect);

        button = findViewById(R.id.btn_reload);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConnect()) {
                    Toast.makeText(DisconnectActivity.this, "Ngon ! Có kết nối mạng !", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DisconnectActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(DisconnectActivity.this, "Không có kết nối mạng rồi, kiểm tra lại đi", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
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
