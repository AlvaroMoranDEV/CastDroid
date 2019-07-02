package com.alvaromoran.podcasts.models.netModels;

import com.alvaromoran.podcasts.models.common.PodCastEpisode;

/**
 * Class that represents a PodCast episode information that has been gathered from the net
 * when searching for more information about a single channel. This information is only cached,
 * it has not been stored yet into the user preferences or in the local storage.
 */
public class PodCastEpisodeNet extends PodCastEpisode {

    /**
     * Constructor of the class - Episode name is a needed parameter
     *
     * @param episodeName name of the episode
     */
    public PodCastEpisodeNet(String episodeName) {
        super(episodeName);
    }
}
