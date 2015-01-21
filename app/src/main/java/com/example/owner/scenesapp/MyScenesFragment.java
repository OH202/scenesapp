package com.example.owner.scenesapp;

/**
 * Created by Owner on 8/5/2014.
 */
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import imgurgridviewapp.util.JSONFunctions;
import imgurgridviewapp.view.GridViewAdapter;


public class MyScenesFragment extends Fragment{

    private static final String TAG = "ScenesApp";
    public static final String SAVED_DATA_KEY = "SAVED_DATA";

    //private StaggeredGridView mGridView;
    //private boolean mHasRequestedMore;

    //private ArrayList<String> mData;

    StaggeredGridView gridView;
    GridViewAdapter gridViewAdapter;
    ProgressDialog mProgressDialog;

    ArrayList<HashMap<String,String>> arrayList;

    String REQUEST_URL = "https://api.imgur.com/3/gallery/album/gyCvp";

    public MyScenesFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(com.example.owner.scenesapp.R.layout.fragment_home, container, false);

        return rootView;


    }

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        new DownloadJSON().execute(arrayList);

        gridView = (StaggeredGridView) getView().findViewById(R.id.grid_view);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getActivity(), FullImageActivity.class);
                // passing array index
                i.putExtra("id", position);
                Toast.makeText(getActivity(), "Item Clicked: " + position, Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });

        gridView.setAdapter(gridViewAdapter);

        //mGridView.setOnScrollListener(this);

    }

    private class DownloadJSON extends AsyncTask<ArrayList<HashMap<String,String>>, Void, ArrayList<HashMap<String,String>>> {

        @Override
        protected void onPreExecute(){

            super.onPreExecute();

        }

        protected ArrayList<HashMap<String,String>> doInBackground(ArrayList<HashMap<String,String>>... voids) {

            arrayList = new  ArrayList<HashMap<String,String>>();
            JSONObject urlJSON = JSONFunctions.getJSONfromURL(REQUEST_URL);

            try {
                System.out.println("STATUS: " + urlJSON.getInt("status"));

            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {

                JSONObject data = urlJSON.getJSONObject("data");

                JSONArray images = data.getJSONArray("images");

                for (int j = 0; j < images.length(); j++){

                    HashMap<String,String> imageMap = new HashMap<String, String>();
                    JSONObject linkJSON = images.getJSONObject(j);

                    imageMap.put(("link" + j), linkJSON.getString("link"));

                    arrayList.add(imageMap);

                }

                System.out.println("arraylist IN: " + arrayList);

            } catch (JSONException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            return arrayList;

        }


        protected void onPostExecute(ArrayList<HashMap<String, String>> arrayList){

            super.onPostExecute(arrayList);

            gridViewAdapter = new GridViewAdapter(getActivity(), arrayList);

            try{

                gridView.setAdapter(gridViewAdapter);

            } catch(NullPointerException e) {
                String err = (e.getMessage()==null)?"Null Pointer Exception: Can't find (stuff)":e.getMessage();
                Log.e("Error", err);
                e.printStackTrace();
            }

        }

    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(SAVED_DATA_KEY, arrayList);
        setUserVisibleHint(true);
    }

    /*@Override
    public void onScrollStateChanged(final AbsListView view, final int scrollState) {
        Log.d(TAG, "onScrollStateChanged:" + scrollState);
    }*/

    /*@Override
    public void onScroll(final AbsListView view, final int firstVisibleItem, final int visibleItemCount, final int totalItemCount) {
        Log.d(TAG, "onScroll firstVisibleItem:" + firstVisibleItem +
                " visibleItemCount:" + visibleItemCount +
                " totalItemCount:" + totalItemCount);
        // our handling
        if (!mHasRequestedMore) {
            int lastInScreen = firstVisibleItem + visibleItemCount;
            if (lastInScreen >= totalItemCount) {
                Log.d(TAG, "onScroll lastInScreen - so load more");
                mHasRequestedMore = true;
                onLoadMoreItems();
            }
        }
    }*/

    /*private void onLoadMoreItems() {
        final ArrayList<String> sampleData = generateData();
        for (String data : sampleData) {
            mAdapter.add(data);
        }
        // stash all the data in our backing store
        mData.addAll(sampleData);
        // notify the adapter that we can update now
        mAdapter.notifyDataSetChanged();
        mHasRequestedMore = false;
    }*/


    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(getActivity(), "Item Clicked: " + position, Toast.LENGTH_SHORT).show();
    }

}
