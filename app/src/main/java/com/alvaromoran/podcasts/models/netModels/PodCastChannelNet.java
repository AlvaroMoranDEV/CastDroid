package com.alvaromoran.podcasts.models.netModels;

import com.alvaromoran.podcasts.models.common.PodCastChannel;

/**
 * Class that represents PodCast channel information that has been retrieve from the
 * ITunes store. This information is only cached, it has not been stored yet into
 * the user preferences or in the local storage.
 */
public class PodCastChannelNet extends PodCastChannel {

    /**
     * Constructor of the class - Channel name is a needed parameter
     *
     * @param channelName name of the channel
     */
    public PodCastChannelNet(String channelName) {
        super(channelName);
    }
}
