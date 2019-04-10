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
    ArrayList<HashMap<String, String>> songsList;

    public AdapterDownload(Context context, ArrayList<HashMap<String, String>> songsList) {
        this.context = context;
        this.songsList = songsList;
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
        viewHolder.tvnamesongdownload.setText(songsList.get(i).get("file_name"));
    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvnamesongdownload;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvnamesongdownload = itemView.findViewById(R.id.tvnameospngdownloaded);
        }
    }
}
