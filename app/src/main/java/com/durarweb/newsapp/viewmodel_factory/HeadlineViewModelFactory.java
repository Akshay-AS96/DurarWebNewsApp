package com.durarweb.newsapp.viewmodel_factory;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.durarweb.newsapp.repository.HeadlinesRepository;
import com.durarweb.newsapp.viewmodel.HeadlinesViewModel;
import com.durarweb.newsapp.viewmodel.NewsViewModel;

public class HeadlineViewModelFactory implements ViewModelProvider.Factory {

    private final HeadlinesRepository repository;

    public HeadlineViewModelFactory(HeadlinesRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HeadlinesViewModel.class)) {
            return (T) new HeadlinesViewModel(repository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}