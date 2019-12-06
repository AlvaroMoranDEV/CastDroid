package com.alvaromoran.castdroid.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alvaromoran.castdroid.R;
import com.alvaromoran.castdroid.backend.helpers.DownloadImageInTask;
import com.alvaromoran.castdroid.backend.services.ChannelInflationService;
import com.alvaromoran.castdroid.backend.services.ChannelPodCastsUpdates;
import com.alvaromoran.castdroid.backend.services.RecommendedChannels;

import java.util.ArrayList;


public class ChannelInformation extends Fragment {

    private com.alvaromoran.data.ChannelInformation information;

    private ImageView mainChannelImage;
    private TextView mainChannelTitle;
    private TextView mainChannelAuthor;
    private TextView mainChannelCopyright;
    private TextView mainChannelCategories;
    private TextView mainChannelDescription;

    private ChannelInflationService channelInflationService;

    public ChannelInformation(com.alvaromoran.data.ChannelInformation information) {
        this.information = information;
        this.channelInflationService = new ChannelInflationService();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_channel_information, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Create view
        super.onViewCreated(view, savedInstanceState);

        // Populate channel with extra info
        this.channelInflationService.populateChannel(this.information);

        // Image populate
        this.mainChannelImage = view.findViewById(R.id.mainChannelImage);
        DownloadImageInTask imageCreationHelper = new DownloadImageInTask(this.mainChannelImage);
        imageCreationHelper.execute(this.information.getImageUrlHigh());

        // Text populate
        this.mainChannelTitle = view.findViewById(R.id.mainChannelTitle);
        this.mainChannelAuthor = view.findViewById(R.id.mainChannelAuthor);
        this.mainChannelCopyright = view.findViewById(R.id.mainChannelCopyright);
        this.mainChannelCategories = view.findViewById(R.id.mainChannelCategories);
        this.mainChannelDescription = view.findViewById(R.id.mainChannelDescription);

        this.mainChannelTitle.setText( this.information.getCollection());
        this.mainChannelAuthor.setText( this.information.getAuthor());
        this.mainChannelCopyright.setText( this.information.getCopyright());
        this.mainChannelCategories.setText( this.information.getCategories().toString());
        this.mainChannelDescription.setText( this.information.getDescription());

        // Episodes population
        //mainEpisodesList
    }

    private void populateChannelInformation() {

    }


}
