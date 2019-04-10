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

import com.example.nhaccuatui.Activity.PlayMusicActivity;
import com.example.nhaccuatui.Model.BaiHat;
import com.example.nhaccuatui.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> baiHatArrayList;

    public SearchAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater =LayoutInflater.from(viewGroup.getContext());
        View view  = inflater.inflate(R.layout.dong_search,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        BaiHat baiHat = baiHatArrayList.get(i);
        viewHolder.tvcasi.setText(baiHat.getCasi());
        viewHolder.tvbaihat.setText(baiHat.getTenbaihat());
        Picasso.with(context).load(baiHat.getHinhbaihat()).into(viewHolder.imgviewbaihat);

    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvbaihat,tvcasi;
        ImageView imgviewbaihat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvbaihat = itemView.findViewById(R.id.tvsearchsong);
            tvcasi = itemView.findViewById(R.id.tvsearchcasi);
            imgviewbaihat = itemView.findViewById(R.id.imgviewsearch);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,PlayMusicActivity.class);
                    intent.putExtra("cakhuc",baiHatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
