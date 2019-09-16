package com.alvaromoran.podcasts.ui.discover;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.alvaromoran.podcasts.R;
import com.alvaromoran.podcasts.models.PodCastChannel;
import com.alvaromoran.podcasts.services.dataAccess.PodCastInfoDAO;
import com.alvaromoran.podcasts.viewAdapters.channelGridView.ChannelViewAdapter;

import java.util.List;

public class DiscoverResultsFragment extends Fragment {

    private DiscoverViewModel discoverViewModel;

    private GridView podcastListGrid;

    public static DiscoverResultsFragment newInstance() {
        return new DiscoverResultsFragment();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.discover_fragment_results).setOnClickListener(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.podcastListGrid = find
        this.discoverViewModel = ViewModelProviders.of(this).get(DiscoverViewModel.class);
        discoverViewModel.getPodCastChannelListForUI().observe(this, { item ->
            // Updates the UI
            GridView podcastListGrid = (GridView)




                GridView gridView = (GridView)findViewById(R.id.ChannelGridViewResult);
        // IN THE CONTROLLER we populate the view and create the click events and add the adapter and so on
        ChannelViewAdapter gridAdapter = new ChannelViewAdapter(this, this.channelsToBeShownUI);
        gridView.setAdapter(gridAdapter);

        UpdateUIFromViewModel reference = this;

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                manager = PodCastInfoDAO.getInstance();
                manager.setActiveView(reference);
                PodCastChannel selectedChannel = channelsToBeShownUI.get(position);
                gridAdapter.notifyDataSetChanged();

                // Populate selected channel and show detailed info
                manager.performTaskOverChannel(selectedChannel);
            }
        });


        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover_results, container, false);
    }


}
