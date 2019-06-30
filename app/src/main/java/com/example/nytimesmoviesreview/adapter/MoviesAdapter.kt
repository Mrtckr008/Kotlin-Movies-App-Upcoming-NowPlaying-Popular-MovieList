package com.example.nytimesmoviesreview.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

import android.support.v7.widget.LinearLayoutManager
import com.example.nytimesmoviesreview.dto.Result


class MoviesAdapter(moviesList:List<Result>):RecyclerView.Adapter<MoviesViewHolder>() {

    var moviesList=moviesList
    private val layoutManager: LinearLayoutManager? = null
    override fun onCreateViewHolder(parent: ViewGroup, ViewType: Int): MoviesViewHolder {
        return MoviesViewHolder(parent)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bindTo(moviesList[position])
    }

    override fun getItemCount(): Int {
        return moviesList.size
     }
    fun setMovieList(moviesList: List<Result>){
        this.moviesList=moviesList
    }


/*    override fun onViewRecycled(holder: MoviesViewHolder) {
        val position:Int = holder.adapterPosition
        fi
    }*/

}