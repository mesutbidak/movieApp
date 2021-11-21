package com.example.myapplication.ui.main;

import static com.example.myapplication.util.Constants.CUSTOM_TAG;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.main_list.MovieModel;
import com.example.myapplication.data.main_list.results;
import com.example.myapplication.service.ClientMovie;
import com.example.myapplication.service.IRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private MutableLiveData<List<MovieModel>> movieList = new MutableLiveData<>();
    private results r;

    /**
     * Send HTTP Request for getting game list
     */
    public void getMoviesData(String movieName) {
        IRequest request = ClientMovie.getApiClient().create(IRequest.class);
        Call<results> call = request.getMovieList(movieName,1);
        call.enqueue(new Callback<results>() {
            @Override
            public void onResponse(Call<results> call, Response<results> response) {
                System.out.println(call.request().url().toString());

                if (response.isSuccessful()) {
                    System.out.println("basarili");
                    Log.d(CUSTOM_TAG, "onResponse: ");
                    r= response.body();
                    movieList.postValue(r.getResults());
                    
                    //movieList.setValue(r.getResults());

                }
            }

            @Override
            public void onFailure(Call<results> call, Throwable t) {
                System.out.println("basarisiz");
                Log.d(CUSTOM_TAG, "onFailure: ");
            }
        });
        //return gameList;
    }

    /**
     * This method is used to observe the Live Data
     */
    public LiveData<List<MovieModel>> getMovieList() {
        return movieList;
    }
}
