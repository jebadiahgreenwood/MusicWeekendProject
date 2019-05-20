package com.example.assignment_2.Model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AppleItunesInterface {
    @GET("search?amp;media=music&amp;entity=song&amp;limit=50")
    Call<SearchResultsPojo> getSearchResultsFor(@Query("term") String searchType);
}
