package com.durarweb.newsapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.durarweb.newsapp.model.Article
import com.durarweb.newsapp.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel(private val repository: NewsRepository) : ViewModel() {
    private val _newsList = MutableLiveData<List<Article>>()
    val newsList: LiveData<List<Article>> get() = _newsList
    val searchResults = MutableLiveData<List<Article>?>()

    val errorMessage = MutableLiveData<String>()
    val loadingState = MutableLiveData<Boolean>()

    init {
        fetchNews()
    }

    private fun fetchNews() {
        viewModelScope.launch {
            loadingState.value = true
            try {
                val response = withContext(Dispatchers.IO) { repository.getNews() }
                _newsList.postValue(response.articles)
                searchResults.postValue(response.articles)
                Log.d("API_SUCCESS", "News: $response")
                Log.d("API_LOG", "Total News: ${response.totalResults}")
            } catch (e: Exception) {
                Log.e("API_ERROR", "Error: ${e.message}")
                errorMessage.postValue(e.message)
            } finally {
                loadingState.value = false
            }
        }
    }

    fun searchNews(query: String) {
        searchResults.value = if (query.isNotEmpty()) {
            _newsList.value?.filter {
                it.title?.contains(query, ignoreCase = true) == true
            }
        } else {
            newsList.value
        }
    }
}