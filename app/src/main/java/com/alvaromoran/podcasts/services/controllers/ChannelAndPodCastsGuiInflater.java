package com.alvaromoran.podcasts.services.controllers;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.alvaromoran.podcasts.models.common.PodCastChannel;
import com.alvaromoran.podcasts.models.common.PodCastEpisode;
import com.alvaromoran.podcasts.services.connections.WebProvider;
import com.alvaromoran.podcasts.services.connections.templates.GenericPodCastMessage;
import com.alvaromoran.podcasts.services.connections.templates.MessageContainer;
import com.alvaromoran.podcasts.services.connections.templates.iTunes.JsonITunesRoot;
import com.alvaromoran.podcasts.services.connections.templates.iTunes.Result;
import com.alvaromoran.podcasts.services.factories.PodCastChannelFactory;
import com.alvaromoran.podcasts.services.factories.PodCastEpisodeFactory;
import com.alvaromoran.podcasts.services.storage.StorageProvider;
import com.alvaromoran.podcasts.viewAdapters.channelGridView.ChannelViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChannelAndPodCastsGuiInflater extends AsyncTask<QueryTaskParameters, Void, List<PodCastChannel>> {

    private WebProvider webProvider;

    private StorageProvider storageProvider;

    private PodCastEpisodeFactory episodeFactory;

    private PodCastChannelFactory channelFactory;

    private AdapterView viewToBeUpdated;

    private QueryTaskParameters taskPerformed;

    public ChannelAndPodCastsGuiInflater(AdapterView viewToBeUpdated) {
        this.webProvider = new WebProvider();
        this.storageProvider = new StorageProvider();
        this.episodeFactory = new PodCastEpisodeFactory();
        this.channelFactory = new PodCastChannelFactory();
        this.viewToBeUpdated = viewToBeUpdated;
    }

    public void addChannelQueryParameter(String... parameters) {
        this.webProvider.addITunesArgument(parameters);
    }

    private List<PodCastChannel> performChannelQuery() {
        List<PodCastChannel> channelsReceived = new ArrayList<>();
        // Creates one channel instance for each result
        MessageContainer messageReceived =  this.webProvider.performQueryOverITunes();
        JsonITunesRoot messageParsed;
        if (messageReceived != null) {
            messageParsed = (JsonITunesRoot) messageReceived.parseMessage();
            for (Result singleResult : messageParsed.results) {
                channelsReceived.add(this.channelFactory.createPodCastChannel(PodCastChannelFactory.PODCAST_CHANNEL_NET, singleResult));
            }
        }
        return channelsReceived;
    }

    private List<PodCastEpisode> fillDeepChannelInformation(PodCastChannel channel) {
        this.webProvider.addFeedUrlPodcastChannelConnection(channel.getFeedUrl());
        MessageContainer messageParsed = this.webProvider.performQueryOverPodcastChannel();
        // Creates one episode instance for each result
        throw new RuntimeException(); // TODO
    }

    @Override
    protected List<PodCastChannel> doInBackground(QueryTaskParameters... taskInformation) {
        List<PodCastChannel> listOfChannels = new ArrayList<>();
        if (taskInformation != null && taskInformation[0] != null) {
            this.taskPerformed = taskInformation[0];
            this.viewToBeUpdated = taskInformation[0].getGraphicElement();
            if (this.taskPerformed.getTypeOfQuery() == QueryTaskParameters.QUERY_OVER_CHANNEL) {

            } else if (this.taskPerformed.getTypeOfQuery() == QueryTaskParameters.QUERY_OVER_ITUNES) {
                listOfChannels = performChannelQuery();
            } else {
                Log.e("doInBackground", "Unsupported operation trying to perform connection");
            }
        }
        return listOfChannels;
    }

    @Override
    protected void onPostExecute(List<PodCastChannel> listOfChannels) {
        // Updates the representation on the UI thread
        final ChannelViewAdapter channelAdapter = new ChannelViewAdapter(this.taskPerformed.getApplicationContext(), listOfChannels);
        this.taskPerformed.getGraphicElement().setAdapter(channelAdapter);
    }
}
