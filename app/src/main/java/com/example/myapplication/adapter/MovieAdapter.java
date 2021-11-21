package com.example.myapplication.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.data.main_list.MovieModel;
import com.example.myapplication.databinding.ItemMovieBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MoviesViewHolders> {

    private List<MovieModel> movies;
    private Context context;
    private ItemClickListener itemClickListener;

    /**
     * Constructor for MovieAdapter class
     *
     * @param context as Context for Glide
     */
    public MovieAdapter(Context context) {
        this.context = context;
        this.movies = new ArrayList<>();
    }

    /**
     * Setting the list data & notify the adapter for render list
     *
     * @param movies as List<MovieModel>
     */
    public void setAdapterList(List<MovieModel> movies) {
        this.movies.addAll(movies);
        this.notifyDataSetChanged();
    }

    /**
     * Default onCreate method.
     * Connect Adapter & layout
     */
    @NotNull
    @Override
    public MoviesViewHolders onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ItemMovieBinding binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MoviesViewHolders(binding);
    }

    /**
     * Prepare row item with data
     */
    @Override
    public void onBindViewHolder(MoviesViewHolders holder, int position) {
        MovieModel movieModel = movies.get(position);
        //System.out.println("deneme");
        if (!TextUtils.isEmpty(movieModel.getPosterPath()))
            Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w780" + movieModel.getPosterPath())
                    .placeholder(R.drawable.ic_baseline_broken_image_24)
                    .into(holder.binding.ivThumbnail);
        if (!TextUtils.isEmpty(movieModel.getOriginalTitle()))
            holder.binding.tvTitle.setText(String.format(movieModel.getOriginalTitle()));
        if (!TextUtils.isEmpty(String.format("Popularity: %s", movieModel.getPopularity())))
            holder.binding.tvPopularity.setText(String.format("Popularity: %s", movieModel.getPopularity()));

    }

    private MovieModel getItem(int pos) {
        return movies.get(pos);
    }

    /**
     * @return the data list count
     */
    @Override
    public int getItemCount() {
        return this.movies.size();
    }

    //region Click Listener

    /**
     * This method is used for te interface in the adapter to communicate with the View
     *
     * @param itemClickListener as ItemClickListener
     */
    public void setOnClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    /**
     * This interface is used to catch the item click event
     */
    public interface ItemClickListener {
        void onClick(int pos, MovieModel movieModel);
    }
    //endregion

    public class MoviesViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemMovieBinding binding;

        public MoviesViewHolders(ItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            try{itemClickListener.onClick(getAdapterPosition(), getItem(getAdapterPosition()));}
            catch (Exception e){
                System.out.println(e);
            }

        }
    }
}
