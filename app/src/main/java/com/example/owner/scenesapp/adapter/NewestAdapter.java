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
import com.example.owner.scenesapp.model.SampleData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;



/***
 * ADAPTER
 */

public class NewestAdapter extends ArrayAdapter<String> {

    private static final String TAG = "NewestAdapter";

    private final LayoutInflater mLayoutInflater;
    private final Random mRandom;
    private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();

    public NewestAdapter(Context context, int textViewResourceId,
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

        sortImages(SampleData.URLS, new ImageComparator());
        Picasso.with(getContext()).load(SampleData.URLS[new Random().nextInt(SampleData.URLS.length)]).into(vh.imgView);
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

    //SORTING

    public class ImageComparator implements Comparator<String> {
        public int compare(String firstImage, String otherImage) {


            float image1 = getRanking(firstImage);
            float image2 = getRanking(otherImage);

            if(image1 > image2) return 1;

            else if(image1 < image2) return -1;

            return 0;

        }

    }

    public int getRanking(String image){

        return 1;

    }

    public void sortImages(String[] Source, ImageComparator Filter) {

        //Format: sort(T[] a, int fromIndex, int toIndex, Comparator<? super T> c)

        Arrays.sort(Source, Filter);

    }


}