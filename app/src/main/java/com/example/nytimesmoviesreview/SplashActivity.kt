package com.example.nytimesmoviesreview

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import com.example.nytimesmoviesreview.ApiCalls.ApiCall
import java.lang.ref.WeakReference

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)




        val task = MyAsyncTask(this)
        task.execute(10)


    }


    class MyAsyncTask internal constructor(var context: SplashActivity) : AsyncTask<Int, String, String?>() {
        private var resp: String? = null
        private val activityReference: WeakReference<SplashActivity> = WeakReference(context)

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
