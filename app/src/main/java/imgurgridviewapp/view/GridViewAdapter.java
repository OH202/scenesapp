package imgurgridviewapp.view;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.etsy.android.grid.util.DynamicHeightImageView;
import com.example.owner.scenesapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Owner on 11/11/2014.
 */
public class GridViewAdapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    public ArrayList<HashMap<String, String>> data;
    HashMap<String, String> result = new HashMap<String, String>();

    private final Random mRandom;
    private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();
    private static final String TAG = "SampleAdapter";

    public GridViewAdapter(Context context, ArrayList<HashMap<String,String>> arrayList){

        this.context = context;
        data = arrayList;

        this.inflater = LayoutInflater.from(context);

        this.mRandom = new Random();

    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    public int getCount() {

       return data.size();

    }

    public Object getItem(int position){

        return null;

    }

    public long getItemId(int position){

        return 0;

    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public String getURL(HashMap<String, String> result, int position) {

        String url = result.get(("link" + position));

        return url;

    }

    public View getView (final int position, View convertView, ViewGroup parent){

        ViewHolder vh;

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.gridview_item, parent, false);

            vh = new ViewHolder();
            vh.imageView = (DynamicHeightImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(vh);

        } else {

            vh = (ViewHolder) convertView.getTag();

        }

        result = data.get(position);

        double positionHeight = getPositionRatio(position);

        vh.imageView.setHeightRatio(positionHeight);

        Picasso.with(context).load(getURL(result, position)).noFade().resize(250,250).into(vh.imageView);

        return convertView;

    }

    private Context getContext() {
        return this.context;
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

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return false;
    }

    static class ViewHolder {
        DynamicHeightImageView imageView;
    }



}
