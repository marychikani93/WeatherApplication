package com.example.myweatherappchallenge.utils

import android.location.Location

interface LocationUtils {
    suspend fun getCurrentLocation(): Location?
}