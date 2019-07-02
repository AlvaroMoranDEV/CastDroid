package com.alvaromoran.podcasts.services.connections.templates;

import com.alvaromoran.podcasts.services.connections.templates.iTunes.JsonITunesRoot;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ITunesMessage implements MessageContainer {

    private String fullMessage;

    private JsonITunesRoot parsedMessage;

    public ITunesMessage() {

    }


    @Override
    public void setFullMessage(String fullMessage) {
        this.fullMessage = fullMessage;
    }


    @Override
    public int getNumberOfItems() {
        return this.parsedMessage.resultCount;
    }

    @Override
    public String getFullMessage() {
        return this.fullMessage;
    }

    @Override
    public Object parseMessage() {
        if (this.parsedMessage != null) {
            return this.parsedMessage;
        } else {
            // Parses the message
            Gson jsonDeserializer = new GsonBuilder().create();
            this.parsedMessage = jsonDeserializer.fromJson(this.fullMessage, JsonITunesRoot.class);
            return this.parsedMessage;
        }
    }

    @Override
    public Object getSingleResult(int id) {
        return null;
    }
}
