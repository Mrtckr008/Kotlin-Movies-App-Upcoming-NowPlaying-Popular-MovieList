package com.example.nytimesmoviesreview.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.nytimesmoviesreview.R
import com.example.nytimesmoviesreview.dto.ResultGetTopRatedSeries
import com.example.nytimesmoviesreview.dto.ResultPopularSeries



class SeriesAdapterGetTopRatedSeries(moviesList:List<ResultGetTopRatedSeries>):RecyclerView.Adapter<SeriesViewHolderGetTopRated>() {

    var moviesList=moviesList
    private val layoutManager: LinearLayoutManager? = null
    override fun onCreateViewHolder(parent: ViewGroup, ViewType: Int): SeriesViewHolderGetTopRated {
        return SeriesViewHolderGetTopRated(parent)
    }

    override fun onBindViewHolder(holder: SeriesViewHolderGetTopRated, position: Int) {
        holder.bindTo(moviesList[position])
        holder.itemView.setOnClickListener{
            System.out.println("mcmcClick"+moviesList[position].id)

        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
     }
    fun setMovieList(moviesList: List<ResultGetTopRatedSeries>){
        this.moviesList=moviesList
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
