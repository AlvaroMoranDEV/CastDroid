package com.alvaromoran.podcasts.services.factories;

import com.alvaromoran.podcasts.models.PodCastChannel;
import com.alvaromoran.podcasts.services.dataAccess.data.iTunes.Result;
import com.alvaromoran.podcasts.services.dataAccess.storage.internalStorage.templates.PodCastChannelXml;

public class PodCastChannelFactory {

    private PodCastChannelFactory() {
        throw new UnsupportedOperationException();
    }

    public static PodCastChannel createPodCastChannel(Object baseInformation) {
        PodCastChannel createdChannel = null;
        if (baseInformation != null) {
            if (baseInformation instanceof Result) {
                createdChannel = createPodCastChannelFromNet((Result) baseInformation);
            } else if (baseInformation instanceof PodCastChannelXml) {
                createdChannel = createPodCastChannelFromInternalStorage((PodCastChannelXml) baseInformation);
            } else {
                createdChannel = null;
            }
        }
        return createdChannel;
    }

    private static PodCastChannel createPodCastChannelFromNet(Result baseInformation) {
        PodCastChannel parsedChannel = new PodCastChannel(baseInformation.artistName);
        parsedChannel.setCollectionName(baseInformation.collectionName);
        parsedChannel.setFeedUrl(baseInformation.feedUrl);
        parsedChannel.setUrlImage(baseInformation.artworkUrl600);
        parsedChannel.setCountry(baseInformation.country);
        if (baseInformation.genreIds != null) {
            baseInformation.genreIds.forEach(genre -> parsedChannel.addChannelTag(genre));
        }
        return parsedChannel;
    }

    private static PodCastChannel createPodCastChannelFromInternalStorage(PodCastChannelXml baseInformation) {
        return null;
    }

}
