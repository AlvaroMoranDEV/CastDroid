package com.alvaromoran.podcasts.services.dataAccess;

import com.alvaromoran.podcasts.models.PodCastChannel;

public class TaskParameters {

    private int typeOfQuery;

    private PodCastChannel selectedChannel;

    public TaskParameters(int typeOfQuery) {
        this.typeOfQuery = typeOfQuery;
    }

    public int getTypeOfQuery() {
        return typeOfQuery;
    }

    public PodCastChannel getSelectedChannel() {
        return selectedChannel;
    }

    public void setSelectedChannel(PodCastChannel selectedChannel) {
        this.selectedChannel = selectedChannel;
    }
}
