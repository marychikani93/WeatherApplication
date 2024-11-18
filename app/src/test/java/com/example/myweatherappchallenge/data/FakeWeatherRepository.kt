package com.example.myweatherappchallenge.data

import com.example.myweatherappchallenge.data.model.FeelsLikeModel
import com.example.myweatherappchallenge.data.model.LocationModel
import com.example.myweatherappchallenge.data.model.WeatherModel
import com.example.myweatherappchallenge.data.repository.WeatherRepository
import com.example.myweatherappchallenge.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.math.max

object FakeWeatherRepository : WeatherRepository {
    fun getWeatherForecast(city: String): Flow<Result<WeatherModel>> {
        return flow {
            emit(Result.Success(fakeWeather))
            emit(Result.Error("ERROR TEST"))
        }
    }

    val fakeWeather = WeatherModel(
        temperature = 77,
        date = "",
        wind = 70,
        humidity = 82,
        name = "Arlington, US",
        feelsLike = FeelsLikeModel(
            maxTemp = "75",
            minTemp = "20",
            sunrise = "20",
            sunset = "75",
            icon = "210a",
        ),
        cloud = 65,
        country = "US",
    )

    override fun getCurrentWeather(city: String): Flow<Result<WeatherModel>> {
        TODO("Not yet implemented")
    }

    override fun getCurrentLocation(long: Double, lat: Double): Flow<Result<LocationModel>> {
        TODO("Not yet implemented")
    }
}