package com.example.myweatherappchallenge.data.model

import com.google.gson.annotations.SerializedName

typealias LocationResponse = ArrayList<LocationElement>

data class LocationElement(
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String,
    @SerializedName("local_names") var localNames : LocalNames,
    @SerializedName("lat") var lat : Double,
    @SerializedName("lon") var lon : Double,
    @SerializedName("state") var state : String
) {
    data class LocalNames (
        @SerializedName("en") var en : String
    )
}

fun LocationElement.toLocation(): LocationModel = LocationModel(
    name = name,
    country = country,
)