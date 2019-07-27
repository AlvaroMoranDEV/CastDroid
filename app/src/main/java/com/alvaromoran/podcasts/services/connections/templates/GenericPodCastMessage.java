package com.alvaromoran.podcasts.services.connections.templates;

import android.util.Xml;

import com.alvaromoran.podcasts.models.common.PodCastEpisode;
import com.alvaromoran.podcasts.services.connections.ConnectionsConstants;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;


public class GenericPodCastMessage extends MessageContainer {

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
    public String getFullMessage() {
        return getFullMessage();
    }

    @Override
    public Object parseMessage() {

        if (this.parsedEpisodes != null) {
            return this.parsedEpisodes;
        } else {
            try {
                XmlPullParser parser = Xml.newPullParser();
                parser.setFeature(XmlPullParser.FEATURE_REPORT_NAMESPACE_ATTRIBUTES, false);
                parser.setInput(new StringReader(getFullMessage()));
                parser.nextTag();
                parseXmlMessage(parser);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void parseXmlMessage(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, getFullMessage(), ConnectionsConstants.GENERIC_START_TAG);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String entityName = parser.getName();
            if (entityName.equalsIgnoreCase(ConnectionsConstants.GENERIC_RSS_TAG)) {
                parseRss(parser);
            }
        }
    }

    private void parseRss(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, getFullMessage(), ConnectionsConstants.GENERIC_RSS_TAG);
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }
            String entityName = parser.getName();
            if (entityName.equalsIgnoreCase(ConnectionsConstants.GENERIC_CHANNEL_TAG)) {
                parseChannel(parser);
            }
        }
    }

    private void parseChannel(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, getFullMessage(), ConnectionsConstants.GENERIC_CHANNEL_TAG);
        ArrayList<PodCastEpisode> retrievedEpisodes = new ArrayList<>();
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String entityName = parser.getName();
            if (entityName.equalsIgnoreCase(ConnectionsConstants.GENERIC_COPYRIGHT_TAG) && this.copyright != null) {
                this.copyright = parseCopyright(parser);
            } else if (entityName.equalsIgnoreCase(ConnectionsConstants.GENERIC_SUMMARY_TAG) && this.summary != null) {
                this.summary = parseChannelSummary(parser);
            } else if (entityName.equalsIgnoreCase(ConnectionsConstants.GENERIC_LINK_TAG) && this.link != null) {
                this.link = parseLink(parser);
            } else if (entityName.equalsIgnoreCase(ConnectionsConstants.GENERIC_ITEM_TAG)) {
                retrievedEpisodes.add(parseItem(parser));
            }
        }
        this.parsedEpisodes = retrievedEpisodes;
    }



    private String parseCopyright(XmlPullParser parser) throws XmlPullParserException, IOException {
        parser.require(XmlPullParser.START_TAG, getFullMessage(), ConnectionsConstants.GENERIC_COPYRIGHT_TAG);
        String title = readGenericText(parser);
        parser.require(XmlPullParser.END_TAG, getFullMessage(), ConnectionsConstants.GENERIC_COPYRIGHT_TAG);
        return title;

    }

    private String parseChannelSummary(XmlPullParser parser) throws XmlPullParserException, IOException {

        return null;
    }

    private String parseLink(XmlPullParser parser) throws XmlPullParserException, IOException {

        return null;
    }

    private PodCastEpisode parseItem(XmlPullParser parser) throws XmlPullParserException, IOException {

        return null;
    }

    private String readGenericText(XmlPullParser parser) throws XmlPullParserException, IOException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;

    }


}
