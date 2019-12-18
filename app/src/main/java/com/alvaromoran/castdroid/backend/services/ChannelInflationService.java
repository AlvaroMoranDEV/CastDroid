package com.alvaromoran.castdroid.backend.services;

import android.os.AsyncTask;

import com.alvaromoran.CastDroidStoreDAO;
import com.alvaromoran.PodCastsDAO;
import com.alvaromoran.castdroid.fragments.ChannelInformationFragment;

public class ChannelInflationService extends AsyncTask<ChannelInformationFragment, Void, ChannelInformationFragment> {

    private PodCastsDAO podCastsDAO;

    public ChannelInflationService() {
        this.podCastsDAO = new CastDroidStoreDAO();
    }

    @Override
    protected ChannelInformationFragment doInBackground(ChannelInformationFragment... channelInformation) {
        this.podCastsDAO.getEnrichedChannelInformation(channelInformation[0].getChannelInfo());
        return channelInformation[0];
    }

    @Override
    protected void onPostExecute(ChannelInformationFragment channelInformation) {
        channelInformation.populateChannelInformation();
    }

}
