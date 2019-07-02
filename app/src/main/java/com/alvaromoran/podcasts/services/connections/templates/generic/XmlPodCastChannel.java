package com.alvaromoran.podcasts.services.connections.templates.generic;

import com.alvaromoran.podcasts.services.connections.templates.MessageContainer;

public class XmlPodCastChannel implements MessageContainer {

    @Override
    public void setFullMessage(String fullMessage) {

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
