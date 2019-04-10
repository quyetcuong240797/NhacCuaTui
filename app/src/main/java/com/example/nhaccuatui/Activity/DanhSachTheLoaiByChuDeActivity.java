package com.example.nhaccuatui.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.nhaccuatui.Adapter.DanhSachTheLoaiTheoChuDeAdapter;
import com.example.nhaccuatui.Model.ChuDe;
import com.example.nhaccuatui.Model.TheLoai;
import com.example.nhaccuatui.R;
import com.example.nhaccuatui.Service.APIService;
import com.example.nhaccuatui.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachTheLoaiByChuDeActivity extends AppCompatActivity {
    ChuDe chuDe;
    RecyclerView recyclerViewTheLoaiByChuDe;
    Toolbar toolbar;
    DanhSachTheLoaiTheoChuDeAdapter danhSachTheLoaiTheoChuDeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_the_loai_by_chu_de);
        GetIntent();
        init();
        GetData();

    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<TheLoai>> callback = dataService.GetTheLoaiTheoChuDe(chuDe.getIdChuDe());
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> theLoaiArrayList = (ArrayList<TheLoai>) response.body();
                danhSachTheLoaiTheoChuDeAdapter = new DanhSachTheLoaiTheoChuDeAdapter(DanhSachTheLoaiByChuDeActivity.this, theLoaiArrayList);
                recyclerViewTheLoaiByChuDe.setLayoutManager(new GridLayoutManager(DanhSachTheLoaiByChuDeActivity.this, 2));
                recyclerViewTheLoaiByChuDe.setAdapter(danhSachTheLoaiTheoChuDeAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewTheLoaiByChuDe = findViewById(R.id.recycleviewtheloaitheochude);
        toolbar = findViewById(R.id.toobartheloaitheochude);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChuDe());
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("chude")) {
        }
        chuDe = (ChuDe) intent.getSerializableExtra("chude");
    }
}
