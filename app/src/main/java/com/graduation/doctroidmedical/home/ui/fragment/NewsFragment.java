package com.graduation.doctroidmedical.home.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.graduation.doctroidmedical.databinding.NewsFragmentBinding;
import com.graduation.doctroidmedical.home.adapter.ArticleAdapter;
import com.graduation.doctroidmedical.home.data.WebServices;
import com.graduation.doctroidmedical.home.pojo.article.ArticleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment implements ArticleAdapter.OnArticleClickListener {
    private static final String TAG = "NewsFragmentTag";
    private ArticleAdapter articleAdapter;
    private NewsFragmentBinding layoutBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        layoutBinding = NewsFragmentBinding.inflate(inflater, container, false);

        articleAdapter = new ArticleAdapter(this);
        layoutBinding.newsRv.setAdapter(articleAdapter);

        fetchNews();

        return layoutBinding.getRoot();
    }

    private void fetchNews() {
        layoutBinding.newLoadingProgress.setVisibility(View.VISIBLE);
        WebServices services = WebServices.startService.create(WebServices.class);
        services.getArticles().enqueue(new Callback<List<ArticleResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<ArticleResponse>> call, @NonNull Response<List<ArticleResponse>> response) {
                if (response.body() != null) {
                    articleAdapter.setArticleResponses(response.body());
                    layoutBinding.newLoadingProgress.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ArticleResponse>> call, @NonNull Throwable t) {
                layoutBinding.newLoadingProgress.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onArticleClick(String articleId) {
        Log.d(TAG, "onArticleClick: " + articleId);
    }
}
