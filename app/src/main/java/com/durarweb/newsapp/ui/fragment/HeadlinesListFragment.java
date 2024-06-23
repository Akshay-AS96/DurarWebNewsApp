package com.durarweb.newsapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.durarweb.newsapp.R;
import com.durarweb.newsapp.databinding.FragmentHeadlinesBinding;
import com.durarweb.newsapp.network.HeadlinesCloudManager;
import com.durarweb.newsapp.repository.HeadlinesRepository;
import com.durarweb.newsapp.ui.adapter.HeadlinesAdapter;
import com.durarweb.newsapp.viewmodel.HeadlinesViewModel;
import com.durarweb.newsapp.viewmodel.SharedViewModel;
import com.durarweb.newsapp.viewmodel_factory.HeadlineViewModelFactory;

public class HeadlinesListFragment extends Fragment {

    private FragmentHeadlinesBinding binding;
    private HeadlinesViewModel headlinesViewModel;
    private HeadlinesAdapter newsAdapter;
    private SharedViewModel sharedViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHeadlinesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HeadlineViewModelFactory factory = new HeadlineViewModelFactory(new HeadlinesRepository(HeadlinesCloudManager.apiService));
        headlinesViewModel = new ViewModelProvider(this, factory).get(HeadlinesViewModel.class);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
        observeViewModel();
    }

    private void setupRecyclerView() {
        newsAdapter = new HeadlinesAdapter(requireContext(),(headlines, url) -> {

            Navigation.findNavController(requireView()).navigate(R.id.action_HeadlineListFragment_to_UserFragment1);
            sharedViewModel.sendNewsHeadline(headlines, url);
        });
        binding.rvList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.rvList.setAdapter(newsAdapter);
    }

    private void observeViewModel() {
        headlinesViewModel.headlineList.observe(getViewLifecycleOwner(), it -> {
            if (it != null) {
                newsAdapter.submitList(it);
            }
        });

        headlinesViewModel.errorMessage.observe(getViewLifecycleOwner(), errorMessage -> {
            Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
        });

        headlinesViewModel.loadingState.observe(getViewLifecycleOwner(), isLoading -> {
            binding.progress2.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        });
    }
}