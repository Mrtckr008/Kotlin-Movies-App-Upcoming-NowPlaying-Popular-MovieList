package com.example.nytimesmoviesreview.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade

import com.example.nytimesmoviesreview.R
import com.example.nytimesmoviesreview.dto.Result
import com.example.nytimesmoviesreview.dto.ResultPopularMovies


class MoviesViewHolderGetPopular(viewGroup: ViewGroup):RecyclerView.ViewHolder
    (LayoutInflater.from(viewGroup.context).inflate(R.layout.movies_item_list,viewGroup,false)){
    private val txtDisplayTitle by lazy { itemView.findViewById<TextView>(R.id.txtDisplayTitle)}
    private val txtHeadline by lazy {itemView.findViewById<TextView>(R.id.txtHeadline)}
    private val txtOpeningDate by lazy { itemView.findViewById<TextView>(R.id.txtOpeningDate) }
    private val imgViewImageUrl by lazy { itemView.findViewById<ImageView>(R.id.imgViewImageUrl) }


    fun bindTo(MoviesDto: ResultPopularMovies) {
        txtDisplayTitle.text = MoviesDto.title
        txtHeadline.text = "Popularity Point: "+MoviesDto.popularity.toString()
        txtOpeningDate.text = "Release Date: "+MoviesDto.releaseDate

        Glide.with(itemView.context).load("https://image.tmdb.org/t/p/original/"+MoviesDto.posterPath)
            .thumbnail(Glide.with(itemView.context).load(R.drawable.abc_ic_go_search_api_material))
            .transition(withCrossFade()).into(imgViewImageUrl)

    }



}

