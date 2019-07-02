package com.alvaromoran.podcasts.models.common;

import java.util.Date;

/**
 * Defines the set of rules that the objects to be stored in the internal memory have to implement.
 * This rules implies the use of a persistent ID for the element to be stored, and some aspects
 * regarding how and where the element its stored and when was the last time the element was accessed
 */
public interface Storable {

    /**
     * Gets the unique ID of the element to be stored
     *
     * @return Id of the stored element
     */
    int getElementPersistentId();

    /**
     * Sets the last time that the element was accessed and used by the application
     *
     * @param accessDate date of the last time the element was accessed
     */
    void setAccessDate(Date accessDate);

    /**
     * Gets the last time that the element was accessed and used by the application
     *
     * @return last time the elemet was used
     */
    Date getAccessDate();

    /**
     * Sets the local path where the element is actually stored
     *
     * @param locationPath local path where the element is located
     */
    void setLocationPath(String locationPath);

    /**
     * Gets the local path where the element is stored
     *
     * @return path where the element is stored
     */
    String getLocationPath();

    /**
     * Sets if the full data of the element is stored in local memory
     *
     * @return <code>true</code> if the data of the element is stored in local memory, <code>false</code>
     * otherwise
     */
    boolean isLocallyStored();

    /**
     * Gets if the full data of the element is stored in local memory or if only a reference
     * for the web resource is stored
     */
    void setLocallyStored(boolean stored);

    /**
     * Sets if the current element is tagged as favourite by the user
     *
     * @param favourite <code>true</code> if favourite
     */
    void setFavourite(boolean favourite);

    /**
     * Gets if the current element has been tagged as favourite by the user
     *
     * @return favourite value
     */
    boolean isFavourite();

}
