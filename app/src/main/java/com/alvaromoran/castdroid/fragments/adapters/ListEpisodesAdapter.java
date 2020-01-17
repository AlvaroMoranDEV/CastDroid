package com.alvaromoran.castdroid.fragments.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alvaromoran.castdroid.R;
import com.alvaromoran.castdroid.backend.tasks.UrlToImageTask;
import com.alvaromoran.castdroid.models.Episode;

import java.util.List;

public class ListEpisodesAdapter extends ArrayAdapter<Episode> implements View.OnClickListener {

    private List<Episode> episodesList;

    private int lastSelectedPosition = -1;

    public ListEpisodesAdapter(Context context, List<Episode> episodesList) {
        super(context, R.layout.listview_episodes, episodesList);
        this.episodesList = episodesList;
    }

    @Override
    public void onClick(View clickedView) {
        int position = (Integer) clickedView.getTag();
        Episode selectedEpisode = getItem(position);
        // TODO
        System.out.println("Selected episode: " + selectedEpisode.getTitle());
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        final View result;
        // Data for current view
        Episode episodeView = getItem(position);
        // Check if view already exists or inflate it otherwise
        EpisodeViewHolder episodeViewHolder;

        // Reference to the graphic elements
        if (convertView == null) {
            episodeViewHolder = new EpisodeViewHolder();
            // Inflate episode view holder
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listview_episodes, parent, false);
            episodeViewHolder.episodeTitle = convertView.findViewById(R.id.episodeListTitle);
            episodeViewHolder.episodePubDate = convertView.findViewById(R.id.episodeListPubDate);
            episodeViewHolder.episodeImage = convertView.findViewById(R.id.episodeListImage);
            convertView.setTag(episodeViewHolder);
            result = convertView;
        } else {
            episodeViewHolder = (EpisodeViewHolder) convertView.getTag();
            result = convertView;
        }

        lastSelectedPosition = position;
        // Populate graphic elements referenced
        episodeViewHolder.episodeTitle.setText(episodeView.getTitle());
        episodeViewHolder.episodePubDate.setText(episodeView.getPubDate().toString());
        UrlToImageTask imageCreationHelper = new UrlToImageTask(episodeViewHolder.episodeImage);
        return result;
    }

    protected static class EpisodeViewHolder {
        ImageView episodeImage;
        TextView episodeTitle;
        TextView episodePubDate;
    }
}
