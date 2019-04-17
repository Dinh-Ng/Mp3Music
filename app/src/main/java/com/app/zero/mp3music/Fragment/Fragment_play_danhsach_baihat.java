package com.app.zero.mp3music.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.zero.mp3music.Activity.Activity_Play_Nhac;
import com.app.zero.mp3music.Adapter.PlayNhacAdapter;
import com.app.zero.mp3music.R;

public class Fragment_play_danhsach_baihat extends Fragment {

    RecyclerView recyclerView;
    View view;
    PlayNhacAdapter playNhacAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_danhsach_baihat, container, false);
        recyclerView = view.findViewById(R.id.recycler_play_baihat);

        if (Activity_Play_Nhac.mangBaiHat.size() > 0) {
            playNhacAdapter = new PlayNhacAdapter(getActivity(), Activity_Play_Nhac.mangBaiHat);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(playNhacAdapter);
        }
        return view;
    }
}
