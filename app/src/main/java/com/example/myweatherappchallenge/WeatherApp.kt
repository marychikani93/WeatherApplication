package com.example.myweatherappchallenge

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.myweatherappchallenge.data.repository.DataStoreRepository
import dagger.hilt.android.HiltAndroidApp

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "CITY_KEY"
)

@HiltAndroidApp
class WeatherApp: Application() {
    lateinit var dataStoreCity: DataStoreRepository
    override fun onCreate() {
        super.onCreate()
        dataStoreCity = DataStoreRepository(dataStore)
    }

}