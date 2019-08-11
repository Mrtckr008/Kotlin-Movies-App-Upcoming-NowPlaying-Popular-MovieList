package com.example.nytimesmoviesreview.adapter

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.nytimesmoviesreview.DetailActivity
import com.example.nytimesmoviesreview.MainActivity
import com.example.nytimesmoviesreview.R
import com.example.nytimesmoviesreview.SearchActivity
import com.example.nytimesmoviesreview.dto.Result
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.ViewCompat
import kotlinx.android.synthetic.main.activity_splash.view.*
import kotlinx.android.synthetic.main.movies_item_list.view.*
import android.support.v4.content.ContextCompat.startActivity
import android.app.Activity
import android.support.v4.view.ViewCompat.getTransitionName
import android.support.v4.view.ViewCompat.setTransitionName
import android.view.View
import com.example.nytimesmoviesreview.ApiCalls.ApiCall
import kotlinx.android.synthetic.main.movies_item_list.*


class MoviesAdapter(moviesList:List<Result>,var context: Context):RecyclerView.Adapter<MoviesViewHolder>() {

    var moviesList=moviesList
    private val layoutManager: LinearLayoutManager? = null
    override fun onCreateViewHolder(parent: ViewGroup, ViewType: Int): MoviesViewHolder {
        return MoviesViewHolder(parent)
    }


    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bindTo(moviesList[position])

        holder.itemView.setOnClickListener{
        System.out.println("mcmcClick"+moviesList[position].id?.toInt())
            DetailActivity.isItMovie=true
            ApiCall.movieId=moviesList[position].id?.toInt().toString()

            val options: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context as Activity, it.imgViewImageUrl, "simple_activity_transition${moviesList[position].id?.toInt().toString()}")


            val intent = Intent(context, DetailActivity::class.java)
            startActivity(context,intent, options.toBundle())
return@setOnClickListener
          }
    }

    override fun getItemCount(): Int {
        return moviesList.size
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

        Glide.with(itemView.context).load("https://image.tmdb.org/t/p/w300/"+MoviesDto.posterPath)
            .thumbnail(Glide.with(itemView.context).load(R.drawable.abc_ic_go_search_api_material))
            .transition(DrawableTransitionOptions.withCrossFade()).into(imgViewImageUrl)
        itemView.setOnClickListener{

        System.out.println("mcmcClick"+ MoviesDto.id)
return@setOnClickListener
        }
    }



}