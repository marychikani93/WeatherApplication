package com.example.myweatherappchallenge.api

import com.example.myweatherappchallenge.BuildConfig
import com.example.myweatherappchallenge.data.model.LocationResponse
import com.example.myweatherappchallenge.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getWeather(
        @Query("appid") appid: String = BuildConfig.API_KEY,
        @Query("q") city: String,
    ): WeatherResponse

    @GET("geo/1.0/reverse")
    suspend fun getLocation(
        @Query("appid") appid: String = BuildConfig.API_KEY,
        @Query("lat") lat: Long,
        @Query("long") long: Long,
        @Query("limit") limit: Int = 5,
    ): LocationResponse
}