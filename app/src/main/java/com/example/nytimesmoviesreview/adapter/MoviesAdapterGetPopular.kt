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
import com.example.nytimesmoviesreview.MainActivity
import com.example.nytimesmoviesreview.R
import com.example.nytimesmoviesreview.dto.ResultPopularMovies
import kotlinx.android.synthetic.main.movies_item_list.view.*


class MoviesAdapterGetPopular(moviesList:List<ResultPopularMovies>,var context:Context):RecyclerView.Adapter<MoviesViewHolderGetPopular>() {

    var moviesList=moviesList
    private val layoutManager: LinearLayoutManager? = null
    override fun onCreateViewHolder(parent: ViewGroup, ViewType: Int): MoviesViewHolderGetPopular {
        return MoviesViewHolderGetPopular(parent)
    }

    override fun onBindViewHolder(holder: MoviesViewHolderGetPopular, position: Int) {
        holder.bindTo(moviesList[position])
        holder.itemView.setOnClickListener{
            System.out.println("mcmcClick"+moviesList[position].id)
            DetailActivity.isItMovie=true
            ApiCall.movieId=moviesList[position].id?.toInt().toString()

            val options: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context as Activity, it.imgViewImageUrl, "simple_activity_transition${moviesList[position].id}")


            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("posterpath", "https://image.tmdb.org/t/p/w300/"+moviesList[position].posterPath)
            intent.putExtra("movieTitle",moviesList[position].title)
            intent.putExtra("transitionId",moviesList[position].id.toString())
            ContextCompat.startActivity(context, intent, options.toBundle())
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
     }


/*    override fun onViewRecycled(holder: MoviesViewHolder) {
        val position:Int = holder.adapterPosition
        fi
    }*/

}

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

        Glide.with(itemView.context).load("https://image.tmdb.org/t/p/w300/"+MoviesDto.posterPath)
            .thumbnail(Glide.with(itemView.context).load(R.drawable.abc_ic_go_search_api_material))
            .transition(DrawableTransitionOptions.withCrossFade()).into(imgViewImageUrl)

    }



}
