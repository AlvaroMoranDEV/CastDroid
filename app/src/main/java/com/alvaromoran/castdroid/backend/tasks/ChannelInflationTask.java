package com.alvaromoran.castdroid.backend.tasks;

import android.os.AsyncTask;

import com.alvaromoran.CastDroidStoreDAO;
import com.alvaromoran.PodCastsDAO;
import com.alvaromoran.castdroid.backend.factories.ChannelFactory;
import com.alvaromoran.castdroid.fragments.ChannelInformationFragment;

import java.util.Map;
import java.util.function.Function;

/**
 * Fills a selected channel with detailed information and the list of episodes
 */
public class ChannelInflationTask extends AsyncTask<Object, Void, Map<Integer, Object>> {

    /**
     * DAO that provides the channel information
     */
    private PodCastsDAO podCastsDAO;

    /**
     * Fragment to be populated with information in the UI thread
     */
    private ChannelInformationFragment referredObject;

    /**
     * Constructor of the class
     */
    public ChannelInflationTask() {
        this.podCastsDAO = new CastDroidStoreDAO();
    }

    /**
     *  Performs the operation of getting channel information in the background
     * @param arguments URL and method to be used to update information
     * @return same UI fragment to be updated in UI thread once the operation is finished
     */
    @Override
    protected Map<Integer, Object> doInBackground(Object... arguments) {
        this.referredObject = (ChannelInformationFragment) arguments[0];
        Map<Integer, Object> information = this.podCastsDAO.getEnrichedChannelInformation(
                this.referredObject.getChannelInformation().getChannelUrl(), true);
        return information;
    }

    /**
     * Updating channel information in the UI thread with information
     * @param information information to be used by the UI thread
     */
    @Override
    protected void onPostExecute(Map<Integer, Object> information) {
        ChannelFactory.channelPopulationWithAdditionalInfo(this.referredObject.getChannelInformation(), information);
        this.referredObject.populateChannelInformation();
    }
}
