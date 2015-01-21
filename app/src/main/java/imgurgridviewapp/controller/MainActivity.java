package imgurgridviewapp.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.etsy.android.grid.StaggeredGridView;
import com.example.owner.scenesapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import imgurgridviewapp.util.JSONFunctions;
import imgurgridviewapp.view.GridViewAdapter;


public class MainActivity extends Activity {

    StaggeredGridView gridView;
    GridViewAdapter gridViewAdapter;
    ProgressDialog mProgressDialog;

    ArrayList<HashMap<String,String>> arrayList;

    String REQUEST_URL = "https://api.imgur.com/3/gallery/album/gyCvp";

    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mProgressDialog = new ProgressDialog(MainActivity.this);

        new DownloadJSON().execute(arrayList);
    }

    private class DownloadJSON extends AsyncTask<ArrayList<HashMap<String,String>>, Void, ArrayList<HashMap<String,String>>>{

        @Override
        protected void onPreExecute(){

            super.onPreExecute();

            mProgressDialog.setTitle(R.string.app_name);
            mProgressDialog.setTitle("Loading");
            mProgressDialog.setIndeterminate(false);

            mProgressDialog.show();

        }


        protected ArrayList<HashMap<String,String>> doInBackground(ArrayList<HashMap<String,String>>... voids) {

            arrayList = new  ArrayList<HashMap<String,String>>();
            JSONObject urlJSON = JSONFunctions.getJSONfromURL(REQUEST_URL);

            try {
                System.out.println(urlJSON.getInt("status"));
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

                    //System.out.print("IMAGEMAP: " + imageMap);

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

            gridView = (StaggeredGridView) findViewById(R.id.grid_view);

            gridViewAdapter = new GridViewAdapter(MainActivity.this, arrayList);

            try {

                gridView.setAdapter(gridViewAdapter);

            } catch(NullPointerException e) {
                String err = (e.getMessage()==null)?"Null Pointer Exception: Can't find (stuff)":e.getMessage();
                Log.e("Error", err);
                e.printStackTrace();
            }

            if(mProgressDialog.isShowing()){mProgressDialog.dismiss();}


        }

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Context getAppContext() {
        return MainActivity.context;
    }

}
