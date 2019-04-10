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
import com.example.nhaccuatui.Model.TheLoai;
import com.example.nhaccuatui.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachTheLoaiTheoChuDeAdapter extends RecyclerView.Adapter<DanhSachTheLoaiTheoChuDeAdapter.ViewHolder>{
    Context context;
    ArrayList<TheLoai> theLoaiArrayList;

    public DanhSachTheLoaiTheoChuDeAdapter(Context context, ArrayList<TheLoai> theLoaiArrayList) {
        this.context = context;
        this.theLoaiArrayList = theLoaiArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_the_loai_theo_chu_de,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvtentheloai.setText(theLoaiArrayList.get(i).getTenTheLoai());
        Picasso.with(context).load(theLoaiArrayList.get(i).getHinhTheLoai()).into(viewHolder.imgviewtheloai);

    }

    @Override
    public int getItemCount() {
        return theLoaiArrayList.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgviewtheloai;
        TextView tvtentheloai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgviewtheloai = itemView.findViewById(R.id.imgviewtheloaitheochude);
            tvtentheloai = itemView.findViewById(R.id.tvtentheloaitheochude);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("idtheloai",theLoaiArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}
