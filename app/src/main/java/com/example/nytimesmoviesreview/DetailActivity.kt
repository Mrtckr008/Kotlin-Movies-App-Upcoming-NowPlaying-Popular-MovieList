package com.example.nytimesmoviesreview

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


class DetailActivity : AppCompatActivity() {







    var responseMovieDetail:GetMovieDetail?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val movieDetailCategory=findViewById<TextView>(R.id.movie_detail_category)

        val movieDetailOverview=findViewById<TextView>(R.id.movie_detail_overview)
        val movieDetailBudget=findViewById<TextView>(R.id.movie_detail_budget)
        val movieName=findViewById<TextView>(R.id.movie_name)
        val movieCountry=findViewById<TextView>(R.id.movie_country)
        val movieDate=findViewById<TextView>(R.id.movie_release_date)

        val movieVoteAvarage=findViewById<TextView>(R.id.movie_vote_avarage)





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
        movieDetailCategory.text="Movie Category: "+categoriesList
        movieCountry.text="Production Country: "+responseMovieDetail?.productionCountries!![0].name
        movieDate.text="Release Date: "+responseMovieDetail?.releaseDate
        movieVoteAvarage.text="Vote: "+responseMovieDetail?.voteAverage
    }



    fun goMovieHomePage(view: View){
        val browse = Intent(Intent.ACTION_VIEW, Uri.parse(responseMovieDetail!!.homepage))
        startActivity(browse)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
