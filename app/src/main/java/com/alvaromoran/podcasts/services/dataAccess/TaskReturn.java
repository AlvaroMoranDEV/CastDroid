package com.alvaromoran.podcasts.services.dataAccess;

import com.alvaromoran.podcasts.models.PodCastChannel;
import com.alvaromoran.podcasts.services.dataAccess.data.MessageContainer;

import java.util.List;

public class TaskReturn {

    private List<PodCastChannel> channelsInformation;

    private boolean taskResult;

    public TaskReturn(boolean taskResult) {
        this.taskResult = taskResult;
    }

    public boolean isTaskResult() {
        return taskResult;
    }

    public List<PodCastChannel> getMessageReturned() {
        return this.channelsInformation;
    }

    public void setMessageReturned(List<PodCastChannel> channelsInformation) {
        this.channelsInformation = channelsInformation;
    }

}
