package com.example.myweatherappchallenge.data.model

data class Weather(
    val temperature: Int,
    val date: String,
    val wind: Int,
    val humidity: Int,
    val name: String,
    val feelsLike: FeelsLike,
    val cloud: Int,
    val country: String,
)