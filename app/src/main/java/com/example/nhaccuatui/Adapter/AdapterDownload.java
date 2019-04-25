package com.example.nhaccuatui.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhaccuatui.Model.BaiHat;
import com.example.nhaccuatui.Model.SongDownload;
import com.example.nhaccuatui.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterDownload extends RecyclerView.Adapter<AdapterDownload.ViewHolder> {
    Context context;
    ArrayList<BaiHat> arrayListsong;

    public AdapterDownload(Context context, ArrayList<BaiHat> arrayListsong) {
        this.context = context;
        this.arrayListsong = arrayListsong;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_download, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvnamesongdownload.setText(arrayListsong.get(i).getTenbaihat());
        viewHolder.tvnamecasidownload.setText(arrayListsong.get(i).getCasi());
        Picasso.with(context).load(arrayListsong.get(i).getLinkbaihat()).into(viewHolder.imgviewsongdownload);
    }

    @Override
    public int getItemCount() {
        return arrayListsong.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvnamesongdownload;
        TextView tvnamecasidownload;
        ImageView imgviewsongdownload;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvnamesongdownload = itemView.findViewById(R.id.tvnameospngdownloaded);
            tvnamecasidownload = itemView.findViewById(R.id.tvnamecasidownload);
            imgviewsongdownload = itemView.findViewById(R.id.imgviewdownloaded);

        }
    }
}
