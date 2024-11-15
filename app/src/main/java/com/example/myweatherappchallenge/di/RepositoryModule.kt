package com.example.myweatherappchallenge.di

import com.example.myweatherappchallenge.api.WeatherApi
import com.example.myweatherappchallenge.data.repository.DefaultWeatherRepository
import com.example.myweatherappchallenge.data.repository.WeatherRepository
import com.google.android.datatransport.runtime.dagger.Module
import com.google.android.datatransport.runtime.dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(weatherApi: WeatherApi) : WeatherRepository = DefaultWeatherRepository(weatherApi)
}