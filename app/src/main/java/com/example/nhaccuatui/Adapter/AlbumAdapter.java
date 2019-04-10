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
import com.example.nhaccuatui.Model.Album;
import com.example.nhaccuatui.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {

    Context context;
    ArrayList<Album> albumArrayList;

    public AlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_album,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Album album = albumArrayList.get(i);
        viewHolder.tvcasialbum.setText(album.getTenCasi());
        viewHolder.tvtenalbum.setText(album.getTenAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(viewHolder.imghinhalbum);

    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imghinhalbum;
        TextView tvtenalbum,tvcasialbum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhalbum =itemView.findViewById(R.id.imageviewalbum);
            tvtenalbum = itemView.findViewById(R.id.textviewtenalbum);
            tvcasialbum = itemView.findViewById(R.id.tvtencasialbum);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("album",albumArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }

}
