package com.example.assignment_2.View;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment_2.Model.MusicDetailsPojo;
import com.example.assignment_2.Presenter.PresenterRock;
import com.example.assignment_2.R;

import java.util.List;

public class RockFragment extends Fragment implements ViewContract {
    private PresenterRock presenter;
    private RecyclerView rvRock;
    private RecyclerAdapter adapterRock;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rocklayout, viewGroup, false);
        adapterRock = new RecyclerAdapter();
        rvRock = view.findViewById(R.id.rv_rock);
        rvRock.setLayoutManager(new LinearLayoutManager(getContext()));
        rvRock.setAdapter(adapterRock);
        swipeRefreshLayout = view.findViewById(R.id.swiperefresh_rock);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                populateMusicList();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        bindPresenter();
        populateMusicList();
        return view;
    }

    @Override
    public void bindPresenter() {
        presenter = new PresenterRock();
        presenter.bind(this);
    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void populateMusicList() {
        presenter.searchMusic();
    }

    @Override
    public void presentMusicList(List<MusicDetailsPojo> dataSet) {
        adapterRock.setDataSet(dataSet);
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public boolean hasNetwork() {
        return false;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            bindPresenter();
            populateMusicList();
        }
    }
}