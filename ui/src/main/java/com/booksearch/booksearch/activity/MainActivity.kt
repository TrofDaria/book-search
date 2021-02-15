package com.booksearch.booksearch.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.booksearch.booksearch.R
import com.booksearch.booksearch.activity.shared.viewmodel.SharedBookSearchViewModel

class MainActivity : AppCompatActivity() {

    private val sharedBookSearchViewModel: SharedBookSearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}