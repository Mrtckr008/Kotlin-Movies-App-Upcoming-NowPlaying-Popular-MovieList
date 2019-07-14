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
    

}
class MoviesViewHolder(viewGroup: ViewGroup):RecyclerView.ViewHolder
    (LayoutInflater.from(viewGroup.context).inflate(R.layout.movies_item_list,viewGroup,false)){
    private val txtDisplayTitle by lazy { itemView.findViewById<TextView>(R.id.txtDisplayTitle)}
    private val txtHeadline by lazy {itemView.findViewById<TextView>(R.id.txtHeadline)}
    private val txtOpeningDate by lazy { itemView.findViewById<TextView>(R.id.txtOpeningDate) }
    private val imgViewImageUrl by lazy { itemView.findViewById<ImageView>(R.id.imgViewImageUrl) }


    fun bindTo(MoviesDto: Result) {
        txtDisplayTitle.text = MoviesDto.title
        txtHeadline.text = "Popularity Point: "+MoviesDto.popularity.toString()
        txtOpeningDate.text = "Release Date: "+MoviesDto.releaseDate

        Glide.with(itemView.context).load("https://image.tmdb.org/t/p/original/"+MoviesDto.posterPath)
            .thumbnail(Glide.with(itemView.context).load(R.drawable.abc_ic_go_search_api_material))
            .transition(DrawableTransitionOptions.withCrossFade()).into(imgViewImageUrl)
itemView.setOnClickListener{



}
    }



}