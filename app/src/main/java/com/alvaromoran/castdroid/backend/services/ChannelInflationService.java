package com.alvaromoran.castdroid.backend.services;

import com.alvaromoran.CastDroidStoreDAO;
import com.alvaromoran.PodCastsDAO;
import com.alvaromoran.data.ChannelInformation;

public class ChannelInflationService {

    private PodCastsDAO podCastsDAO;

    public ChannelInflationService() {
        this.podCastsDAO = new CastDroidStoreDAO();
    }

    public void populateChannel(ChannelInformation channelInformation) {
        this.podCastsDAO.getEnrichedChannelInformation(channelInformation);
    }
}
