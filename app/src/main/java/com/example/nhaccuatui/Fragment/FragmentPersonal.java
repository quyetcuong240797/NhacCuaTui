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
import com.example.nhaccuatui.R;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

public class FragmentPersonal extends Fragment {
    View view;
    RecyclerView recyclerViewDownload;
    AdapterDownload adapterDownload;
    final String MEDIA_PATH = Environment.getExternalStorageDirectory()+"";
    private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_personal, container, false);
        recyclerViewDownload = view.findViewById(R.id.recycleview_download);
        recyclerViewDownload.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterDownload = new AdapterDownload(getActivity(), getPlayList(MEDIA_PATH));
        recyclerViewDownload.setAdapter(adapterDownload);
        return view;
    }

    ArrayList<HashMap<String, String>> getPlayList(String rootPath) {
        ArrayList<HashMap<String, String>> fileList = new ArrayList<>();
        try {
            File rootFolder = new File(rootPath);
            File[] files = rootFolder.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    if (getPlayList(file.getAbsolutePath()) != null) {
                        fileList.addAll(getPlayList(file.getAbsolutePath()));
                    } else {
                        break;
                    }
                } else if (file.getName().endsWith(".mp3")) {
                    HashMap<String, String> song = new HashMap<>();
                    song.put("file_path", file.getAbsolutePath());
                    song.put("file_name", file.getName());
                    fileList.add(song);
                }
            }
            return fileList;
        } catch (Exception e) {
            return null;
        }
    }

    class FileExtensionFilter implements FilenameFilter {
        public boolean accept(File dir, String name) {
            return (name.endsWith(".mp3") || name.endsWith(".MP3"));
        }
    }


}