package com.example.nhaccuatui.Service;

public interface IPlayMusic {

    void create(String linkbaihat);

    void start();

    void pause();
    void reset();

    int getDuration();

    int getCurrentPosition();

    boolean isPlaying();

    void seek(int position);

    void loop(boolean isLoop);

    int getSong();

    void stopService();

    void changeSong(int i);
}


