package com.alvaromoran.podcasts.services.dataAccess.connections;

import android.os.AsyncTask;
import android.util.Log;

import com.alvaromoran.podcasts.models.PodCastChannel;
import com.alvaromoran.podcasts.services.dataAccess.TaskParameters;
import com.alvaromoran.podcasts.services.dataAccess.TaskReturn;
import com.alvaromoran.podcasts.services.dataAccess.data.GenericPodCastMessage;
import com.alvaromoran.podcasts.services.dataAccess.data.ITunesMessage;
import com.alvaromoran.podcasts.services.dataAccess.data.MessageContainer;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.stream.Collectors;

public class TaskWebExecutor extends AsyncTask<Void, Integer, TaskReturn> {

    public static int CONNECTION_ITUNES = 0;

    public static int CONNECTION_PROVIDER = 1;

    private static int CONNECTION_UNDEF = -1;

    private boolean taskExecuted = false;

    private int connectionType = CONNECTION_UNDEF;

    private String connectionUrl;

    private StringBuilder connectionUrlBuilder;

    /** Parameters used for the query over ITunes */
    private HashMap<String, String> parameters;

    private MessageContainer receivedMessage;

    private PodCastChannel channelToBeFilled;


    public TaskWebExecutor(TaskParameters taskParameters) {
        if (taskParameters != null) {
            this.connectionType = taskParameters.getTypeOfQuery();
            if (this.connectionType == CONNECTION_ITUNES) {
                this.connectionUrlBuilder = new StringBuilder();
                this.parameters = new HashMap<>();
            } else if (this.connectionType == CONNECTION_PROVIDER) {
                this.channelToBeFilled = taskParameters.getSelectedChannel();
            }
        }
    }

    public void addConnectionParameter(String... parameters) {
        if (this.connectionType == CONNECTION_ITUNES) {
            if (parameters != null && parameters.length == 1 && !parameters[0].equalsIgnoreCase("")) {
                this.parameters.put(ConnectionsConstants.ITunesSearchKeys.TERM.toString(), parameters[0]);
            } else if (parameters != null  && parameters.length == 2 && checkCorrectSearchParameter(parameters[0])) {
                this.parameters.put(parameters[0], parameters[1]);
            }
        } else if (this.connectionType == CONNECTION_PROVIDER){
            if (parameters != null && parameters[0] != null) {
                this.connectionUrl = parameters[0];
            }
        }
    }

    public void removeConnectionParameter(String parameter) {
        if (this.connectionType == CONNECTION_ITUNES) {
            if (parameter != null && parameter.equalsIgnoreCase("") && checkCorrectSearchParameter(parameter)) {
                this.parameters.remove(parameter);
            } else if (parameter == null) {
                cleanQuery();
            }
        } else if (this.connectionType == CONNECTION_PROVIDER) {
            if (parameter == null) {
                this.connectionUrl = null;
            }
        }
    }

    private void cleanQuery() {
        this.parameters.clear();
        this.connectionUrlBuilder = new StringBuilder();
    }

    public boolean isTaskExecuted() {
        return taskExecuted;
    }

    public MessageContainer getReceivedMessage() {
        return receivedMessage;
    }

     @Override
    protected TaskReturn doInBackground(Void... parameters) {
        HttpURLConnection serverConnection = null;
        String result = null;
        boolean validResult = false;
        String fullStringQuery = null;

        // Final URL creation
        if (this.connectionType == CONNECTION_ITUNES) {
             fullStringQuery = createFullQuery();
        } else if (this.connectionType == CONNECTION_PROVIDER){
             fullStringQuery = this.connectionUrl;
        }
        // Checking URL integrity
        if (checkConnectionUrl(fullStringQuery)) {
             try {
                 // Http URL connection with the server
                 URL fullUrlQuery = new URL(fullStringQuery);
                 serverConnection = (HttpURLConnection) fullUrlQuery.openConnection();
                 serverConnection.setRequestMethod("GET");
                 // Buffer to read the answer
                 InputStream in = new BufferedInputStream(serverConnection.getInputStream());
                 result = new BufferedReader(new InputStreamReader(in))
                         .lines().collect(Collectors.joining("\n"));
                 validResult = true;

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
        }

        // Message parsing
        if (result != null) {
            if (this.connectionType == CONNECTION_ITUNES) {
                this.receivedMessage = new ITunesMessage(result);
                this.receivedMessage.parseMessage();
            } else if (this.connectionType == CONNECTION_PROVIDER){
                this.receivedMessage = new GenericPodCastMessage(result);
                this.receivedMessage.parseMessage();
            }
        }

        // Task object creation with needed information
        TaskReturn taskReturn = new TaskReturn(validResult);
        return taskReturn;
    }

    @Override
    protected void onPostExecute(TaskReturn taskReturn) {
        this.taskExecuted = true;
        // Populate the UI elements
        if (taskReturn.isTaskResult()) {
            if (this.connectionType == CONNECTION_ITUNES) {d

            } else if (this.connectionType == CONNECTION_PROVIDER && this.receivedMessage != null) {
                // Fill episodes and extra channel information
                GenericPodCastMessage messageParsed = (GenericPodCastMessage) this.receivedMessage;
                this.channelToBeFilled.setChannelEpisodes(messageParsed.getParsedEpisodes());
                this.channelToBeFilled.setCopyRight(messageParsed.getCopyright());
                this.channelToBeFilled.setLink(messageParsed.getLink());
                this.channelToBeFilled.setSummary(messageParsed.getSummary());
            }
        }
    }

    private boolean checkConnectionUrl(String url) {
        boolean validUrl;
        try {
            new URL(url).toURI();
            validUrl = true;
        } catch(Exception e) {
            Log.e("checkConnectionUrl", "URL is not correct when trying to perform a query over channel - URL: " + url);
            validUrl = false;
        }
        return validUrl;
    }

    private boolean checkCorrectSearchParameter(String parameterToCheck) {
        boolean validParameter = false;
        for (ConnectionsConstants.ITunesSearchKeys key : ConnectionsConstants.ITunesSearchKeys.values()) {
            if (key.name().equalsIgnoreCase(parameterToCheck)) {
                validParameter = true;
            }
        }
        return validParameter;
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
            this.connectionUrlBuilder.append(ConnectionsConstants.MAIN_ITUNES_URL);
            // Add each parameter to the query
            this.parameters.forEach((key, value) ->
                    this.connectionUrlBuilder.append(key)
                            .append(ConnectionsConstants.ITUNES_PARAMETER_EQUAL)
                            .append(value)
                            .append(ConnectionsConstants.ITUNES_PARAMETER_SEPARATION));

            // Remove the last ITunes parameter separation
            this.connectionUrlBuilder.setLength(this.connectionUrlBuilder.length() - 1);
            queryUrl = this.connectionUrlBuilder.toString();
        }
        return queryUrl;
    }

}
