package com.example.nytimesmoviesreview.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

import android.support.v7.widget.LinearLayoutManager
import com.example.nytimesmoviesreview.dto.ResultPopularMovies


class MoviesAdapterGetPopular(moviesList:List<ResultPopularMovies>):RecyclerView.Adapter<MoviesViewHolderGetPopular>() {

    var moviesList=moviesList
    private val layoutManager: LinearLayoutManager? = null
    override fun onCreateViewHolder(parent: ViewGroup, ViewType: Int): MoviesViewHolderGetPopular {
        return MoviesViewHolderGetPopular(parent)
    }

    override fun onBindViewHolder(holder: MoviesViewHolderGetPopular, position: Int) {
        holder.bindTo(moviesList[position])
    }

    override fun getItemCount(): Int {
        return moviesList.size
     }
    fun setMovieList(moviesList: List<ResultPopularMovies>){
        this.moviesList=moviesList
    }


/*    override fun onViewRecycled(holder: MoviesViewHolder) {
        val position:Int = holder.adapterPosition
        fi
    }*/

}