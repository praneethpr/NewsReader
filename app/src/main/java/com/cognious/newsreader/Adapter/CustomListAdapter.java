package com.cognious.newsreader.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.cognious.newsreader.Model.News;
import com.cognious.newsreader.R;
import com.cognious.newsreader.VolleySingleton;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter<News> {

    List<News> newsList;

    Context context;

    int resource;


    //constructor initializing the values
    public CustomListAdapter(@NonNull Context context, int resource, @NonNull List<News> newsList) {
        super(context, resource, newsList);
        this.context = context;
        this.resource = resource;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //getting the view
        View view = layoutInflater.inflate(resource, null, false);

        ImageLoader imageLoader = VolleySingleton.getInstance(context).getImageLoader();


        TextView title = view.findViewById(R.id.title);
        NetworkImageView thumbNail = view.findViewById(R.id.thumbnail);

        // getting the item of the specified position
        News newsItem = newsList.get(position);

        // thumbnail image
        thumbNail.setImageUrl(newsItem.getThumbnailUrl(), imageLoader);

        // title
        title.setText(newsItem.getTitle());

        return view;
    }
}
