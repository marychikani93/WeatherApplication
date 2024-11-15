package com.example.myweatherappchallenge.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("coord") val cords: Cords,
    @SerializedName("weather") val weather: Weather,
    @SerializedName("base") val base: String,
    @SerializedName("main") val main: Main,
    @SerializedName("visibility") val visibility: Int,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("clouds") val clouds: Clouds,
    @SerializedName("dt") val dt: Int,
    @SerializedName("sys") val sys: Sys,
    @SerializedName("timezone") val timezone: Double,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("cod") val cod: Int,
) {
    data class Cords(
        @SerializedName("lat") val lat: Double,
        @SerializedName("lon") val lon: Double,
    )

    data class Weather(
        @SerializedName("id") val id: Int,
        @SerializedName("main") val main: String,
        @SerializedName("description") val description: String,
        @SerializedName("icon") val icon: String,
    )

    data class Main(
        @SerializedName("temp") val temp: Double,
        @SerializedName("feels_like") val feelsLike: Double,
        @SerializedName("temp_min") val tempMin: Double,
        @SerializedName("temp_max") val tempMax: Double,
        @SerializedName("pressure") val pressure: Int,
        @SerializedName("humidity") val humidity: Int,
        @SerializedName("sea_level") val seaLevel: Int,
        @SerializedName("grnd_level") val grndLevel: Int,
    )

    data class Wind(
        @SerializedName("speed") val speed: Double,
        @SerializedName("deg") val deg: Int,
    )

    data class Clouds(
        @SerializedName("all") val all: Int,
    )

    data class Sys(
        @SerializedName("type") val type: Int,
        @SerializedName("id") val id: Int,
        @SerializedName("country") val country: String,
        @SerializedName("sunrise") val sunrise: Int,
        @SerializedName("sunset") val sunset: Int,
    )
}

fun WeatherResponse.toWeather(): Weather = Weather(
    temperature = main.temp.toInt(),
    date = dt.toString(),
    wind = wind.speed.toInt(),
    humidity = main.humidity,
    name = name,
    cloud = clouds.all,
    country = sys.country,
    feelsLike = FeelsLike(
        maxTemp = main.tempMax.toInt().toString(),
        minTemp = main.tempMin.toInt().toString(),
        sunset = sys.sunset.toString(),
        sunrise = sys.sunrise.toString(),
        icon = weather.icon,
    )
)