package com.cognious.newsreader;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.LruCache;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cognious.newsreader.Adapter.CustomListAdapter;
import com.cognious.newsreader.Model.News;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RequestQueue reqQueue;

    List<News> newsList;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);

        newsList = new ArrayList<>();

        final CustomListAdapter adapter = new CustomListAdapter(this, R.layout.list_row, newsList);

        listView.setAdapter(adapter);

        reqQueue = Volley.newRequestQueue(this);

        Date date = new Date();
        String modifiedDate= new SimpleDateFormat("yyyyMMdd").format(date);
        String url = "https://cognious.com/news/api/india/" + modifiedDate;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    for (int i = 0; i < response.getJSONArray("articles").length(); i++) {
                        JSONObject articleObj = response.getJSONArray("articles").getJSONObject(i);
                        String title = articleObj.getString("title");
                        String thumbnailUrl = articleObj.getString("urlToImage");
                        newsList.add(new News(title, thumbnailUrl));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                adapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR", "Message:" + error.getMessage());
            }
        });

        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }
}
