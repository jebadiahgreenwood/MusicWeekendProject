package com.example.assignment_2.Presenter;

import android.content.Context;

import com.example.assignment_2.Model.DataModel;
import com.example.assignment_2.Model.SearchResultsPojo;
import com.example.assignment_2.View.ViewContract;

public class PresenterPop implements PresenterContract{
    private ViewContract view;
    private DataModel dataModel;
    @Override
    public void bind(ViewContract view) {
        this.view = view;
        dataModel = DataModel.getInstance();
        dataModel.setPresenterPop(this);
    }

    @Override
    public Context getViewContext() {
        return view.getViewContext();
    }

    @Override
    public void unBind() {
        view = null;
        dataModel = null;
    }

    @Override
    public void searchMusic() {
        if (dataModel != null)
            dataModel.searchItunesForPop();
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
    public boolean hasNetwork() {
        return false;
    }
}
