package com.durarweb.newsapp.viewmodel_factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.durarweb.newsapp.repository.NewsRepository
import com.durarweb.newsapp.viewmodel.NewsViewModel

class NewsViewModelFactory(private val repository: NewsRepository) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}