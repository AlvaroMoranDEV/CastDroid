package com.alvaromoran.castdroid.backend.helpers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadImageInTask extends AsyncTask<String, Void, Bitmap> {

    private ImageView referredImage;

    public DownloadImageInTask(ImageView imageReference) {
        this.referredImage = imageReference;
    }

    @Override
    protected Bitmap doInBackground(String... urls) {
        String urlToFetch = urls[0];
        Bitmap image = null;
        try {
            InputStream input = new URL(urlToFetch).openStream();
            image = BitmapFactory.decodeStream(input);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    protected void onPostExecute(Bitmap image) {
        this.referredImage.setImageBitmap(image);
    }
}
