package com.cognious.newsreader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        /*
        //Get the intent that started this activity and get the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //Capture the layout's textview and set it to string
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);
        */

        final TextView mTextView = findViewById(R.id.textView);

        //Instantiate the RequestQueue
        RequestQueue queue = Volley.newRequestQueue(this);

        Date date = new Date();
        String modifiedDate= new SimpleDateFormat("yyyyMMdd").format(date);
        String url = "https://cognious.com/news/api/india/" + modifiedDate;

        /*
        //Request a string response from the provided URL
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mTextView.setText("Response is :" + response.substring(0, 500));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("that aint working");
            }
        }
        );
        queue.add(stringRequest);
        */

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    mTextView.setText(response.getJSONArray("articles").toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("not working");
            }
        });

        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }
}
