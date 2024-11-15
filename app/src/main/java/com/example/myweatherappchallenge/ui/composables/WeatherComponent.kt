package com.example.myweatherappchallenge.ui.composables

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myweatherappchallenge.WeatherViewModel
import com.example.myweatherappchallenge.ui.WeatherUiState

@Composable
fun WeatherComponent(
    modifier: Modifier = Modifier,
    viewModel: WeatherViewModel,
) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        WeatherContent(
            modifier = modifier.padding(innerPadding),
            viewModel = viewModel,
        )
    }
    /*val context = LocalContext.current
    val requestLocationPermission by WeatherViewModel.requestLocationPermission.collectAsState()

    if (requestLocationPermission) {
        LocationPermissionComponent(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION,
            stringResource(id = R.string.permission_location_rationale),
        ) { permissionAction ->

            when (permissionAction) {
                is PermissionAction.OnPermissionGranted -> {
                    Log.d(TAG, "Permission grant successful") {
                        locationViewModel.setRequestLocationPermission(false)
                    }
                    is PermissionAction.OnPermissionDenied -> {
                        Log.d(TAG, "Permission grant denied")
                        locationViewModel.setRequestLocationPermission(false)
                    }
                }

            }
        }
    }*/
}

@Composable
fun WeatherContent(
    modifier: Modifier,
    viewModel: WeatherViewModel,
) {

    var city by remember { mutableStateOf("") }
    val weatherState by viewModel.weatherState.collectAsState()
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
            IconButton(onClick = { viewModel.getWeatherInfo(city) }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
        }
        WeatherDetails(weatherState)
    }
}

@Composable
fun WeatherDetails(
    data: WeatherUiState
){
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
            model = "https:${data.weather?.feelsLike?.icon}".replace("64x64", "128x128"),
            contentDescription = "Weather Icon",
            contentScale = ContentScale.Crop,
            //placeholder = painterResource(R.drawable.baseline_broken_image_24), // replace with your actual placeholder
            //error = painterResource(R.drawable.baseline_error_24)
        )

//        Text(text = data.current.condition.text, fontSize = 64.sp)
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
                    WeatherItem(value = data.weather?.feelsLike?.maxTemp.toString(), key = "max temp")
                    WeatherItem(value = data.weather?.feelsLike?.minTemp.toString(), key = "min temp")
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