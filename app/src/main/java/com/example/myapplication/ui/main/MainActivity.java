package com.example.myapplication.ui.main;

import static com.example.myapplication.util.Constants.ARG_MOVIE_ID;
import static com.example.myapplication.util.Constants.CUSTOM_TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.adapter.MovieAdapter;
import com.example.myapplication.data.main_list.MovieModel;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.ui.detail.MovieDetailActivity;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button btn;

    private ActivityMainBinding binding;
    private MainViewModel mViewModel;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //region View Binding
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //endregion

        //Prepare View Model
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        this.initComponents();
        this.initClicks();
        this.initObservers();

        editText=binding.editTextSearch;
        btn= Objects.<Button>requireNonNull(binding.btnSearch);
        btn.setOnClickListener(v -> {
            if(editText.getText().toString().matches(".*[a-zA-Z]+.*")){
                String movieName = new String(editText.getText().toString());
                //Toast.makeText(MainActivity.this, movieName, Toast.LENGTH_LONG).show();
                this.observeMovieData(movieName);
            }
            else{
                Toast.makeText(MainActivity.this,
                        "You should enter at least one character",
                        Toast.LENGTH_LONG).show();
            }
        });
        Toast.makeText(MainActivity.this,
                "Welcome to MyApp",
                Toast.LENGTH_LONG).show();



    }

    /**
     * Initialize components & Create necessary adapter
     */
    private void initComponents() {
        //Recycler View initialize
        binding.rvMovies.setLayoutManager(new LinearLayoutManager(this));
        binding.rvMovies.setItemAnimator(new DefaultItemAnimator());
        movieAdapter = new MovieAdapter(this);
        binding.rvMovies.setAdapter(movieAdapter);
    }

    /**
     * Handle RecyclerView's clicks
     */
    private void initClicks() {
        movieAdapter.setOnClickListener((pos, movieModel) -> {
            Log.d(CUSTOM_TAG, "onClick: ");
            //region show MovieDetailActivity & send the movieId to the activity
            Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
            intent.putExtra(ARG_MOVIE_ID, movieModel.getId());
            startActivity(intent);
            //endregion
        });
    }

    /**
     * Initialize observers for getting data from the ViewModel
     */
    private void initObservers() {
        mViewModel.getMovieList().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                Log.d(CUSTOM_TAG, "onChanged: ");
                prepareRecycler(movieModels);
            }
        });
        /*
        mViewModel.callRequest().observe(this, (Observer<List<GameModel>>) gameModels -> Log.d("TAG", "onChanged: "));
         */
    }

    /**
     * Observe the game data that stored in the ViewModel
     */
    private void observeMovieData(String movieName) {
        mViewModel.getMoviesData(movieName);
    }

    /**
     * Set the data to the RecyclerView's adapter
     *
     * @param models as List<MovieModel>
     */
    private void prepareRecycler(List<MovieModel> models) {
        movieAdapter.setAdapterList(models);
    }
}