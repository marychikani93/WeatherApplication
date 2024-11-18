package com.example.myweatherappchallenge.ui

import com.example.myweatherappchallenge.WeatherViewModel
import com.example.myweatherappchallenge.data.FakeDataStoreRepository
import com.example.myweatherappchallenge.data.FakeWeatherRepository
import com.example.myweatherappchallenge.data.FakeWeatherRepository.fakeWeather
import com.example.myweatherappchallenge.data.repository.WeatherRepository
import com.example.myweatherappchallenge.data.repository.DataStoreRepository
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class WeatherViewModelTest {

    private lateinit var viewModel: WeatherViewModel

    private val weatherRepository: WeatherRepository = FakeWeatherRepository
    private val dataStore: DataStoreRepository = FakeDataStoreRepository

    @Before
    fun setUp() {
        viewModel = WeatherViewModel(weatherRepository, dataStore)
    }

    @Test
    fun `when getWeather completes, it should emit success state`() = runTest {

        viewModel.weatherState.test {

            assertEquals(WeatherUiState(weather = fakeWeather), awaitItem())
        }
    }
}