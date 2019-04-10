package com.example.nhaccuatui.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nhaccuatui.Adapter.BaiHatAdapter;
import com.example.nhaccuatui.Model.BaiHat;
import com.example.nhaccuatui.R;
import com.example.nhaccuatui.Service.APIService;
import com.example.nhaccuatui.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBaiHat extends Fragment {
    View view;
    RecyclerView recyclerViewBaiHat;
    BaiHatAdapter baiHatAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_baihat,container,false);
        recyclerViewBaiHat =view.findViewById(R.id.recycleviewBaiHat);

        GetData();
        return view;


    }

    private void GetData() {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>>  callback = dataService.GetBaiHat();
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> baiHatArrayList  = (ArrayList<BaiHat>) response.body();
                baiHatAdapter = new BaiHatAdapter(getActivity(),baiHatArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewBaiHat.setLayoutManager(linearLayoutManager);
                recyclerViewBaiHat.setAdapter(baiHatAdapter);

            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
}
