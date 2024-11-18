package com.example.myweatherappchallenge.data

import com.example.myweatherappchallenge.data.model.WeatherResponse

val weatherApiResponse: WeatherResponse
    get() = WeatherResponse(
        cords = WeatherResponse.Cords(
            lon = -97.1081,
            lat = 32.7357
        ),
        weather = listOf(
            WeatherResponse.Weather(
                id = 800,
                main = "Clear",
                description = "clear sky",
                icon = "01d"
            )
        ),
        base = "stations",
        main = WeatherResponse.Main(
            temp = 296.32,
            pressure = 1001,
            humidity = 36,
            feelsLike = 70.0,
            tempMin = 35.0,
            tempMax = 70.0,
            seaLevel = 23,
            grndLevel = 32,
        ),
        visibility = 1000,
        wind = WeatherResponse.Wind(
            speed = 9.26,
            deg = 240,
        ),
        clouds = WeatherResponse.Clouds(
            all = 0
        ),
        dt = 1731965496,
        sys = WeatherResponse.Sys(
            type = 2,
            id = 2006353,
            country = "US",
            sunrise = 1731934914,
            sunset = 1731972352
        ),
        timezone = -21600.0,
        id = 4671240,
        name = "Arlington",
        cod = 200
    )
