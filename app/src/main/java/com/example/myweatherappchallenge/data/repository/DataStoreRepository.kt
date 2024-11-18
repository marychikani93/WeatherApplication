package com.example.myweatherappchallenge.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepository @Inject constructor(private val dataStore: DataStore<Preferences>) {

    // to make sure there's only one instance
    companion object {
        val CITY_KEY = stringPreferencesKey("city")
    }

    //get the saved city
    val getCity: Flow<String?> = dataStore.data
        .map { preferences ->
            preferences[CITY_KEY] ?: "Arlington, US"
        }

    //save city into datastore
    suspend fun saveCity(city: String) {
        dataStore.edit { preferences ->
            preferences[CITY_KEY] = city
        }
    }


}