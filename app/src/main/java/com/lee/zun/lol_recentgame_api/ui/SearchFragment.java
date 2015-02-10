package com.lee.zun.lol_recentgame_api.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.lee.zun.lol_recentgame_api.R;
import com.lee.zun.lol_recentgame_api.api.RiotApi;
import com.lee.zun.lol_recentgame_api.api.SummonerUriBuilder;
import com.lee.zun.lol_recentgame_api.data.summoner.SummonerIdDto;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by namhyun on 2015-02-09.
 */
public class SearchFragment extends Fragment implements View.OnClickListener{
    public static final int REQUEST_DATA_CODE = 100;
    public static final int RESULT_DATA_EXIST = 101;
    public static final String RESULT_KEY = "result";

    private String mCDNVersion = null;
    private SummonerIdDto resultDto = null;

    private View mResultView;
    private ImageView mProfileIconView;
    private ImageView mLevelView;
    private TextView mSummonerNameView;
    private TextView mSummonerLevelView;
    private Button mSearchAgainBtn;
    private Button mSelectBtn;

    private MenuItem searchItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        mResultView = rootView.findViewById(R.id.result_layout);
        mResultView.setVisibility(View.INVISIBLE);
        mProfileIconView = (ImageView) rootView.findViewById(R.id.profile_image);
        mLevelView = (ImageView) rootView.findViewById(R.id.level_image);
        mSummonerNameView = (TextView) rootView.findViewById(R.id.summoner_name_view);
        mSummonerLevelView = (TextView) rootView.findViewById(R.id.summoner_level_view);
        mSearchAgainBtn = (Button) rootView.findViewById(R.id.search_again_btn);
        mSelectBtn = (Button) rootView.findViewById(R.id.select_btn);

        setLevelImage(mLevelView);
        mSearchAgainBtn.setOnClickListener(this);
        mSelectBtn.setOnClickListener(this);

        new CDNRequestTask().execute("kr", "profileicon");
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search, menu);
        searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint(getResources().getString(R.string.action_search_hint));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                String queryText = s.toLowerCase().trim();
                new SummonerRequestTask().execute("kr", queryText);
                MenuItemCompat.collapseActionView(searchItem);
                getActivity().setTitle(queryText);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        MenuItemCompat.expandActionView(searchItem);
    }

    private void setLevelImage(ImageView view) {
        view.setColorFilter(getResources().getColor(R.color.search_color_accent));
        Bitmap bitmap = ((BitmapDrawable) view.getDrawable()).getBitmap();
        setCircleImage(bitmap, view);
    }

    private void setCircleImage(Bitmap bitmap, ImageView view) {
        RoundedBitmapDrawable bitmapDrawable =
                RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        bitmapDrawable.setCornerRadius(Math.max(bitmap.getWidth(), bitmap.getHeight()) / 2.0f);
        bitmapDrawable.setAntiAlias(true);
        view.setImageDrawable(bitmapDrawable);
    }

    private void setDataToExternal(SummonerIdDto result){
        this.resultDto = result;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.search_again_btn:
                if(searchItem != null){
                    MenuItemCompat.expandActionView(searchItem);
                }
                break;
            case R.id.select_btn:
                if(resultDto != null){
                    Intent intent = getActivity().getIntent();
                    intent.putExtra(RESULT_KEY, resultDto);
                    getActivity().setResult(RESULT_DATA_EXIST, intent);
                }
                getActivity().finish();
                break;
        }
    }

    private class CDNRequestTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            final String BASE_URL = "http://ddragon.leagueoflegends.com/realms/";
            Uri uri = Uri.parse(BASE_URL).buildUpon().appendPath(params[0] + ".json").build();
            OkHttpClient okHttpClient = new OkHttpClient();
            Response response = null;
            try {
                Request request = new Request.Builder()
                        .url(uri.toString())
                        .build();
                response = okHttpClient.newCall(request).execute();
                if (response.code() == 200) {
                    JSONObject object = new JSONObject(response.body().string());
                    JSONObject cdnDataObject = object.getJSONObject("n");
                    return cdnDataObject.getString(params[1]);
                } else {
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            mCDNVersion = result;
        }
    }

    private class SummonerRequestTask extends AsyncTask<String, Void, SummonerIdDto> {

        private SummonerIdDto getSummonerDataFromJson(String jsonStr, String summonerName)
                throws JSONException {
            final String RESULT_ID = "id";
            final String RESULT_NAME = "name";
            final String RESULT_PROFILE_ICON_ID = "profileIconId";
            final String RESULT_REVISION_DATE = "revisionDate";
            final String RESULT_SUMMONER_LEVEL = "summonerLevel";

            JSONObject object = new JSONObject(jsonStr);
            JSONObject summonerObject = object.getJSONObject(summonerName);

            long id = summonerObject.getLong(RESULT_ID);
            String name = summonerObject.getString(RESULT_NAME);
            int profileIconId = summonerObject.getInt(RESULT_PROFILE_ICON_ID);
            long revisionDate = summonerObject.getLong(RESULT_REVISION_DATE);
            long summonerLevel = summonerObject.getLong(RESULT_SUMMONER_LEVEL);

            SummonerIdDto summonerIdDto = new SummonerIdDto();
            summonerIdDto.setId(id);
            summonerIdDto.setName(name);
            summonerIdDto.setProfileIconId(profileIconId);
            summonerIdDto.setRevisionDate(revisionDate);
            summonerIdDto.setSummonerLevel(summonerLevel);
            return summonerIdDto;
        }

        private String getProfileUrl(String versionStr, int profileIconId) {
            final String PROFILE_URL = "http://ddragon.leagueoflegends.com/cdn/%s/img/profileicon/%s.png";
            return String.format(PROFILE_URL, versionStr, String.valueOf(profileIconId));
        }

        @Override
        protected SummonerIdDto doInBackground(String... params) {
            SummonerIdDto resultDto = null;
            try {
                Response response = RiotApi.newInstance().getResponceFromBuilder(
                        new SummonerUriBuilder().region(params[0]).byName().summonerName(params[1]));
                if (response.code() == 200) {
                    resultDto = getSummonerDataFromJson(response.body().string(), params[1]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return resultDto;
        }

        @Override
        protected void onPostExecute(SummonerIdDto summonerIdDto) {
            super.onPostExecute(summonerIdDto);
            if (summonerIdDto != null) {
                setDataToExternal(summonerIdDto);
                mSummonerNameView.setText(summonerIdDto.getName());
                mSummonerLevelView.setText(String.valueOf(summonerIdDto.getSummonerLevel()));
                Glide.with(getActivity())
                        .load(getProfileUrl(mCDNVersion, summonerIdDto.getProfileIconId()))
                        .asBitmap()
                        .into(new BitmapImageViewTarget(mProfileIconView) {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                super.onResourceReady(resource, glideAnimation);
                                setCircleImage(resource, mProfileIconView);
                            }
                        });
                mResultView.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(getActivity(),
                        getResources().getString(R.string.result_error), Toast.LENGTH_SHORT).show();
                mResultView.setVisibility(View.INVISIBLE);
            }
        }
    }
}
