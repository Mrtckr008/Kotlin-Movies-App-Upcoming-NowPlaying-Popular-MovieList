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
import com.example.nytimesmoviesreview.Fragments.MainFragment
import com.example.nytimesmoviesreview.Fragments.PopularMoviesFragment
import com.example.nytimesmoviesreview.Fragments.TrendMoviesAndSeriesFragment
import com.example.nytimesmoviesreview.dto.GetPopularSeries
import com.example.nytimesmoviesreview.model.SeriesGetPopularModel
import com.example.nytimesmoviesreview.Fragments.MostPopularSeriesFragment
import com.example.nytimesmoviesreview.network.NytimesServiceInterface
import com.example.nytimesmoviesreview.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    private final var MENU_ITEM_ITEM1: Int = 1
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

        fragment = MainFragment()

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






        /*     val fragmentAdapter=MyPagerFragmentAdapter(supportFragmentManager)
        viewpager_main.adapter=fragmentAdapter          Tab Layout oluşturmak için
        tabLayout.setupWithViewPager(viewpager_main)*/

//        val menu = nav_view?.getMenu()
//        for (i in 1..2) {
//            menu?.add(Menu.NONE, i, Menu.NONE, "Runtime item $i")        //Ana başlık eklemek için
//        }

//        val subMenu = menu?.addSubMenu("Runtime 1")
        //      for (i in 1..2) {                   //Ana başlıktan alt başlığa gitmek için
        //        subMenu?.
        //     }

      //  supportFragmentManager.beginTransaction().replace(R.id.fragment_frame, fragment!!).commit()// frame layouta fragmentları yüklüyor.

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
            changeFragment(PopularMoviesFragment())
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
        adapter.addFragment(MainFragment(), "Now Playing Movies")
        adapter.addFragment(PopularMoviesFragment(), "Most Popular Movies")
        adapter.addFragment(TrendMoviesAndSeriesFragment(), "Trend Movies&Series")
        viewPager.adapter = adapter
    }

    private fun setupViewSeriesPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(MainFragment(), "Most Popular Series")
        adapter.addFragment(PopularMoviesFragment(), "Something of Series")
        adapter.addFragment(TrendMoviesAndSeriesFragment(), "Something of Series")
        viewPager.adapter = adapter
    }



    fun changeFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_fragment_view,
            MostPopularSeriesFragment()
        )
        fragmentTransaction.replace(R.id.detail_fragment_linearlayout,
            MostPopularSeriesFragment()
        )

        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
        setupViewSeriesPager(viewPager!!)


   //     supportFragmentManager.beginTransaction()
   //         .add(android.R.id.content, MostPopularSeriesFragment())
   //         .commit()
    }
}






