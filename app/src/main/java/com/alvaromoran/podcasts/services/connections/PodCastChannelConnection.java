package com.alvaromoran.podcasts.services.connections;

import android.widget.ProgressBar;

import com.alvaromoran.podcasts.services.connections.templates.MessageContainer;

public class PodCastChannelConnection extends Connection {

    public PodCastChannelConnection() {
        super();
    }

    public PodCastChannelConnection(ProgressBar progressBar) {
        super(progressBar);
    }


    @Override
    protected MessageContainer doInBackground(Void... voids) {
        return null;
    }

    @Override
    public void addDefaultQueryParameter(String parameter) {

    }

    @Override
    public String createFullQuery() {
        return null;
    }
}
