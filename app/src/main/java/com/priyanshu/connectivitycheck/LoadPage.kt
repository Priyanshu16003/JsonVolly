package com.priyanshu.connectivitycheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import com.android.volley.Request
import com.squareup.picasso.Picasso

class LoadPage : AppCompatActivity() {
    private lateinit var courseNameTV : TextView
    private lateinit var courseTracksTV : TextView
    private lateinit var courseBatchTV : TextView
    private lateinit var courseCV : CardView
    private lateinit var courseIV : ImageView
    private lateinit var loadingPB : ProgressBar

    private val url : String = "https://jsonkeeper.com/b/63OH"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_page)

        loadingPB = findViewById(R.id.idLoadingPB)
        courseCV = findViewById(R.id.idCVCourse)
        courseNameTV = findViewById(R.id.idTVCourseName)
        courseTracksTV = findViewById(R.id.idTVTracks)
        courseBatchTV = findViewById(R.id.idTVBatch)
        courseIV = findViewById(R.id.idIVCourse)

        val queue = Volley.newRequestQueue(this@LoadPage)
        Log.d("a", "23")
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            { response ->
                Log.d("a", "" + queue.toString())

                loadingPB.visibility = View.GONE

                courseCV.visibility = View.VISIBLE

                try {
                    val courseName: String = response.getString("courseName")
                    val courseTracks: String = response.getString("courseTracks")
                    val courseMode: String = response.getString("courseMode")
                    val courseImageURL: String = response.getString("courseimg")

                    courseNameTV.text = courseName
                    Log.d("a", "db")
                    courseTracksTV.text = courseTracks
                    courseBatchTV.text = courseMode

                    Picasso.get().load(courseImageURL).into(courseIV)
                    Log.d("a", "wqe")
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, { error ->
                Toast.makeText(this, "Fail to get data..", Toast.LENGTH_LONG).show()
            })

        queue.add(jsonObjectRequest)
    }
}