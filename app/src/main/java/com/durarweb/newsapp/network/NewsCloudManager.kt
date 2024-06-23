package com.durarweb.newsapp.network

import com.durarweb.newsapp.network.service.NewsApiService
import com.durarweb.newsapp.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface NewsCloudManager {

    companion object {

        private val retrofit: Retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()


        val newsApiService: NewsApiService = retrofit.create(NewsApiService::class.java)
    }
}

