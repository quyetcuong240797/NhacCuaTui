package com.example.nhaccuatui.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.nhaccuatui.Adapter.DanhSachChuDeAdapter;
import com.example.nhaccuatui.Model.ChuDe;
import com.example.nhaccuatui.R;
import com.example.nhaccuatui.Service.APIService;
import com.example.nhaccuatui.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachChuDeActivity extends AppCompatActivity {
    RecyclerView recyclerViewchude;
    Toolbar toolbarchude;
    DanhSachChuDeAdapter danhSachChuDeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_chu_de);
        inits();
        GetData();
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<ChuDe>> callback = dataService.GetDanhSachCacChuDe();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> mangchude = (ArrayList<ChuDe>) response.body();
                danhSachChuDeAdapter = new DanhSachChuDeAdapter(DanhSachChuDeActivity.this,mangchude);
                recyclerViewchude.setLayoutManager(new GridLayoutManager(DanhSachChuDeActivity.this,1));
                recyclerViewchude.setAdapter(danhSachChuDeAdapter);

            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }

    private void inits() {
        recyclerViewchude = findViewById(R.id.recycleviewchude);
        toolbarchude = findViewById(R.id.toobarchude);
        setSupportActionBar(toolbarchude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Chủ Đề");
        toolbarchude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
