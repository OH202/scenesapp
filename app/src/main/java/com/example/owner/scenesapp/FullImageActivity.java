package com.example.owner.scenesapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import java.net.URL;

public class FullImageActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.owner.scenesapp.R.layout.full_image);

        URL imageURL;

        Intent intent = getIntent();
        long imageId = (Long) intent.getExtras().get(FullImageActivity.class.getName());

        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);

        imageView.setLayoutParams(new ViewGroup.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT));

        imageView.setImageResource((int) imageId);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

    }

}