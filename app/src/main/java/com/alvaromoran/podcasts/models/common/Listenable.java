package com.alvaromoran.podcasts.models.common;

/**
 * Defines the interface of the objects that may be suitable for listening, such as
 * PodCasts, AudioBooks and so on
 */
public interface Listenable {

    /**
     * Gets if the audio is currently being played or not
     *
     * @return <code>true</code> if the audio is being listened, <code>false</code> otherwise
     */
    boolean isBeingListened();

    /**
     * Method that sets that the audio is being played
     */
    void startPlaying();

    /**
     * Method that sets that the audio is being stopped
     *
     * @param duration time in seconds when the audio playing was aborted
     */
    void stopPlaying(int duration);

    /**
     * Checks if the current audio has being played at least once in the past
     *
     * @return <code>true</code> if the audio has been played, <code>false</code> otherwise
     */
    boolean hasBeenListened();

    /**
     * Sets the time when the audio playing was aborted
     *
     * @param whenLeftOf time in seconds when the audio playing was aborted
     */
    void setWhenLeftOf(int whenLeftOf);

}
