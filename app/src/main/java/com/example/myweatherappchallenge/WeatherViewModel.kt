package com.example.myweatherappchallenge

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweatherappchallenge.data.model.Location
import com.example.myweatherappchallenge.data.repository.WeatherRepository
import com.example.myweatherappchallenge.ui.SearchState
import com.example.myweatherappchallenge.ui.WeatherUiState
import com.example.myweatherappchallenge.utils.DEFAULT_CITY
import com.example.myweatherappchallenge.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
) : ViewModel() {

    private val _weatherState: MutableStateFlow<WeatherUiState> =
        MutableStateFlow(WeatherUiState(inProgress = true))
    val weatherState: StateFlow<WeatherUiState> = _weatherState.asStateFlow()

    private val _searchComponent: MutableState<SearchState> =
        mutableStateOf(value = SearchState.DISABLED)
    val searchComponent: State<SearchState> = _searchComponent

    private val _searchText: MutableState<String> = mutableStateOf( value = "")
    val searchText: State<String> = _searchText

    private val _requestLocationPermission: MutableStateFlow<Boolean> =  MutableStateFlow(false)
    val requestLocationPermission = _requestLocationPermission.asStateFlow()

    fun setRequestLocationPermission(request: Boolean) {
        _requestLocationPermission.value = request
    }

    fun setSearchState(value: SearchState) {
        _searchComponent.value = value
    }

    fun setSearchText(value: String) {
        _searchText.value = value
    }

    init {
        getWeatherInfo()
    }


    fun getWeatherInfo(city: String = DEFAULT_CITY) {
        repository.getCurrentWeather(city).map { result ->
            when (result) {
                is Result.Success -> {
                    _weatherState.value = WeatherUiState(
                        weather = result.data
                    )
                }

                is Result.Error -> {
                    _weatherState.value = WeatherUiState(
                        errorMessage = result.errorMessage
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}