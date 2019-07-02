package com.alvaromoran.podcasts.models.storedModels;

import com.alvaromoran.podcasts.models.common.PodCastEpisode;
import com.alvaromoran.podcasts.models.common.Storable;

import java.util.Date;

/**
 * Class that represents a PodCast episode that has been stored into internal memory. This
 * allows the user some specific actions, such as flag the episode as favourite and keep
 * some of its information cached into local storage. Even the whole episode is able to be
 * stored into the internal memory to be listened in offline mode.
 */
public class PodCastEpisodeStored extends PodCastEpisode implements Storable {

    //region Class Variables

    /**
     * Id of the channel when being stored in the local storage
     */
    private int persistentId;

    /**
     * Date when the channel was accessed /listened last time
     */
    private Date accessDate;

    /**
     * Path where the audio is stored into internal memory
     */
    private String locationPath;

    /**
     * Flag to check if the element has been stored into internal memory
     */
    private boolean locallyStored;

    /**
     * Sets if the episode has been flagged as favourite by the user
     */
    private boolean favourite;

    // endregion

    //region Constructors

    /**
     * Constructor of the class
     *
     * @param episodeName         name of the episode
     * @param elementPersistentId internal unique UI used to store the element
     */
    public PodCastEpisodeStored(String episodeName, int elementPersistentId) {
        super(episodeName);
        this.persistentId = elementPersistentId;
    }

    // endregion

    // region Storable implementation

    @Override
    public int getElementPersistentId() {
        return this.persistentId;
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
        this.locationPath = locationPath;
        if (locationPath != null) {
            this.locallyStored = true;
        } else {
            this.locallyStored = false;
        }
    }

    @Override
    public String getLocationPath() {
        return this.locationPath;
    }

    @Override
    public boolean isLocallyStored() {
        return this.locallyStored;
    }

    @Override
    public void setLocallyStored(boolean stored) {
        this.locallyStored = stored;
    }

    @Override
    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    @Override
    public boolean isFavourite() {
        return this.favourite;
    }

    //endregion
}
