package com.durarweb.newsapp.network.service


import com.durarweb.newsapp.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("everything")
    suspend fun getEverything(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String
    ): NewsResponse

}