package com.alvaromoran.castdroid.backend.tasks;

import android.os.AsyncTask;

import com.alvaromoran.castdroid.R;
import com.alvaromoran.castdroid.backend.services.UserSettingsService;
import com.alvaromoran.castdroid.fragments.adapters.GenresAdapter;
import com.alvaromoran.castdroid.models.Genre;
import com.alvaromoran.constants.ITunesGenreKeys;

import java.util.ArrayList;
import java.util.List;

public class HomeGenresTask extends AsyncTask<GenresAdapter, Void, List<Genre>> {

    private UserSettingsService userSettings;

    private GenresAdapter genresAdapter;

    public HomeGenresTask() {
    }

    // TODO for the moment placeholder here until the library supports it
    private List<Genre> getUserGenresTEST() {
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre(1, ITunesGenreKeys.SEARCH_GENRE_ARTS.toString(), ITunesGenreKeys.SEARCH_GENRE_ARTS.toString(), R.drawable.bg_comedy));
        genres.add(new Genre(2, ITunesGenreKeys.SEARCH_GENRE_GOVERNMENT.toString(), ITunesGenreKeys.SEARCH_GENRE_GOVERNMENT.toString(), R.drawable.bg_games));
        genres.add(new Genre(3, ITunesGenreKeys.SEARCH_GENRE_GAMES.toString(), ITunesGenreKeys.SEARCH_GENRE_GAMES.toString(), R.drawable.bg_news));
        return genres;
    }

    @Override
    protected List<Genre> doInBackground(GenresAdapter... genresAdapters) {
        this.genresAdapter = genresAdapters[0];
        return getUserGenresTEST();
    }

    @Override
    protected void onPostExecute(List<Genre> genres) {
        this.genresAdapter.updateData(genres);
    }
}
