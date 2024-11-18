package com.example.myweatherappchallenge.utils

import android.location.Location

interface Location {
    suspend fun getCurrentLocation(): Location?
}