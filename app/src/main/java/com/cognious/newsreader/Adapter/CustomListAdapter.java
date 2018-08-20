package com.cognious.newsreader.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import com.cognious.newsreader.Model.News;
import com.cognious.newsreader.R;
import com.cognious.newsreader.VolleySingleton;

import java.util.List;

public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.NewsHolder> {

    private List<News> newsList;

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
    public CustomListAdapter(List<News> newsList) {
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, viewGroup, false);

        NewsHolder newsHolder = new NewsHolder(view);

        return newsHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        holder.title.setText(this.newsList.get(position).getTitle());

        ImageLoader imageLoaderThumb = VolleySingleton.getInstance(holder.thumbNail.getContext()).getImageLoader();
        holder.thumbNail.setImageUrl(this.newsList.get(position).getThumbnailUrl(), imageLoaderThumb);

        ImageLoader imageLoaderLogo = VolleySingleton.getInstance(holder.sourceLogoUrl.getContext()).getImageLoader();
        holder.sourceLogoUrl.setImageUrl(this.newsList.get(position).getSourceLogoUrl(), imageLoaderLogo);

        long time = this.newsList.get(position).getPublishedAt().getTime();
        long now = System.currentTimeMillis();

        CharSequence ago =
                DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS);

        holder.publishedAt.setText(ago.toString());

    }

    @Override
    public int getItemCount() {
        return this.newsList.size();
    }

    public void addItem(News newsItem, int index) {
        this.newsList.add(index, newsItem);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        this.newsList.remove(index);
        notifyItemRemoved(index);
    }
}
