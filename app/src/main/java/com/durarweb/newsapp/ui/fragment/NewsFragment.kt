package com.durarweb.newsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.durarweb.newsapp.databinding.FragmentNewsListBinding
import com.durarweb.newsapp.network.NewsCloudManager
import com.durarweb.newsapp.repository.NewsRepository
import com.durarweb.newsapp.ui.adapter.NewsListAdapter
import com.durarweb.newsapp.viewmodel.NewsViewModel
import com.durarweb.newsapp.viewmodel_factory.NewsViewModelFactory

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsListBinding
    private val newsViewModel: NewsViewModel by viewModels {
        NewsViewModelFactory(NewsRepository(NewsCloudManager.newsApiService))
    }
    private lateinit var newsAdapter: NewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSearchView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsListAdapter { article ->
        }
        binding.list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
        }
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newsViewModel.searchNews(newText ?: "")
                return true
            }
        })
    }

    private fun observeViewModel() {
        newsViewModel.searchResults.observe(viewLifecycleOwner) { articles ->
            if (articles != null) {
                newsAdapter.submitList(articles)
            }
        }

        newsViewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        }

        newsViewModel.loadingState.observe(viewLifecycleOwner) { isLoading ->
            binding.progress.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }
}