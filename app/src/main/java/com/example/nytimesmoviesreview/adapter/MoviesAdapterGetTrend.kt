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
import com.example.nytimesmoviesreview.dto.Result
import com.example.nytimesmoviesreview.dto.ResultUpcoming
import com.example.nytimesmoviesreview.dto.TrendMoviesAndSeriesResult


class MoviesAdapterGetUpcoming(moviesList:List<TrendMoviesAndSeriesResult>):RecyclerView.Adapter<MoviesViewHolderGetUpcoming>() {

    var moviesList=moviesList
    private val layoutManager: LinearLayoutManager? = null
    override fun onCreateViewHolder(parent: ViewGroup, ViewType: Int): MoviesViewHolderGetUpcoming {
        return MoviesViewHolderGetUpcoming(parent)
    }

    override fun onBindViewHolder(holder: MoviesViewHolderGetUpcoming, position: Int) {
        holder.bindTo(moviesList[position])
        holder.itemView.setOnClickListener{
            System.out.println("mcmcClick"+moviesList[position].TrendMoviesAndSeriesid)

        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
     }
    fun setMovieList(moviesList: List<TrendMoviesAndSeriesResult>){
        this.moviesList=moviesList
    }


/*    override fun onViewRecycled(holder: MoviesViewHolder) {
        val position:Int = holder.adapterPosition
        fi
    }*/

}


class MoviesViewHolderGetUpcoming(viewGroup: ViewGroup):RecyclerView.ViewHolder
    (LayoutInflater.from(viewGroup.context).inflate(R.layout.movies_item_list,viewGroup,false)){
    private val txtDisplayTitle by lazy { itemView.findViewById<TextView>(R.id.txtDisplayTitle)}
    private val txtHeadline by lazy {itemView.findViewById<TextView>(R.id.txtHeadline)}
    private val txtOpeningDate by lazy { itemView.findViewById<TextView>(R.id.txtOpeningDate) }
    private val imgViewImageUrl by lazy { itemView.findViewById<ImageView>(R.id.imgViewImageUrl) }


    fun bindTo(MoviesDto: TrendMoviesAndSeriesResult) {
        if(MoviesDto.title!=null)
        txtDisplayTitle.text = MoviesDto.title
        else txtDisplayTitle.text = MoviesDto.name
        txtHeadline.text = "Popularity Point: "+MoviesDto.popularity.toString()
        if (MoviesDto.releaseDate!=null)
        txtOpeningDate.text = "Release Date: "+MoviesDto.releaseDate
        else
            txtOpeningDate.text = "Overview: "+MoviesDto.overview

        Glide.with(itemView.context).load("https://image.tmdb.org/t/p/w300/"+MoviesDto.posterPath)
            .thumbnail(Glide.with(itemView.context).load(R.drawable.abc_ic_go_search_api_material))
            .transition(DrawableTransitionOptions.withCrossFade()).into(imgViewImageUrl)

    }



}
