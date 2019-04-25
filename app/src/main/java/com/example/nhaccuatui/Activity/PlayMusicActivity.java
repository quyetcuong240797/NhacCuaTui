package com.example.nhaccuatui.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhaccuatui.Adapter.ViewPagerPlaylistSong;
import com.example.nhaccuatui.Fragment.Fragment_Disk;
import com.example.nhaccuatui.Fragment.Fragment_Play_List_Song;
import com.example.nhaccuatui.Model.BaiHat;
import com.example.nhaccuatui.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PlayMusicActivity extends AppCompatActivity {
    Toolbar toolbarplaymusic;
    TextView tvtimesong, tvtotaltimesong, tvnamesongplay, tvnamecasiplay;
    SeekBar seekbar;
    ImageView imgalarm;
    ImageButton imgplay, imgpre, imgnext, imgsuffule, imgrepeat;
    ViewPager viewPagerPlayMusic;
    public static ArrayList<BaiHat> mangbaihat = new ArrayList<>();
    public static ViewPagerPlaylistSong adapterlistsong;
    Fragment_Disk fragment_disk;
    Fragment_Play_List_Song fragment_play_list_song;
    public static BaiHat baiHat;
    MediaPlayer mediaPlayer;
    int position = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;
    int minutesoff;
    int minuteson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        init();
        eventclick();

    }

    private void eventclick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapterlistsong.getItem(0) != null) {
                    if (mangbaihat.size() > 0) {
                        fragment_disk.Playnhac(mangbaihat.get(0).getHinhbaihat());
                        handler.removeCallbacks(this);
                    } else {
                        handler.postDelayed(this, 300);
                    }
                }
            }
        }, 500);

        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.iconplay);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        Fragment_Disk.objectAnimator.pause();
                    }
                } else {
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.iconpause);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        Fragment_Disk.objectAnimator.resume();
                    }
                }
            }
        });

        imgrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (repeat == false) {
                    if (checkrandom == true) {
                        checkrandom = false;
                        imgrepeat.setImageResource(R.drawable.iconsyned);
                        imgsuffule.setImageResource(R.drawable.iconsuffle);


                    }
                    imgrepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = true;
                } else {
                    imgrepeat.setImageResource(R.drawable.iconsyned);
                    repeat = false;
                }
            }
        });

        imgsuffule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkrandom == false) {
                    if (repeat == true) {
                        repeat = false;
                        imgsuffule.setImageResource(R.drawable.iconshuffled);
                        imgrepeat.setImageResource(R.drawable.iconrepeat);

                    }
                    imgrepeat.setImageResource(R.drawable.iconshuffled);
                    checkrandom = true;
                } else {
                    imgsuffule.setImageResource(R.drawable.iconshuffled);
                    repeat = false;
                }
            }
        });

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mangbaihat.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;

                    }
                    if (position < mangbaihat.size()) {
                        imgplay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = mangbaihat.size();
                            }
                            position -= 1;

                        }

                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > mangbaihat.size() - 1) {
                            position = 0;
                        }

                        new Mp3().execute(mangbaihat.get(position).getLinkbaihat());
                        fragment_disk.Playnhac(mangbaihat.get(position).getHinhbaihat());
                        tvnamecasiplay.setText(mangbaihat.get(position).getCasi());
                        tvnamesongplay.setText(mangbaihat.get(position).getTenbaihat());
                        UpdateTim();
                    }
                }
                imgnext.setClickable(false);
                imgpre.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgnext.setClickable(true);
                        imgpre.setClickable(true);
                    }
                }, 5000);
            }
        });

        imgpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mangbaihat.size() > 0) {
                    if (mediaPlayer.isPlaying() || mediaPlayer != null) {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;

                    }
                    if (position < mangbaihat.size()) {
                        imgplay.setImageResource(R.drawable.iconpause);
                        position--;

                        if (position < 0) {
                            position = mangbaihat.size() - 1;
                        }

                        if (repeat == true) {
                            position += 1;

                        }

                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        new Mp3().execute(mangbaihat.get(position).getLinkbaihat());
                        fragment_disk.Playnhac(mangbaihat.get(position).getHinhbaihat());
                        tvnamecasiplay.setText(mangbaihat.get(position).getCasi());
                        tvnamesongplay.setText(mangbaihat.get(position).getTenbaihat());
                        UpdateTim();

                    }
                }
                imgnext.setClickable(false);
                imgpre.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgnext.setClickable(true);
                        imgpre.setClickable(true);
                    }
                }, 2000);
            }
        });
    }


    private void GetDataFromIntent() {
        Intent intent = getIntent();
        mangbaihat.clear();
        if (intent != null) {
            if (intent.hasExtra("song")) {
                baiHat = intent.getParcelableExtra("song");
                mangbaihat.add(baiHat);
            }
            if (intent.hasExtra("danhsachsong")) {
                ArrayList<BaiHat> songArrayList = intent.getParcelableArrayListExtra("danhsachsong");
                mangbaihat = songArrayList;
            }

        }

    }

    private void init() {
        toolbarplaymusic = findViewById(R.id.toolbar_playmusic);
        tvtimesong = findViewById(R.id.tvtimesong);
        tvnamesongplay = findViewById(R.id.tvnamesongplay);
        tvnamecasiplay = findViewById(R.id.tvnamecasiplay);
        tvtotaltimesong = findViewById(R.id.totaltimesong);
        if (mangbaihat.size() > 1) {
            tvnamecasiplay.setText(mangbaihat.get(0).getCasi());
            tvnamesongplay.setText(mangbaihat.get(0).getTenbaihat());
        } else {
            tvnamesongplay.setText(baiHat.getTenbaihat());
            tvnamecasiplay.setText(baiHat.getCasi());
        }
        seekbar = findViewById(R.id.seek_bar);
        imgalarm = findViewById(R.id.imgviewalarm);
        imgalarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialogalarm = new Dialog(PlayMusicActivity.this);
                dialogalarm.setTitle("Hẹn Giờ Tắt Nhạc");
                dialogalarm.setContentView(R.layout.dialog_alarm_on_off_music);
                Window window = dialogalarm.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();
                wlp.gravity = Gravity.BOTTOM;
                wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
                window.setAttributes(wlp);
                final SeekBar seekBarOff = dialogalarm.findViewById(R.id.seek_bar_off_music);
                final SeekBar seekBarOn = dialogalarm.findViewById(R.id.seek_bar_on_music);
                final TextView tvminutesoff = dialogalarm.findViewById(R.id.tvminutes_off_music);
                final TextView tvminuteson = dialogalarm.findViewById(R.id.tvminutes_on_music);
                final EditText edminutesoff = dialogalarm.findViewById(R.id.edtmunute_soff_music);
                final EditText edtminuteson = dialogalarm.findViewById(R.id.edtmunutes_on_music);
                TextView tvthietlap = dialogalarm.findViewById(R.id.tvthietlap);
                TextView tvdongthietlap = dialogalarm.findViewById(R.id.tvdongthietlap);
                dialogalarm.show();


                seekBarOff.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(final SeekBar seekBar, int progress, boolean fromUser) {
                        tvminutesoff.setText(String.valueOf(seekBar.getProgress()));
                        edminutesoff.setVisibility(View.INVISIBLE);
                        edtminuteson.setVisibility(View.INVISIBLE);
                        seekBarOn.setVisibility(View.INVISIBLE);
                        if (Integer.parseInt(tvminutesoff.getText().toString()) == 0) {
                            edminutesoff.setVisibility(View.VISIBLE);
                            edtminuteson.setVisibility(View.VISIBLE);
                            seekBarOn.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        if (tvminutesoff.getVisibility() == View.VISIBLE) {
                            minutesoff = Integer.parseInt(tvminutesoff.getText().toString());
                        } else if (edminutesoff.getText() != null) {
                            minutesoff = Integer.parseInt(edminutesoff.getText().toString());
                        }
                    }
                });


                seekBarOn.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        tvminuteson.setText(String.valueOf(seekBar.getProgress()));
                        edtminuteson.setVisibility(View.INVISIBLE);
                        seekBarOff.setVisibility(View.INVISIBLE);
                        edminutesoff.setVisibility(View.INVISIBLE);
                        if (Integer.parseInt(tvminuteson.getText().toString()) == 0) {
                            edtminuteson.setVisibility(View.VISIBLE);
                            edminutesoff.setVisibility(View.VISIBLE);
                            edminutesoff.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        if (tvminuteson.getVisibility() == View.VISIBLE) {
                            minuteson = Integer.parseInt(tvminuteson.getText().toString());
                        }
                        if (edminutesoff.getVisibility() == View.VISIBLE) {
                            minuteson = Integer.parseInt(edtminuteson.getText().toString());
                        }

                    }
                });

                tvdongthietlap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogalarm.dismiss();
                    }
                });

                tvthietlap.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (seekBarOff.getVisibility() == View.VISIBLE) {
                            Timer timer = new Timer();
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    if (mediaPlayer.isPlaying()) {
                                        mediaPlayer.pause();
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                            Fragment_Disk.objectAnimator.pause();
                                        }
                                        imgplay.setImageResource(R.drawable.iconplay);
                                    }
                                }
                            }, minutesoff * 1000 * 60);
                            dialogalarm.dismiss();
                            Toast.makeText(PlayMusicActivity.this, "Đã thiết lập xong", Toast.LENGTH_SHORT).show();
                        }
                        if (seekBarOn.getVisibility() == View.VISIBLE) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                Fragment_Disk.objectAnimator.pause();
                            }
                            mediaPlayer.pause();
                            Timer timer = new Timer();
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    mediaPlayer.start();
                                    imgplay.setImageResource(R.drawable.iconpause);
                                }

                            }, minuteson * 1000);
                            dialogalarm.dismiss();
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                Fragment_Disk.objectAnimator.resume();
                            }
                            Toast.makeText(PlayMusicActivity.this, "Đã thiết lập xong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        imgplay = findViewById(R.id.imgbuttonlay);
        imgpre = findViewById(R.id.imgbuttonpre);
        imgnext = findViewById(R.id.imgbuttonnext);
        imgrepeat = findViewById(R.id.imgbuttonrepeat);
        imgsuffule = findViewById(R.id.imgbuttonSuffle);
        viewPagerPlayMusic = findViewById(R.id.viewpager_playmusic);
        toolbarplaymusic.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbarplaymusic);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaymusic.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mediaPlayer.stop();
                mangbaihat.clear();
            }
        });
        fragment_disk = new Fragment_Disk();
        fragment_play_list_song = new Fragment_Play_List_Song();
        adapterlistsong = new ViewPagerPlaylistSong(getSupportFragmentManager());
        adapterlistsong.AddFragment(fragment_disk);
        adapterlistsong.AddFragment(fragment_play_list_song);
        viewPagerPlayMusic.setAdapter(adapterlistsong);
        fragment_disk = (Fragment_Disk) adapterlistsong.getItem(0);
        if (mangbaihat.size() > 0) {
            new Mp3().execute(mangbaihat.get(0).getLinkbaihat());
            imgplay.setImageResource(R.drawable.iconpause);
        }


    }


    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        tvtotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        seekbar.setMax(mediaPlayer.getDuration());
    }

    private void UpdateTim() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    seekbar.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    tvtimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
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
                if (next == true) {
                    if (position < mangbaihat.size()) {
                        imgplay.setImageResource(R.drawable.iconpause);
                        position++;
                        if (repeat == true) {
                            if (position == 0) {
                                position = mangbaihat.size();
                            }
                            position -= 1;

                        }

                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(mangbaihat.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > mangbaihat.size() - 1) {
                            position = 0;
                        }

                        new Mp3().execute(mangbaihat.get(position).getLinkbaihat());
                        fragment_disk.Playnhac(mangbaihat.get(position).getHinhbaihat());
                        tvnamecasiplay.setText(mangbaihat.get(position).getCasi());
                        tvnamesongplay.setText(mangbaihat.get(position).getTenbaihat());

                    }

                    imgnext.setClickable(false);
                    imgpre.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgnext.setClickable(true);
                            imgpre.setClickable(true);
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

    class Mp3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {

                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();

                    }
                });
                mediaPlayer.setDataSource(s);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTim();
        }
    }
}


