package com.alvaromoran.podcasts.services.connections.templates;

import com.alvaromoran.podcasts.services.connections.templates.iTunes.JsonITunesRoot;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ITunesMessage extends MessageContainer {

    private JsonITunesRoot parsedMessage;

    public ITunesMessage(String fullMessage) {
        super(fullMessage);
    }

    @Override
    public int getNumberOfItems() {
        return this.parsedMessage.resultCount;
    }

    @Override
    public Object parseMessage() {
        if (this.parsedMessage != null) {
            return this.parsedMessage;
        } else {
            // Parses the message
            Gson jsonDeserializer = new GsonBuilder().create();
            this.parsedMessage = jsonDeserializer.fromJson(getFullMessage(), JsonITunesRoot.class);
            return this.parsedMessage;
        }
    }
}
