package com.example.myapplication.ui.detail;

import static com.example.myapplication.util.Constants.ARG_MOVIE_ID;
import static com.example.myapplication.util.Constants.CUSTOM_TAG;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.data.detail.MovieDetailModel;
import com.example.myapplication.databinding.ActivityMovieDetailBinding;

public class MovieDetailActivity extends AppCompatActivity {

    private ActivityMovieDetailBinding binding;
    private MovieDetailViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //region View Binding
        binding = ActivityMovieDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //endregion

        //Prepare View Model
        mViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);


        initObservers();
        getMovieDetail();
    }


    /**
     * Initialize observers for getting data from the ViewModel
     */
    private void initObservers() {
        mViewModel.getMovieDetail().observe(this, new Observer<MovieDetailModel>() {
            @Override
            public void onChanged(MovieDetailModel movieDetailModel) {
                Log.d(CUSTOM_TAG, "onChanged: ");
                prepareView(movieDetailModel);
            }
        });
    }

    /**
     * Observe the movie data that stored in the ViewModel
     */
    private void getMovieDetail() {
        int movieId = 0;
        //region get data from previous Activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            movieId = extras.getInt(ARG_MOVIE_ID, 0);
        }
        //endregion
        //region check the movieId
        if (movieId != 0)
            mViewModel.getMovieDetail(movieId);
        else
            Toast.makeText(this, "Movie not found.", Toast.LENGTH_SHORT).show();
        //endregion
    }

    /**
     * Prepare UI Components
     *
     * @param model as MovieDetailModel
     */
    private void prepareView(MovieDetailModel model) {
        //region iMovie's components
        if (!TextUtils.isEmpty(model.getBackdropPath()))
            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/original" + model.getBackdropPath())
                    .placeholder(R.drawable.ic_baseline_broken_image_24)
                    .into(binding.iMovie.ivScreenshot1);
        if (!TextUtils.isEmpty(model.getOverview()))
            binding.iMovie.tvOverview.setText(model.getOverview());
        if (!TextUtils.isEmpty(Double.toString(model.getVoteAverage())))
            binding.iMovie.tvScore.setText(Double.toString(model.getVoteAverage()));
        if (!TextUtils.isEmpty(model.getReleaseDate()))
            binding.iMovie.tvReleaseDate.setText("Release Date: " + model.getReleaseDate());
        if (model.getGenres() != null){
            String temp="";
            for(int i=0;i<model.getGenres().size();i++){
                temp+=model.getGenres().get(i).getName();
                if(i != model.getGenres().size()-1){
                    temp+=", ";
                }
            }
            binding.iMovie.tvCategory.setText(temp);
        }

        //endregion


    }
}