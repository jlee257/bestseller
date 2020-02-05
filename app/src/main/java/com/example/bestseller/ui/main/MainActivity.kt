package com.example.bestseller.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bestseller.R
import com.example.bestseller.ui.search.imperative.SearchActivity
import com.example.bestseller.ui.search.impl.ReactiveSearchActivity2
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonImperative.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }

        buttonReactive.setOnClickListener {
            startActivity(Intent(this, ReactiveSearchActivity2::class.java))
        }
    }
}
