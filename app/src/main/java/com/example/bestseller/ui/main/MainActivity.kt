package com.example.bestseller.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bestseller.R
import com.example.bestseller.ui.search.reactive.ReactiveSearchActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonReactive.setOnClickListener {
            startActivity(Intent(this, ReactiveSearchActivity::class.java))
        }
    }
}
