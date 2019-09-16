package com.alvaromoran.podcasts.services.dataAccess;

import com.alvaromoran.podcasts.models.PodCastChannel;

public interface IChannelPodCasts {

    //region ITunes section

    void addITunesQueryParameter(String... parameters);

    void removeITunesQueryParameter(String key);

    void performTaskOverITunes();

    //endregion


    // region Net Channel

    void addChannelQueryParameter(PodCastChannel channelToBeFilled, String... parameters);

    void performTaskOverChannel(PodCastChannel channelToBeFilled);

    //endregion

}
