package com.alvaromoran.castdroid.models;

public class Genre {

    private int genreId;

    private String genreName;

    private String genreDisplayName;

    private String genreSearchTerm;

    private int drawableBackgroundItem;

    public Genre (int genreId, String genreName, String genreSearchTerm, int drawableBackgroundItem) {
        this.genreId = genreId;
        this.genreName = genreName;
        this.genreSearchTerm = genreSearchTerm;
        this.drawableBackgroundItem = drawableBackgroundItem;
    }

    public void setGenreDisplayName(String genreDisplayName) {
        this.genreDisplayName = genreDisplayName;
    }

    public int getGenreId() {
        return genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public String getGenreDisplayName() {
        return genreDisplayName;
    }

    public String getGenreSearchTerm() {
        return genreSearchTerm;
    }

    public int getDrawableBackgroundItem() {
        return drawableBackgroundItem;
    }
}
