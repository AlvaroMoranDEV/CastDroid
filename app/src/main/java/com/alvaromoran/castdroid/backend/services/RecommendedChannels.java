package com.alvaromoran.castdroid.backend.services;

import android.os.AsyncTask;

import com.alvaromoran.CastDroidStoreDAO;
import com.alvaromoran.PodCastsDAO;
import com.alvaromoran.castdroid.fragments.adapters.RecylerChannelAdapter;
import com.alvaromoran.data.ChannelInformation;

import java.util.List;

public class RecommendedChannels  extends AsyncTask<RecylerChannelAdapter, Void, Void> {

    private PodCastsDAO podCastsDAO;

    private RecylerChannelAdapter referredUIAdapter;
    private List<ChannelInformation> returnedList;

    public RecommendedChannels() {
        this.podCastsDAO = new CastDroidStoreDAO();
    }

    @Override
    protected Void doInBackground(RecylerChannelAdapter... lists) {
        this.referredUIAdapter = lists[0];
        this.podCastsDAO.setAutoQueryChannelsOption(true);
        this.returnedList = this.podCastsDAO.updateTermSearchParameter("historia");
        return null;
    }

    @Override
    protected void onPostExecute(Void args) {
        referredUIAdapter.updateData(this.returnedList);
    }
}
