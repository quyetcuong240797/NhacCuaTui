package com.example.nhaccuatui.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nhaccuatui.Model.BaiHat;
import com.example.nhaccuatui.R;

import java.util.ArrayList;

public class PlayListSongPlayScreenAdapter extends RecyclerView.Adapter<PlayListSongPlayScreenAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> baiHatArrayList;

    public PlayListSongPlayScreenAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_play_screen_list,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        BaiHat baiHat = baiHatArrayList.get(i);
        viewHolder.tvnamesong.setText(baiHat.getTenbaihat());
        viewHolder.tvcasi.setText(baiHat.getCasi());
        viewHolder.tvindex.setText(i+1+"");
    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvindex,tvcasi,tvnamesong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvindex = itemView.findViewById(R.id.tvsonginlistindex);
            tvcasi =  itemView.findViewById(R.id.tvnamecasiinlist);
            tvnamesong = itemView.findViewById(R.id.tvnamesonginlist);

        }
    }
}
