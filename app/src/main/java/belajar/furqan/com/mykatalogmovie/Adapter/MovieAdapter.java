package belajar.furqan.com.mykatalogmovie.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import belajar.furqan.com.mykatalogmovie.Model.Movie;
import belajar.furqan.com.mykatalogmovie.R;
import belajar.furqan.com.mykatalogmovie.View.MovieView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    List<Movie> movieList = new ArrayList<>();
    Context context;
    MovieView movieView;

    public MovieAdapter(List<Movie> movieList, Context context, MovieView movieView) {
        this.movieList = movieList;
        this.context = context;
        this.movieView = movieView;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_movie, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(movieList.get(position));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMovie;
        CardView cvMovie;
        TextView tvTitle, tvOverviewTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvMovie = itemView.findViewById(R.id.cv_movie);
            ivMovie = itemView.findViewById(R.id.iv_movie);
            tvTitle = itemView.findViewById(R.id.tv_title_movie);
            tvOverviewTitle = itemView.findViewById(R.id.tv_overview_movie);
        }

        public void bind(final Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverviewTitle.setText(movie.getOverview());
            if (movie.getPosterPath() != null)
                Picasso.get().load("https://image.tmdb.org/t/p/w500" + movie.getPosterPath()).fit().centerInside().into(ivMovie);
            cvMovie.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    movieView.detailMovie(Integer.toString(movie.getId()),movie.getTitle());
                }
            });
        }
    }
}
