package com.example.nhaccuatui.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.nhaccuatui.Adapter.DanhSachAlBumAdapter;
import com.example.nhaccuatui.Model.Album;
import com.example.nhaccuatui.R;
import com.example.nhaccuatui.Service.APIService;
import com.example.nhaccuatui.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachAlBumActivity extends AppCompatActivity {
    RecyclerView recyclerViewAllAlbum;
    Toolbar toobarAllAlbum;
    DanhSachAlBumAdapter danhSachAlBumAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_al_bum);
        init();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<Album>> callback = dataService.GetAllAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumArrayList = (ArrayList<Album>) response.body();
                danhSachAlBumAdapter = new DanhSachAlBumAdapter(DanhSachAlBumActivity.this, albumArrayList);
                recyclerViewAllAlbum.setLayoutManager(new GridLayoutManager(DanhSachAlBumActivity.this, 2));
                recyclerViewAllAlbum.setAdapter(danhSachAlBumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewAllAlbum = findViewById(R.id.recycleAllAlbum);
        toobarAllAlbum = findViewById(R.id.toolbarAllAlbum);
        setSupportActionBar(toobarAllAlbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả AlBum");
        toobarAllAlbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
