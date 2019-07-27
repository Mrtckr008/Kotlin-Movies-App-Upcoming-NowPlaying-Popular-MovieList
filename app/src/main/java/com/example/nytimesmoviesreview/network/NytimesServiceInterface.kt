package com.example.nytimesmoviesreview.network

import com.example.nytimesmoviesreview.dto.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface NytimesServiceInterface {
    @GET()
    fun getNowPlayingMovies(@Url url:String): Call<GetMovieNowPlaying>

    @GET()
    fun getPopularMovies(@Url url:String): Call<GetPopularMovies>

    @GET()
    fun getUpcomingMovies(@Url url:String): Call<GetMovieGetUpcoming>

    @GET()
    fun getTrendMoviesAndSeries(@Url url:String): Call<GetTrendMoviesAndSeries>

    @GET()
    fun getPopularSeries(@Url url:String): Call<GetPopularSeries>

    @GET()
    fun getTopRatedSeries(@Url url:String): Call<GetTopRatedSeries>

    @GET()
    fun getSearchMovie(@Url url:String, @QueryMap queryMap:Map<String, String>): Call<GetSearchMovieDTO>

    @GET()
    fun getMovieDetail(@Url url:String): Call<GetMovieDetail>
}