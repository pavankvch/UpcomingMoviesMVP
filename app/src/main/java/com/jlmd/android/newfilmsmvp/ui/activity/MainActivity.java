package com.jlmd.android.newfilmsmvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import com.jlmd.android.newfilmsmvp.R;
import com.jlmd.android.newfilmsmvp.bus.event.ItemSelectedEvent;
import com.jlmd.android.newfilmsmvp.domain.model.Movie;
import com.jlmd.android.newfilmsmvp.utils.Constants;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;


public class MainActivity extends BaseActivity {

    @Inject
    protected Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerBus();
    }

    private void registerBus() {
        bus.register(this);
    }

    @Subscribe
    public void onItemSelectedEvent(ItemSelectedEvent event) {
        launchMovieDetailsActivity(event.getMovie());
    }

    private void launchMovieDetailsActivity(Movie movie) {
        Intent detailsActivityIntent = new Intent(this, MovieDetailsActivity.class);
        detailsActivityIntent.putExtra(Constants.KEY_MOVIE_DETAILS, movie);
        detailsActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(detailsActivityIntent);
    }
}