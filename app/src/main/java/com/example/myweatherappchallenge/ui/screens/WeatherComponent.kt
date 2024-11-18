package com.example.myweatherappchallenge.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myweatherappchallenge.WeatherViewModel
import com.example.myweatherappchallenge.ui.WeatherUiState
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@SuppressLint("PermissionLaunchedDuringComposition")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun WeatherComponent(
    modifier: Modifier = Modifier,
    weatherViewModel: WeatherViewModel,
) {
    val context = LocalContext.current
    //instantiate the city
    val savedCity by weatherViewModel.cityUiState.collectAsState()

    val permissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    val locationPermissions = rememberMultiplePermissionsState(
        permissions = permissions.asList()
    )

    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionsMap ->
        val areGranted = permissionsMap.values.reduce { acc, next -> acc && next }
        if (areGranted) {
            weatherViewModel.getCurrentLocation()
        } else {
            //val lastSearch = weatherViewModel.getPreviousSearchWord()
            savedCity.name?.let { weatherViewModel.getWeatherInfo(it) }
        }
    }

    //when the app get launched for the first time
    LaunchedEffect(Unit) {
        val hasLocationPermission = locationPermissions.allPermissionsGranted
        if (!hasLocationPermission) { // Check if permissions are already granted
            launcherMultiplePermissions.launch(permissions)
        } else {
            //val lastSearch = weatherViewModel.getPreviousSearchWord()
            savedCity.name?.let { weatherViewModel.getWeatherInfo(it) }
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

        WeatherContent(
            modifier = modifier.padding(innerPadding),
            viewModel = weatherViewModel,
        )
    }
}

@Composable
fun WeatherContent(
    modifier: Modifier,
    viewModel: WeatherViewModel,
) {
    val scope = rememberCoroutineScope()

    val weatherState by viewModel.weatherState.collectAsState()
    var city by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            OutlinedTextField(
                value = city,
                onValueChange = { city = it },
                label = { Text("Search City") },
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = {
                viewModel.getWeatherInfo(city)

            }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
        }
        if (weatherState.showDetails) {
            WeatherDetails(weatherState)
        }
    }
}

@Composable
fun WeatherDetails(
    data: WeatherUiState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location Icon",
                modifier = Modifier.size(40.dp)
            )
            Text(text = data.weather?.name.orEmpty(), fontSize = 24.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = data.weather?.country.orEmpty(), fontSize = 17.sp, color = Color.Gray)
        }
        Spacer(modifier = Modifier.height(16.dp))


        Text(text = "Condition", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "${data.weather?.temperature}°F", fontSize = 64.sp)

        AsyncImage(
            modifier = Modifier.size(160.dp),
            model = "https://openweathermap.org/img/wn/${data.weather?.feelsLike?.icon}@2x.png".replace(
                "64x64",
                "128x128"
            ),
            contentDescription = "Weather Icon",
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(16.dp))

        Card {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherItem(value = data.weather?.humidity.toString(), key = "Humidity")
                    WeatherItem(value = data.weather?.wind.toString(), key = "Wind Speed")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherItem(
                        value = data.weather?.feelsLike?.maxTemp.toString(),
                        key = "max temp"
                    )
                    WeatherItem(
                        value = data.weather?.feelsLike?.minTemp.toString(),
                        key = "min temp"
                    )
                }
            }
        }


    }
}

@Composable
fun WeatherItem(key: String, value: String) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = value, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = key, fontSize = 16.sp, fontWeight = FontWeight.SemiBold, color = Color.Gray)
    }
}

@Preview
@Composable
fun WeatherComponentPreview() {
    /*WeatherComponent(
        modifier = ,
        viewModel = WeatherViewModel(repository = )
    )*/
}