package com.alvaromoran.castdroid.backend.factories;

import com.alvaromoran.castdroid.models.Channel;
import com.alvaromoran.castdroid.models.Episode;
import com.alvaromoran.constants.ChannelAndEpisodesMapArguments;
import com.alvaromoran.data.ChannelInformation;
import com.alvaromoran.data.SingleEpisode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ChannelFactory {

    private static final String CATEGORY_PODCASTS = "Podcasts";

    public static Channel channelCreationFromDTO(ChannelInformation channelInformation) {
        if (channelInformation != null) {
            Channel channel = new Channel();
            // Fill object properties
            channel.setChannelTitle(channelInformation.getCollection());
            channel.setCategories(generateCategoriesList(channelInformation.getCategories()));
            channel.setImageUrl(channelInformation.getImageUrlHigh());
            channel.setChannelAuthor(channelInformation.getAuthor());
            channel.setChannelUrl(channelInformation.getFeedUrl());
            return channel;
        } else {
            return null;
        }
    }

    public static void channelPopulationWithAdditionalInfo(Channel channel, Map<Integer, Object> additionalInfo) {
        // Retrieve additional information
        String author = (String)additionalInfo.get(ChannelAndEpisodesMapArguments.CHANNEL_AUTHOR);
        String description =  (String)additionalInfo.get(ChannelAndEpisodesMapArguments.CHANNEL_DESCRIPTION);
        String link =  (String)additionalInfo.get(ChannelAndEpisodesMapArguments.CHANNEL_LINK);

        List<Episode> episodes = new ArrayList<>();
        // Episodes construction
        List<SingleEpisode> episodesListNotParsed =
                (List<SingleEpisode>)additionalInfo.get(ChannelAndEpisodesMapArguments.CHANNEL_EPISODES_LIST);
        // Transform each episode from its DTO to its app model form
        if (episodesListNotParsed != null) {
            episodesListNotParsed.forEach( notParsedEpisode -> {
                episodes.add(EpisodeFactory.episodeCreationFromDTO(notParsedEpisode));
            });
        }
        // Population of the final channel object
        channel.setChannelAuthor(author);
        channel.setChannelDescription(description);
        channel.setChannelContactUrl(link);
        channel.setEpisodes(episodes);
    }

    private static List<String> generateCategoriesList(Collection<String> categoriesNotParsed) {
        List<String> finalCategories = new ArrayList<>();
        if (categoriesNotParsed != null) {
            categoriesNotParsed.forEach(category -> {
                if (!category.equalsIgnoreCase(CATEGORY_PODCASTS)) {
                    finalCategories.add(category);
                }
            });
        }
        return finalCategories;
    }

}
