package com.alvaromoran.podcasts.models.netModels;

import com.alvaromoran.podcasts.models.common.PodCastChannel;

/**
 * Class that represents PodCast channel information that has been gathered automatically
 * from the net as recommendation. Information about this channel has been obtained by
 * analyzing the tags associated to favourite or downloaded information by the user
 */
public class PodCastChannelRecommended extends PodCastChannel {

    /**
     * Channel that has generated the recommendation for the current channel
     */
    private PodCastChannel parentRecomendation;

    /**
     * Constructor of the class - Channel name is a needed parameter
     *
     * @param channelName name of the channel
     */
    public PodCastChannelRecommended(String channelName, PodCastChannel parentRecomendation) {
        super(channelName);
        this.parentRecomendation = parentRecomendation;
    }
}
