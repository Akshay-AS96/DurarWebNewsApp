package com.durarweb.newsapp.network;

import com.durarweb.newsapp.network.service.HeadLinesApiService;
import com.durarweb.newsapp.utils.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HeadlinesCloudManager {

    public static final HeadLinesApiService apiService;

    static {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(HeadLinesApiService.class);
    }
}