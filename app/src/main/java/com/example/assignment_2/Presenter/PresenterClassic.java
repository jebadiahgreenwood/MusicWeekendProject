package com.example.assignment_2.Presenter;

import android.content.Context;

import com.example.assignment_2.Model.DataModel;
import com.example.assignment_2.Model.SearchResultsPojo;
import com.example.assignment_2.View.ViewContract;

public class PresenterClassic implements PresenterContract {
    private ViewContract view;
    private DataModel dataModel;
    @Override
    public void bind(ViewContract view) {
        this.view = view;
        dataModel = DataModel.getInstance();
        dataModel.setPresenterClassic(this);
    }

    @Override
    public void unBind() {
        view = null;
        dataModel = null;
    }

    @Override
    public void searchMusic() {
        if (dataModel != null)
            dataModel.searchItunesForClassic();
    }

    @Override
    public void presentRockSearchResults(SearchResultsPojo resultsPojo) {
        if (view != null)
            view.presentMusicList(resultsPojo.results);
    }

    @Override
    public void networkFailure(String message) {
        if (view != null)
            view.onError(message);
    }

    @Override
    public boolean hasNetwork()
    {
        return view.hasNetwork();
    }

    @Override
    public Context getViewContext()
    {
        return view.getViewContext();
    }

}
