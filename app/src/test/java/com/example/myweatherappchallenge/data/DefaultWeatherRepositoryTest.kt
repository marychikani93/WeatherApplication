package com.example.myweatherappchallenge.data

import com.example.myweatherappchallenge.api.WeatherApi
import com.example.myweatherappchallenge.data.model.WeatherModel
import com.example.myweatherappchallenge.data.model.toWeather
import com.example.myweatherappchallenge.data.repository.DefaultWeatherRepository
import com.example.myweatherappchallenge.utils.Result
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DefaultWeatherRepositoryTest {
    private lateinit var repository: DefaultWeatherRepository
    private val weatherApi = mockk<WeatherApi>()

    @Before
    fun setup() {
        repository = DefaultWeatherRepository(weatherApi)
    }

    @Test
    fun `when getWeatherForecast is called, it should emit loading state and then success state`() =
        runTest {
            coEvery {
                weatherApi.getWeather(
                    any(),
                    any(),
                    any()
                )
            } returns weatherApiResponse

            val results = mutableListOf<Result<WeatherModel>>()
            repository.getCurrentWeather("Merida").collect { result ->
                results.add(result)
            }
            assertEquals(Result.Success(weatherApiResponse.toWeather()), results[1])
        }
}