package com.graduation.doctroidmedical.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.graduation.doctroidmedical.adapter.ArticleAdapter;
import com.graduation.doctroidmedical.databinding.NewsLayoutBinding;
import com.graduation.doctroidmedical.pojo.article.ArticleResponse;
import com.graduation.doctroidmedical.utils.WebServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment {
    private static final String TAG = "NewsFragmentTag";
    private ArticleAdapter articleAdapter;
    private NewsLayoutBinding layoutBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layoutBinding = NewsLayoutBinding.inflate(inflater, container, false);

        articleAdapter = new ArticleAdapter(getContext());
        layoutBinding.newsRv.setAdapter(articleAdapter);
        layoutBinding.newsRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        fetchNews();

        return layoutBinding.getRoot();
    }

    private void fetchNews() {
        layoutBinding.newLoadingProgress.setVisibility(View.VISIBLE);
        Log.d(TAG, "fetching news data...");
        WebServices services = WebServices.startService.create(WebServices.class);
        services.getArticles().enqueue(new Callback<List<ArticleResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<ArticleResponse>> call, @NonNull Response<List<ArticleResponse>> response) {
                if (response.body() != null) {
                    Log.d(TAG, "onResponse: not null");
                    List<ArticleResponse> articleResponses = response.body();
                    for (ArticleResponse resp : articleResponses) {
                        Log.d(TAG, "onResponse: Text: " + resp.getText());
                        Log.d(TAG, "onResponse: Title: " + resp.getTitle());
                        Log.d(TAG, "onResponse: Date: " + resp.getDate());
                        Log.d(TAG, "onResponse: Author: " + resp.getAuthor());
                        Log.d(TAG, "onResponse: Picture: " + resp.getPicture());
                    }
                    articleAdapter.setArticleResponses(articleResponses);
                    layoutBinding.newLoadingProgress.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ArticleResponse>> call, @NonNull Throwable t) {
                layoutBinding.newLoadingProgress.setVisibility(View.INVISIBLE);
            }
        });
    }
}
