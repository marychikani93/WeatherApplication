package com.example.myweatherappchallenge.data.model

data class WeatherModel(
    val temperature: Int,
    val date: String,
    val wind: Int,
    val humidity: Int,
    val name: String,
    val feelsLike: FeelsLikeModel,
    val cloud: Int,
    val country: String,
)