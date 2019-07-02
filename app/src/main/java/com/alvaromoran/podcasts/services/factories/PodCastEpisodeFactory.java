package com.alvaromoran.podcasts.services.factories;

import com.alvaromoran.podcasts.models.common.PodCastEpisode;
import com.alvaromoran.podcasts.models.storedModels.PodCastEpisodeStored;

public class PodCastEpisodeFactory {

    public static final int PODCAST_EPISODE_STORED = 0;
    public static final int PODCAST_EPISODE_NET = 1;

    public PodCastEpisode createPodCastEpisode(int podCastEpisodeType, Object objectInformation) {
        // TODO
        PodCastEpisode createdEpisode = null;

        if (podCastEpisodeType == PODCAST_EPISODE_STORED && objectInformation instanceof PodCastEpisodeStored) {

        } else if (podCastEpisodeType == PODCAST_EPISODE_NET) {

        }
        return createdEpisode;
    }

    private PodCastEpisode createPodCastEpisodeStored(Object objectInformation) {
        PodCastEpisode parsedEpisode = null;
        // TODO
        return parsedEpisode;
    }

    private PodCastEpisode createPodCastEpisodeNet(Object objectInformation) {
        PodCastEpisode parsedEpisode = null;
        // TODO
        return parsedEpisode;
    }

}
