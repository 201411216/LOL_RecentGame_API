package com.lee.zun.lol_recentgame_api.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.lee.zun.lol_recentgame_api.R;

/**
 * Created by namhyun on 2015-02-09.
 */
public class SearchActivity extends ActionBarActivity {
    private static final String LOG_TAG = "SchoolSetActivity";
    private Toolbar mToolbar;

    private void initToolbar() {
        Log.d(LOG_TAG, "initToolbar");
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new SearchFragment())
                    .commit();
        }
        initToolbar();
    }
}
