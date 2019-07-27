package com.example.nytimesmoviesreview.adapter

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.nytimesmoviesreview.ApiCalls.ApiCall
import com.example.nytimesmoviesreview.DetailActivity
import com.example.nytimesmoviesreview.R
import com.example.nytimesmoviesreview.dto.ResultPopularSeries



class MoviesAdapterGetPopularSeries(moviesList:List<ResultPopularSeries>,var context:Context):RecyclerView.Adapter<SeriesViewHolderGetUpcoming>() {

    var moviesList=moviesList
    private val layoutManager: LinearLayoutManager? = null
    override fun onCreateViewHolder(parent: ViewGroup, ViewType: Int): SeriesViewHolderGetUpcoming {
        return SeriesViewHolderGetUpcoming(parent)
    }

    override fun onBindViewHolder(holder: SeriesViewHolderGetUpcoming, position: Int) {
        holder.bindTo(moviesList[position])

        holder.itemView.setOnClickListener{
            System.out.println("mcmcClick"+moviesList[position].id)
            DetailActivity.isItMovie=false
            ApiCall.movieId=moviesList[position].id?.toString()
            val intent = Intent(context, DetailActivity::class.java)

            ContextCompat.startActivity(context, intent, null)

            return@setOnClickListener
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
     }
    fun setMovieList(moviesList: List<ResultPopularSeries>){
        this.moviesList=moviesList
    }
}


class SeriesViewHolderGetUpcoming(viewGroup: ViewGroup):RecyclerView.ViewHolder
    (LayoutInflater.from(viewGroup.context).inflate(R.layout.movies_item_list,viewGroup,false)){
    private val txtDisplayTitle by lazy { itemView.findViewById<TextView>(R.id.txtDisplayTitle)}
    private val txtHeadline by lazy {itemView.findViewById<TextView>(R.id.txtHeadline)}
    private val txtOpeningDate by lazy { itemView.findViewById<TextView>(R.id.txtOpeningDate) }
    private val imgViewImageUrl by lazy { itemView.findViewById<ImageView>(R.id.imgViewImageUrl) }



    fun bindTo(MoviesDto: ResultPopularSeries) {

        View.OnClickListener{
            System.out.println("mcmcClick"+MoviesDto.id)
        }
        txtDisplayTitle.text = MoviesDto.name
        txtHeadline.text = "Popularity Point: "+MoviesDto.popularity.toString()
        txtOpeningDate.text = "Overview: "+MoviesDto.overview

        Glide.with(itemView.context).load("https://image.tmdb.org/t/p/w300/"+MoviesDto.posterPath)
            .thumbnail(Glide.with(itemView.context).load(R.drawable.abc_ic_go_search_api_material))
            .transition(DrawableTransitionOptions.withCrossFade()).into(imgViewImageUrl)

    }



}
