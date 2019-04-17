package com.app.zero.mp3music.Service;

import com.app.zero.mp3music.Model.Album;
import com.app.zero.mp3music.Model.Baihat;
import com.app.zero.mp3music.Model.ChuDe;
import com.app.zero.mp3music.Model.ChuDeTheLoai;
import com.app.zero.mp3music.Model.Playlist;
import com.app.zero.mp3music.Model.Quangcao;
import com.app.zero.mp3music.Model.TheLoai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {

    @GET("songbanner.php")
    Call<List<Quangcao>> GetDataBanner();

    @GET("Playlist.php")
    Call<List<Playlist>> GetPlaylistDay();

    @GET("chudevatheloai.php")
    Call<ChuDeTheLoai> GetChuDeDay();

    @GET("album.php")
    Call<List<Album>> GetAlbumHot();



    @GET("baihatduocthich.php")
    Call<List<Baihat>> GetBaiHatHot();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetBaiHatTheoQuangCao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetBaiHatTheoPlaylist(@Field("idplaylist") String idplaylist);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetBaiHatTheoTheLoai(@Field("idtheloai") String idtheloai);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Baihat>> GetBaiHatTheoAlbum(@Field("idalbum") String idalbum);

    @GET("danhsachcacplaylist.php")
    Call<List<Playlist>> GetDanhsachPlaylist();

    @GET("tatcachude.php")
    Call<List<ChuDe>> GetDanhsachChuDe();

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> GetTheLoaiTheoChuDe(@Field("idchude") String idchude);

    @GET("tatcaalbum.php")
    Call<List<Album>> GetAlbum();

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> UpdateLuotThich(@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<Baihat>> SearchBaiHat(@Field("tukhoa") String tukhoa);
}
