package com.example.nhaccuatui.Fragment;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nhaccuatui.Adapter.AdapterDownload;
import com.example.nhaccuatui.Model.BaiHat;
import com.example.nhaccuatui.R;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

public class FragmentPersonal extends Fragment {
    View view;
    RecyclerView recyclerViewDownload;
    AdapterDownload adapterDownload;
    ArrayList<BaiHat> baiHatArrayList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personal, container, false);
        recyclerViewDownload = view.findViewById(R.id.recycleview_download);
        recyclerViewDownload.setLayoutManager(new LinearLayoutManager(getActivity()));
        addSong();
        recyclerViewDownload.setAdapter(adapterDownload);
        return view;
    }

    private void addSong() {
        baiHatArrayList = new ArrayList<>();
    }


}