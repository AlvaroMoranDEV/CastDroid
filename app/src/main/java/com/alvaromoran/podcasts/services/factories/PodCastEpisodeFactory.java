package com.alvaromoran.podcasts.services.factories;

import com.alvaromoran.podcasts.models.PodCastEpisode;
import com.alvaromoran.podcasts.services.dataAccess.connections.ConnectionsConstants;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class PodCastEpisodeFactory {

    public static final String ITEM_TITLE_TAG = "title";
    public static final String ITEM_SUBTITLE_TAG = "subtitle";
    public static final String ITEM_SUMMARY_TAG = "summary";
    public static final String ITEM_AUTHOR_TAG = "author";
    public static final String ITEM_GUID_TAG = "guid";
    public static final String ITEM_PUBDATE_TAG = "pubDate";
    public static final String ITEM_DURATION_TAG = "duration";
    public static final String GENERIC_ITEM_TAG = "item";
    public static final String GENERAL_NAMESPACE = null;

    /**
     * Private constructor to avoid instantiation
     */
    private PodCastEpisodeFactory() {
        throw new UnsupportedOperationException();
    }

    public static int PODCAST_FROM_STORAGE = 0;

    public static int PODCAST_FROM_INTERNET = 1;

    public static PodCastEpisode createPodCastEpisode(int typeOfCreation, String fullMessage, XmlPullParser parser) {
        PodCastEpisode createdEpisode = null;
        if (fullMessage != null) {
            try {
                if (typeOfCreation == PODCAST_FROM_INTERNET) {
                    createdEpisode = createPodCastEpisodeFromNet(fullMessage, parser);
                } else if (typeOfCreation == PODCAST_FROM_STORAGE) {
                    createdEpisode = createPodCastEpisodeFromStorage(fullMessage, parser);
                } else {
                    createdEpisode = null;
                }
            } catch(XmlPullParserException e) {

            } catch (IOException e) {

            }
        }
        return createdEpisode;
    }

    private static PodCastEpisode createPodCastEpisodeFromNet(String fullMessage, XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, GENERAL_NAMESPACE, GENERIC_ITEM_TAG);
        String title = null, subtitle = null, author = null, summary = null, pubDate = null, duration = null, guid = null;

        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String entityName = parser.getName();
            if (entityName.equalsIgnoreCase(ITEM_TITLE_TAG) && title == null) {
                title = parseTitle(parser);
            } else if (entityName.equalsIgnoreCase(ITEM_SUBTITLE_TAG) && subtitle == null) {
                subtitle = parseSubtitle(parser);
            } else if (entityName.equalsIgnoreCase(ITEM_SUMMARY_TAG) && summary == null) {
                summary = parseSummary(parser);
            } else if (entityName.equalsIgnoreCase(ITEM_AUTHOR_TAG) && author == null) {
                author = parseAuthor(parser);
            } else if (entityName.equalsIgnoreCase(ITEM_GUID_TAG) && guid == null) {
                guid = parseGuid(parser);
            } else if (entityName.equalsIgnoreCase(ITEM_PUBDATE_TAG) && pubDate == null) {
                pubDate = parsePubDate(parser);
            } else if (entityName.equalsIgnoreCase(ITEM_DURATION_TAG) && duration == null) {
                duration = parseDuration(parser);
            } else {
                avoidTag(parser);
            }
        }

        // Episode creation
        PodCastEpisode episode = new PodCastEpisode(title);
        episode.setAuthor(author);
        episode.setEpisodeSubtitle(subtitle);
        episode.setSummary(summary);

        return episode;
    }

    private static PodCastEpisode createPodCastEpisodeFromStorage(String fullMessage, XmlPullParser parser) throws XmlPullParserException, IOException {
        return null;
    }


    private static String parseTitle(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, GENERAL_NAMESPACE, ITEM_TITLE_TAG);
        String title = readGenericText(parser);
        parser.require(XmlPullParser.END_TAG, GENERAL_NAMESPACE, ITEM_TITLE_TAG);
        return title;
    }

    private static String parseSubtitle(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, GENERAL_NAMESPACE, ITEM_SUBTITLE_TAG);
        String subtitle = readGenericText(parser);
        parser.require(XmlPullParser.END_TAG, GENERAL_NAMESPACE, ITEM_SUBTITLE_TAG);
        return subtitle;
    }

    private static String parseSummary(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, GENERAL_NAMESPACE, ITEM_SUMMARY_TAG);
        String summary = readGenericText(parser);
        parser.require(XmlPullParser.END_TAG, GENERAL_NAMESPACE, ITEM_SUMMARY_TAG);
        return summary;
    }

    private static String parseAuthor(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, GENERAL_NAMESPACE, ITEM_AUTHOR_TAG);
        String author = readGenericText(parser);
        parser.require(XmlPullParser.END_TAG, GENERAL_NAMESPACE, ITEM_AUTHOR_TAG);
        return author;
    }

    private static String parseGuid(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, GENERAL_NAMESPACE, ITEM_GUID_TAG);
        String guid = readGenericText(parser);
        parser.require(XmlPullParser.END_TAG, GENERAL_NAMESPACE, ITEM_GUID_TAG);
        return guid;
    }

    private static String parsePubDate(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, GENERAL_NAMESPACE, ITEM_PUBDATE_TAG);
        String pubDate = readGenericText(parser);
        parser.require(XmlPullParser.END_TAG, GENERAL_NAMESPACE, ITEM_PUBDATE_TAG);
        return pubDate;
    }

    private static String parseDuration(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, GENERAL_NAMESPACE, ITEM_DURATION_TAG);
        String duration = readGenericText(parser);
        parser.require(XmlPullParser.END_TAG, GENERAL_NAMESPACE, ITEM_DURATION_TAG);
        return duration;
    }

    private static String readGenericText(XmlPullParser parser) throws XmlPullParserException, IOException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;

    }

    private static void avoidTag(XmlPullParser parser) throws XmlPullParserException, IOException {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}
