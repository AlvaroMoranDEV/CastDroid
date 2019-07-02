package com.alvaromoran.podcasts.services.connections.templates;

public interface MessageContainer {

    void setFullMessage(String fullMessage);

    int getNumberOfItems();

    String getFullMessage();

    Object parseMessage();

    Object getSingleResult(int id);

}
