package com.example.owner.scenesapp;

/**
 * Created by Owner on 8/5/2014.
 */
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;
import com.example.owner.scenesapp.adapter.SampleAdapter;

import java.util.ArrayList;


public class CategoriesFragment extends Fragment{

    private static final String TAG = "ScenesApp";
    public static final String SAVED_DATA_KEY = "SAVED_DATA";

    private StaggeredGridView mGridView;
    private boolean mHasRequestedMore;
    private SampleAdapter mAdapter;
    private ArrayList<String> mData;

    public CategoriesFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(com.example.owner.scenesapp.R.layout.fragment_home, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mGridView = (StaggeredGridView) getView().findViewById(com.example.owner.scenesapp.R.id.grid_view);

        if (savedInstanceState == null) {
            final LayoutInflater layoutInflater = getActivity().getLayoutInflater();


        }

        if (mAdapter == null) {
            mAdapter = new SampleAdapter(getActivity(),android.R.layout.simple_list_item_1, generateData());
        }

        if (savedInstanceState != null) {
            mData = savedInstanceState.getStringArrayList(SAVED_DATA_KEY);
        }

        if (mData == null) {
            mData = generateData();
        }

        for (String data : mData) {
            mAdapter.add(data);
        }

        mGridView.setAdapter(mAdapter);

    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putStringArrayList(SAVED_DATA_KEY, mData);
    }


    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(getActivity(), "Item Clicked: " + position, Toast.LENGTH_SHORT).show();
    }

    private ArrayList<String> generateData() {
        ArrayList<String> listData = new ArrayList<String>();
        listData.add("http://i62.tinypic.com/2iitkhx.jpg");
        listData.add("http://i61.tinypic.com/w0omeb.jpg");
        listData.add("http://i60.tinypic.com/w9iu1d.jpg");
        listData.add("http://i60.tinypic.com/iw6kh2.jpg");
        listData.add("http://i57.tinypic.com/ru08c8.jpg");
        listData.add("http://i60.tinypic.com/k12r10.jpg");
        listData.add("http://i58.tinypic.com/2e3daug.jpg");
        listData.add("http://i59.tinypic.com/2igznfr.jpg");

        return listData;
    }


}
