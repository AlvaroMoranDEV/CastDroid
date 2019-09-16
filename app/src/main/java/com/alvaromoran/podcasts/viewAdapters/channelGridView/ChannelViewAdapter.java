package com.alvaromoran.podcasts.viewAdapters.channelGridView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.alvaromoran.podcasts.R;
import com.alvaromoran.podcasts.models.PodCastChannel;
import com.alvaromoran.podcasts.services.dataAccess.connections.GenericImageLoader;

import java.util.List;

public class ChannelViewAdapter extends BaseAdapter {

    private List<PodCastChannel> channelsInformation;

    private GridView channelGridView = null;

    Context context;

    public ChannelViewAdapter(Context mainActivity, List<PodCastChannel> channelsInformation) {
        this.channelsInformation = channelsInformation;
        this.context = mainActivity;
    }


    @Override
    public int getCount() {
        return this.channelsInformation.size();
    }

    @Override
    public Object getItem(int position) {
        return this.channelsInformation.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final PodCastChannel podcastChannel = this.channelsInformation.get(position);

        if (convertView == null) {
            final LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(R.layout.channel_list_grid, null);
        }

        final ImageView channelImage = convertView.findViewById(R.id.channel_image);
        final TextView channelTitle = convertView.findViewById(R.id.channel_title);
        final TextView channelDescription = convertView.findViewById(R.id.channel_description);

        GenericImageLoader imageLoader = new GenericImageLoader(channelImage);
        imageLoader.execute(podcastChannel.getUrlImage());
        channelTitle.setText(podcastChannel.getChannelName());
        channelDescription.setText(podcastChannel.getChannelBriefDescription());

        return convertView;
    }




}
