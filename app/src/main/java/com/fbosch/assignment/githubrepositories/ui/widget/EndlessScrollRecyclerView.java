package com.fbosch.assignment.githubrepositories.ui.widget;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.fbosch.assignment.githubrepositories.R;

import static com.fbosch.assignment.githubrepositories.util.Constants.VISIBLE_THRESHOLD;

public class EndlessScrollRecyclerView extends RelativeLayout {

    private EndlessRecyclerOnScrollListener scrollListener;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    public EndlessScrollRecyclerView(Context context) {
        super(context);
        init();
    }

    public EndlessScrollRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EndlessScrollRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setUp(OnLoadMoreListener listener) {
        scrollListener = new EndlessRecyclerOnScrollListener(VISIBLE_THRESHOLD) {
            @Override
            public void onLoadMore(int offset) {
                progressBar.setVisibility(View.VISIBLE);
                listener.loadMore(offset);
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
    }


    public void setLayoutManager(LinearLayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    public void setHasFixedSize(boolean hasFixedSize) {
        recyclerView.setHasFixedSize(hasFixedSize);
    }

    public void swapAdapter(RecyclerView.Adapter adapter, boolean removeAndRecycleExistingViews) {
        recyclerView.swapAdapter(adapter, removeAndRecycleExistingViews);
        resetScrollListener();
    }

    public void addItemDecoration(RecyclerView.ItemDecoration itemDecoration) {
        recyclerView.addItemDecoration(itemDecoration);
    }

    public void onErrorLoadingItems() {
        progressBar.setVisibility(View.GONE);
    }

    public void onNewItemsLoaded() {
        progressBar.setVisibility(View.GONE);
    }

    private void init() {
        progressBar = new ProgressBar(getContext());
        LayoutParams progressBarParams = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        progressBarParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        progressBarParams.addRule(ALIGN_PARENT_BOTTOM);
        progressBar.setVisibility(GONE);
        progressBar.setId(R.id.endless_scroll_progress_bar_id);

        recyclerView = new RecyclerView(getContext());
        recyclerView.setId(R.id.endless_scroll_recycler_view_id);
        LayoutParams recyclerViewParams = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        recyclerViewParams.addRule(ABOVE, progressBar.getId());

        addView(progressBar, progressBarParams);
        addView(recyclerView, recyclerViewParams);
    }

    private void resetScrollListener() {
        if (scrollListener != null) {
            scrollListener.resetState();
        }
    }

    public interface OnLoadMoreListener {
        void loadMore(int offset);
    }

}
