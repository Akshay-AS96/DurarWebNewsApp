package com.durarweb.newsapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.durarweb.newsapp.R
import com.durarweb.newsapp.databinding.NewsListItemBinding
import com.durarweb.newsapp.model.Article
import java.text.SimpleDateFormat
import java.util.Locale

class NewsListAdapter(private val onArticleClick: (Article) -> Unit) :
    ListAdapter<Article, NewsListAdapter.NewsViewHolder>(NewsDiffCallback()) {
    private var context: Context? = null

    inner class NewsViewHolder(
        private val binding: NewsListItemBinding,
        private val onArticleClick: (Article) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                binding.news?.let { article ->
                    onArticleClick(article)
                }
            }
        }

        private fun timeFormat(date: String?): String? {
            return try {
                val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
                val parsedDate = date?.let { inputFormat.parse(it) }

                val outputFormat =
                    SimpleDateFormat("EEEE, MMMM dd, yyyy 'at' hh:mm:ss a", Locale.getDefault())
                parsedDate?.let { outputFormat.format(it) }

            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }


        fun bind(article: Article) {
            binding.news = article
            binding.executePendingBindings()
            binding.newsPublishedAt.text =
                (context?.getString(R.string.published_at) + timeFormat(article.publishedAt)) ?: ""

            Glide.with(binding.root)
                .load(article.urlToImage)
                .into(binding.newsImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding =
            NewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        this.context = parent.context
        return NewsViewHolder(binding, onArticleClick)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class NewsDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}