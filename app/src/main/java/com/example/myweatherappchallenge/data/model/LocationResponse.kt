package com.example.myweatherappchallenge.data.model

import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String,
)

fun LocationResponse.toLocation(): Location = Location(
    name = name,
    country = country,
)