package com.alvaromoran.podcasts.services.connections;

import com.alvaromoran.podcasts.services.connections.templates.ITunesMessage;
import com.alvaromoran.podcasts.services.connections.templates.MessageContainer;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.stream.Collectors;

/**
 * Class used to manage the connection with the ITunes store, perform queries and parse the received
 * information into a class to be used by the rest of the logic
 */
public class ITunesConnection extends Connection {

    public ITunesConnection() {
        super();
    }

    @Override
    public void addDefaultQueryParameter(String parameter) {
        if (parameter != null && !parameter.equalsIgnoreCase("")) {
            this.parameters.put(ConnectionsConstants.ITunesSearchKeys.TERM.toString(), parameter);
        }
    }

    public MessageContainer performQuery() {
        HttpURLConnection serverConnection = null;
        String result = null;
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
            throw new RuntimeException("Error creating a valid URL", e);
        } catch (ProtocolException e) {
            throw new RuntimeException("Protocol exception when trying to set the request method", e);
        } catch (IOException e) {
            throw new RuntimeException("IO Exception when performing the query", e);
        } catch (Exception e) {
            throw new RuntimeException("General exception processing query", e);
        } finally {
            // Close connection
            if (serverConnection != null) {
                serverConnection.disconnect();
            }
        }
        return messageParsing(result);
    }

    private MessageContainer messageParsing(String result) {
        // Parses the returned message into an object
        ITunesMessage itunesMessage = new ITunesMessage();
        itunesMessage.setFullMessage(result);
        itunesMessage.parseMessage();
        storeLastResponse(itunesMessage);
        return itunesMessage;
    }

    /**
     * Method used to create the full query with all the arguments
     *
     * @return full formatted url
     */
    private String createFullQuery() {

        String queryUrl = null;
        // Validations for the needed arguments
        if (this.parameters.get(ConnectionsConstants.ITunesSearchKeys.TERM.toString()) != null &&
                !"".equalsIgnoreCase(this.parameters.get(ConnectionsConstants.ITunesSearchKeys.TERM.toString()))) {

            // Main URL
            this.urlBuilder.append(ConnectionsConstants.MAIN_ITUNES_URL);
            // Add each parameter to the query
            this.parameters.forEach((key, value) ->
                    this.urlBuilder.append(key)
                            .append(ConnectionsConstants.ITUNES_PARAMETER_EQUAL)
                            .append(value)
                            .append(ConnectionsConstants.ITUNES_PARAMETER_SEPARATION));

            // Remove the last ITunes parameter separation
            this.urlBuilder.setLength(this.urlBuilder.length() - 1);
            queryUrl = this.urlBuilder.toString();
        }
        return queryUrl;
    }
}
