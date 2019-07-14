package com.example.nytimesmoviesreview

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.view.View
import com.example.nytimesmoviesreview.ApiCalls.ApiCall
import com.example.nytimesmoviesreview.dto.*
import com.example.nytimesmoviesreview.model.*
import com.example.nytimesmoviesreview.network.NytimesServiceInterface
import com.example.nytimesmoviesreview.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.ref.WeakReference

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)




        val task = MyAsyncTask(this)
        task.execute(10)


    }


    class MyAsyncTask internal constructor(var context: SearchActivity) : AsyncTask<Int, String, String?>() {
        private var resp: String? = null
        private val activityReference: WeakReference<SearchActivity> = WeakReference(context)

        override fun onPreExecute() {
            System.out.println("MCOnPreExecute")
            val activity = activityReference.get()
            if (activity == null || activity.isFinishing) return

        }

        override fun doInBackground(vararg params: Int?): String? {
            publishProgress("Calls Started") // Calls onProgressUpdate()
            try {
                ApiCall().calls()
            } catch (e: InterruptedException) {
                e.printStackTrace()
                resp = e.message
            } catch (e: Exception) {
                e.printStackTrace()
                resp = e.message
            }
            return resp                                                              //Need to find some way for if api has some problem.
        }


        override fun onPostExecute(result: String?) {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(context,intent,null)
        }
    }

}
