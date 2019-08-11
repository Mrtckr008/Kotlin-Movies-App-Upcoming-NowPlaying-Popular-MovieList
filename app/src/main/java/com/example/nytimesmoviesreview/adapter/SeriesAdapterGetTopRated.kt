package com.example.nytimesmoviesreview.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.nytimesmoviesreview.ApiCalls.ApiCall
import com.example.nytimesmoviesreview.DetailActivity
import com.example.nytimesmoviesreview.R
import com.example.nytimesmoviesreview.dto.ResultGetTopRatedSeries
import com.example.nytimesmoviesreview.dto.ResultPopularSeries
import kotlinx.android.synthetic.main.movies_item_list.view.*


class SeriesAdapterGetTopRatedSeries(moviesList:List<ResultGetTopRatedSeries>,var context:Context):RecyclerView.Adapter<SeriesViewHolderGetTopRated>() {

    var seriesList=moviesList
    private val layoutManager: LinearLayoutManager? = null
    override fun onCreateViewHolder(parent: ViewGroup, ViewType: Int): SeriesViewHolderGetTopRated {
        return SeriesViewHolderGetTopRated(parent)
    }

    override fun onBindViewHolder(holder: SeriesViewHolderGetTopRated, position: Int) {
        holder.bindTo(seriesList[position])
        holder.itemView.setOnClickListener{
            System.out.println("mcmcClick"+seriesList[position].id)
            DetailActivity.isItMovie=false
            ApiCall.movieId=seriesList[position].id?.toString()
            val options: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context as Activity, it.imgViewImageUrl, "simple_activity_transition${seriesList[position].id}")


            val intent = Intent(context, DetailActivity::class.java)
            ContextCompat.startActivity(context, intent, options.toBundle())
            return@setOnClickListener
        }
    }

    override fun getItemCount(): Int {
        return seriesList.size
     }
    fun setMovieList(moviesList: List<ResultGetTopRatedSeries>){
        this.seriesList=moviesList
    }

}


class SeriesViewHolderGetTopRated(viewGroup: ViewGroup):RecyclerView.ViewHolder
    (LayoutInflater.from(viewGroup.context).inflate(R.layout.movies_item_list,viewGroup,false)){
    private val txtDisplayTitle by lazy { itemView.findViewById<TextView>(R.id.txtDisplayTitle)}
    private val txtHeadline by lazy {itemView.findViewById<TextView>(R.id.txtHeadline)}
    private val txtOpeningDate by lazy { itemView.findViewById<TextView>(R.id.txtOpeningDate) }
    private val imgViewImageUrl by lazy { itemView.findViewById<ImageView>(R.id.imgViewImageUrl) }


    fun bindTo(MoviesDto: ResultGetTopRatedSeries) {

        txtDisplayTitle.text = MoviesDto.name
        txtHeadline.text = "Popularity Point: "+MoviesDto.popularity.toString()
        txtOpeningDate.text = "Overview: "+MoviesDto.overview

        Glide.with(itemView.context).load("https://image.tmdb.org/t/p/w300/"+MoviesDto.posterPath)
            .thumbnail(Glide.with(itemView.context).load(R.drawable.abc_ic_go_search_api_material))
            .transition(DrawableTransitionOptions.withCrossFade()).into(imgViewImageUrl)

    }



}
