package com.example.nhaccuatui.Adapter;

import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhaccuatui.Activity.MainActivity;
import com.example.nhaccuatui.Activity.PlayMusicActivity;
import com.example.nhaccuatui.Model.BaiHat;
import com.example.nhaccuatui.R;
import com.example.nhaccuatui.Service.APIService;
import com.example.nhaccuatui.Service.DataService;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachBaiHatAdapter extends RecyclerView.Adapter<DanhSachBaiHatAdapter.ViewHolder> {
    ArrayList<BaiHat> baiHatArrayList;
    Context context;
    private ProgressDialog mProgressDialog;
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    PlayMusicActivity playMusicActivity;


    public DanhSachBaiHatAdapter(ArrayList<BaiHat> baiHatArrayList, Context context) {
        this.baiHatArrayList = baiHatArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_danh_sach_bai_hat, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        BaiHat baiHat = baiHatArrayList.get(i);
        viewHolder.tvtenbaihat.setText(baiHat.getTenbaihat());
        viewHolder.tvtencasi.setText(baiHat.getCasi());
        viewHolder.tvindex.setText(i + 1 + "");
    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvindex, tvtenbaihat, tvtencasi;
        ImageView imgiconlike, imgicondownload;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            tvindex = itemView.findViewById(R.id.tvdanhsachindex);
            tvtencasi = itemView.findViewById(R.id.tvdanhsachtencasi);
            tvtenbaihat = itemView.findViewById(R.id.tvdanhsachtenbaihat);
            imgiconlike = itemView.findViewById(R.id.imgviewdanhsachiconlike);
            imgicondownload = itemView.findViewById(R.id.imgviewdanhsachicondownload);

            imgiconlike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgiconlike.setImageResource(R.drawable.iconloved);
                    DataService dataService = APIService.getService();
                    Call<String> callback = dataService.UpdateLuotThich("1", baiHatArrayList.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });
                    imgiconlike.setEnabled(false);
                }

            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("song", baiHatArrayList.get(getPosition()));
                    context.startActivity(intent);

                }
            });

            imgicondownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Dialog dialog = new Dialog(itemView.getContext());
                    dialog.setTitle("Download");
                    dialog.setContentView(R.layout.dialog_download);
                    Window window = dialog.getWindow();
                    WindowManager.LayoutParams wlp = window.getAttributes();
                    wlp.gravity = Gravity.BOTTOM;
                    wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                    window.setAttributes(wlp);
                    Button btnAcceptDownload = dialog.findViewById(R.id.btnacceptndownload);
                    Button btnCancleDownload = dialog.findViewById(R.id.btncanceldownload);
                    btnAcceptDownload.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            DownloadManager downloadManager = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
                            Uri uri = Uri.parse(baiHatArrayList.get(getPosition()).getLinkbaihat());
                            DownloadManager.Request request = new DownloadManager.Request(uri);
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        }
                    });

                    btnCancleDownload.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            });

        }
    }




}
