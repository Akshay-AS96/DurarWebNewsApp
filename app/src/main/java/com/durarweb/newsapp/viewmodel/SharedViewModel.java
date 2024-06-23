package com.durarweb.newsapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.durarweb.newsapp.model.SourceHeadLines;

public class SharedViewModel extends ViewModel {
    private final MutableLiveData<SourceHeadLines> _response = new MutableLiveData<>();
    public final LiveData<SourceHeadLines> response = _response;
    private final MutableLiveData<String> _responseImage = new MutableLiveData<>();
    public final LiveData<String> responseImage = _responseImage;

    public void sendNewsHeadline(SourceHeadLines data, String image) {
        _response.setValue(data);
        _responseImage.setValue(image);
    }
}