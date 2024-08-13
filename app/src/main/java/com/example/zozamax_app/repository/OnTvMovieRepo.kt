package com.example.zozamax_app.repository

import com.example.zozamax_app.AppModule
import com.example.zozamax_app.data.MovieApiResponse
import com.example.zozamax_app.data.Result
import retrofit2.Response

class OnTvMovieRepo {
    suspend fun getOnTvMovies(): Response<MovieApiResponse> {
        val retrofit = AppModule().getRetrofitInstance()
        return retrofit.getOnTvMovies()
    }


}