package com.lee.zun.lol_recentgame_api;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.lee.zun.lol_recentgame_api.api.SummonerUriBuilder;
import com.lee.zun.lol_recentgame_api.data.recent.GameDto;
import com.lee.zun.lol_recentgame_api.data.summoner.SummonerIdDto;
import com.lee.zun.lol_recentgame_api.ui.SearchActivity;
import com.lee.zun.lol_recentgame_api.ui.SearchFragment;
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
        } else if (id == R.id.action_search) {
            startActivityForResult(
                    new Intent(this, SearchActivity.class), SearchFragment.REQUEST_DATA_CODE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SearchFragment.REQUEST_DATA_CODE){
            if(resultCode == SearchFragment.RESULT_DATA_EXIST){
                SummonerIdDto result = data.getParcelableExtra(SearchFragment.RESULT_KEY);
                if(result != null) {
                    Toast.makeText(this,
                            "Returned " + result.getName() + ", " + result.getSummonerLevel(),
                            Toast.LENGTH_SHORT).show();
                }
            } else if(resultCode == Activity.RESULT_CANCELED){
                Toast.makeText(this, "Returned null data", Toast.LENGTH_SHORT).show();
            }
        }
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
            for (int i = 0; i < 10; i++) {
                mAdapter.addItem(new GameDto());
            }
            return rootView;
        }

        public class RequestTask extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                OkHttpClient okHttpClient = new OkHttpClient();
                Response response = null;
                String result = null;
                try {
                    Request request = new Request.Builder()
                            .url(new SummonerUriBuilder().region("kr").byName().summonerName(params[0]).getURL())
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
