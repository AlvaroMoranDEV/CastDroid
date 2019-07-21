package com.alvaromoran.podcasts.services.controllers;

import android.content.Context;
import android.widget.AdapterView;

import com.alvaromoran.podcasts.models.common.PodCastChannel;
import com.alvaromoran.podcasts.services.connections.Connection;

public class QueryTaskParameters {

    public static int QUERY_OVER_ITUNES = 0;

    public static int QUERY_OVER_CHANNEL = 1;

    private int typeOfQuery;

    private Context applicationContext;

    private AdapterView graphicElement;

    private PodCastChannel selectedChannel;

    public QueryTaskParameters (int typeOfQuery, AdapterView graphicElement, Context applicationContext) {
        this.typeOfQuery = typeOfQuery;
        this.graphicElement = graphicElement;
        this.applicationContext = applicationContext;
    }

    public int getTypeOfQuery() {
        return typeOfQuery;
    }

    public AdapterView getGraphicElement() {
        return graphicElement;
    }

    public PodCastChannel getSelectedChannel() {
        return selectedChannel;
    }

    public void setSelectedChannel(PodCastChannel selectedChannel) {
        this.selectedChannel = selectedChannel;
    }

    public Context getApplicationContext() {
        return this.applicationContext;
    }

}
