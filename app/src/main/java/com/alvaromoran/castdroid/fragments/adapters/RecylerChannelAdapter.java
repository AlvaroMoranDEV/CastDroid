package com.alvaromoran.castdroid.fragments.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alvaromoran.castdroid.R;
import com.alvaromoran.castdroid.backend.tasks.UrlToImageTask;
import com.alvaromoran.castdroid.fragments.ChannelInformationFragment;
import com.alvaromoran.castdroid.models.Channel;
import com.alvaromoran.data.ChannelInformation;

import java.util.List;

public class RecylerChannelAdapter extends RecyclerView.Adapter<RecylerChannelAdapter.ChannelViewHolder>{

    private LayoutInflater inflater;
    private List<Channel> channelsList;
    private FragmentManager fragmentManager;

    public RecylerChannelAdapter(Context context, List<Channel> channelsList, FragmentManager fragmentManager) {
        this.inflater = LayoutInflater.from(context);
        this.channelsList = channelsList;
        this.fragmentManager = fragmentManager;
     }

    @Override
    public ChannelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_channels, parent, false);
        return new ChannelViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return this.channelsList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final ChannelViewHolder holder, int position) {
        holder.setFullInformation(this.channelsList.get(position));
        holder.channelAuthor.setText(this.channelsList.get(position).getChannelAuthor());
        holder.channelName.setText(this.channelsList.get(position).getChannelTitle());
        UrlToImageTask imageCreationHelper = new UrlToImageTask( holder.channelImage);
        imageCreationHelper.execute(this.channelsList.get(position).getImageUrl());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = ChannelInformationFragment.newInstance(holder.channelInformation);
                fragmentManager.beginTransaction().replace(R.id.application_frame, fragment).addToBackStack(null).commit();
            }
        });
    }

    public void updateData(List<Channel> channelsList) {
        this.channelsList.clear();
        this.channelsList.addAll(channelsList);
        this.notifyDataSetChanged();
    }

    protected static class ChannelViewHolder extends RecyclerView.ViewHolder {
        TextView channelName;
        TextView channelAuthor;
        ImageView channelImage;
        Channel channelInformation;

        public ChannelViewHolder(@NonNull View itemView) {
            super(itemView);
            this.channelName = itemView.findViewById(R.id.cardChannelTitle);
            this.channelAuthor =  itemView.findViewById(R.id.cardChannelAuthor);
            this.channelImage = itemView.findViewById(R.id.cardChannelImage);
        }

        public void setFullInformation(Channel channelInformation) {
            this.channelInformation = channelInformation;
        }
    }



}
