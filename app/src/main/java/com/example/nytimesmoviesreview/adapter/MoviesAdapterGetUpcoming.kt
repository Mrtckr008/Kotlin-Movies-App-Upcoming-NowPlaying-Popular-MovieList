package com.example.nytimesmoviesreview.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

import android.support.v7.widget.LinearLayoutManager
import com.example.nytimesmoviesreview.dto.Result
import com.example.nytimesmoviesreview.dto.ResultUpcoming


class MoviesAdapterGetUpcoming(moviesList:List<ResultUpcoming>):RecyclerView.Adapter<MoviesViewHolderGetUpcoming>() {

    var moviesList=moviesList
    private val layoutManager: LinearLayoutManager? = null
    override fun onCreateViewHolder(parent: ViewGroup, ViewType: Int): MoviesViewHolderGetUpcoming {
        return MoviesViewHolderGetUpcoming(parent)
    }

    override fun onBindViewHolder(holder: MoviesViewHolderGetUpcoming, position: Int) {
        holder.bindTo(moviesList[position])
    }

    override fun getItemCount(): Int {
        return moviesList.size
     }
    fun setMovieList(moviesList: List<ResultUpcoming>){
        this.moviesList=moviesList
    }


/*    override fun onViewRecycled(holder: MoviesViewHolder) {
        val position:Int = holder.adapterPosition
        fi
    }*/

}