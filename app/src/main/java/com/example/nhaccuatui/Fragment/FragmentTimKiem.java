package com.example.nhaccuatui.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nhaccuatui.Adapter.SearchAdapter;
import com.example.nhaccuatui.Model.BaiHat;
import com.example.nhaccuatui.R;
import com.example.nhaccuatui.Service.APIService;
import com.example.nhaccuatui.Service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentTimKiem extends Fragment {

    View view;
    Toolbar toolbarsearch;
    RecyclerView recyclerViewSearch;
    TextView tvkhongcodulieu;
    SearchAdapter searchAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tim_kiem, container, false);
        toolbarsearch = view.findViewById(R.id.toobarsearchbaihat);
        recyclerViewSearch = view.findViewById(R.id.recycle_searchbaihat);
        tvkhongcodulieu = view.findViewById(R.id.tvkhongcodulieu);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbarsearch);
        toolbarsearch.setTitle("");
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_view, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchTuKhoaBaiHat(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void searchTuKhoaBaiHat(String query) {
        DataService dataService = APIService.getService();
        Call<List<BaiHat>> callback = dataService.GetSearchBaiHat(query);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> mangbaihat = (ArrayList<BaiHat>) response.body();
                if (mangbaihat.size() > 0) {
                    searchAdapter = new SearchAdapter(getActivity(), mangbaihat);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerViewSearch.setLayoutManager(linearLayoutManager);
                    recyclerViewSearch.setAdapter(searchAdapter);
                    tvkhongcodulieu.setVisibility(View.GONE);
                    recyclerViewSearch.setVisibility(View.VISIBLE);
                } else {
                    recyclerViewSearch.setVisibility(View.GONE);
                    tvkhongcodulieu.setVisibility(View.VISIBLE);
                }
                Log.d("BBB", "Suceess");
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
}
