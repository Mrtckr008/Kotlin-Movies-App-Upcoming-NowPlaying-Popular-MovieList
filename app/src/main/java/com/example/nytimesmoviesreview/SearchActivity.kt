package com.example.nytimesmoviesreview

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.nytimesmoviesreview.dto.*
import com.example.nytimesmoviesreview.model.*
import com.example.nytimesmoviesreview.network.NytimesServiceInterface
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
        val callTrendMoviesAndSeries=service.getTrendMoviesAndSeries("trending/all/day?api_key=ac3cbd07a68825e9716c144bd088350f")



        callNowPlayingMovies.enqueue(object : Callback<GetMovieNowPlaying> {
            override fun onResponse(call: Call<GetMovieNowPlaying>?, response: Response<GetMovieNowPlaying>?) {
                val movieList=ArrayList(response!!.body().results)
                MovieNowPlayingModel.setResponse(movieList)

            }

            override fun onFailure(call: Call<GetMovieNowPlaying>?, t: Throwable?) {
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

        callGetUpcomingMovies.enqueue(object : Callback<GetMovieGetUpcoming> {
            override fun onResponse(call: Call<GetMovieGetUpcoming>?, response: Response<GetMovieGetUpcoming>?) {
                val movieList=ArrayList(response!!.body().results)
                MovieGetUpcomingModel.setResponse(movieList)

            }

            override fun onFailure(call: Call<GetMovieGetUpcoming>?, t: Throwable?) {
            }
        })


        callTrendMoviesAndSeries.enqueue(object : Callback<GetTrendMoviesAndSeries> {
            override fun onResponse(call: Call<GetTrendMoviesAndSeries>?, response: Response<GetTrendMoviesAndSeries>?) {
                val movieList=ArrayList(response!!.body().results)
                MovieGetTrendModel.setResponse(movieList)

            }

            override fun onFailure(call: Call<GetTrendMoviesAndSeries>?, t: Throwable?) {
            }
        })


        if(SeriesGetTopRatedModel.getResponse()==null){
            val service= RetrofitClient.getClient().create(NytimesServiceInterface::class.java)
            val callGetRatedSeries=service.getTopRatedSeries("tv/top_rated?api_key=ac3cbd07a68825e9716c144bd088350f&language=en-US&page=1")

            callGetRatedSeries.enqueue(object : Callback<GetTopRatedSeries> {
                override fun onResponse(call: Call<GetTopRatedSeries>?, response: Response<GetTopRatedSeries>?) {
                    val movieList=ArrayList(response!!.body().results)
                    SeriesGetTopRatedModel.setResponse(movieList)
                }
                override fun onFailure(call: Call<GetTopRatedSeries>?, t: Throwable?) {
                    System.out.println("mcmcmc:")
                }
            })
        }





    }

   fun searchMovie(view:View){
       val intent=Intent(this,MainActivity::class.java)
       startActivity(intent)
   }
}
