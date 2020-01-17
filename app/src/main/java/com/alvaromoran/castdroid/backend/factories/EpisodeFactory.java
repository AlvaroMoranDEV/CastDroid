package com.alvaromoran.castdroid.backend.factories;

import com.alvaromoran.castdroid.models.Episode;
import com.alvaromoran.data.SingleEpisode;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EpisodeFactory {

    private static final int SECONDS_IN_HOUR = 3600;

    private static final int SECONDS_IN_MINUTE = 60;

    private static final int BASIC_FORMAT = 2;

    private static final int EXTENDED_FORMAT = 3;

    private static final String DEFAULT_PUBDATE_FORMAT = "DD MMM YYYY HH:mm:ss";

    private static final String COMMA_SEPARATOR = ",";

    private static final String SPACE_SEPARATOR = " ";


    public static Episode episodeCreationFromDTO(SingleEpisode episodeInformation) {
        if (episodeInformation != null) {
            Episode episode = new Episode();
            episode.setTitle(episodeInformation.getTitle());
            episode.setAudioUrl(episodeInformation.getAudioInformation().getAudioUrl());
            episode.setDurationSecs(parseDurationIntoSecs(episodeInformation.getEpisodeDuration()));
            episode.setDurationString(episodeInformation.getEpisodeDuration());
            episode.setImageUlr(episodeInformation.getImageUrl());
            episode.setPubDate(createDateFromString(episodeInformation.getReleaseDate()));
            episode.setSubTitle(episodeInformation.getSubTitle());
            episode.setSummary(episodeInformation.getSummary());
            return episode;
        } else {
            return null;
        }
    }

    private static int parseDurationIntoSecs(String duration) {
        int seconds = 0;
        // Check if duration is parsed in HH:mm:ss or mm:ss
        String[] tokenizedDuration = duration.split(":");
        try {
            if (tokenizedDuration.length == EXTENDED_FORMAT) {
                // Expected format: HH:mm:ss
                seconds += Integer.parseInt(tokenizedDuration[0]) * SECONDS_IN_HOUR;
                seconds += Integer.parseInt(tokenizedDuration[1]) * SECONDS_IN_MINUTE;
                seconds += Integer.parseInt(tokenizedDuration[2]);

            } else if (tokenizedDuration.length == BASIC_FORMAT) {
                // Expected format: mm:ss
                seconds += Integer.parseInt(tokenizedDuration[0]) * SECONDS_IN_MINUTE;
                seconds += Integer.parseInt(tokenizedDuration[1]);
            }
        } catch (NumberFormatException ex) {
            seconds = 0;
        }
        return seconds;
    }

    private static Date createDateFromString(String notParsedString) {
        // Day literal is not needed
        if (notParsedString.indexOf(COMMA_SEPARATOR) != -1) {
            notParsedString =  notParsedString.substring(notParsedString.indexOf(COMMA_SEPARATOR) + 1, notParsedString.length());
        }
        // GMT is not needed
        if (notParsedString.lastIndexOf(SPACE_SEPARATOR) != -1) {
            notParsedString =  notParsedString.substring(0, notParsedString.lastIndexOf(SPACE_SEPARATOR));
        }
        DateFormat formatter = new SimpleDateFormat(DEFAULT_PUBDATE_FORMAT);
        try {
            return formatter.parse(notParsedString);
        } catch (ParseException e) {
            return null;
        }
    }

}
