package com.alvaromoran.podcasts.services.connections;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class GenericImageLoader extends AsyncTask<String, Void, Bitmap> {

    private ImageView loadedImage;

    public GenericImageLoader(ImageView loadedImage) {
        this.loadedImage = loadedImage;
    }


    @Override
    protected Bitmap doInBackground(String... urls) {
        String imageUrl = urls[0];
        Bitmap bitImage = null;

        if (imageUrl != null) {
            try {
                InputStream inputStream = new URL(imageUrl).openStream();
                bitImage = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                throw new RuntimeException("Unable to create the input stream when trying to get image information", e);
            } catch (Exception e) {
                throw new RuntimeException("General exception when trying to get image information", e);
            }
        }
        return bitImage;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        this.loadedImage.setImageBitmap(result);
    }
}
