package com.alvaromoran.podcasts;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.alvaromoran.podcasts.models.PodCastChannel;
import com.alvaromoran.podcasts.ui.discover.DiscoverResultsFragment;
import com.alvaromoran.podcasts.ui.discover.DiscoverViewModel;

import java.util.List;

public class DiscoverActivity extends AppCompatActivity {

    private List<PodCastChannel> channelsToBeShownUI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discover_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, DiscoverResultsFragment.newInstance())
                    .commitNow();
        }

        // ViewModel relation with UI and objects population
        ViewModel discoverViewModel = ViewModelProviders.of(this).get(DiscoverViewModel.class);
        ((DiscoverViewModel) discoverViewModel).getPodCastChannelListForUI().observe(this, podCastChannels -> {
            if (podCastChannels != null) {
                channelsToBeShownUI = podCastChannels;
            }
        });

    }

    //region User Actions

    public void userActionSearchTriggered(View view) {
        // Find input text
        EditText textField = (EditText) findViewById(R.id.button);
        String text = textField.getText().toString();

        // Trigger the search podCasts functionality in the viewmodel
        ViewModel discoverViewModel = ViewModelProviders.of(this).get(DiscoverViewModel.class);
        ((DiscoverViewModel) discoverViewModel).userActionSearchTriggered(text);
    }

    //endregion



}
