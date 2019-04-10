package com.example.nhaccuatui.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nhaccuatui.Activity.DanhSachBaiHatActivity;
import com.example.nhaccuatui.Activity.DanhSachChuDeActivity;
import com.example.nhaccuatui.Activity.DanhSachTheLoaiByChuDeActivity;
import com.example.nhaccuatui.Model.CheDeTheLoai;
import com.example.nhaccuatui.Model.ChuDe;
import com.example.nhaccuatui.Model.TheLoai;
import com.example.nhaccuatui.R;
import com.example.nhaccuatui.Service.APIService;
import com.example.nhaccuatui.Service.DataService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentChuDeTheLoai extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView tvxemthemchudetheloai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude_theloai, container, false);
        horizontalScrollView = view.findViewById(R.id.horizontalscrollview);
        tvxemthemchudetheloai = view.findViewById(R.id.tvxemthemchudetheloai);
        tvxemthemchudetheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DanhSachChuDeActivity.class);
                startActivity(intent);
            }
        });
        GetData();
        return view;
    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<CheDeTheLoai> callback = dataService.GetChuDeTheThoaiCurrentDay();
        callback.enqueue(new Callback<CheDeTheLoai>() {
            @Override
            public void onResponse(Call<CheDeTheLoai> call, Response<CheDeTheLoai> response) {
                CheDeTheLoai cheDeTheLoai = response.body();

                final ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();
                chuDeArrayList.addAll(cheDeTheLoai.getChuDe());

                final ArrayList<TheLoai> theLoaiArrayList = new ArrayList<>();
                theLoaiArrayList.addAll(cheDeTheLoai.getTheLoai());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(580, 250);
                layoutParams.setMargins(10, 20, 10, 30);

                for (int i = 0; i < (chuDeArrayList.size()); i++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(20);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (chuDeArrayList.get(i).getHinhChuDe() != null) {
                        Picasso.with(getActivity()).load(chuDeArrayList.get(i).getHinhChuDe()).into(imageView);
                    }

                    TextView textView = new TextView(getActivity());
                    textView.setTextColor(Color.WHITE);
                    textView.setTextSize(18);
                    textView.setText(chuDeArrayList.get(i).getTenChuDe());

                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    cardView.addView(textView);
                    linearLayout.addView(cardView);
                    final int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent =new Intent(getActivity(), DanhSachTheLoaiByChuDeActivity.class);
                            intent.putExtra("chude",chuDeArrayList.get(finalI));
                            startActivity(intent);
                        }
                    });

                }


                for (int j = 0; j < (theLoaiArrayList.size()); j++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(20);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (theLoaiArrayList.get(j).getHinhTheLoai() != null) {
                        Picasso.with(getActivity()).load(theLoaiArrayList.get(j).getHinhTheLoai()).into(imageView);
                    }

                    TextView textView = new TextView(getActivity());
                    textView.setTextColor(Color.WHITE);
                    textView.setTextSize(18);
                    textView.setText(theLoaiArrayList.get(j).getTenTheLoai());

                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    cardView.addView(textView);
                    linearLayout.addView(cardView);


                    final int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), DanhSachBaiHatActivity.class);
                            intent.putExtra("idtheloai", theLoaiArrayList.get(finalJ));
                            startActivity(intent);

                        }
                    });
                }

                horizontalScrollView.addView(linearLayout);


            }

            @Override
            public void onFailure(Call<CheDeTheLoai> call, Throwable t) {

            }
        });
    }
}
