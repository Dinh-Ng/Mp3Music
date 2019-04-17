package com.app.zero.mp3music.Fragment;

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

import com.app.zero.mp3music.Adapter.SearchBaiHatAdapter;
import com.app.zero.mp3music.Model.Baihat;
import com.app.zero.mp3music.R;
import com.app.zero.mp3music.Service.APIService;
import com.app.zero.mp3music.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Tim_Kiem extends Fragment{
    View view;
    Toolbar toolbar;
    RecyclerView recyclerView;
    TextView txt_khong_du_lieu;
    SearchBaiHatAdapter searchBaiHatAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tim_kiem, container, false);
        toolbar = view.findViewById(R.id.toolbar_search);
        recyclerView = view.findViewById(R.id.recycler_search);
        txt_khong_du_lieu = view.findViewById(R.id.txt_khongcodulieu);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
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
                SearchTuKhoaBaiHat(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void SearchTuKhoaBaiHat(String query) {
        Dataservice dataservice = APIService.getService();
        Call<List<Baihat>> callback = dataservice.SearchBaiHat(query);
        callback.enqueue(new Callback<List<Baihat>>() {
            @Override
            public void onResponse(@NonNull Call<List<Baihat>> call, @NonNull Response<List<Baihat>> response) {
                ArrayList<Baihat> mangbaihat = (ArrayList<Baihat>) response.body();
                if (mangbaihat.size() > 0) {
                    searchBaiHatAdapter = new SearchBaiHatAdapter(getActivity(), mangbaihat);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(searchBaiHatAdapter);
                    txt_khong_du_lieu.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    Log.d("BBB", mangbaihat.get(0).getTenbaihat());
                } else {
                    recyclerView.setVisibility(View.GONE);
                    txt_khong_du_lieu.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Baihat>> call, @NonNull Throwable t) {

            }
        });
    }
}
