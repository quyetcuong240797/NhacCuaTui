package com.example.nhaccuatui.Service;

import com.example.nhaccuatui.Model.Album;
import com.example.nhaccuatui.Model.BaiHat;
import com.example.nhaccuatui.Model.CheDeTheLoai;
import com.example.nhaccuatui.Model.ChuDe;
import com.example.nhaccuatui.Model.Playlist;
import com.example.nhaccuatui.Model.Quangcao;
import com.example.nhaccuatui.Model.TheLoai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataService {

    @GET("songbanner.php")
    Call<List<Quangcao>> GetDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> GetPlaylistCurrentDay();

    @GET("themesandcategories.php")
    Call<CheDeTheLoai> GetChuDeTheThoaiCurrentDay();

    @GET("album.php")
    Call<List<Album>> GetAlbum();

    @GET("song.php")
    Call<List<BaiHat>> GetBaiHat();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihattheoquangcao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihattheoplaylist(@Field("idplaylist") String idplaylist);

    @GET("danhsachcacplaylist.php")
    Call<List<Playlist>> GetDanhSachCacPlaylist();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhsachbaihattheotheloai(@Field("idtheloai") String idtheloai);


    @GET("xemthemchude.php")
    Call<List<ChuDe>> GetDanhSachCacChuDe();

    @FormUrlEncoded
    @POST("gettheloaibychude.php")
    Call<List<TheLoai>> GetTheLoaiTheoChuDe(@Field("idchude") String idchude);

    @GET("getallalbum.php")
    Call<List<Album>> GetAllAlbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatByAlBum(@Field("idalbum") String idalbum);


    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> UpdateLuotThich(@Field("luotthich") String luotthich,@Field("idbaihat") String idbaihat);


    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<BaiHat>> GetSearchBaiHat(@Field("tukhoa") String tukhoa);




}
