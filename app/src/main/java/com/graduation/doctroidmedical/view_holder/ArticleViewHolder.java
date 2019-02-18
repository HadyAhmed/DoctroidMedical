package com.graduation.doctroidmedical.view_holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.graduation.doctroidmedical.R;

public class ArticleViewHolder extends RecyclerView.ViewHolder {
    private TextView articleBody, articleAuthor, articleDate;
    private ImageView articleImage;

    public ArticleViewHolder(@NonNull View itemView) {
        super(itemView);
        articleBody = itemView.findViewById(R.id.article_body);
        articleAuthor = itemView.findViewById(R.id.author_name);
        articleDate = itemView.findViewById(R.id.article_date);
        articleImage = itemView.findViewById(R.id.article_image);
    }

    public TextView getArticleBody() {
        return articleBody;
    }

    public TextView getArticleAuthor() {
        return articleAuthor;
    }

    public TextView getArticleDate() {
        return articleDate;
    }

    public ImageView getArticleImage() {
        return articleImage;
    }
}
