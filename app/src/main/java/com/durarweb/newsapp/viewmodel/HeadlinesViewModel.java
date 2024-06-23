package com.durarweb.newsapp.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.durarweb.newsapp.model.Article;
import com.durarweb.newsapp.model.HeadlinesResponse;
import com.durarweb.newsapp.model.SourceHeadLines;
import com.durarweb.newsapp.repository.HeadlinesRepository;

import java.util.List;

public class HeadlinesViewModel extends ViewModel {
    private final MutableLiveData<List<SourceHeadLines>> _headlineList = new MutableLiveData<>();
    public final LiveData<List<SourceHeadLines>> headlineList = _headlineList;
    public final MutableLiveData<List<Article>> searchResults = new MutableLiveData<>();

    public final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    public final MutableLiveData<Boolean> loadingState = new MutableLiveData<>();

    private final HeadlinesRepository repository;

    public HeadlinesViewModel(HeadlinesRepository repository) {
        this.repository = repository;
        fetchHeadlines();
    }

    private void fetchHeadlines() {
        loadingState.setValue(true);
        try {
            repository.getHeadlines(new HeadlinesRepository.HeadlinesCallback() {
                @Override
                public void onSuccess(HeadlinesResponse response) {
                    _headlineList.postValue(response.getSources());
                    Log.d("API_SUCCESS", "Headlines: " + response);
                    loadingState.postValue(false);
                }

                @Override
                public void onFailure(String errorMessage) {
                    Log.e("API_ERROR", "Error: " + errorMessage);
                    HeadlinesViewModel.this.errorMessage.postValue(errorMessage);
                    loadingState.postValue(false);
                }
            });
        } catch (Exception e) {
            Log.e("API_ERROR", "Error: " + e.getMessage());
            errorMessage.postValue(e.getMessage());
            loadingState.postValue(false);
        }
    }
}