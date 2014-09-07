package com.example.owner.scenesbasicapp5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;


import com.example.owner.scenesbasicapp5.model.SampleData;

public class FullImageActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);

        // get intent data
        Intent i = getIntent();

        // Selected image id
        int position = i.getExtras().getInt("id");


        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
        //imageView.setImageResource(SampleData.URLS[position]);
    }

}