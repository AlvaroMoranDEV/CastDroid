package com.alvaromoran.podcasts.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Abstract class to define the common behaviour of the different PodCasts that will be used
 * in the application
 */
public class PodCastChannel implements Comparable<PodCastChannel> {

    //region Class Variables

    /**
     * Name of the channel
     */
    private String channelName;

    /** Name of the artist */
    private String artistName;

    /**
     * Channel brief description to be shown to the user in the UI
     */
    private String channelBriefDescription;

    /**
     * More detailed channel explanation to be shown to the user in the UI if the user wants to
     */
    private String channelFullDescription;

    /**
     * Name of the collection associated to the set of videos the channel is providing
     */
    private String collectionName;

    /**
     * ULR of the image to be displayed (may be a local path or a real URL)
     */
    private String urlImage;

    /**
     * Country where the audio was recorded
     */
    private String country;

    /**
     * Set of tags associated with the content that the channel is providing
     */
    private List<String> channelTags;

    /**
     * List of associated episodes of the channel
     */
    private List<PodCastEpisode> channelEpisodes;

    /**
     * URL where the channels information is retrieved from
     */
    private String feedUrl;

    /** Copyright of the channel */
    private String copyRight;

    /** Summary of the channel */
    private String summary;

    /** Link associated to the channel producer*/
    private String link;

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
     * @param channelName name of the channel
     */
    public PodCastChannel(String channelName) {
        this.channelName = channelName;
        this.channelTags = new ArrayList<>();
        this.channelEpisodes = new ArrayList<>();

    }

    // endregion

    //region Comparable implementation

    @Override
    public int compareTo(PodCastChannel otherChannel) {
        final int EQUALS = 0;
        final int NOT_EQUALS = -1;

        // Variables to be compared: channel name, collection name and brief description
        // We only should care about items that are "equals" between them
        if (otherChannel != null) {
            if (this.channelName.equalsIgnoreCase(otherChannel.getChannelName())
                    && this.collectionName.equalsIgnoreCase(otherChannel.getCollectionName())
                    && this.channelBriefDescription.equalsIgnoreCase(otherChannel.getChannelBriefDescription())) {
                return EQUALS;
            } else {
                return NOT_EQUALS;
            }
        } else {
            return NOT_EQUALS;
        }
    }

    // endregion

    // region Getters and Setters

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getChannelName() {
        return channelName;
    }

    public String getChannelBriefDescription() {
        return channelBriefDescription;
    }

    public void setChannelBriefDescription(String channelBriefDescription) {
        this.channelBriefDescription = channelBriefDescription;
    }

    public String getChannelFullDescription() {
        return channelFullDescription;
    }

    public void setChannelFullDescription(String channelFullDescription) {
        this.channelFullDescription = channelFullDescription;
    }

    public void addChannelTag(String newTag) {
        this.channelTags.add(newTag);
    }

    public void setChannelTags(List<String> channelTags) {
        this.channelTags = channelTags;
    }

    public Collection<String> getChannelTags() {
        return this.channelTags;
    }

    public void addChannelEpisode(PodCastEpisode singleEpisode) {
        this.channelEpisodes.add(singleEpisode);
    }

    public void setChannelEpisodes(List<PodCastEpisode> listOfEpisodes) {
        this.channelEpisodes = listOfEpisodes;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getFeedUrl() {
        return feedUrl;
    }

    public void setFeedUrl(String feedUrl) {
        this.feedUrl = feedUrl;
    }

    public Collection<PodCastEpisode> getFullListOfEpisodes() {
        return this.channelEpisodes;
    }

    public PodCastEpisode getSinglePodCastEpisode(int episodeId) {
        if (episodeId > 0 && episodeId < this.channelEpisodes.size()) {
            return this.channelEpisodes.get(episodeId);
        } else {
            return null;
        }
    }

    public Date getAccessDate() {
        return accessDate;
    }

    public void setAccessDate(Date accessDate) {
        this.accessDate = accessDate;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public String getCopyRight() {
        return copyRight;
    }

    public void setCopyRight(String copyRight) {
        this.copyRight = copyRight;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    // endregion

}
