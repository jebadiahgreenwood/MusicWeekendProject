package com.example.assignment_2.View;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment; import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment_2.Model.MusicDetailsPojo;
import com.example.assignment_2.Presenter.PresenterClassic;
import com.example.assignment_2.Presenter.PresenterRock;
import com.example.assignment_2.R;

import java.util.List;

public class ClassicFragment extends Fragment implements ViewContract {
    private PresenterClassic presenter;
    private RecyclerView rvClassic;
    private RecyclerAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.classiclayout, viewGroup, false);
        adapter = new RecyclerAdapter();
        rvClassic = view.findViewById(R.id.rv_classic);
        rvClassic.setLayoutManager(new LinearLayoutManager(getContext()));
        rvClassic.setAdapter(adapter);
        swipeRefreshLayout = view.findViewById(R.id.swiperefresh_classic);
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
        presenter = new PresenterClassic();
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
        adapter.setDataSet(dataSet);
    }

    @Override
    public Context getViewContext() {
        return getContext();
    }

    @Override
    public boolean hasNetwork()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) getViewContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) return true;
        return false;
    }
}
