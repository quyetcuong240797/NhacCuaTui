package com.example.nhaccuatui.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nhaccuatui.Activity.PlayMusicActivity;
import com.example.nhaccuatui.Adapter.PlayListSongPlayScreenAdapter;
import com.example.nhaccuatui.R;

public class Fragment_Play_List_Song extends Fragment {
    View view;
    RecyclerView recyclerViewPlayListSong;
    PlayListSongPlayScreenAdapter playListSongPlayScreenAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_list_song,container,false);
        recyclerViewPlayListSong = view.findViewById(R.id.recycle_list_song_play_music);
        if(PlayMusicActivity.mangbaihat.size() >0 ) {
            playListSongPlayScreenAdapter = new PlayListSongPlayScreenAdapter(getActivity(), PlayMusicActivity.mangbaihat);
            recyclerViewPlayListSong.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewPlayListSong.setAdapter(playListSongPlayScreenAdapter);
        }
        return view;

    }
}
