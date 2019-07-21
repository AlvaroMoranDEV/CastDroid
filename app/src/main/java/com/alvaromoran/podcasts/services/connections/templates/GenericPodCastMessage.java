package com.alvaromoran.podcasts.services.connections.templates;

import com.alvaromoran.podcasts.services.connections.templates.generic.XmlRoot;

public class GenericPodCastMessage implements MessageContainer {

    private XmlRoot parsedMessage;

    private String fullMessage;

    @Override
    public void setFullMessage(String fullMessage) {
    this.fullMessage = fullMessage;
    }

    @Override
    public int getNumberOfItems() {
        return 0;
    }

    @Override
    public String getFullMessage() {
        return null;
    }

    @Override
    public Object parseMessage() {
        return null;
    }

    @Override
    public Object getSingleResult(int id) {
        return null;
    }
}
