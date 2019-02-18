package com.graduation.doctroidmedical.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.graduation.doctroidmedical.R;
import com.graduation.doctroidmedical.pojo.article.ArticleResponse;
import com.graduation.doctroidmedical.utils.WebServices;
import com.graduation.doctroidmedical.view_holder.ArticleViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleViewHolder> {
    private static final String TAG = "ArticleAdapterTag";
    private List<ArticleResponse> articleResponses;
    private Context context;

    public ArticleAdapter(Context context) {
        this.context = context;
    }

    public List<ArticleResponse> getArticleResponses() {
        return articleResponses;
    }

    public void setArticleResponses(List<ArticleResponse> articleResponses) {
        this.articleResponses = articleResponses;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ArticleViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder viewHolder, int i) {
        ArticleResponse currentArticle = articleResponses.get(i);

        Log.d(TAG, "onBindViewHolder: " + currentArticle.getText());
        Log.d(TAG, "onBindViewHolder: " + currentArticle.getDate());
        Log.d(TAG, "onBindViewHolder: " + currentArticle.getAuthor().getFristName());
        Log.d(TAG, "onBindViewHolder: " + currentArticle.getPicture());

        viewHolder.getArticleBody().setText(currentArticle.getText());
        viewHolder.getArticleDate().setText(currentArticle.getDate());
        viewHolder.getArticleAuthor().setText(currentArticle.getAuthor().getFristName());
        String imageUrl = WebServices.BASE_URL + WebServices.IMAGE_PATH + currentArticle.getPicture();
        Picasso.with(context).load(imageUrl).into(viewHolder.getArticleImage());
    }

    @Override
    public int getItemCount() {
        if (articleResponses == null) {
            return 0;
        } else {
            return articleResponses.size();
        }
    }
}
