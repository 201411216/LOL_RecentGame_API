package com.lee.zun.lol_recentgame_api;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.lee.zun.lol_recentgame_api.api.BaseUriBuilder;
import com.lee.zun.lol_recentgame_api.api.GameUriBuilder;
import com.lee.zun.lol_recentgame_api.api.SummonerUriBuilder;
import com.lee.zun.lol_recentgame_api.data.recent.GameDto;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        private ListAdapter mAdapter;
        private ListView mListView;

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            mAdapter = new ListAdapter(getActivity());
            mListView = (ListView) rootView.findViewById(R.id.list_recentGameFragment);
            mListView.setAdapter(mAdapter);
            new RequestTask().execute(new SummonerUriBuilder().region("kr").byName().summonerName("The Zun"));
            return rootView;
        }

        public class RequestTask extends AsyncTask<BaseUriBuilder, Void, String> {

            @Override
            protected String doInBackground(BaseUriBuilder... params) {
                OkHttpClient okHttpClient = new OkHttpClient();
                Response response = null;
                String result = null;
                try {
                    Request request = new Request.Builder()
                            .url(params[0].getURL())
                            .build();
                    response = okHttpClient.newCall(request).execute();
                    result = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }
        }
    }
}
