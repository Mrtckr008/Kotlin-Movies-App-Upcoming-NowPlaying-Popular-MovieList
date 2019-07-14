package com.example.nytimesmoviesreview.ApiCalls

import com.example.nytimesmoviesreview.dto.*
import com.example.nytimesmoviesreview.model.MovieGetPopularModel
import com.example.nytimesmoviesreview.model.MovieGetTrendModel
import com.example.nytimesmoviesreview.model.MovieGetUpcomingModel
import com.example.nytimesmoviesreview.model.MovieNowPlayingModel
import com.example.nytimesmoviesreview.network.NytimesServiceInterface
import com.example.nytimesmoviesreview.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiCall {
    fun calls() {
        val service = RetrofitClient.getClient().create(NytimesServiceInterface::class.java)
        val callNowPlayingMovies =
            service.getNowPlayingMovies("movie/now_playing?api_key=ac3cbd07a68825e9716c144bd088350f")
        val callGetPopularMovies =
            service.getPopularMovies("movie/popular?api_key=ac3cbd07a68825e9716c144bd088350f&language=en-US&page=1")
        val callGetUpcomingMovies =
            service.getUpcomingMovies("movie/upcoming?api_key=ac3cbd07a68825e9716c144bd088350f&language=en-US&page=1")
        val callTrendMoviesAndSeries =
            service.getTrendMoviesAndSeries("trending/all/day?api_key=ac3cbd07a68825e9716c144bd088350f")

    /*    callNowPlayingMovies.enqueue(object : Callback<GetMovieNowPlaying> {
            override fun onResponse(call: Call<GetMovieNowPlaying>?, response: Response<GetMovieNowPlaying>?) {
                val movieList = ArrayList(response!!.body().results)
                MovieNowPlayingModel.setResponse(movieList)
            }

            override fun onFailure(call: Call<GetMovieNowPlaying>?, t: Throwable?) {
            }
        })*/

        var movieList:GetMovieNowPlaying? = callNowPlayingMovies.clone().execute().body()
        MovieNowPlayingModel.setResponse(movieList!!.results as ArrayList<Result>)


      /*  callGetPopularMovies.enqueue(object : Callback<GetPopularMovies> {
            override fun onResponse(call: Call<GetPopularMovies>?, response1: Response<GetPopularMovies>?) {
                val movieList = ArrayList(response1!!.body().results)
                MovieGetPopularModel.setResponse(movieList)

            }

            override fun onFailure(call: Call<GetPopularMovies>?, t: Throwable?) {
            }
        })*/

        var movieList1:GetPopularMovies? = callGetPopularMovies.clone().execute().body()
        MovieGetPopularModel.setResponse(movieList1!!.results as ArrayList<ResultPopularMovies>)




    /*    callGetUpcomingMovies.enqueue(object : Callback<GetMovieGetUpcoming> {
            override fun onResponse(call: Call<GetMovieGetUpcoming>?, response: Response<GetMovieGetUpcoming>?) {
                val movieList = ArrayList(response!!.body().results)
                MovieGetUpcomingModel.setResponse(movieList)

            }

            override fun onFailure(call: Call<GetMovieGetUpcoming>?, t: Throwable?) {
            }
        })*/

        var movieList2:GetMovieGetUpcoming? = callGetUpcomingMovies.clone().execute().body()
        MovieGetUpcomingModel.setResponse(movieList2!!.results as ArrayList<ResultUpcoming>)



     /*   callTrendMoviesAndSeries.enqueue(object : Callback<GetTrendMoviesAndSeries> {
            override fun onResponse(
                call: Call<GetTrendMoviesAndSeries>?,
                response: Response<GetTrendMoviesAndSeries>?
            ) {
                val movieList = ArrayList(response!!.body().results)
                MovieGetTrendModel.setResponse(movieList)

            }

            override fun onFailure(call: Call<GetTrendMoviesAndSeries>?, t: Throwable?) {
            }
        })
*/

        var movieList3:GetTrendMoviesAndSeries? = callTrendMoviesAndSeries.clone().execute().body()
        MovieGetTrendModel.setResponse(movieList3!!.results as ArrayList<TrendMoviesAndSeriesResult>)



    }

}