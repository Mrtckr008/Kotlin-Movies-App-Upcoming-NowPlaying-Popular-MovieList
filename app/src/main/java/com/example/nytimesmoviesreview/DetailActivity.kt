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
import android.net.Uri
import com.example.nytimesmoviesreview.dto.GetMovieDetail
import com.example.nytimesmoviesreview.dto.GetSeriesDetail


class DetailActivity : AppCompatActivity() {
companion object {
    var isItMovie=true
}






    var responseMovieDetail:GetMovieDetail?=null
    var responseSeriesDetail:GetSeriesDetail?=null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val movieDetailCategory=findViewById<TextView>(R.id.movie_detail_category)

        val movieDetailOverview=findViewById<TextView>(R.id.movie_detail_overview)
        val movieDetailBudget=findViewById<TextView>(R.id.movie_detail_budget)
        val movieName=findViewById<TextView>(R.id.movie_name)
        val movieCountry=findViewById<TextView>(R.id.movie_country)
        val movieDate=findViewById<TextView>(R.id.movie_release_date)
        val movieVoteAverage=findViewById<TextView>(R.id.movie_vote_avarage)

            if(isItMovie){
                responseMovieDetail=ApiCall().callMovieDetail()
                Glide.with(this).load("https://image.tmdb.org/t/p/w300/"+responseMovieDetail?.posterPath)
                    .thumbnail(Glide.with(this).load(R.drawable.abc_ic_go_search_api_material))
                    .transition(DrawableTransitionOptions.withCrossFade()).into(movie_detail_image)

                movieName.text=responseMovieDetail?.title
                movieDetailBudget.text="Movie budget: "+responseMovieDetail?.budget.toString()+" $"
                movieDetailOverview.text=responseMovieDetail?.overview
                var categoriesList:String?=""
                for (item in 0..responseMovieDetail?.genres!!.size-1) {
                    if(item==responseMovieDetail?.genres!!.size-1)
                        categoriesList += responseMovieDetail?.genres!![item].name
                    else
                        categoriesList += responseMovieDetail?.genres!![item].name + "-"
                }
                movieDetailCategory.text= "Movie Category: $categoriesList"
                movieCountry.text="Production Country: "+responseMovieDetail?.productionCountries!![0].name
                movieDate.text="Release Date: "+responseMovieDetail?.releaseDate
                movieVoteAverage.text="Vote: "+responseMovieDetail?.voteAverage

            }
        else{
                responseSeriesDetail=ApiCall().callSeriesDetail()
                Glide.with(this).load("https://image.tmdb.org/t/p/w300/"+responseSeriesDetail?.posterPath)
                    .thumbnail(Glide.with(this).load(R.drawable.abc_ic_go_search_api_material))
                    .transition(DrawableTransitionOptions.withCrossFade()).into(movie_detail_image)

                movieName.text=responseSeriesDetail?.name

                movieDetailOverview.text=responseSeriesDetail?.overview
                var categoriesList:String?=""
                for (item in 0..responseSeriesDetail?.genres!!.size-1) {
                    if(item==responseSeriesDetail?.genres!!.size-1)
                        categoriesList += responseSeriesDetail?.genres!![item].name
                    else
                        categoriesList += responseSeriesDetail?.genres!![item].name + "-"
                }
              movieDetailBudget.text="Total Season-Episode: "+responseSeriesDetail?.numberOfSeasons.toString()+"-"+responseSeriesDetail?.numberOfEpisodes.toString()
                movieDetailCategory.text= "Movie Category: $categoriesList"
                movieCountry.text="Production Company: "+responseSeriesDetail?.productionCompanies!![0].name
                movieDate.text="First Release Date: "+responseSeriesDetail?.firstAirDate
                movieVoteAverage.text="Vote: "+responseSeriesDetail?.voteAverage


            }


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

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
