package com.alvaromoran.podcasts.ui.discover;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.alvaromoran.podcasts.models.PodCastChannel;
import com.alvaromoran.podcasts.services.dataAccess.IChannelPodCasts;
import com.alvaromoran.podcasts.services.dataAccess.PodCastInfoDAO;

import java.util.List;

public class DiscoverViewModel extends AndroidViewModel {

    @NonNull
    IChannelPodCasts informationProvider;

    @NonNull
    MutableLiveData<List<PodCastChannel>> podCastChannelListForUI;

    public DiscoverViewModel(@NonNull Application application) {
        super(application);
        this.informationProvider = PodCastInfoDAO.getInstance();
        this.podCastChannelListForUI = new MutableLiveData<>();
    }

    @NonNull
    public LiveData<List<PodCastChannel>> getPodCastChannelListForUI() {
        return podCastChannelListForUI;
    }

    public void userActionSearchTriggered(String text) {

        this.informationProvider.addITunesQueryParameter(text);
        this.informationProvider.performTaskOverITunes();
    }

}