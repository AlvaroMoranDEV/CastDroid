package com.alvaromoran.castdroid.backend.tasks;

import android.os.AsyncTask;

import com.alvaromoran.CastDroidStoreDAO;
import com.alvaromoran.PodCastsDAO;
import com.alvaromoran.castdroid.backend.factories.ChannelFactory;
import com.alvaromoran.castdroid.backend.services.UserSettingsService;
import com.alvaromoran.castdroid.fragments.adapters.RecylerChannelAdapter;
import com.alvaromoran.castdroid.models.Channel;
import com.alvaromoran.data.ChannelInformation;

import java.util.ArrayList;
import java.util.List;

public class ChannelUpdatesTask extends AsyncTask<RecylerChannelAdapter, Void, Void> {

    private PodCastsDAO podCastsDAO;

    private RecylerChannelAdapter referredUIAdapter;

    private List<ChannelInformation> returnedList;

    public ChannelUpdatesTask() {
        this.podCastsDAO = new CastDroidStoreDAO();
    }

    @Override
    protected Void doInBackground(RecylerChannelAdapter... lists) {
        this.referredUIAdapter = lists[0];
        this.podCastsDAO.setAutoQueryChannelsOption(true);
        this.returnedList = this.podCastsDAO.updateTermSearchParameter("onda cero");
        return null;
    }

    @Override
    protected void onPostExecute(Void args) {
        List<Channel> channels = new ArrayList<>();
        this.returnedList.forEach(notParsedChannel -> channels.add(ChannelFactory.channelCreationFromDTO(notParsedChannel)));
        this.referredUIAdapter.updateData(channels);
    }
}
