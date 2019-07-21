package com.alvaromoran.podcasts.services.connections;

import android.widget.GridView;
import android.widget.ProgressBar;

import com.alvaromoran.podcasts.services.connections.templates.GenericPodCastMessage;
import com.alvaromoran.podcasts.services.connections.templates.ITunesMessage;
import com.alvaromoran.podcasts.services.connections.templates.MessageContainer;

/**
 * Class that manages the connection over the ITunes store or the different PodCast providers
 * over the web
 */
public class WebProvider {

    //region Class variables

    /**
     * Manages the connection over the ITunes store, used as PodCasts database
     */
    private final Connection itunesConnection;

    /**
     * Manages the connection over a single PodCast provider
     */
    private final Connection podcastChannelConnection;

    //endregion

    // region Constructors

    /**
     * Empty constructor of the class
     */
    public WebProvider() {
        this.itunesConnection = new ITunesConnection();
        this.podcastChannelConnection = new PodCastChannelConnection();
    }

    // endregion

    //region ITunes methods

    /**
     * Adds an argument to the query that is going to be executed over the ITunes store
     *
     * @param parameters relation of key-value of the parameter to be added. If only the value
     *                   is provided, the code will assume that the main search term is going to
     *                   be used for that value
     */
    public void addITunesArgument(String... parameters) {

        if (parameters.length == 1) {
            // Default search term
            this.itunesConnection.addDefaultQueryParameter(parameters[0]);
        } else if (parameters.length == 2) {
            // Special search term
            this.itunesConnection.addAdditionalQueryParameter(parameters[0], parameters[1]);
        }
    }

    /**
     * Performs the query over the ITunes store
     *
     * @return parsed message received from the provider with the results of the query
     */
    public MessageContainer performQueryOverITunes() {
        return this.itunesConnection.performQuery();
    }

    /**
     * Removes a query parameter over the ITunes store
     *
     * @param key key of the parameter to be removed
     */
    public void removeITunesQueryArgument(String key) {
        this.itunesConnection.removeQueryParameter(key);
    }

    /**
     * Cleans all the query parameters over the ITunes store
     */
    public void cleanITunesQuery() {
        this.itunesConnection.cleanQuery();
    }

    // endregion

    //region PodCast Provider methods

    /**
     * Adds the feed URL that is going to be used to establish connection with the PodCast provider
     *
     * @param feedUrl URL
     */
    public void addFeedUrlPodcastChannelConnection(String feedUrl) {
        this.podcastChannelConnection.addDefaultQueryParameter(feedUrl);
    }

    /**
     * Access to the URL of the PodCast provider, expecting a result from ir with the channel
     * and episodes detailed information
     *
     * @return parsed message received from the provider
     */
    public MessageContainer performQueryOverPodcastChannel() {
        return this.podcastChannelConnection.performQuery();
    }

    //endregion


}
