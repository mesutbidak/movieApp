package com.example.myapplication.ui.detail;

import static com.example.myapplication.util.Constants.CUSTOM_TAG;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication.data.detail.MovieDetailModel;
import com.example.myapplication.service.ClientMovie;
import com.example.myapplication.service.IRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailViewModel extends ViewModel {

    private MutableLiveData<MovieDetailModel> movieDetail = new MutableLiveData<>();

    /**
     * Send HTTP Request for getting movie detail via gameId
     */
    public void getMovieDetail(int movieId) {
        IRequest request = ClientMovie.getApiClient().create(IRequest.class);
        Call<MovieDetailModel> call = request.getMovieDetail(movieId);
        call.enqueue(new Callback<MovieDetailModel>() {
            @Override
            public void onResponse(Call<MovieDetailModel> call, Response<MovieDetailModel> response) {
                if (response.isSuccessful()) {
                    Log.d(CUSTOM_TAG, "onResponse: ");
                    movieDetail.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MovieDetailModel> call, Throwable t) {
                Log.d(CUSTOM_TAG, "onFailure: ");
            }
        });
    }

    /**
     * This method is used to observe the Live Data
     */
    public LiveData<MovieDetailModel> getMovieDetail() {
        return movieDetail;
    }
}

