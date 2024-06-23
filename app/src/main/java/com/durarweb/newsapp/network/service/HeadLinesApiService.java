package com.durarweb.newsapp.network.service;

import com.durarweb.newsapp.model.HeadlinesResponse;
import com.durarweb.newsapp.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface HeadLinesApiService {

    @GET("top-headlines/sources")
    Call<HeadlinesResponse> getTopHeadlinesSources(
            @Query("apiKey") String apiKey
    );

}