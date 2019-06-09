package com.graduation.doctroidmedical.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.graduation.doctroidmedical.databinding.ArticleItemBinding;
import com.graduation.doctroidmedical.home.data.WebServices;
import com.graduation.doctroidmedical.home.pojo.article.ArticleResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    private List<ArticleResponse> articleResponses;
    private OnArticleClickListener onArticleClickListener;
    private LayoutInflater inflater;

    public ArticleAdapter(OnArticleClickListener onArticleClickListener) {
        this.onArticleClickListener = onArticleClickListener;
    }

    public void setArticleResponses(List<ArticleResponse> articleResponses) {
        this.articleResponses = articleResponses;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (inflater == null) {
            inflater = LayoutInflater.from(viewGroup.getContext());
        }
        return new ArticleViewHolder(ArticleItemBinding.inflate(inflater, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder viewHolder, int i) {
        ArticleResponse currentArticle = articleResponses.get(i);
        viewHolder.setArticle(currentArticle);
        viewHolder.setArticleClickListener(onArticleClickListener);
        Picasso.get().load(WebServices.BASE_URL + WebServices.IMAGE_PATH + currentArticle.getPicture()).into(viewHolder.newsItemBinding.articleImage);
    }

    @Override
    public int getItemCount() {
        if (articleResponses == null) {
            return 0;
        } else {
            return articleResponses.size();
        }
    }

    public interface OnArticleClickListener {
        void onArticleClick(View v, String articleId);
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder {
        private ArticleItemBinding newsItemBinding;

        ArticleViewHolder(@NonNull ArticleItemBinding itemView) {
            super(itemView.getRoot());
            newsItemBinding = itemView;

        }

        void setArticle(ArticleResponse article) {
            this.newsItemBinding.setArticle(article);
        }

        void setArticleClickListener(OnArticleClickListener articleClickListener) {
            this.newsItemBinding.setOnArticleClick(articleClickListener);
        }

    }
}
