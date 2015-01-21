package com.example.owner.scenesapp.adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.etsy.android.grid.util.DynamicHeightImageView;
import com.example.owner.scenesapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;



/***
 * ADAPTER
 */

public class SampleAdapter extends ArrayAdapter<String> {

    private static final String TAG = "SampleAdapter";

    private final LayoutInflater mLayoutInflater;
    private final Random mRandom;
    private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();


    private Integer[] categoryImages = {
            R.drawable.orange_cat, R.drawable.sample_image,R.drawable.testimage,R.drawable.scenescategory1,R.drawable.scenescategory2
    };

    public SampleAdapter(Context context, int textViewResourceId,
                         ArrayList<String> objects) {
        super(context, textViewResourceId, objects);
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mRandom = new Random();
    }

    @Override
    public View getView(final int position, View convertView,
                        final ViewGroup parent) {

        ViewHolder vh;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_item_sample,
                    parent, false);

            vh = new ViewHolder();
            vh.imgView = (DynamicHeightImageView) convertView
                    .findViewById(R.id.imgView);

            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        double positionHeight = getPositionRatio(position);

        vh.imgView.setHeightRatio(positionHeight);

        Picasso.with(getContext()).setLoggingEnabled(true);
        Picasso.with(getContext()).setDebugging(true);


        Picasso.with(getContext()).load(categoryImages[new Random().nextInt(categoryImages.length)]).into(vh.imgView);

        /*		// Picasso image loader library starts here
		Picasso.with(getContext())
				.load(CATEGORY_LOGO_URL
						+ resultp.get(CategoryActivity.TAG_CATEGORIES_LOGO)) // Photo URL
				.placeholder(R.drawable.placeholder) // This image will be displayed while photo URL is loading
				.error(R.drawable.error) // if error shows up during downloading
				.fit().centerCrop() // settings
				.into(category_logo); // we put it into our layout component (imageview)

		return view;*/
        return convertView;
    }

    static class ViewHolder {
        DynamicHeightImageView imgView;
    }

    private double getPositionRatio(final int position) {
        double ratio = sPositionHeightRatios.get(position, 0.0);
        // if not yet done generate and stash the columns height
        // in our real world scenario this will be determined by
        // some match based on the known height and width of the image
        // and maybe a helpful way to get the column height!
        if (ratio == 0) {
            ratio = getRandomHeightRatio();
            sPositionHeightRatios.append(position, ratio);
            Log.d(TAG, "getPositionRatio:" + position + " ratio:" + ratio);
        }
        return ratio;
    }

    private double getRandomHeightRatio() {
        return (mRandom.nextDouble() / 2.0) + 1.0; // height will be 1.0 - 1.5
        // the width
    }


}