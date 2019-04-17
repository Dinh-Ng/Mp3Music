package com.app.zero.mp3music.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.app.zero.mp3music.Fragment.Fragment_Dia_nhac;
import com.app.zero.mp3music.Fragment.Fragment_play_danhsach_baihat;
import com.app.zero.mp3music.Fragment.ViewPaperPlaylistNhac;
import com.app.zero.mp3music.Model.Baihat;
import com.app.zero.mp3music.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class Activity_Play_Nhac extends AppCompatActivity {

    Toolbar toolbar;
    TextView txtTime, txtTotalTime;
    SeekBar seekBar;
    ImageButton btnRandom, btnPreview, btnPlay, btnNext, btnRepeat;
    ViewPager viewPager;

    MediaPlayer mediaPlayer;

    int position = 0;

    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;

    Fragment_Dia_nhac fragment_dia_nhac;
    Fragment_play_danhsach_baihat fragment_play_danhsach_baihat;

    public static ArrayList<Baihat> mangBaiHat = new ArrayList<>();

    public static ViewPaperPlaylistNhac adapternhac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        setID();
        eventClick();
    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapternhac.getItem(1) != null) {
                    if (mangBaiHat.size() > 0) {
                        fragment_dia_nhac.Playnhac(mangBaiHat.get(0).getHinhbaihat());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.iconplay);
                    fragment_dia_nhac.stopDisc();
                } else {
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.iconpause);
                    fragment_dia_nhac.startDisc();
                }
            }
        });
        btnRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!repeat) {
                    if (checkrandom) {
                        checkrandom = false;
                        btnRepeat.setImageResource(R.drawable.iconsyned);
                        btnRandom.setImageResource(R.drawable.iconsuffle);
                    }
                    btnRepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                } else {
                    btnRepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkrandom) {
                    if (repeat) {
                        repeat = false;
                        btnRandom.setImageResource(R.drawable.iconshuffled);
                        btnRepeat.setImageResource(R.drawable.iconrepeat);
                    }
                    btnRandom.setImageResource(R.drawable.iconshuffled);
                    checkrandom = true;
                } else {
                    btnRandom.setImageResource(R.drawable.iconsuffle);
                    checkrandom = false;
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mangBaiHat.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (mangBaiHat.size())) {
                        btnPlay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat) {
                            if (position == 0) {
                                position = mangBaiHat.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom) {
                            Random random = new Random();
                            int index = random.nextInt(mangBaiHat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (mangBaiHat.size() - 1)) {
                            position = 0;
                        }
                        new playMp3().execute(mangBaiHat.get(position).getLinkbaihat());
                        fragment_dia_nhac.Playnhac(mangBaiHat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangBaiHat.get(position).getTenbaihat());
                        updateTime();
                    }
                }
                btnPreview.setClickable(false);
                btnNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnPreview.setClickable(false);
                        btnNext.setClickable(false);
                    }
                }, 5000);
            }
        });
        btnPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mangBaiHat.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if (position < (mangBaiHat.size())) {
                        btnPlay.setImageResource(R.drawable.iconpause);
                        position--;
                        if (position < 0) {
                            position = mangBaiHat.size() - 1;
                        }

                        if (repeat) {
                            position += 1;
                        }
                        if (checkrandom) {
                            Random random = new Random();
                            int index = random.nextInt(mangBaiHat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }

                        new playMp3().execute(mangBaiHat.get(position).getLinkbaihat());
                        fragment_dia_nhac.Playnhac(mangBaiHat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangBaiHat.get(position).getTenbaihat());
                        updateTime();
                    }
                }
                btnPreview.setClickable(false);
                btnNext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btnPreview.setClickable(false);
                        btnNext.setClickable(false);
                    }
                }, 5000);
            }
        });
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangBaiHat.clear();
        if (intent != null) {
            if (intent.hasExtra("cakhuc")) {
                Baihat baihat = intent.getParcelableExtra("cakhuc");
                mangBaiHat.add(baihat);
            }

            if (intent.hasExtra("cacbaihat")) {
                mangBaiHat = intent.getParcelableArrayListExtra("cacbaihat");
            }
        }

    }

    private void setID() {
        toolbar = findViewById(R.id.toolbar_play_nhac);
        seekBar = findViewById(R.id.seekbar_song);
        txtTime = findViewById(R.id.txt_time_song);
        txtTotalTime = findViewById(R.id.txt_total_time_song);
        btnRandom = findViewById(R.id.btn_ngaunhien);
        btnPreview = findViewById(R.id.btn_preview);
        btnPlay = findViewById(R.id.btn_play);
        btnNext = findViewById(R.id.btn_next);
        btnRepeat = findViewById(R.id.btn_repeat);
        viewPager = findViewById(R.id.viewpaper_play_nhac);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                mediaPlayer.stop();
                mangBaiHat.clear();
            }
        });
        toolbar.setTitleTextColor(Color.WHITE);
        fragment_dia_nhac = new Fragment_Dia_nhac();
        fragment_play_danhsach_baihat = new Fragment_play_danhsach_baihat();
        adapternhac = new ViewPaperPlaylistNhac(getSupportFragmentManager());
        adapternhac.addFragment(fragment_play_danhsach_baihat);
        adapternhac.addFragment(fragment_dia_nhac);
        viewPager.setAdapter(adapternhac);

        fragment_dia_nhac = (Fragment_Dia_nhac) adapternhac.getItem(1);
        if (mangBaiHat.size() > 0) {
            getSupportActionBar().setTitle(mangBaiHat.get(0).getTenbaihat());
            new playMp3().execute(mangBaiHat.get(0).getLinkbaihat());
            btnPlay.setImageResource(R.drawable.iconpause);
        }
    }

    @SuppressLint("StaticFieldLeak")
    class playMp3 extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String baihat) {
            super.onPostExecute(baihat);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });

                mediaPlayer.setDataSource(baihat);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            timeSong();
            updateTime();
        }
    }

    @SuppressLint("SimpleDateFormat")
    private void timeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotalTime.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekBar.setMax(mediaPlayer.getDuration());
    }

    @SuppressLint("SimpleDateFormat")
    private void updateTime() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTime.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, 300);

        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next) {
                    if (position < (mangBaiHat.size())) {
                        btnPlay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat) {
                            if (position == 0) {
                                position = mangBaiHat.size();
                            }
                            position -= 1;
                        }
                        if (checkrandom) {
                            Random random = new Random();
                            int index = random.nextInt(mangBaiHat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > (mangBaiHat.size() - 1)) {
                            position = 0;
                        }
                        new playMp3().execute(mangBaiHat.get(position).getLinkbaihat());
                        fragment_dia_nhac.Playnhac(mangBaiHat.get(position).getHinhbaihat());
                        getSupportActionBar().setTitle(mangBaiHat.get(position).getTenbaihat());
                    }
                    btnPreview.setClickable(false);
                    btnNext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            btnPreview.setClickable(false);
                            btnNext.setClickable(false);
                        }
                    }, 5000);
                    next = false;
                    handler1.removeCallbacks(this);
                } else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }
}
