package com.fbosch.assignment.githubrepositories.ui.repositorylist;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.fbosch.assignment.githubrepositories.BR;
import com.fbosch.assignment.githubrepositories.R;
import com.fbosch.assignment.githubrepositories.data.model.Repository;

import java.util.ArrayList;
import java.util.List;

class RepositoryListViewAdapter extends RecyclerView.Adapter<RepositoryListViewAdapter.ViewHolder> {

    private List<Repository> repositories = new ArrayList<>();

    RepositoryListViewAdapter(List<Repository> repositories) {
        this.repositories = repositories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_list_repository, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBindView(repositories.get(position));
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    void addItems(List<Repository> repositories) {
        int previous = this.getItemCount();
        this.repositories.addAll(repositories);
        notifyItemRangeInserted(previous, this.getItemCount());
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ViewDataBinding binding;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBindView(Repository item) {
            binding.setVariable(BR.repository, item);
            binding.executePendingBindings();
        }
    }
}
