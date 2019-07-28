package com.alvaromoran.podcasts.services.controllers;

import android.content.Context;
import android.widget.AdapterView;

import com.alvaromoran.podcasts.models.common.PodCastChannel;

/**
 * Class to store all needed information to perform a query over the internet
 */
public class QueryTaskParameters {

    //region Class Variables

    /** Perform query over the iTunes store */
    public static int QUERY_OVER_ITUNES = 0;

    /** Perfom query over a particular feed */
    public static int QUERY_OVER_CHANNEL = 1;

    /** Type of query to be executed between iTunes or particular feed */
    private int typeOfQuery;

    /** Application context to showcase the received information */
    private Context applicationContext;

    /** View where the information will be shown to the user */
    private AdapterView graphicElement;

    /** Selected channel */
    private PodCastChannel selectedChannel;

    //endregion

    //region Constructors

    /**
     * Constructor of the query task
     * @param typeOfQuery
     * @param graphicElement
     * @param applicationContext
     */
    public QueryTaskParameters (int typeOfQuery, AdapterView graphicElement, Context applicationContext) {
        this.typeOfQuery = typeOfQuery;
        this.graphicElement = graphicElement;
        this.applicationContext = applicationContext;
    }

    //endregion

    //region Getters and Setters

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

    //endregion

}
