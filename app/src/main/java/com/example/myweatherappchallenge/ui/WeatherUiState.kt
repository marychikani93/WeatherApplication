package com.example.myweatherappchallenge.ui

import com.example.myweatherappchallenge.data.model.Weather

data class WeatherUiState(
    val weather: Weather? = null,
    val inProgress: Boolean = false,
    val errorMessage: String? = null,
)