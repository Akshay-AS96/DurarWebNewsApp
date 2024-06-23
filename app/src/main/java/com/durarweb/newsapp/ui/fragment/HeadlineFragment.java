package com.durarweb.newsapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.durarweb.newsapp.databinding.FragmentNewsBinding;
import com.durarweb.newsapp.model.SourceHeadLines;
import com.durarweb.newsapp.viewmodel.SharedViewModel;

public class HeadlineFragment extends Fragment {
    private FragmentNewsBinding binding;
    private SourceHeadLines data;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedViewModel sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        sharedViewModel.response.observe(getViewLifecycleOwner(), product -> {
            data = product;
            binding.newsTitle.setText(product.getName());
            binding.newsDescription.setText(product.getDescription());
            binding.newsCountry.setText(product.getCountry());
            binding.newsUrl.setText(product.getUrl());
            binding.newsLanguage.setText(product.getLanguage());
        });
        sharedViewModel.responseImage.observe(getViewLifecycleOwner(),url -> {
            Glide.with(binding.getRoot())
                    .load(url)
                    .into(binding.newsImage);
        });
    }
}