package com.durarweb.newsapp.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.durarweb.newsapp.R;
import com.durarweb.newsapp.databinding.HeadlinesListItemBinding;
import com.durarweb.newsapp.model.SourceHeadLines;

import java.util.ArrayList;

public class HeadlinesAdapter extends ListAdapter<SourceHeadLines, HeadlinesAdapter.HeadlinesViewHolder> {

    private final OnHeadlinesClickListener onHeadlinesClickListener;
    Context context;
    private static ArrayList<String> imageUrls = new ArrayList<>();
    static int randomIndex = 0;

    public HeadlinesAdapter(Context context, OnHeadlinesClickListener onHeadlinesClickListener) {
        super(new NewsDiffCallback());
        this.context = context;
        this.onHeadlinesClickListener = onHeadlinesClickListener;
        imageUrls.add(context.getString(R.string.url_one));
        imageUrls.add(context.getString(R.string.url_two));
        imageUrls.add(context.getString(R.string.url_three));
        imageUrls.add(context.getString(R.string.url_four));
    }

    public static class HeadlinesViewHolder extends RecyclerView.ViewHolder {

        private final HeadlinesListItemBinding binding;

        public HeadlinesViewHolder(HeadlinesListItemBinding binding, OnHeadlinesClickListener onHeadlinesClickListener) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(v -> {
                SourceHeadLines headlines = binding.getHeadlines();
                if (headlines != null) {
                    onHeadlinesClickListener.onHeadlinesClick(headlines, imageUrls.get(randomIndex));
                }
            });
        }

        public void bind(SourceHeadLines sourceHeadLines, ArrayList<String> imageUrls) {
            binding.setHeadlines(sourceHeadLines);
            binding.executePendingBindings();
            if (!imageUrls.isEmpty()) {
                randomIndex = (int) (Math.random() * imageUrls.size());
                Glide.with(binding.getRoot())
                        .load(imageUrls.get(randomIndex))
                        .into(binding.newsImage);
            }
        }
    }

    @NonNull
    @Override
    public HeadlinesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HeadlinesListItemBinding binding = HeadlinesListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new HeadlinesViewHolder(binding, onHeadlinesClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HeadlinesViewHolder holder, int position) {
        holder.bind(getItem(position), imageUrls);
    }

    public interface OnHeadlinesClickListener {
        void onHeadlinesClick(SourceHeadLines headlines, String url);
    }

    // DiffUtil callback for efficient list updates
    private static class NewsDiffCallback extends DiffUtil.ItemCallback<SourceHeadLines> {
        @Override
        public boolean areItemsTheSame(SourceHeadLines oldItem, SourceHeadLines newItem) {
            return oldItem.getUrl().equals(newItem.getUrl());
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull SourceHeadLines oldItem, @NonNull SourceHeadLines newItem) {
            return oldItem == newItem;
        }
    }
}