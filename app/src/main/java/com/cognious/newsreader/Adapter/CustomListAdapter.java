package com.cognious.newsreader.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import com.cognious.newsreader.Model.News;
import com.cognious.newsreader.R;
import com.cognious.newsreader.VolleySingleton;
import com.cognious.newsreader.WebViewActivity;

import java.util.List;

public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.NewsHolder> {

    private List<News> newsList;

    private Context mContext;

    public static class NewsHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView title;
        NetworkImageView thumbNail;
        NetworkImageView sourceLogoUrl;
        TextView publishedAt;

        public NewsHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            title = itemView.findViewById(R.id.title);
            thumbNail = itemView.findViewById(R.id.thumbnail);
            sourceLogoUrl = itemView.findViewById(R.id.source_logo);
            publishedAt = itemView.findViewById(R.id.published_time);
        }
    }

    //constructor initializing the values
    public CustomListAdapter(Context mContext, List<News> newsList) {
        this.newsList = newsList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, viewGroup, false);
        NewsHolder newsHolder = new NewsHolder(view);

        return newsHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final NewsHolder holder, int position) {
        final News currentItem = this.newsList.get(position);
        holder.title.setText(currentItem.getTitle());

        ImageLoader imageLoaderThumb = VolleySingleton.getInstance(holder.thumbNail.getContext()).getImageLoader();
        holder.thumbNail.setImageUrl(currentItem.getThumbnailUrl(), imageLoaderThumb);

        ImageLoader imageLoaderLogo = VolleySingleton.getInstance(holder.sourceLogoUrl.getContext()).getImageLoader();
        holder.sourceLogoUrl.setImageUrl(currentItem.getSourceLogoUrl(), imageLoaderLogo);

        long time = currentItem.getPublishedAt().getTime();
        long now = System.currentTimeMillis();

        CharSequence ago =
                DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS);

        holder.publishedAt.setText(ago.toString());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent webViewBrowser = new Intent(mContext, WebViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url", currentItem.getSourceUrl());
                webViewBrowser.putExtras(bundle);
                mContext.startActivity(webViewBrowser);
            }
        });


    }

    @Override
    public int getItemCount() {
        return this.newsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
