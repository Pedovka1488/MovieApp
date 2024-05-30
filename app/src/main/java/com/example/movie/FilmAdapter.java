package com.example.movie;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie.models.Film;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {
    private List<Film> filmList;

    public FilmAdapter(List<Film> filmList) {
        this.filmList = filmList;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycler, parent, false);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        Film film = filmList.get(position);
        holder.titleTextView.setText(film.getTitle());
        holder.imdbTextView.setText(film.getImdb());
        holder.minutesTextView.setText(film.getMinutes()+" Minutes");
        Picasso.get().load(film.getImage()).into(holder.image);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailsActivity.class);
            intent.putExtra("title", film.getTitle());
            intent.putExtra("image", film.getImage());
            intent.putExtra("description", film.getDescription());
            intent.putExtra("price", film.getPrice());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    public static class FilmViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, imdbTextView, minutesTextView;
        ImageView image;

        public FilmViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            imdbTextView = itemView.findViewById(R.id.imdbTextView);
            minutesTextView = itemView.findViewById(R.id.minutesTextView);
            image = itemView.findViewById(R.id.image);
        }
    }
}
