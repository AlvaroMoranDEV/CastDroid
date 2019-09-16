package com.alvaromoran.podcasts.services.dataAccess.data;

import android.util.Xml;

import com.alvaromoran.podcasts.models.PodCastEpisode;
import com.alvaromoran.podcasts.services.dataAccess.connections.ConnectionsConstants;
import com.alvaromoran.podcasts.services.factories.PodCastEpisodeFactory;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


public class GenericPodCastMessage extends MessageContainer {

    public static final String GENERAL_NAMESPACE = null;

    /** General channel constants*/
    public static final String GENERIC_START_TAG = "xml";
    public static final String GENERIC_RSS_TAG = "rss";
    public static final String GENERIC_CHANNEL_TAG = "channel";
    public static final String GENERIC_SUMMARY_TAG = "summary";
    public static final String GENERIC_COPYRIGHT_TAG = "copyright";
    public static final String GENERIC_LINK_TAG = "link";
    public static final String GENERIC_ITEM_TAG = "item";



    private List<PodCastEpisode> parsedEpisodes;

    private String summary;

    private String copyright;

    private String link;


    public GenericPodCastMessage(String fullMessage) {
        super(fullMessage);
    }

    @Override
    public int getNumberOfItems() {
        return 0;
    }

    @Override
    public Object parseMessage() {

        if (this.parsedEpisodes != null) {
            return this.parsedEpisodes;
        } else {
            try {
                XmlPullParser parser = Xml.newPullParser();
                parser.setInput(new StringReader(getFullMessage()));
                parser.nextTag();
                parseRss(parser);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void parseRss(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, GENERAL_NAMESPACE, GENERIC_RSS_TAG);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String entityName = parser.getName();
            if (entityName.equalsIgnoreCase(GENERIC_CHANNEL_TAG)) {
                parseChannel(parser);
            }
        }
    }

    private void parseChannel(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, GENERAL_NAMESPACE, GENERIC_CHANNEL_TAG);
        ArrayList<PodCastEpisode> retrievedEpisodes = new ArrayList<>();
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String entityName = parser.getName();
            if (entityName.equalsIgnoreCase(GENERIC_COPYRIGHT_TAG) && this.copyright == null) {
                this.copyright = parseCopyright(parser);
            } else if (entityName.equalsIgnoreCase(GENERIC_SUMMARY_TAG) && this.summary == null) {
                this.summary = parseChannelSummary(parser);
            } else if (entityName.equalsIgnoreCase(GENERIC_LINK_TAG) && this.link == null) {
                this.link = parseLink(parser);
            } else if (entityName.equalsIgnoreCase(GENERIC_ITEM_TAG)) {
                retrievedEpisodes.add(PodCastEpisodeFactory.createPodCastEpisode(PodCastEpisodeFactory.PODCAST_FROM_INTERNET, getFullMessage(), parser));
            } else {
                avoidTag(parser);
            }
        }
        this.parsedEpisodes = retrievedEpisodes;
    }

    public List<PodCastEpisode> getParsedEpisodes() {
        return this.parsedEpisodes;
    }



    private String parseCopyright(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, GENERAL_NAMESPACE, GENERIC_COPYRIGHT_TAG);
        String title = readGenericText(parser);
        parser.require(XmlPullParser.END_TAG, GENERAL_NAMESPACE, GENERIC_COPYRIGHT_TAG);
        return title;

    }

    private String parseChannelSummary(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, GENERAL_NAMESPACE, GENERIC_SUMMARY_TAG);
        String summary = readGenericText(parser);
        parser.require(XmlPullParser.END_TAG, GENERAL_NAMESPACE, GENERIC_SUMMARY_TAG);
        return summary;
    }

    private String parseLink(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, GENERAL_NAMESPACE, GENERIC_LINK_TAG);
        String link = readGenericText(parser);
        parser.require(XmlPullParser.END_TAG, GENERAL_NAMESPACE, GENERIC_LINK_TAG);
        return link;
    }

    private String readGenericText(XmlPullParser parser) throws XmlPullParserException, IOException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;

    }

    private void avoidTag(XmlPullParser parser) throws XmlPullParserException, IOException {
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

    @Override
    public String getFullMessage() {
        return super.getFullMessage();
    }


    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
