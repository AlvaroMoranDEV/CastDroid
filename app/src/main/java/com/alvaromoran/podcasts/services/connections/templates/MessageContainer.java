package com.alvaromoran.podcasts.services.connections.templates;

public abstract class MessageContainer {

    private final String fullMessage;

    public MessageContainer(String fullMessage) {
        this.fullMessage = fullMessage;
    }

    public String getFullMessage() {
        return this.fullMessage;
    }

    public abstract int getNumberOfItems();

    public abstract Object parseMessage();
}
