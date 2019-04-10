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
import android.widget.Toast;

import com.example.nhaccuatui.Activity.PlayMusicActivity;
import com.example.nhaccuatui.Model.BaiHat;
import com.example.nhaccuatui.R;
import com.example.nhaccuatui.Service.APIService;
import com.example.nhaccuatui.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaiHatAdapter extends RecyclerView.Adapter<BaiHatAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> baiHatArrayList;

    public BaiHatAdapter(Context context, ArrayList<BaiHat> baiHatArrayList) {
        this.context = context;
        this.baiHatArrayList = baiHatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_baihat,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        BaiHat baiHat = baiHatArrayList.get(i);
        viewHolder.tvtenbaihat.setText(baiHat.getTenbaihat());
        viewHolder.tvtencasi.setText(baiHat.getCasi());
        Picasso.with(context).load(baiHat.getHinhbaihat()).into(viewHolder.imgbaihat);

    }

    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvtencasi,tvtenbaihat;
        ImageView imgbaihat,imgiconlike,imgdownload;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvtenbaihat = itemView.findViewById(R.id.tvtenbaihat);
            tvtencasi = itemView.findViewById(R.id.tvtencasi);
            imgbaihat = itemView.findViewById(R.id.imageviewbaihat);
            imgiconlike = itemView.findViewById(R.id.imgiconlike);
            imgdownload = itemView.findViewById(R.id.imgicondownload);

            imgiconlike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgiconlike.setImageResource(R.drawable.iconloved);
                    DataService dataService = APIService.getService();
                    Call<String> callback = dataService.UpdateLuotThich("1",baiHatArrayList.get(getPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua = response.body();
                            if(ketqua.equals("Success")) {
                                Toast.makeText(context,"Ban Da Thich",Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Loi", Toast.LENGTH_SHORT).show();
                            }
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
                        intent.putExtra("song",baiHatArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
