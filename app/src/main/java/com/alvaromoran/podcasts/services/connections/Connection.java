package com.alvaromoran.podcasts.services.connections;

import com.alvaromoran.podcasts.services.connections.templates.MessageContainer;

import java.util.HashMap;

/**
 * Abstract class that implements all basic functionality to perform
 * a query over an URL
 */
public abstract class Connection {

    /**
     * String builder to create the URL to perform the query
     */
    protected StringBuilder urlBuilder;

    /**
     * Additional parameters to perform the query over the server
     */
    protected HashMap<String, String> parameters;

    protected MessageContainer lastQueryAnswer;

    /**
     * Empty constructor of the class
     */
    public Connection() {
        this.urlBuilder = new StringBuilder();
        this.parameters = new HashMap<>();
    }

    /**
     * Adds additional parameters to perform the query over the web provider
     *
     * @param key   parameter key
     * @param value parameter value
     */
    public void addAdditionalQueryParameter(String key, String value) {
        if (key != null && value != null && !key.isEmpty() && !value.isEmpty()) {
            this.parameters.put(key, value);
        }
    }

    /**
     * Cleans all query parameters stored
     */
    public void cleanQuery() {
        this.parameters.clear();
    }

    /**
     * Removes a single query parameter by specifying its key
     *
     * @param key key to access the query parameter to be deleted
     */
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
    public void storeLastResponse(MessageContainer receivedMessage) {
        this.lastQueryAnswer = receivedMessage;
    }

    /**
     * Gets the result of the last query performed
     *
     * @return last query message received
     */
    public MessageContainer getLastQueryResult() {
        return this.lastQueryAnswer;
    }




    abstract public MessageContainer performQuery();

    /**
     * Adds the default and main parameter to perform the query over the web provider
     */
    abstract void addDefaultQueryParameter(String parameter);

}
