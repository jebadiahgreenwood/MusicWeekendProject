package com.example.assignment_2.View;

import android.content.Context;

import com.example.assignment_2.Model.MusicDetailsPojo;

import java.util.List;

public interface ViewContract {
    void bindPresenter();
    void onError(String message);
    void populateMusicList();
    void presentMusicList(List<MusicDetailsPojo> dataSet);
    Context getViewContext();
    boolean hasNetwork();
}
