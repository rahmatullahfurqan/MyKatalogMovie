package belajar.furqan.com.mykatalogmovie.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import belajar.furqan.com.mykatalogmovie.Model.GenreMovie;
import belajar.furqan.com.mykatalogmovie.R;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {
    List<GenreMovie> genreMovieList = new ArrayList<>();
    Context context;

    public GenreAdapter(List<GenreMovie> genreMovieList, Context context) {
        this.genreMovieList = genreMovieList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_genre, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(genreMovieList.get(position));
    }

    @Override
    public int getItemCount() {
        return genreMovieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvGenre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGenre = itemView.findViewById(R.id.tv_name_genre);
        }

        public void bind(GenreMovie genreMovie) {
            tvGenre.setText(genreMovie.getName());
        }
    }
}
