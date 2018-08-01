package com.cognious.newsreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SampleScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_screen);

        //Get ListView object from xml
        final ListView listView = findViewById(R.id.list);

        //Defined Array values to show in ListView
        String[] values = new String[] {
                "General",
                "National",
                "International",
                "Sports",
                "Fashion",
                "Blah",
                "Blah",
                "BLah",
                "Blah"
        };

        //Define a new adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, values);

        //Assign adapter to ListView
        listView.setAdapter(adapter);

        //ListView item Click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Listview clicked item index
                int itemPosition = position;

                //Listview Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);

                //Show Alert
                Toast.makeText(getApplicationContext(), "Position :" + itemPosition +"ListItem :"+ itemValue, Toast.LENGTH_LONG).show();
            }
        });
    }
}
