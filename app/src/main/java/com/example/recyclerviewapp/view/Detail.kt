package com.example.recyclerviewapp.view

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerviewapp.R

class Detail : AppCompatActivity() {
    private var movieTitle: TextView? = null
    private var movieDetail: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)


        movieTitle = findViewById(R.id.movieTitle)
        movieDetail = findViewById(R.id.movieDetail)

        val intent = intent
        val title = intent.getStringExtra("movie_title")
        val detail = intent.getStringExtra("movie_description")

        movieTitle?.text = title
        movieDetail?.text = detail
    }
}
