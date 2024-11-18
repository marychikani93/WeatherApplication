package com.example.myweatherappchallenge.data.repository

import com.example.myweatherappchallenge.api.WeatherApi
import com.example.myweatherappchallenge.data.model.LocationModel
import com.example.myweatherappchallenge.data.model.WeatherModel
import com.example.myweatherappchallenge.data.model.toLocation
import com.example.myweatherappchallenge.data.model.toWeather
import com.example.myweatherappchallenge.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DefaultWeatherRepository @Inject constructor(
    private val weatherApi: WeatherApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : WeatherRepository {
    override fun getCurrentWeather(city: String): Flow<Result<WeatherModel>> = flow {
        try {
            val result = weatherApi.getWeather(city = city).toWeather()
            emit(Result.Success(result))
        } catch (exception: HttpException) {
            emit(Result.Error(exception.message().toString()))
        } catch (exception: IOException) {
            emit(Result.Error("Please check your network connection and try again!"))
        }
    }.flowOn(dispatcher)

    override fun getCurrentLocation(long: Double, lat: Double): Flow<Result<LocationModel>> = flow {
        try {
            val result = weatherApi.getLocation(lon = long, lat = lat)[0].toLocation()
            emit(Result.Success(result))
        } catch (exception: HttpException) {
            emit(Result.Error(exception.message().toString()))
        } catch (exception: IOException) {
            emit(Result.Error("Please check your network connection and try again!"))
        }
    }.flowOn(dispatcher)
}