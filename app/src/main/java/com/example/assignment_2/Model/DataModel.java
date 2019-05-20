package com.example.assignment_2.Model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.assignment_2.Presenter.PresenterClassic;
import com.example.assignment_2.Presenter.PresenterPop;
import com.example.assignment_2.Presenter.PresenterRock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataModel {
    private static final String TAG = "DataModel";
    public PersitanceDB persitanceDB;
    //singleton
    private DataModel()
    {

    }
    private static DataModel dataModel = null;
    public static DataModel getInstance()
    {
        if (dataModel == null)
            dataModel = new DataModel();
        return dataModel;
    }
    PresenterClassic presenterClassic;
    PresenterRock presenterRock;
    PresenterPop presenterPop;
    Callback<SearchResultsPojo> searchCompleteRock = new Callback<SearchResultsPojo>() {
        @Override
        public void onResponse(Call<SearchResultsPojo> call, Response<SearchResultsPojo> response) {
            if (presenterRock != null)
                presenterRock.presentRockSearchResults(response.body());
        }

        @Override
        public void onFailure(Call<SearchResultsPojo> call, Throwable t) {
            presenterRock.networkFailure(t.getMessage());
        }
    };

    Callback<SearchResultsPojo> searchCompleteClassic = new Callback<SearchResultsPojo>() {
        @Override
        public void onResponse(Call<SearchResultsPojo> call, Response<SearchResultsPojo> response) {
            if (presenterClassic != null) {
                presenterClassic.presentRockSearchResults(response.body());
                SearchResultsPojo resultsPojo = response.body();
                Iterator iterator = resultsPojo.results.iterator();
                ContentValues contentValues = new ContentValues();
                persitanceDB.clearData();
                SQLiteDatabase writeDB = persitanceDB.getWritableDatabase();
                while (iterator.hasNext())
                {
                    MusicDetailsPojo details = (MusicDetailsPojo) iterator.next();
                    contentValues.put(TableConstants.ClassicTable.ARTIST_NAME_COLUMN, details.artistName);
                    contentValues.put(TableConstants.ClassicTable.TRACK_NAME_COLUMN, details.trackName);
                    if (details.artworkUrl60 != null)
                        contentValues.put(TableConstants.ClassicTable.ARTWORK_URI_COLUMN, details.artworkUrl60);
                    if (details.previewUrl != null)
                        contentValues.put(TableConstants.ClassicTable.SAMPLE_URI_COLUMN, details.previewUrl);
                    if (details.trackPrice != null)
                        contentValues.put(
                                TableConstants.ClassicTable.TRACK_PRICE_COLUMN,
                                details.trackPrice.toString());
                    else
                        Log.d(TAG, "price info missing from MusicDetailsPojo");
                    if(writeDB.insert(TableConstants.ClassicTable.TABLE_NAME, null, contentValues) > 0)
                    {
                        //TODO success
                        //Log.d("Database Test Insert", "success");

                    }else{
                        //TODO failure
                    }
                }
                writeDB.close();

            }
        }

        @Override
        public void onFailure(Call<SearchResultsPojo> call, Throwable t) {
            presenterClassic.networkFailure(t.getMessage());
        }
    };

    Callback<SearchResultsPojo> searchCompletePop = new Callback<SearchResultsPojo>() {
        @Override
        public void onResponse(Call<SearchResultsPojo> call, Response<SearchResultsPojo> response) {
            if (presenterPop != null)
                presenterPop.presentRockSearchResults(response.body());
        }

        @Override
        public void onFailure(Call<SearchResultsPojo> call, Throwable t) {
            presenterPop.networkFailure(t.getMessage());
        }
    };

    public void setPresenterRock(PresenterRock presenter)
    {
        this.presenterRock = presenter;
    }
    public void setPresenterClassic(PresenterClassic presenter)
    {
        this.presenterClassic = presenter;
        persitanceDB = new PersitanceDB(presenterClassic.getViewContext());
    }
    public void setPresenterPop(PresenterPop presenter)
    {
        this.presenterPop = presenter;
    }


    public void searchItunesForRock()
    {
        new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AppleItunesInterface.class)
                .getSearchResultsFor("rock")
                .enqueue(searchCompleteRock);
    }

    public void searchItunesForClassic()
    {
        if(presenterClassic.hasNetwork()) {
            new Retrofit.Builder()
                    .baseUrl("https://itunes.apple.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(AppleItunesInterface.class)
                    .getSearchResultsFor("classic")
                    .enqueue(searchCompleteClassic);
        }else{
            if(!persitanceDB.isTableExisting(TableConstants.ClassicTable.TABLE_NAME))
                return;
            SQLiteDatabase readDB = persitanceDB.getReadableDatabase();
            Cursor cursor = readDB.query(TableConstants.ClassicTable.TABLE_NAME, null, null, null, null, null, null);
            List<MusicDetailsPojo> searchResults = new ArrayList<>() ;
            while (cursor.moveToNext())
            {
                MusicDetailsPojo details = new MusicDetailsPojo();
                try{
                    details.artistName = cursor.getString(cursor.getColumnIndexOrThrow(
                            TableConstants.ClassicTable.ARTIST_NAME_COLUMN));
                    details.trackPrice = new Double(cursor.getString(cursor.getColumnIndexOrThrow(
                            TableConstants.ClassicTable.TRACK_PRICE_COLUMN)));
                    details.trackName =cursor.getString(cursor.getColumnIndexOrThrow(
                            TableConstants.ClassicTable.TRACK_NAME_COLUMN));
                    details.artworkUrl60 = cursor.getString(cursor.getColumnIndexOrThrow(
                            TableConstants.ClassicTable.ARTWORK_URI_COLUMN));
                    details.previewUrl = cursor.getString(cursor.getColumnIndexOrThrow(
                            TableConstants.ClassicTable.SAMPLE_URI_COLUMN));
                }catch (Exception err) {
                    err.printStackTrace();
                }
                searchResults.add(details);
            }
            SearchResultsPojo search = new SearchResultsPojo();
            search.resultCount = searchResults.size();
            search.results = searchResults;
            presenterClassic.presentRockSearchResults(search);
        }
    }

    public void searchItunesForPop()
    {
        new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AppleItunesInterface.class)
                .getSearchResultsFor("pop")
                .enqueue(searchCompletePop);
    }






}
