package com.example.myweatherappchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.myweatherappchallenge.ui.screens.WeatherComponent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val weatherViewModel: WeatherViewModel by viewModels()
        setContent {
            WeatherComponent(
                weatherViewModel = weatherViewModel
            )
        }

    }
}