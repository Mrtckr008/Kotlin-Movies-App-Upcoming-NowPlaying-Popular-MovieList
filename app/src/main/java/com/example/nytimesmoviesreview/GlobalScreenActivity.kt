package com.example.nytimesmoviesreview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.nytimesmoviesreview.adapter.MoviesAdapter
import com.example.nytimesmoviesreview.model.MovieNowPlayingModel
import kotlinx.android.synthetic.main.main_fragment.*

class GlobalScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_global_screen_activity)


        menuRecyclerView.apply {
            layoutManager= LinearLayoutManager(context)

            adapter= MoviesAdapter(MovieNowPlayingModel.getResponse()!!.toList())
    }
}
}
