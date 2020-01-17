package com.alvaromoran.castdroid.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alvaromoran.castdroid.R;
import com.alvaromoran.castdroid.backend.tasks.ChannelUpdatesTask;
import com.alvaromoran.castdroid.backend.tasks.HomeGenresTask;
import com.alvaromoran.castdroid.backend.tasks.RecommendationTasks;
import com.alvaromoran.castdroid.fragments.adapters.GenresAdapter;
import com.alvaromoran.castdroid.models.Channel;
import com.alvaromoran.castdroid.models.Genre;
import com.alvaromoran.data.ChannelInformation;
import com.alvaromoran.castdroid.fragments.adapters.RecylerChannelAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Logic for the Home fragment
 */
public class HomeFragment extends Fragment {

    /** Subscriptions management */
    private ChannelUpdatesTask podcastsSubscriptionsInflater;
    private RecyclerView recyclerViewSubs;
    private List<Channel> listOfChannelsSubs;
    private RecylerChannelAdapter adapterRecyclerSubs;

    /** Recommendations management */
    private RecommendationTasks podcastsRecommendationsInflater;
    private RecyclerView recyclerViewRecommended;
    private List<Channel> listOfChannelsRecommended;
    private RecylerChannelAdapter adapterRecyclerRecommended;

    /** Categories management */
    private HomeGenresTask homeGenresInflater;
    private GridView gridViewHomeGenres;
    private List<Genre> listOfGenres;
    private GenresAdapter gridViewGenresAdapter;

    /**
     * Empty constructor
     */
    public HomeFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.listOfChannelsSubs = new ArrayList<>();
        this.listOfChannelsRecommended = new ArrayList<>();
        this.podcastsSubscriptionsInflater = new ChannelUpdatesTask();
        this.podcastsRecommendationsInflater = new RecommendationTasks();
        this.listOfGenres = new ArrayList<>();
        this.homeGenresInflater = new HomeGenresTask();
        return inflater.inflate(R.layout.fragment_home, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // Create view
        super.onViewCreated(view, savedInstanceState);

        // Populate subscriptions
        this.recyclerViewSubs = getView().findViewById(R.id.recyclerMySubs);
        this.recyclerViewSubs.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        this.adapterRecyclerSubs = new RecylerChannelAdapter(this.getContext(), this.listOfChannelsSubs, getActivity().getSupportFragmentManager());
        this.recyclerViewSubs.setAdapter(this.adapterRecyclerSubs);
        this.podcastsSubscriptionsInflater.execute(this.adapterRecyclerSubs);

        // Populate recommendations
        this.recyclerViewRecommended = getView().findViewById(R.id.recyclerRecommended);
        this.recyclerViewRecommended.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        this.adapterRecyclerRecommended = new RecylerChannelAdapter(this.getContext(), this.listOfChannelsRecommended, getActivity().getSupportFragmentManager());
        this.recyclerViewRecommended.setAdapter(this.adapterRecyclerRecommended);
        this.podcastsRecommendationsInflater.execute(this.adapterRecyclerRecommended);

        // Populate genres
        this.gridViewHomeGenres = getView().findViewById(R.id.home_categories_grid);
        this.gridViewGenresAdapter = new GenresAdapter(this.getContext(), this.listOfGenres);
        this.gridViewHomeGenres.setAdapter(this.gridViewGenresAdapter);
        this.homeGenresInflater.execute(this.gridViewGenresAdapter);

        // Buttons initialization
        ImageButton buttonSettings = getView().findViewById(R.id.home_settingsButton);
        buttonSettings.setOnClickListener(view1 -> {
            Fragment fragment = new SettingsFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.application_frame, fragment).addToBackStack(null).commit();
        });
        ImageButton buttonProfile = getView().findViewById(R.id.home_userProfileButton);
        buttonProfile.setOnClickListener(view12 -> {
            Fragment fragment = new ProfileFragment();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.application_frame, fragment).addToBackStack(null).commit();
        });
    }
}
