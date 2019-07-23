package com.example.nytimesmoviesreview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.nytimesmoviesreview.dto.GetSearchMovieDTO
import com.example.nytimesmoviesreview.dto.SearchMovieResults
import com.example.nytimesmoviesreview.model.MovieSearchModel
import com.example.nytimesmoviesreview.network.NytimesServiceInterface
import com.example.nytimesmoviesreview.network.RetrofitClient
import android.os.StrictMode
import android.support.v7.widget.LinearLayoutManager
import com.example.nytimesmoviesreview.adapter.MoviesAdapterGetPopular
import com.example.nytimesmoviesreview.adapter.MoviesAdapterGetSearch
import com.example.nytimesmoviesreview.model.MovieGetPopularModel
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.now_playing_fragment.*


class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        callSearchApi()



    }
    fun callSearchApi(){ val service = RetrofitClient.getClient().create(NytimesServiceInterface::class.java)

        val callSearchMovies = service.getSearchMovie("search/movie?api_key=ac3cbd07a68825e9716c144bd088350f&language=en-US",MovieSearchModel.queriesData)
        var movieList4: GetSearchMovieDTO? = callSearchMovies.clone().execute().body()
        // MovieGetTrendModel.setResponse(movieList3!!.results as ArrayList<TrendMoviesAndSeriesResult>)
        MovieSearchModel.setResponse(movieList4!!.results as ArrayList<SearchMovieResults>)


        SearchMenuRecyclerView.apply {
            layoutManager=LinearLayoutManager(this@SearchActivity)
            adapter = MoviesAdapterGetSearch(MovieSearchModel.getResponse()!!.toList())
        }}


}
