package com.alvaromoran.podcasts.services.factories;

import com.alvaromoran.podcasts.models.common.PodCastChannel;
import com.alvaromoran.podcasts.models.netModels.PodCastChannelNet;
import com.alvaromoran.podcasts.models.storedModels.PodCastChannelStored;
import com.alvaromoran.podcasts.services.connections.templates.iTunes.Result;

public class PodCastChannelFactory {

    public static final int PODCAST_CHANNEL_STORED = 0;
    public static final int PODCAST_CHANNEL_NET = 1;
    public static final int PODCAST_CHANNEL_RECOMMENDED = 2;


    public PodCastChannel createPodCastChannel(int podCastChannelType, Object objectInformation) {

        PodCastChannel createdChannel = null;

        if (podCastChannelType == PODCAST_CHANNEL_STORED && objectInformation instanceof PodCastChannelStored) {
            createdChannel = createPodCastChannelStored(objectInformation);
        } else if (podCastChannelType == PODCAST_CHANNEL_NET || podCastChannelType == PODCAST_CHANNEL_RECOMMENDED && objectInformation instanceof Result) {
            createdChannel = createPodCastChannelNet(objectInformation);
        } else {
            createdChannel = null;
        }
        return createdChannel;
    }

    private PodCastChannel createPodCastChannelStored(Object objectInformation) {

        PodCastChannel parsedChannel = null;
        // TODO
        return parsedChannel;
    }

    private PodCastChannel createPodCastChannelNet(Object objectInformation) {

        PodCastChannel parsedChannel = null;
        Result receivedChannel = (Result) objectInformation;
        // Only title, feed and collection name will be filled since the most important information
        // is retrieved when performing the connection over the channel feed
        parsedChannel = new PodCastChannelNet(receivedChannel.artistName);
        parsedChannel.setCollectionName(receivedChannel.collectionName);

        return parsedChannel;
    }


}
