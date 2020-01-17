package com.alvaromoran.castdroid.fragments.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alvaromoran.castdroid.R;
import com.alvaromoran.castdroid.models.Genre;

import java.util.List;

public class GenresAdapter extends ArrayAdapter<Genre> implements View.OnClickListener {

    private List<Genre> genres;

    private int lastSelectedPosition = -1;

    public GenresAdapter (Context context, List<Genre> genres ){
        super(context, R.layout.gridview_categories, genres);
        this.genres = genres;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View result;
        // Data for current view
        Genre genreView = getItem(position);
        // Check if view already exists or inflate it otherwise
        GenreViewHolder genreViewHolder;

        // Reference to the graphic elements
        if (convertView == null) {
            genreViewHolder = new GenreViewHolder();
            // Inflate genre view holder
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.gridview_categories, parent, false);
            genreViewHolder.genreName = convertView.findViewById(R.id.category_text);
            genreViewHolder.genreImage = convertView.findViewById(R.id.category_background);
            convertView.setTag(genreViewHolder);
            result = convertView;
        } else {
            genreViewHolder = (GenreViewHolder) convertView.getTag();
            result = convertView;
        }

        lastSelectedPosition = position;
        // Populate graphic elements referenced
        genreViewHolder.genreName.setText(genres.get(position).getGenreName());
        genreViewHolder.genreImage.setImageResource(genres.get(position).getDrawableBackgroundItem());
        return result;
    }


    public void updateData(List<Genre> genres) {
        this.genres = genres;
        this.notifyDataSetChanged();
    }

    @Override
    public void onClick(View clickedView) {
        int position = (Integer) clickedView.getTag();
        Genre selectedGenre = getItem(position);
        System.out.println("HE SELECCIONADO EL GENERO: " + selectedGenre.getGenreName());
    }

    protected static class GenreViewHolder {
        ImageView genreImage;
        TextView genreName;
    }
}
