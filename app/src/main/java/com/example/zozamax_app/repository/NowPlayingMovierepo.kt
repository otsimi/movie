package com.example.zozamax_app.repository

import com.example.zozamax_app.AppModule
import com.example.zozamax_app.data.MovieApiResponse
import retrofit2.Response

class NowPlayingMovieRepo() {
    suspend fun getNowPlayingMovies(): Response<MovieApiResponse> {
        val retrofit = AppModule().getRetrofitInstance()
        return retrofit.getNowPlayingMovies()
    }
}