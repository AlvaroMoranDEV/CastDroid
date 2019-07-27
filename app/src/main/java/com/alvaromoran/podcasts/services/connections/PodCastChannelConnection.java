package com.alvaromoran.podcasts.services.connections;

import com.alvaromoran.podcasts.services.connections.templates.GenericPodCastMessage;
import com.alvaromoran.podcasts.services.connections.templates.MessageContainer;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.stream.Collectors;

public class PodCastChannelConnection extends Connection {

    public PodCastChannelConnection() {
        super();
    }

    @Override
    public MessageContainer performQuery() {
        HttpURLConnection serverConnection = null;
        String result = null;
        MessageContainer parsedMessage = null;
        try {
            // Http URL connection with the server
            URL fullUrlQuery = new URL(createFullQuery());
            serverConnection = (HttpURLConnection) fullUrlQuery.openConnection();
            serverConnection.setRequestMethod("GET");
            // Buffer to read the answer
            InputStream in = new BufferedInputStream(serverConnection.getInputStream());
            result = new BufferedReader(new InputStreamReader(in))
                    .lines().collect(Collectors.joining("\n"));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            if (serverConnection != null) {
                serverConnection.disconnect();
            }
        }

        // Message parsing
        MessageContainer podcastEpisodes = new GenericPodCastMessage(result);
        podcastEpisodes.parseMessage();
        storeLastResponse(podcastEpisodes);
        return podcastEpisodes;
    }

    @Override
    public void addDefaultQueryParameter(String parameter) {

    }

    public String createFullQuery() {
        return null;
    }

}
