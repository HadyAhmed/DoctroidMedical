package com.graduation.doctroidmedical.home.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.graduation.doctroidmedical.databinding.ArticleDetailItemBinding;
import com.graduation.doctroidmedical.home.data.WebServices;
import com.graduation.doctroidmedical.home.pojo.article.ArticleResponse;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleDetailFragment extends Fragment {
    private ArticleDetailItemBinding itemBinding;
    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        itemBinding = ArticleDetailItemBinding.inflate(inflater, container, false);
        if (getArguments() != null) {
            WebServices.startService.create(WebServices.class)
                    .getArticleDetail(ArticleDetailFragmentArgs.fromBundle(getArguments()).getArticleId())
                    .enqueue(new Callback<ArticleResponse>() {
                        @Override
                        public void onResponse(@NonNull Call<ArticleResponse> call, @NonNull Response<ArticleResponse> response) {
                            if (response.body() != null) {
                                itemBinding.setArticle(response.body());
                                Picasso.get().load(WebServices.BASE_URL + WebServices.IMAGE_PATH + response.body().getPicture()).into(itemBinding.articleImage);
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<ArticleResponse> call, @NonNull Throwable t) {
                            Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        return itemBinding.getRoot();
    }
}
