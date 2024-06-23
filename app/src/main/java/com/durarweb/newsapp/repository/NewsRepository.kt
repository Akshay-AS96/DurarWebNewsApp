package com.durarweb.newsapp.repository


import com.durarweb.newsapp.model.NewsResponse
import com.durarweb.newsapp.network.service.NewsApiService
import com.durarweb.newsapp.utils.Constants.Companion.API_KEY
import com.durarweb.newsapp.utils.Constants.Companion.QUERY


class NewsRepository(private val userService: NewsApiService) {

    // Fetches a list of news from the API
    suspend fun getNews(): NewsResponse {
        return userService.getEverything(QUERY, apiKey = API_KEY)
    }
}