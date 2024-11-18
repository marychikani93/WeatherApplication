package com.example.myweatherappchallenge.data.repository

import com.example.myweatherappchallenge.data.model.LocationModel
import com.example.myweatherappchallenge.data.model.WeatherModel
import com.example.myweatherappchallenge.utils.Result
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getCurrentWeather(city: String): Flow<Result<WeatherModel>>
    fun getCurrentLocation(long: Double, lat: Double): Flow<Result<LocationModel>>
}