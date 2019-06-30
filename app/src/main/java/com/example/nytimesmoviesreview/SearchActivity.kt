package com.example.nytimesmoviesreview

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.nytimesmoviesreview.dto.GetPopularMovies
import com.example.nytimesmoviesreview.dto.MovieGetUpcoming
import com.example.nytimesmoviesreview.dto.MovieNowPlaying
import com.example.nytimesmoviesreview.model.MovieGetPopularModel
import com.example.nytimesmoviesreview.model.MovieGetUpcomingModel
import com.example.nytimesmoviesreview.model.MovieNowPlayingModel
import com.example.nytimesmoviesreview.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val service= RetrofitClient.getClient().create(NytimesServiceInterface::class.java)
        val callNowPlayingMovies=service.getNowPlayingMovies("movie/now_playing?api_key=ac3cbd07a68825e9716c144bd088350f")
        val callGetPopularMovies=service.getPopularMovies("movie/popular?api_key=ac3cbd07a68825e9716c144bd088350f&language=en-US&page=1")
        val callGetUpcomingMovies=service.getUpcomingMovies("movie/upcoming?api_key=ac3cbd07a68825e9716c144bd088350f&language=en-US&page=1")



        callNowPlayingMovies.enqueue(object : Callback<MovieNowPlaying> {
            override fun onResponse(call: Call<MovieNowPlaying>?, response: Response<MovieNowPlaying>?) {
                val movieList=ArrayList(response!!.body().results)
                MovieNowPlayingModel.setResponse(movieList)

            }

            override fun onFailure(call: Call<MovieNowPlaying>?, t: Throwable?) {
            }
        })

        callGetPopularMovies.enqueue(object : Callback<GetPopularMovies> {
            override fun onResponse(call: Call<GetPopularMovies>?, response1: Response<GetPopularMovies>?) {
                val movieList=ArrayList(response1!!.body().results)
                MovieGetPopularModel.setResponse(movieList)

            }

            override fun onFailure(call: Call<GetPopularMovies>?, t: Throwable?) {
            }
        })

        callGetUpcomingMovies.enqueue(object : Callback<MovieGetUpcoming> {
            override fun onResponse(call: Call<MovieGetUpcoming>?, response: Response<MovieGetUpcoming>?) {
                val movieList=ArrayList(response!!.body().results)
                MovieGetUpcomingModel.setResponse(movieList)

            }

            override fun onFailure(call: Call<MovieGetUpcoming>?, t: Throwable?) {
            }
        })








    }

   fun searchMovie(view:View){
       val intent=Intent(this,MainActivity::class.java)
       startActivity(intent)
   }
}
