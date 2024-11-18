package com.example.myweatherappchallenge.ui

import app.cash.turbine.test
import com.example.myweatherappchallenge.WeatherViewModel
import com.example.myweatherappchallenge.data.FakeWeatherRepository
import com.example.myweatherappchallenge.data.FakeWeatherRepository.fakeWeather
import com.example.myweatherappchallenge.data.repository.DataStoreRepository
import com.example.myweatherappchallenge.data.repository.WeatherRepository
import com.example.myweatherappchallenge.utils.LocationUtils
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class WeatherViewModelTest {

    private lateinit var viewModel: WeatherViewModel

    private val weatherRepository: WeatherRepository = FakeWeatherRepository
    private val dataStore: DataStoreRepository = mockk()
    private val location: LocationUtils = mockk()

    @Before
    fun setUp() {
        viewModel = WeatherViewModel(
            repository = weatherRepository,
            dataStoreCity = dataStore,
            location = location
        )
    }

    @Test
    fun `when getWeather completes, it should emit success state`() = runTest {

        viewModel.weatherState.test {
            assertEquals(WeatherUiState(weather = fakeWeather), awaitItem())
        }
    }
}