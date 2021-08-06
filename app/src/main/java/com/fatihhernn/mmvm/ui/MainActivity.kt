package com.fatihhernn.mmvm.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.fatihhernn.mmvm.R
import com.fatihhernn.mmvm.data.remote.NetworkApiService
import com.fatihhernn.mmvm.di.EndPoint
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {



    @Inject
    lateinit var service: NetworkApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity","$service")
    }
}