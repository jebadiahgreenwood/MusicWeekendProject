package com.example.assignment_2.Presenter;

import android.content.Context;

import com.example.assignment_2.Model.SearchResultsPojo;
import com.example.assignment_2.View.ViewContract;

public interface PresenterContract {
    void bind(ViewContract view);
    Context getViewContext();
    void unBind();
    void searchMusic();
    void presentRockSearchResults(SearchResultsPojo resultsPojo);
    void networkFailure(String message);
    boolean hasNetwork();
}
