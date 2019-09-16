package com.alvaromoran.podcasts.services.dataAccess;

import com.alvaromoran.podcasts.models.PodCastChannel;
import com.alvaromoran.podcasts.services.dataAccess.connections.TaskWebExecutor;

/**
 * Class in charge to manage the operations to get information from the net or from the internal
 * storage system
 *
 * This class is implemented following the singleton pattern with lazy initialization, since only
 * the graphic thread will be accessing this class (no thread-safe initialization needed)
 */
public class PodCastInfoDAO implements IChannelPodCasts{

    //region Class variables

    private TaskWebExecutor taskWebExecutor;

    //endregion

    //region Constructor

    /** Single instance of the class */
    private static PodCastInfoDAO singletonInstance;

    /**
     * Private constructor of the class to avoid external access
     */
    private PodCastInfoDAO() {

    }

    /**
     * Instance management - lazy singleton implementation
     * @return class instance
     */
    public static PodCastInfoDAO getInstance() {
        if (singletonInstance == null) {
            singletonInstance= new PodCastInfoDAO();
        }
        return singletonInstance;
    }

    //endregion

    @Override
    public void addChannelQueryParameter(PodCastChannel channelToBeFilled, String... parameters) {
        checkOrCreatePodCastTask(channelToBeFilled);
        this.taskWebExecutor.addConnectionParameter(parameters);
    }


    @Override
    public void performTaskOverChannel(PodCastChannel channelToBeFilled) {
        checkOrCreatePodCastTask(channelToBeFilled);
        addChannelQueryParameter(channelToBeFilled, channelToBeFilled.getFeedUrl());
        this.taskWebExecutor.execute();
    }

    @Override
    public void addITunesQueryParameter(String... parameters) {
        checkOrCreateITunesTask();
        this.taskWebExecutor.addConnectionParameter(parameters);
    }

    @Override
    public void removeITunesQueryParameter(String parameter) {
        checkOrCreateITunesTask();
        this.taskWebExecutor.removeConnectionParameter(parameter);
    }

    @Override
    public void performTaskOverITunes() {
        checkOrCreateITunesTask();
        this.taskWebExecutor.execute();
    }


    private void checkOrCreateITunesTask() {
        if (this.taskWebExecutor == null || this.taskWebExecutor.isTaskExecuted()) {
            TaskParameters newTask = new TaskParameters(TaskWebExecutor.CONNECTION_ITUNES);
            this.taskWebExecutor = new TaskWebExecutor(newTask);
        }
    }

    private void checkOrCreatePodCastTask(PodCastChannel parentChannel) {
        if (this.taskWebExecutor == null || this.taskWebExecutor.isTaskExecuted()) {
            TaskParameters newTask = new TaskParameters(TaskWebExecutor.CONNECTION_PROVIDER);
            newTask.setSelectedChannel(parentChannel);
            this.taskWebExecutor = new TaskWebExecutor(newTask);
        }
    }
}
