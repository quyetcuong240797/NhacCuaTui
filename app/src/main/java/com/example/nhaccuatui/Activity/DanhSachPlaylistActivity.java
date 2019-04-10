package com.example.nhaccuatui.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.nhaccuatui.Adapter.DanhsachPlaylistAdapter;
import com.example.nhaccuatui.Adapter.PlayListAdapter;
import com.example.nhaccuatui.Model.Playlist;
import com.example.nhaccuatui.R;
import com.example.nhaccuatui.Service.APIService;
import com.example.nhaccuatui.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachPlaylistActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    DanhsachPlaylistAdapter danhsachPlaylistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_playlist);
        anhxa();
        inits();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Playlist>> callback = dataService.GetDanhSachCacPlaylist();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> playlistArrayList = (ArrayList<Playlist>) response.body();
                danhsachPlaylistAdapter = new DanhsachPlaylistAdapter(DanhSachPlaylistActivity.this,playlistArrayList);
                recyclerView.setLayoutManager(new GridLayoutManager(DanhSachPlaylistActivity.this,2));
                recyclerView.setAdapter(danhsachPlaylistAdapter);

            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {

            }
        });
    }

    private void inits() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("PlayLists");
        toolbar.setTitleTextColor(getResources().getColor(R.color.mautim));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void anhxa() {
        toolbar = findViewById(R.id.toolbardanhsachplaylist);
        recyclerView = findViewById(R.id.recycleViewDanhsachPlaylist);

    }
}
