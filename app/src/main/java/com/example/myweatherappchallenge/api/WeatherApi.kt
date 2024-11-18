package com.example.myweatherappchallenge.api

import com.example.myweatherappchallenge.BuildConfig
import com.example.myweatherappchallenge.data.model.LocationResponse
import com.example.myweatherappchallenge.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("appid") appid: String = BuildConfig.API_KEY,
        @Query("q") city: String,
        @Query("units") units: String = "imperial", //in order to get f units
    ): WeatherResponse

    @GET("geo/1.0/reverse")
    suspend fun getLocation(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("limit") limit: Int = 5,
        @Query("appid") appid: String = BuildConfig.API_KEY,
    ): LocationResponse
}