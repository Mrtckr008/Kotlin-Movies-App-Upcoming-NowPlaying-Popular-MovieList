package com.example.nytimesmoviesreview

import com.example.nytimesmoviesreview.dto.GetPopularMovies
import com.example.nytimesmoviesreview.dto.MovieGetUpcoming
import com.example.nytimesmoviesreview.dto.MovieNowPlaying
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface NytimesServiceInterface {
    @GET()
    fun getNowPlayingMovies(@Url url:String): Call<MovieNowPlaying>

    @GET()
    fun getPopularMovies(@Url url:String): Call<GetPopularMovies>

    @GET()
    fun getUpcomingMovies(@Url url:String): Call<MovieGetUpcoming>

}