package com.durarweb.newsapp.repository;

import static com.durarweb.newsapp.utils.Constants.API_KEY;

import androidx.annotation.NonNull;

import com.durarweb.newsapp.model.HeadlinesResponse;
import com.durarweb.newsapp.network.service.HeadLinesApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeadlinesRepository {

    private final HeadLinesApiService apiService;

    public HeadlinesRepository(HeadLinesApiService apiService) {
        this.apiService = apiService;
    }

    // Fetches a list of news from the API
    public void getHeadlines(final HeadlinesCallback callback) {
        Call<HeadlinesResponse> call = apiService.getTopHeadlinesSources(API_KEY);
        call.enqueue(new Callback<HeadlinesResponse>() {
            @Override
            public void onResponse(@NonNull Call<HeadlinesResponse> call, @NonNull Response<HeadlinesResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<HeadlinesResponse> call, @NonNull Throwable t) {
                callback.onFailure("Network Failure: " + t.getMessage());
            }
        });
    }

    public interface HeadlinesCallback {
        void onSuccess(HeadlinesResponse headlineResponse);

        void onFailure(String errorMessage);
    }
}