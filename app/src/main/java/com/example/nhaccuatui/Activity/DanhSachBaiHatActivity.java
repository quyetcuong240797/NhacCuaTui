package com.example.nhaccuatui.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.StrictMode;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.nhaccuatui.Adapter.DanhSachBaiHatAdapter;
import com.example.nhaccuatui.Model.Album;
import com.example.nhaccuatui.Model.BaiHat;
import com.example.nhaccuatui.Model.Playlist;
import com.example.nhaccuatui.Model.Quangcao;
import com.example.nhaccuatui.Model.TheLoai;
import com.example.nhaccuatui.R;
import com.example.nhaccuatui.Service.APIService;
import com.example.nhaccuatui.Service.DataService;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatActivity extends AppCompatActivity {
    Quangcao quangcao;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewDanhsachBaihat;
    FloatingActionButton floatingActionButton;
    ImageView imgviewdanhsachcakhuc;
    public static ArrayList<BaiHat> mangbaihat = new ArrayList<>();
    DanhSachBaiHatAdapter danhSachAdapter;
    Playlist playlist;
    TheLoai theLoai;
    Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DataIntent();
        anhxa();
        init();
        if (quangcao != null && !quangcao.getTenBaiHat().equals("")) {
            setValueIntent(quangcao.getTenBaiHat(), quangcao.getHinhBaiHat());
            GetDataQuangCao(quangcao.getIdQuangCao());

        }

        if (playlist != null && !playlist.getTen().equals("")) {
            setValueIntent(playlist.getTen(), playlist.getHinhPlaylist());
            GetDataplaylist(playlist.getIdPlaylist());

        }

        if (theLoai != null && !theLoai.getTenTheLoai().equals("")) {
            setValueIntent(theLoai.getTenTheLoai(), theLoai.getHinhTheLoai());
            GetDataTheloai(theLoai.getIdTheLoai());
        }

        if (album != null && !album.getTenAlbum().equals("")) {
            setValueIntent(album.getTenAlbum(), album.getHinhAlbum());
            GetDataAlbum(album.getIdAlbum());
        }

    }

    private void GetDataAlbum(String idAlbum) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetDanhSachBaiHatByAlBum(idAlbum);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhSachAdapter = new DanhSachBaiHatAdapter(mangbaihat,DanhSachBaiHatActivity.this);
                recyclerViewDanhsachBaihat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerViewDanhsachBaihat.setAdapter(danhSachAdapter);
                eventClicks();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetDataTheloai(String idtheloai) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetDanhsachbaihattheotheloai(idtheloai);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhSachAdapter = new DanhSachBaiHatAdapter(mangbaihat,DanhSachBaiHatActivity.this );
                recyclerViewDanhsachBaihat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerViewDanhsachBaihat.setAdapter(danhSachAdapter);
                eventClicks();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetDataplaylist(String idplaylist) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetDanhsachbaihattheoplaylist(idplaylist);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhSachAdapter = new DanhSachBaiHatAdapter(mangbaihat,DanhSachBaiHatActivity.this );
                recyclerViewDanhsachBaihat.setLayoutManager(new LinearLayoutManager(DanhSachBaiHatActivity.this));
                recyclerViewDanhsachBaihat.setAdapter(danhSachAdapter);
                eventClicks();

            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetDataQuangCao(String idquangcao) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetDanhsachbaihattheoquangcao(idquangcao);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                mangbaihat = (ArrayList<BaiHat>) response.body();
                danhSachAdapter = new DanhSachBaiHatAdapter(mangbaihat,DanhSachBaiHatActivity.this );
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DanhSachBaiHatActivity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewDanhsachBaihat.setLayoutManager(linearLayoutManager);
                recyclerViewDanhsachBaihat.setAdapter(danhSachAdapter);
                eventClicks();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });

    }

    private void setValueIntent(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url = new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                collapsingToolbarLayout.setBackground(bitmapDrawable);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(hinh).into(imgviewdanhsachcakhuc);

    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

    }

    private void anhxa() {
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        imgviewdanhsachcakhuc = findViewById(R.id.imageviewdanhsachcakhuc);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        toolbar = findViewById(R.id.toobarDanhsach);
        recyclerViewDanhsachBaihat = findViewById(R.id.recycleviewdanhsachbaihat);
        floatingActionButton = findViewById(R.id.floatingactionbutton);
        floatingActionButton.setEnabled(false);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("banner")) {
                quangcao = (Quangcao) intent.getSerializableExtra("banner");
            }

            if (intent.hasExtra("itemplaylist")) {
                playlist = (Playlist) intent.getSerializableExtra("itemplaylist");

            }

            if (intent.hasExtra("idtheloai")) {
                theLoai = (TheLoai) intent.getSerializableExtra("idtheloai");
            }

            if (intent.hasExtra("album")) {
                album = (Album) intent.getSerializableExtra("album");
            }
        }
    }

    private void eventClicks() {
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachBaiHatActivity.this,PlayMusicActivity.class);
                intent.putExtra("danhsachsong",mangbaihat);
                startActivity(intent);
            }
        });
    }
}
