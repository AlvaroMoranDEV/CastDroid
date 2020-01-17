package com.alvaromoran.castdroid.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Channel implements Parcelable {

    private String imageUrl;

    private String channelTitle;

    private String channelAuthor;

    private String channelDescription;

    private String copyRight;

    private List<String> categories;

    private List<Episode> episodes;

    private String channelUrl;

    private String channelContactUrl;

// App generated variables

    private boolean isLiked;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getChannelAuthor() {
        return channelAuthor;
    }

    public void setChannelAuthor(String channelAuthor) {
        this.channelAuthor = channelAuthor;
    }

    public String getChannelDescription() {
        return channelDescription;
    }

    public void setChannelDescription(String channelDescription) {
        this.channelDescription = channelDescription;
    }

    public String getCopyRight() {
        return copyRight;
    }

    public void setCopyRight(String copyRight) {
        this.copyRight = copyRight;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public String getChannelUrl() {
        return channelUrl;
    }

    public void setChannelUrl(String channelUrl) {
        this.channelUrl = channelUrl;
    }

    public String getChannelContactUrl() {
        return channelContactUrl;
    }

    public void setChannelContactUrl(String channelContactUrl) {
        this.channelContactUrl = channelContactUrl;
    }

    public Channel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.imageUrl);
        dest.writeString(this.channelTitle);
        dest.writeString(this.channelAuthor);
        dest.writeString(this.channelDescription);
        dest.writeString(this.copyRight);
        dest.writeStringList(this.categories);
        dest.writeList(this.episodes);
        dest.writeString(this.channelUrl);
        dest.writeString(this.channelContactUrl);
        dest.writeByte(this.isLiked ? (byte) 1 : (byte) 0);
    }

    protected Channel(Parcel in) {
        this.imageUrl = in.readString();
        this.channelTitle = in.readString();
        this.channelAuthor = in.readString();
        this.channelDescription = in.readString();
        this.copyRight = in.readString();
        this.categories = in.createStringArrayList();
        this.episodes = new ArrayList<Episode>();
        in.readList(this.episodes, Episode.class.getClassLoader());
        this.channelUrl = in.readString();
        this.channelContactUrl = in.readString();
        this.isLiked = in.readByte() != 0;
    }

    public static final Creator<Channel> CREATOR = new Creator<Channel>() {
        @Override
        public Channel createFromParcel(Parcel source) {
            return new Channel(source);
        }

        @Override
        public Channel[] newArray(int size) {
            return new Channel[size];
        }
    };
}
