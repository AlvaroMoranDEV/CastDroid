package com.alvaromoran.castdroid.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alvaromoran.castdroid.R;
import com.alvaromoran.castdroid.backend.tasks.UrlToImageTask;
import com.alvaromoran.castdroid.backend.tasks.ChannelInflationTask;
import com.alvaromoran.castdroid.fragments.adapters.ListEpisodesAdapter;
import com.alvaromoran.castdroid.models.Channel;
import com.alvaromoran.castdroid.models.Episode;
import com.alvaromoran.constants.ChannelAndEpisodesMapArguments;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;


public class ChannelInformationFragment extends Fragment {

    /**
     * Parameter name for channel information
     */
    private static final String PARAM_CHANNEL = "channelInformation";

    /**
     * Task to get the channel information
     */
    private ChannelInflationTask channelInflationTask;

    /**
     * Channel model information to be displayed
     */
    private Channel channelInformation;

    /**
     * Empty constructor
     */
    public ChannelInformationFragment( ) {
    }

    /**
     * Instance generator of these fragments
     * @param channel related channels to be shown in the UI
     * @return built fragment
     */
    public static ChannelInformationFragment newInstance(Channel channel) {
        ChannelInformationFragment channelFragment = new ChannelInformationFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelable(PARAM_CHANNEL, channel);
        channelFragment.setArguments(arguments);
        return channelFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.channelInformation = getArguments().getParcelable(PARAM_CHANNEL);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_channel_information, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Create view
        super.onViewCreated(view, savedInstanceState);
        // Populate channel with extra info
        this.channelInflationTask = new ChannelInflationTask();
        Object[] parameters = {this};
        this.channelInflationTask.execute(parameters);
    }

    public void populateChannelInformation() {
        // Image populate
        ImageView mainChannelImage = getView().findViewById(R.id.mainChannelImage);
        UrlToImageTask imageCreationHelper = new UrlToImageTask(mainChannelImage);
        imageCreationHelper.execute(this.channelInformation.getImageUrl());
        // Text references
        TextView mainChannelTitle = getView().findViewById(R.id.mainChannelTitle);
        TextView mainChannelAuthor = getView().findViewById(R.id.mainChannelAuthor);
        TextView mainChannelCopyright = getView().findViewById(R.id.mainChannelCopyright);
        TextView mainChannelCategories = getView().findViewById(R.id.mainChannelCategories);
        TextView mainChannelDescription = getView().findViewById(R.id.mainChannelDescription);
        // Text populate
        mainChannelTitle.setText( this.channelInformation.getChannelTitle());
        mainChannelAuthor.setText( this.channelInformation.getChannelAuthor());
        mainChannelCopyright.setText( this.channelInformation.getCopyRight());
        mainChannelCategories.setText( this.channelInformation.getCategories().toString());
        mainChannelDescription.setText( this.channelInformation.getChannelDescription());
        // Episodes population
        if (this.channelInformation.getEpisodes() != null) {
            ListView episodesList = getView().findViewById(R.id.mainEpisodesList);
            ListEpisodesAdapter episodesAdapter = new ListEpisodesAdapter(getContext(), new ArrayList<Episode>(this.channelInformation.getEpisodes()));
            episodesList.setAdapter(episodesAdapter);
        }
    }

    public Channel getChannelInformation() {
        return channelInformation;
    }
}
