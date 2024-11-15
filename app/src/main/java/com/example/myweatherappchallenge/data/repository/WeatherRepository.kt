package com.example.myweatherappchallenge.data.repository

import com.example.myweatherappchallenge.data.model.Location
import com.example.myweatherappchallenge.data.model.Weather
import com.example.myweatherappchallenge.utils.Result
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    fun getCurrentWeather(city: String): Flow<Result<Weather>>
    fun getCurrentLocation(long: Long, lat: Long): Flow<Result<Location>>
}