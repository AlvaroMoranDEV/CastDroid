package com.alvaromoran.podcasts.services.connections;

/**
 * Query interface to provide all needed functionality to create the queries to
 */
public interface Query {

    /**
     * Adds the default and main parameter to perform the query over the web provider
     */
    void addDefaultQueryParameter(String parameter);

    /**
     * Adds additional parameters to perform the query over the web provider
     *
     * @param key   parameter key
     * @param value parameter value
     */
    void addAdditionalQueryParameter(String key, String value);

    /**
     * Cleans all query parameters stored
     */
    void cleanQuery();

    /**
     * Removes a single query parameter by specifying its key
     *
     * @param key key to access the query parameter to be deleted
     */
    void removeQueryParameter(String key);

    /**
     * Creates the full query to be performed
     *
     * @return full query
     */
    String createFullQuery();


}
