package com.alvaromoran.podcasts.models.storedModels;

import com.alvaromoran.podcasts.models.common.PodCastChannel;
import com.alvaromoran.podcasts.models.common.Storable;

import java.util.Date;

/**
 * Class that represents a PodCast channel that has been stored into internal memory. This
 * allows the user some specific actions, such as flag the channel as favourite and keep
 * some of its information cached into local storage
 */
public class PodCastChannelStored extends PodCastChannel implements Storable {

    //region Class Variables

    /**
     * Id of the channel when being stored in the local storage
     */
    private int elementPersistentId;

    /**
     * Date when the channel was accessed /listened last time
     */
    private Date accessDate;

    /**
     * Sets if the channel has been flagged as favourite by the user
     */
    private boolean favourite;

    // endregion

    //region Constructors

    /**
     * Constructor of the class - Channel name is a needed parameter
     *
     * @param channelName         name of the channel
     * @param elementPersistentId unique id to store the channel information
     */
    public PodCastChannelStored(String channelName, int elementPersistentId) {
        super(channelName);
        this.elementPersistentId = elementPersistentId;
    }

    //endregion

    //region Storable Methods Override

    @Override
    public int getElementPersistentId() {
        return this.elementPersistentId;
    }

    @Override
    public void setAccessDate(Date accessDate) {
        this.accessDate = accessDate;
    }

    @Override
    public Date getAccessDate() {
        return this.accessDate;
    }

    @Override
    public void setLocationPath(String locationPath) {
    }

    @Override
    public String getLocationPath() {
        return null;
    }

    @Override
    public boolean isLocallyStored() {
        return false;
    }

    @Override
    public void setLocallyStored(boolean stored) {
    }

    @Override
    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    @Override
    public boolean isFavourite() {
        return this.favourite;
    }

    // endregion
}
