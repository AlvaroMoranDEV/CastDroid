package com.alvaromoran.podcasts.services.connections;

import android.widget.ProgressBar;

import com.alvaromoran.podcasts.services.connections.templates.MessageContainer;

public class PodCastChannelConnection extends Connection {

    public PodCastChannelConnection() {
        super();
    }

    @Override
    public MessageContainer performQuery() {
        return null;
    }

    @Override
    public void addDefaultQueryParameter(String parameter) {

    }

    public String createFullQuery() {
        return null;
    }
}
