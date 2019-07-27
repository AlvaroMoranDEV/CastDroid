package com.alvaromoran.podcasts.services.connections;

/**
 * Class that provides all constants needed for perform connections over the ITunes Store and
 * the generic podcasts providers
 */
public final class ConnectionsConstants {

//region ITunes Main Constants

    /**
     * Following constants will be used for generating the main query for the ITunes store
     * Default value for the store is provided by SEARCH_MAIN_TERM
     */

    public enum ITunesSearchKeys {
        TERM("term"),
        COUNTRY("country"),
        MEDIA("media"),
        ENTITY("entity"),
        CALLBACK("callback"),
        LIMIT("limit"),
        LANG("lang"),
        VERSION("version"),
        EXPLICIT("explicit");

        private final String key;

        ITunesSearchKeys(final String key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return this.key;
        }
    }

//endregion

// region ITunes Specific Constants

    public static final String SEARCH_TERM_PODCAST = "podcast";
    public static final String SEARCH_TERM_PODCAST_AUTHOR = "podcastAuthor";
    public static final String SEARCH_ATTRIBUTE_PODCAST_TITLE = "titleTerm";
    public static final String SEARCH_ATTRIBUTE_PODCAST_LANGUAGE = "languageTerm";
    public static final String SEARCH_ATTRIBUTE_PODCAST_AUTHOR = "authorTerm";
    public static final String SEARCH_ATTRIBUTE_PODCAST_GENRE = "genreIndex";
    public static final String SEARCH_ATTRIBUTE_PODCAST_ARTIST = "artistTerm";
    public static final String SEARCH_ATTRIBUTE_PODCAST_RATING = "ratingIndex";
    public static final String SEARCH_ATTRIBUTE_PODCAST_KEYWORDS = "keywordsTerm";
    public static final String SEARCH_ATTRIBUTE_PODCAST_DESCRIPTION = "descriptionTerm";

// endregion

// region ITunes Genres

    /**
     * Set of genres provided for the podcasts by the ITunes Store
     */
    public static final String SEARCH_GENRE_ARTS = "Arts";
    public static final String SEARCH_GENRE_COMEDY = "Comedy";
    public static final String SEARCH_GENRE_EDUCATION = "Education";
    public static final String SEARCH_GENRE_KIDS = "Kids & Family";
    public static final String SEARCH_GENRE_HEALTH = "Health";
    public static final String SEARCH_GENRE_TV = "TV & Film";
    public static final String SEARCH_GENRE_MUSIC = "Music";
    public static final String SEARCH_GENRE_NEWS = "News & Politics";
    public static final String SEARCH_GENRE_RELIGION = "Religion & Spirituality";
    public static final String SEARCH_GENRE_SCIENCE = "Science & Medicine";
    public static final String SEARCH_GENRE_SPORTS = "Sports & Recreation";
    public static final String SEARCH_GENRE_TECHNOLOGY = "Technology";
    public static final String SEARCH_GENRE_BUSINESS = "Business";
    public static final String SEARCH_GENRE_GAMES = "Games & Hobbies";
    public static final String SEARCH_GENRE_CULTURE = "Society & Culture";
    public static final String SEARCH_GENRE_GOVERNMENT = "Government & Organizations";

// endregion

//region ITunes Query Separators

    /**
     * Separator between query terms
     */
    public static final String ITUNES_PARAMETER_SEPARATION = "&";

    /**
     * Separator between query term key and its value
     */
    public static final String ITUNES_PARAMETER_EQUAL = "=";

//endregion

// region ITunes Home

    public static final String MAIN_ITUNES_URL = "https://itunes.apple.com/search?";

//endregion

    public static final String GENERIC_START_TAG = "xml";
    public static final String GENERIC_RSS_TAG = "rss";
    public static final String GENERIC_CHANNEL_TAG = "channel";
    public static final String GENERIC_SUMMARY_TAG = "summary";
    public static final String GENERIC_COPYRIGHT_TAG = "copyright";
    public static final String GENERIC_LINK_TAG = "link";
    public static final String GENERIC_ITEM_TAG = "item";



}
