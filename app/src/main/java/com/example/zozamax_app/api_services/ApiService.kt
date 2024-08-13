package com.example.zozamax_app.api_services

import com.example.zozamax_app.data.MovieApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query



interface  ApiService {
    @GET("3/account/21323729/rated/movies")
    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5OWNjMTMyZGJhODM0ZTRlNTZlYWZkYWNhMzgyYjJkZCIsInN1YiI6IjY2NjkzYzE2ZTcxMDM0MDEwZmJlYWE1OCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.s2yy6z0a1ZRt05q25ZZ34dugk3MTedmTOUfkzvMb_uE"
    )
    fun getRatedMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("sort_by") sortBy: String = "created_at.asc"
    ): Response<MovieApiResponse>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String="en-US",
        @Query("page") page: Int =1
    ): Response<MovieApiResponse>

    //getting movie images
    @GET("")
    suspend fun getMovieImage()

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("language") language: String="en-US",
        @Query("page") page: Int =1
    ): Response<MovieApiResponse>


    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(

        @Query("language") language: String="en-US",
        @Query("page") page: Int =1
    ): Response<MovieApiResponse>


    @GET("tv/on_the_air")
    suspend fun getOnTvMovies(

        @Query("language") language: String="en-US",
        @Query("page") page: Int =1
    ): Response<MovieApiResponse>


    @GET("movie/top_rated")
    suspend fun geTopMovies(
        @Query("language") language: String="en-US",
        @Query("page") page: Int =1
    ): Response<MovieApiResponse>

}



