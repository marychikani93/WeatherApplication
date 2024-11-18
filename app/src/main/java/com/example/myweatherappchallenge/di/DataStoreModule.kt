package com.example.myweatherappchallenge.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.myweatherappchallenge.data.repository.DataStoreRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

val Context.userDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "CITY_KEY"
)

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    // provides instance of DataStore
    @Provides
    @Singleton
    fun provideUserDataStorePreferences(
        @ApplicationContext applicationContext: Context
    ): DataStore<Preferences> {
        return applicationContext.userDataStore
    }
}