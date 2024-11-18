package com.example.myweatherappchallenge

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherappchallenge.data.repository.DataStoreRepository
import com.example.myweatherappchallenge.data.repository.WeatherRepository
import com.example.myweatherappchallenge.ui.CityUiState
import com.example.myweatherappchallenge.ui.WeatherUiState
import com.example.myweatherappchallenge.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val dataStoreCity: DataStoreRepository,
    private val location: com.example.myweatherappchallenge.utils.Location,
    private val repository: WeatherRepository,
) : ViewModel() {

    private val _weatherState: MutableStateFlow<WeatherUiState> =
        MutableStateFlow(WeatherUiState(inProgress = true))
    val weatherState: StateFlow<WeatherUiState> = _weatherState.asStateFlow()

    val cityUiState: StateFlow<CityUiState> =
        dataStoreCity.getCity.map { name ->
            CityUiState(name)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = CityUiState("Unknown")
        )

    private fun saveCity(city: String) {
        viewModelScope.launch {
            dataStoreCity.saveCity(city)
        }
    }

    fun getCurrentLocation() {
        viewModelScope.launch(Dispatchers.IO) {
            location.getCurrentLocation()?.let { getCityByLocation(it) } // Location
        }
    }

    private fun getCityByLocation(currentLocation: Location) {
        repository.getCurrentLocation(currentLocation.longitude, currentLocation.latitude)
            .map { result ->
                when (result) {
                    is Result.Success -> {
                        _weatherState.update {
                            it.copy(
                                lastSearchText = result.data.name + "," + result.data.country
                            )
                        }
                        getWeatherInfo(city = result.data.name + "," + result.data.country)
                    }

                    is Result.Error -> {
                        _weatherState.update {
                            it.copy(
                                showDetails = false,
                                errorMessage = result.errorMessage
                            )
                        }
                    }
                }
            }.launchIn(viewModelScope)
    }

    fun getWeatherInfo(city: String) {
        saveCity(city)
        repository.getCurrentWeather(city).map { result ->
            when (result) {
                is Result.Success -> {
                    _weatherState.update {
                        it.copy(
                            lastSearchText = city,
                            showDetails = true,
                            weather = result.data
                        )
                    }
                }

                is Result.Error -> {
                    _weatherState.update {
                        it.copy(
                            showDetails = false,
                            errorMessage = result.errorMessage
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }
}