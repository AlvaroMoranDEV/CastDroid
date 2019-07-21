package com.alvaromoran.podcasts.models.graphicInterface;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.alvaromoran.podcasts.models.common.PodCastChannel;

/**
 * Stores all information to be shown to the user as an GUI element about a PodCast channel
 */
public class ChannelSingleItem {

    /** Full channel information */
    private PodCastChannel relatedChannel;

    /** Channel title */
    private TextView channelTitle;

    /** Channel description */
    private TextView channelDescription;

    /** Channel Image */
    private ImageView channelImage;

    /**
     * Constructor of the class
     * @param relatedChannel full channel informatiion reference
     * @param channelTitle channel title
     * @param channelDescription channel description
     * @param channelImage channel image
     */
    public ChannelSingleItem(PodCastChannel relatedChannel, TextView channelTitle, TextView channelDescription, ImageView channelImage) {
        this.relatedChannel = relatedChannel;
        this.channelTitle = channelTitle;
        this.channelDescription = channelDescription;
        this.channelImage = channelImage;
    }

    public PodCastChannel getRelatedChannel() {
        return relatedChannel;
    }

    public TextView getChannelTitle() {
        return channelTitle;
    }

    public TextView getChannelDescription() {
        return channelDescription;
    }

    public ImageView getChannelImage() {
        return channelImage;
    }
}
