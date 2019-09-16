package com.alvaromoran.podcasts.models;

import java.util.Date;

/**
 * Abstract class to define main aspects of a PodCast episode
 */
public class PodCastEpisode implements Comparable<PodCastEpisode> {

    //region Class Variables

    /**
     * Name of the episode
     */
    private String episodeTitle;

    /**
     * Subtitule of the episode
     */
    private String episodeSubtitle;

    /**
     * Name of the author
     */
    private String author;

    /**
     * Duration of the episode in seconds
     */
    private int secondsDuration;

    /**
     * Release date of the episode
     */
    private Date releaseDate;

    /**
     * URL where the episode is stored in the net
     */
    private String urlFeed;

    /**
     * Description of the current episode
     */
    private String summary;

    /**
     * Language of the podcast episode
     */
    private String language;

    /**
     * Flag to check if the episode is being listened
     */
    private boolean isBeingListened;

    /**
     * Flag to check if the episode has been listened
     */
    private boolean hasBeenListened;

    /**
     * Second where the episode was left during the last playing
     */
    private int secondsLeftOf;

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

    // region Constructors

    /**
     * Constructor of the class
     *
     * @param episodeTitle name of the episode
     */
    public PodCastEpisode(String episodeTitle) {
        this.episodeTitle = episodeTitle;
        this.releaseDate = new Date();
    }

    // endregion

    // region Listenable implementation


    public void addUrlFeed(String urlFeed) {
        this.urlFeed = urlFeed;
    }


    public void startPlaying() {
        this.isBeingListened = true;
        this.hasBeenListened = true;
    }


    public void stopPlaying(int duration) {
        this.isBeingListened = false;
        setWhenLeftOf(duration);
    }


    public String getUrlFeed() {
        return this.urlFeed;
    }


    public boolean isBeingListened() {
        return this.isBeingListened;
    }


    public boolean hasBeenListened() {
        return this.hasBeenListened;
    }


    public void setWhenLeftOf(int whenLeftOf) {
        this.secondsLeftOf = whenLeftOf;
    }

    // endregion

    // region Comparable implementation
    @Override
    public int compareTo(PodCastEpisode otherEpisode) {
        final int EQUALS = 0;
        final int NOT_EQUALS = -1;

        // Variables to be compared: feedURL, name and author
        // We only should care about items that are "equals" between them
        if (otherEpisode != null) {
            if (this.episodeTitle.equalsIgnoreCase(otherEpisode.getEpisodeTitle())
                    && this.author.equalsIgnoreCase(otherEpisode.getAuthor())
                    && this.urlFeed.equalsIgnoreCase(otherEpisode.getUrlFeed())) {
                return EQUALS;
            } else {
                return NOT_EQUALS;
            }
        } else {
            return NOT_EQUALS;
        }
    }

    // endregion

    //region Getters and Setters

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getSecondsDuration() {
        return secondsDuration;
    }

    public void setSecondsDuration(int secondsDuration) {
        this.secondsDuration = secondsDuration;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getSecondsLeftOf() {
        return secondsLeftOf;
    }

    public String getEpisodeTitle() {
        return episodeTitle;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }


    public String getEpisodeSubtitle() {
        return episodeSubtitle;
    }

    public void setEpisodeSubtitle(String episodeSubtitle) {
        this.episodeSubtitle = episodeSubtitle;
    }

    public void setSecondsLeftOf(int secondsLeftOf) {
        this.secondsLeftOf = secondsLeftOf;
    }

    public Date getAccessDate() {
        return accessDate;
    }

    public void setAccessDate(Date accessDate) {
        this.accessDate = accessDate;
    }

    public String getLocationPath() {
        return locationPath;
    }

    public void setLocationPath(String locationPath) {
        this.locationPath = locationPath;
    }

    public boolean isLocallyStored() {
        return locallyStored;
    }

    public void setLocallyStored(boolean locallyStored) {
        this.locallyStored = locallyStored;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    // endregion
}
