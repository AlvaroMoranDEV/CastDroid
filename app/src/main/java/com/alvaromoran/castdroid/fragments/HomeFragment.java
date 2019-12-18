package com.alvaromoran.castdroid.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alvaromoran.castdroid.R;
import com.alvaromoran.castdroid.backend.services.ChannelPodCastsUpdates;
import com.alvaromoran.castdroid.backend.services.RecommendedChannels;
import com.alvaromoran.data.ChannelInformation;
import com.alvaromoran.castdroid.fragments.adapters.RecylerChannelAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Logic for the Home fragment
 */
public class HomeFragment extends Fragment {

    /** Subscriptions management */
    private ChannelPodCastsUpdates podcastsSubscriptionsInflater;
    private RecyclerView recyclerViewSubs;
    private List<ChannelInformation> listOfChannelsSubs;
    private RecylerChannelAdapter adapterRecyclerSubs;

    /** Recommendations management */
    private RecommendedChannels podcastsRecommendationsInflater;
    private RecyclerView recyclerViewRecommended;
    private List<ChannelInformation> listOfChannelsRecommended;
    private RecylerChannelAdapter adapterRecyclerRecommended;

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
        this.podcastsSubscriptionsInflater = new ChannelPodCastsUpdates();
        this.podcastsRecommendationsInflater = new RecommendedChannels();
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
    }

}
