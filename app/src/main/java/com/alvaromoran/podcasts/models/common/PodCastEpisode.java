package com.alvaromoran.podcasts.models.common;

import java.util.Date;

/**
 * Abstract class to define main aspects of a PodCast episode
 */
public abstract class PodCastEpisode implements Listenable, Comparable<PodCastEpisode> {

    //region Class Variables

    /**
     * Name of the episode
     */
    private String episodeName;

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
    private String description;

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

    // endregion

    // region Constructors

    /**
     * Constructor of the class
     *
     * @param episodeName name of the episode
     */
    public PodCastEpisode(String episodeName) {
        this.episodeName = episodeName;
        this.releaseDate = new Date();
    }

    // endregion

    // region Listenable implementation

    @Override
    public void addUrlFeed(String urlFeed) {
        this.urlFeed = urlFeed;
    }

    @Override
    public void startPlaying() {
        this.isBeingListened = true;
        this.hasBeenListened = true;
    }

    @Override
    public void stopPlaying(int duration) {
        this.isBeingListened = false;
        setWhenLeftOf(duration);
    }

    @Override
    public String getUrlFeed() {
        return this.urlFeed;
    }

    @Override
    public boolean isBeingListened() {
        return this.isBeingListened;
    }

    @Override
    public boolean hasBeenListened() {
        return this.hasBeenListened;
    }

    @Override
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
            if (this.episodeName.equalsIgnoreCase(otherEpisode.getEpisodeName())
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

    public String getEpisodeName() {
        return episodeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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


    // endregion
}
