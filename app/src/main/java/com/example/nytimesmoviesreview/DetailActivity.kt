package com.example.nytimesmoviesreview

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.nytimesmoviesreview.ApiCalls.ApiCall
import kotlinx.android.synthetic.main.activity_detail.*
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.AsyncTask
import android.widget.ProgressBar
import com.example.nytimesmoviesreview.dto.GetMovieDetail
import com.example.nytimesmoviesreview.dto.GetPopularSeries
import com.example.nytimesmoviesreview.dto.GetSeriesDetail
import com.example.nytimesmoviesreview.dto.GetTopRatedSeries
import com.example.nytimesmoviesreview.model.SeriesGetPopularModel
import com.example.nytimesmoviesreview.model.SeriesGetTopRatedModel
import com.example.nytimesmoviesreview.network.NytimesServiceInterface
import com.example.nytimesmoviesreview.network.RetrofitClient
import com.example.nytimesmoviesreview.utils.TinyDB
import com.google.gson.Gson
import com.r0adkll.slidr.Slidr
import com.r0adkll.slidr.model.SlidrConfig
import com.r0adkll.slidr.model.SlidrPosition
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.ref.WeakReference


class DetailActivity : AppCompatActivity() {
companion object {
    var isItMovie=true
}
    var movieDetailCategory:TextView?=null
    var responseMovieDetail:GetMovieDetail?=null
    var responseSeriesDetail:GetSeriesDetail?=null

    var movieDetailOverview:TextView?= null
    var movieDetailBudget:TextView?=null
    var movieName:TextView?=null
    var movieCountry:TextView?=null
    var movieDate:TextView?=null
    var movieVoteAverage:TextView?=null
    var progressBar:ProgressBar?=null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        movieDetailCategory=findViewById<TextView>(R.id.movie_detail_category)
        movieDetailOverview=findViewById<TextView>(R.id.movie_detail_overview)
        movieDetailBudget=findViewById<TextView>(R.id.movie_detail_budget)
        movieName=findViewById<TextView>(R.id.movie_name)
        movieCountry=findViewById<TextView>(R.id.movie_country)
        movieDate=findViewById<TextView>(R.id.movie_release_date)
        movieVoteAverage=findViewById<TextView>(R.id.movie_vote_avarage)
        progressBar=findViewById(R.id.progressBar)


        val posterPath:String = intent.getStringExtra("posterpath")
        val movieTitle:String = intent.getStringExtra("movieTitle")
        val transitionId:String = intent.getStringExtra("transitionId")

            movie_detail_image.transitionName = "simple_activity_transition$transitionId"
            Glide.with(this).load(posterPath)
                .thumbnail(Glide.with(this).load(R.drawable.abc_ic_go_search_api_material))
                .transition(DrawableTransitionOptions.withCrossFade()).into(movie_detail_image)

            movieName?.text = movieTitle


        val config: SlidrConfig = SlidrConfig.Builder()
            .position(SlidrPosition.LEFT)
            .sensitivity(1f)
            .scrimColor(Color.BLACK)
            .scrimStartAlpha(0.8f)
            .scrimEndAlpha(0f)
            .velocityThreshold(2400f)
            .distanceThreshold(0.25f)
            .edge(false)
            .edgeSize(0.18f).build()

        val slidr= Slidr.attach(this, config)
        val task =
            DetailActivity.PostOtherViewPager(this)                                                                    //  Use for download new feeds in background.
        task.execute(10)
        slidr.unlock()
    }

    fun goMovieHomePage(view: View){
        if(isItMovie) {
            val browse = Intent(Intent.ACTION_VIEW, Uri.parse(responseMovieDetail!!.homepage))
            startActivity(browse)
        }
        else{
            val browse = Intent(Intent.ACTION_VIEW, Uri.parse(responseSeriesDetail!!.homepage))
            startActivity(browse)
        }
    }

    fun addFavoriteList(view:View){
        val localFeedList = TinyDB(this).getListString("getMovieList")
        if(responseMovieDetail!=null){
        val saveValuePostData = responseMovieDetail
            val gson = Gson()
            val saveJsonValue = gson.toJson(saveValuePostData)
            localFeedList?.add(saveJsonValue)
            TinyDB(this).putListString("getMovieList", localFeedList)
        }
        else{
            val saveValuePostData=responseSeriesDetail
            val gson = Gson()
            val saveJsonValue = gson.toJson(saveValuePostData)
            localFeedList?.add(saveJsonValue)
            TinyDB(this).putListString("getMovieList", localFeedList)
        }
    }

    fun addWatchedList(view:View){
        val localFeedList = TinyDB(this).getListString("getWatchedMovieList")
        if(responseMovieDetail!=null){
            val saveValuePostData = responseMovieDetail
            val gson = Gson()
            val saveJsonValue = gson.toJson(saveValuePostData)
            localFeedList?.add(saveJsonValue)
            TinyDB(this).putListString("getWatchedMovieList", localFeedList)
        }
        else{
            val saveValuePostData=responseSeriesDetail
            val gson = Gson()
            val saveJsonValue = gson.toJson(saveValuePostData)
            localFeedList?.add(saveJsonValue)
            TinyDB(this).putListString("getWatchedMovieList", localFeedList)
        }
    }

    fun addWatchList(view:View){
        val localFeedList = TinyDB(this).getListString("getWatchMovieList")
        if(responseMovieDetail!=null){
            val saveValuePostData = responseMovieDetail
            val gson = Gson()
            val saveJsonValue = gson.toJson(saveValuePostData)
            localFeedList?.add(saveJsonValue)
            TinyDB(this).putListString("getWatchMovieList", localFeedList)
        }
        else{
            val saveValuePostData=responseSeriesDetail
            val gson = Gson()
            val saveJsonValue = gson.toJson(saveValuePostData)
            localFeedList?.add(saveJsonValue)
            TinyDB(this).putListString("getWatchMovieList", localFeedList)
        }
    }


    class PostOtherViewPager internal constructor(var context: DetailActivity) : AsyncTask<Int, String, String?>() {
        private var resp: String? = null
        private val activityReference: WeakReference<DetailActivity> = WeakReference(context)
        override fun onPreExecute() {
            System.out.println("MCOnPreExecute")
            val activity = activityReference.get()
            if (activity == null || activity.isFinishing) return
        }

        override fun doInBackground(vararg params: Int?): String? {
            publishProgress("Calls Started") // Calls onProgressUpdate()
            try {
                if(isItMovie){
                    context.responseMovieDetail=ApiCall().callMovieDetail()
                }
                else{
                    context.responseSeriesDetail=ApiCall().callSeriesDetail()
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
                resp = e.message
            } catch (e: Exception) {
                e.printStackTrace()
                resp = e.message
            }
            return resp                                                                                                     //Need to find some way for if api has some problem.
        }

        override fun onPostExecute(result: String?) {
            if(isItMovie) {
                context.fav_image.visibility=View.VISIBLE
                context.watched_image_list.visibility=View.VISIBLE
                context.watch_image_list.visibility=View.VISIBLE
                context.progressBar?.visibility=View.GONE
                context.movieDetailBudget?.text="Movie budget: "+context.responseMovieDetail?.budget.toString()+" $"
                context.movieDetailOverview?.text=context.responseMovieDetail?.overview
                var categoriesList:String?=""
                for (item in 0..context.responseMovieDetail?.genres!!.size-1) {
                    if(item==context.responseMovieDetail?.genres!!.size-1)
                        categoriesList += context.responseMovieDetail?.genres!![item].name
                    else
                        categoriesList += context.responseMovieDetail?.genres!![item].name + "-"
                }
                context.movieDetailCategory?.text= "Movie Category: $categoriesList"
                context.movieCountry?.text =
                    "Production Country: " + context.responseMovieDetail?.productionCountries!![0].name
                context.movieDate?.text = "Release Date: " + context.responseMovieDetail?.releaseDate
                context.movieVoteAverage?.text = "Vote: " + context.responseMovieDetail?.voteAverage

            }
            else{
                context.fav_image.visibility=View.VISIBLE
                context.watched_image_list.visibility=View.VISIBLE
                context.watch_image_list.visibility=View.VISIBLE
                context.progressBar?.visibility=View.GONE
                context.movieDetailOverview?.text=context.responseSeriesDetail?.overview
                var categoriesList:String?=""
                for (item in 0..context.responseSeriesDetail?.genres!!.size-1) {
                    if(item==context.responseSeriesDetail?.genres!!.size-1)
                        categoriesList += context.responseSeriesDetail?.genres!![item].name
                    else
                        categoriesList += context.responseSeriesDetail?.genres!![item].name + "-"
                }
                context.movieDetailBudget?.text="Total Season-Episode: "+context.responseSeriesDetail?.numberOfSeasons.toString()+"-"+context.responseSeriesDetail?.numberOfEpisodes.toString()
                context.movieDetailCategory?.text= "Movie Category: $categoriesList"
                context.movieCountry?.text="Production Company: "+context.responseSeriesDetail?.productionCompanies!![0].name
                context.movieDate?.text="First Release Date: "+context.responseSeriesDetail?.firstAirDate
                context.movieVoteAverage?.text="Vote: "+context.responseSeriesDetail?.voteAverage


            }
            //When all of pager's first 5 feed download, progressbar is invisible.
        }
    }
}
