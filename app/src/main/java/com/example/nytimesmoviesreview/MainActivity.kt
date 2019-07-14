package com.example.nytimesmoviesreview

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.*
import com.example.nytimesmoviesreview.adapter.ViewPagerAdapter
import android.view.View.GONE
import android.view.LayoutInflater
import com.example.nytimesmoviesreview.fragments.*
import com.example.nytimesmoviesreview.dto.GetPopularSeries
import com.example.nytimesmoviesreview.model.SeriesGetPopularModel
import com.example.nytimesmoviesreview.dto.GetTopRatedSeries
import com.example.nytimesmoviesreview.model.SeriesGetTopRatedModel
import com.example.nytimesmoviesreview.network.NytimesServiceInterface
import com.example.nytimesmoviesreview.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.app.Activity
import android.app.PendingIntent.getActivity
import android.view.WindowManager
import android.widget.EditText
import android.support.v4.content.ContextCompat.getSystemService
import android.view.inputmethod.InputMethodManager


class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    private var nav_view: NavigationView? = null
    private var toolbar: Toolbar? = null
    private var fragment: Fragment? = null
    private var drawer: DrawerLayout? = null
    lateinit var mFragmentManager: FragmentManager
    lateinit var mFragmentTransaction: FragmentTransaction


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_layout)
        CallSeriesApis()
        fragment = NowPlayingMoviesFragment()


        setSupportActionBar(toolbar)
        nav_view = findViewById(R.id.nav_view)
        toolbar = findViewById(R.id.toolbar_include)
        drawer = findViewById(R.id.drawer)
        setSupportActionBar(toolbar)


        var toggle = ActionBarDrawerToggle(this, drawer, toolbar, 0, 0)
        drawer?.addDrawerListener(toggle)

        nav_view?.setNavigationItemSelectedListener(this)

        mFragmentManager = supportFragmentManager
        mFragmentTransaction = mFragmentManager.beginTransaction()

        viewPager = findViewById(R.id.viewpager_main) as ViewPager
        setupViewMainPager(viewPager!!)

        tabLayout = findViewById(R.id.tabLayout) as TabLayout
        tabLayout!!.setupWithViewPager(viewPager)
        getSupportActionBar()?.setDisplayShowTitleEnabled(false)    //toolbar texti kaldırma-dynamic olarak biz ekleyebiliriz.
        toggle.syncState()      //toolbar icon çıkması için


    //    val mainLayout:ConstraintLayout=findViewById(R.id.constraintLayout)
        val mainFragment: Fragment? = getSupportFragmentManager().findFragmentById(R.id.main_fragment_view)
        val layoutInflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val childLayout = layoutInflater.inflate(R.layout.template_layout,findViewById(R.id.templateImage))
    }
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var id: Int = item.itemId
        if (id == R.id.movie_lists) run {
            drawer?.closeDrawers()          //seçildiğinde menünün kapanması için
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        if (id == R.id.series_lists) {
            drawer?.closeDrawers()
            changeFragment(MostPopularSeriesFragment())
         //   tabLayout?.setVisibility(GONE)
         //   viewPager?.setVisibility(GONE)
        }
        if (item.itemId == 1) {
            changeFragment(MostPopularMoviesFragment())
            tabLayout?.setVisibility(GONE)
        } else {

        }
        return true
    }

    fun openDrawerMenu(view: View) {
        drawer!!.openDrawer(Gravity.LEFT)

    }

    private fun setupViewMainPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(NowPlayingMoviesFragment(), "Now Playing Movies")
        adapter.addFragment(MostPopularMoviesFragment(), "Most Popular Movies")
        adapter.addFragment(TrendMoviesAndSeriesFragment(), "Trend Movies&Series")
        viewPager.adapter = adapter
    }

    private fun setupViewSeriesPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(NowPlayingMoviesFragment(), "Most Popular Series")
        adapter.addFragment(MostPopularMoviesFragment(), "Top Rated Series")
        viewPager.adapter = adapter
    }

    fun changeFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.main_fragment_view, MostPopularSeriesFragment())
        fragmentTransaction.addToBackStack(null)

        setupViewSeriesPager(viewPager!!)

        fragmentTransaction.replace(R.id.detail_fragment_linearlayout, TopRatedSeriesFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
        setupViewSeriesPager(viewPager!!)
    }

    fun CallSeriesApis(){
        if(SeriesGetPopularModel.getResponse()==null){
            val service= RetrofitClient.getClient().create(NytimesServiceInterface::class.java)
            val callPopularSeries=service.getPopularSeries("tv/popular?api_key=ac3cbd07a68825e9716c144bd088350f&language=en-US&page=1")

            callPopularSeries.enqueue(object : Callback<GetPopularSeries> {
                override fun onResponse(call: Call<GetPopularSeries>?, response: Response<GetPopularSeries>?) {
                    val movieList=ArrayList(response!!.body().results)
                    SeriesGetPopularModel.setResponse(movieList)
                }
                override fun onFailure(call: Call<GetPopularSeries>?, t: Throwable?) {
                }
            })
        }

        if(SeriesGetTopRatedModel.getResponse()==null){
            val service= RetrofitClient.getClient().create(NytimesServiceInterface::class.java)
            val callGetRatedSeries=service.getTopRatedSeries("tv/top_rated?api_key=ac3cbd07a68825e9716c144bd088350f&language=en-US&page=1")

            callGetRatedSeries.enqueue(object : Callback<GetTopRatedSeries> {
                override fun onResponse(call: Call<GetTopRatedSeries>?, response: Response<GetTopRatedSeries>?) {
                    val movieList=ArrayList(response!!.body().results)
                    SeriesGetTopRatedModel.setResponse(movieList)
                }
                override fun onFailure(call: Call<GetTopRatedSeries>?, t: Throwable?) {
                    System.out.println("mcmcmc:")
                }
            })
        }

    }

}






