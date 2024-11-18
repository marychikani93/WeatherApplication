package com.example.myweatherappchallenge.ui

import com.example.myweatherappchallenge.data.model.LocationModel
import com.example.myweatherappchallenge.data.model.WeatherModel

data class WeatherUiState(
    val weather: WeatherModel? = null,
    val inProgress: Boolean = false,
    val errorMessage: String? = null,
    val currentLocation: LocationModel? = null,
    val showDetails: Boolean = false,
    val lastSearchText: String? = null
)