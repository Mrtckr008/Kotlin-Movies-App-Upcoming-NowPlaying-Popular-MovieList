package com.example.nytimesmoviesreview

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
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
import com.example.nytimesmoviesreview.model.SeriesGetPopularModel
import com.example.nytimesmoviesreview.model.SeriesGetTopRatedModel
import com.example.nytimesmoviesreview.network.NytimesServiceInterface
import com.example.nytimesmoviesreview.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.os.StrictMode
import android.widget.EditText
import android.support.v7.app.AlertDialog
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import com.example.nytimesmoviesreview.Fragments.UserFavoriteListFragment
import com.example.nytimesmoviesreview.Fragments.UserWatchListFragment
import com.example.nytimesmoviesreview.Fragments.UserWatchedListFragment
import com.example.nytimesmoviesreview.dto.*
import com.example.nytimesmoviesreview.model.MovieSearchModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.filter_movie.*
import kotlinx.android.synthetic.main.filter_movie.view.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import java.lang.ref.WeakReference


class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    var decision=false
    private var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    private var nav_view: NavigationView? = null
    private var toolbar: Toolbar? = null
    private var fragment: Fragment? = null
    private var drawer: DrawerLayout? = null
    lateinit var mFragmentManager: FragmentManager
    lateinit var mFragmentTransaction: FragmentTransaction
    var myEventListenerVariable=0
var movieNameFilter:EditText?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drawer_layout)


        val task = PostOtherViewPager(this)                                                                    //  Use for download new feeds in background.
        task.execute(10)

        fragment = NowPlayingMoviesFragment()


        //login button click of custom layout
        val v = LayoutInflater.from(this).inflate(R.layout.filter_movie, null)

        movieNameFilter = v.findViewById<EditText>(R.id.movie_name_filtered)

        setSupportActionBar(toolbar)
        nav_view = findViewById(R.id.nav_view)
        toolbar = findViewById(R.id.toolbar_include)
        drawer = findViewById(R.id.drawer)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, 0, 0)
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

        fragment_frame.visibility=View.GONE
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
        else if (id == R.id.series_lists) {
            decision=false
            drawer?.closeDrawers()
            changeFragment(MostPopularSeriesFragment())

         //   tabLayout?.setVisibility(GONE)
         //   viewPager?.setVisibility(GONE)
        }
        else if(id==R.id.favorite_list){

            toolbar_title.visibility=View.VISIBLE
            toolbar_title.text="Favorite List"
            tabLayout?.visibility = View.GONE
            decision=true
            drawer?.closeDrawers()
            changeFragment(UserFavoriteListFragment())
        }

        else if(id==R.id.watched_list){
            toolbar_title.text="Watched List"
            tabLayout?.visibility = View.GONE
            toolbar_title.visibility=View.VISIBLE
            decision=true
            drawer?.closeDrawers()
            changeFragment(UserWatchedListFragment())
        }

        else if(id==R.id.watch_list){
            toolbar_title.text="Watch List"
            tabLayout?.visibility = View.GONE


            toolbar_title.visibility=View.VISIBLE
            decision=true
            drawer?.closeDrawers()
            changeFragment(UserWatchListFragment())
        }
        else{
            Toast.makeText(this,"This feature coming soon",Toast.LENGTH_SHORT).show()
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

    private fun setupViewUserListPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(UserFavoriteListFragment(), "Favorite List")
        adapter.addFragment(UserWatchedListFragment(), "Watched List")
        adapter.addFragment(UserWatchListFragment(), "Watch List")
        viewPager.adapter = adapter
    }
    fun changeFragment(fragment: Fragment) {
        if(!decision) {
            myEventListenerVariable=0
            val edit = findViewById<View>(R.id.search_edit_text) as EditText
            val filterButton=findViewById<View>(R.id.filterMovie) as ImageView
            filterButton.setVisibility(View.GONE)
            edit.setVisibility(View.GONE);
            search_button.setVisibility(View.VISIBLE)
            fragment_frame.visibility=View.GONE
            toolbar_title.visibility=View.GONE
            tabLayout?.visibility = View.VISIBLE
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.main_fragment_view, MostPopularSeriesFragment())
            fragmentTransaction.addToBackStack(null)
            setupViewSeriesPager(viewPager!!)
            fragmentTransaction.replace(R.id.detail_fragment_linearlayout, TopRatedSeriesFragment())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
            setupViewSeriesPager(viewPager!!)
        }
        else{
            myEventListenerVariable=0
            val edit = findViewById<View>(R.id.search_edit_text) as EditText
            val filterButton=findViewById<View>(R.id.filterMovie) as ImageView
            filterButton.setVisibility(View.GONE)
            edit.setVisibility(View.GONE);
            search_button.setVisibility(View.GONE)

            fragment_frame.visibility=View.VISIBLE
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_frame, fragment)
            fragmentTransaction.commit()
        }
    }


    class PostOtherViewPager internal constructor(var context: MainActivity) : AsyncTask<Int, String, String?>() {
        private var resp: String? = null
        private val activityReference: WeakReference<MainActivity> = WeakReference(context)
        override fun onPreExecute() {
            System.out.println("MCOnPreExecute")
            val activity = activityReference.get()
            if (activity == null || activity.isFinishing) return
        }

        override fun doInBackground(vararg params: Int?): String? {
            publishProgress("Calls Started") // Calls onProgressUpdate()
            try {
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
            } catch (e: InterruptedException) {
                e.printStackTrace()
                resp = e.message
            } catch (e: Exception) {
                e.printStackTrace()
                resp = e.message
            }
            return resp                                                                                                     //Need to find some way for if api has some problem.
        }
        override fun onPostExecute(result: String?) {
                        //When all of pager's first 5 feed download, progressbar is invisible.
        }
    }

    fun searchButton(view: View){
        val edit = findViewById<View>(R.id.search_edit_text) as EditText
        if(myEventListenerVariable==0) {
            val filterButton=findViewById<View>(R.id.filterMovie) as ImageView
            edit.isEnabled = true
            edit.isFocusable = true
            filterButton.setVisibility(View.VISIBLE)
            edit.setVisibility(View.VISIBLE);
            myEventListenerVariable++
        }
        else
        {
            val data = HashMap<String,String>()
            if(edit.text.toString()!="") {
                data.put("query",edit.text.toString())
                MovieSearchModel.queriesData=data
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)}
            else
                (Toast.makeText(this, "Please enter to movie name!", Toast.LENGTH_SHORT).show())
        }
    }

    fun filterMovie(view: View){
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.filter_movie, null)
        //AlertDialogBuilder
        val mBuilder = AlertDialog
            .Builder(this)
            .setView(mDialogView)
        //show dialog
        val  mAlertDialog = mBuilder.show()
        mAlertDialog.window.setLayout(1000, 800)                 // width and height have to calculate with screen size.

        mDialogView.search_movie.setOnClickListener {
            if(movieNameFilter?.text.toString()!=""){
                val data = HashMap<String,String>()

                data.put("query",movie_name_filtered.text.toString())
                if(movie_year_filter.text.toString()!="")
                data.put("year",movie_year_filter.text.toString())
                if(adult_checkbox.isChecked){
                    data.put("include_adult","true")
                }
                else data.put("include_adult","false")


                MovieSearchModel.queriesData=data

                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
}

            else{
                Toast.makeText(this, "Please enter to movie name!", Toast.LENGTH_SHORT).show()
            }



            //dismiss dialog
            mAlertDialog.dismiss()
            //get text from EditTexts of custom layout

            //set the input text in TextView

        }




    }
}






