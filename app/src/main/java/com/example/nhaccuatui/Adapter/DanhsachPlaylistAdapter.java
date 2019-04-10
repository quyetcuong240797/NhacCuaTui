package com.example.nhaccuatui.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhaccuatui.Activity.DanhSachBaiHatActivity;
import com.example.nhaccuatui.Model.Playlist;
import com.example.nhaccuatui.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhsachPlaylistAdapter extends RecyclerView.Adapter<DanhsachPlaylistAdapter.ViewHolder> {
    Context context;
    ArrayList<Playlist> playlistArrayList;

    public DanhsachPlaylistAdapter(Context context, ArrayList<Playlist> playlistArrayList) {
        this.context = context;
        this.playlistArrayList = playlistArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_danh_sach_cac_playlist, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Playlist playlist = playlistArrayList.get(i);
        Picasso.with(context).load(playlist.getHinhPlaylist()).into(viewHolder.hinhnen);
        viewHolder.tvtenplaylist.setText(playlist.getTen());

    }

    @Override
    public int getItemCount() {
        return playlistArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView hinhnen;
        TextView tvtenplaylist;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hinhnen = itemView.findViewById(R.id.imgviewdanhsachcacplaylist);
            tvtenplaylist = itemView.findViewById(R.id.tvtendanhsachcacplaylist);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("itemplaylist",playlistArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}
