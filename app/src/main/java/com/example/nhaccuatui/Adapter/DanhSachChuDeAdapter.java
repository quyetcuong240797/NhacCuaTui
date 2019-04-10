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

import com.example.nhaccuatui.Activity.DanhSachTheLoaiByChuDeActivity;
import com.example.nhaccuatui.Model.ChuDe;
import com.example.nhaccuatui.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachChuDeAdapter extends RecyclerView.Adapter<DanhSachChuDeAdapter.ViewHolder> {
    Context context;
    ArrayList<ChuDe> chuDeArrayList;

    public DanhSachChuDeAdapter(Context context, ArrayList<ChuDe> chuDeArrayList) {
        this.context = context;
        this.chuDeArrayList = chuDeArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_danh_sach_chu_de, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Picasso.with(context).load(chuDeArrayList.get(i).getHinhChuDe()).into(viewHolder.imgviewdanhsachchude);
        viewHolder.tvtendanhsachchude.setText(chuDeArrayList.get(i).getTenChuDe());
    }

    @Override
    public int getItemCount() {
        return chuDeArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgviewdanhsachchude;
        TextView tvtendanhsachchude;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgviewdanhsachchude = itemView.findViewById(R.id.imageviewdanhsachchude);
            tvtendanhsachchude = itemView.findViewById(R.id.tvtendanhsachchude);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent =new Intent(context, DanhSachTheLoaiByChuDeActivity.class);
                    intent.putExtra("chude",chuDeArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
