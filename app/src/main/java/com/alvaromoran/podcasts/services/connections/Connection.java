package com.alvaromoran.podcasts.services.connections;

import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.alvaromoran.podcasts.services.connections.templates.MessageContainer;

import java.util.HashMap;

/**
 * Abstract class that implements all basic functionality to perform
 * a query over an URL
 */
public abstract class Connection extends AsyncTask<Void, Void, MessageContainer> implements Query {

    /**
     * String builder to create the URL to perform the query
     */
    protected StringBuilder urlBuilder;

    /**
     * Additional parameters to perform the query over the server
     */
    protected HashMap<String, String> parameters;

    /**
     * Reference to a progress bar represented by the UI
     */
    protected ProgressBar progressBarReference;

    /**
     * Result of the last query that has been performed over the net
     */
    protected MessageContainer lastQueryResult;

    /**
     * Empty constructor of the class
     */
    public Connection() {
        this.urlBuilder = new StringBuilder();
        this.parameters = new HashMap<>();
    }

    /**
     * Constructor of the class with reference for the progress bar to be displayed
     * when performing a query
     *
     * @param progressBar reference to the progress bar to be shown in the UI
     */
    public Connection(ProgressBar progressBar) {
        this(); // Chained constructor
        this.progressBarReference = progressBar;
    }

    @Override
    public void addAdditionalQueryParameter(String key, String value) {
        if (key != null && value != null && !key.isEmpty() && !value.isEmpty()) {
            this.parameters.put(key, value);
        }
    }

    @Override
    public void cleanQuery() {
        this.parameters.clear();
    }

    @Override
    public void removeQueryParameter(String key) {
        if (key != null) {
            this.parameters.remove(key);
        }
    }

    /**
     * Method to store the result of the last query performed
     *
     * @param receivedMessage received message
     */
    protected void storeLastResponse(MessageContainer receivedMessage) {
        this.lastQueryResult = receivedMessage;
    }

    /**
     * Gets the result of the last query performed
     *
     * @return last query message received
     */
    public MessageContainer getLastQueryResult() {
        return this.lastQueryResult;
    }

    @Override
    protected void onPreExecute() {
        if (this.progressBarReference != null) {
            progressBarReference.setVisibility(ProgressBar.VISIBLE);
            onProgressUpdate(0);
        }
    }

    @Override
    protected void onPostExecute(MessageContainer result) {
        onProgressUpdate(30);
        result.parseMessage();
        onProgressUpdate(60);
        storeLastResponse(result);
        onProgressUpdate(100);
    }

    protected void onProgressUpdate(int... values) {
        if (this.progressBarReference != null) {
            this.progressBarReference.setProgress(values[0]);
            if (values[0] == 100) {
                this.progressBarReference.setVisibility(ProgressBar.INVISIBLE);
            }
        }
    }

}
