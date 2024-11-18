package com.example.myweatherappchallenge.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.Job
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope

private val testContext: Context = ApplicationProvider.getApplicationContext()
private val testCoroutineDispatcher = TestCoroutineDispatcher()
private const val TEST_DATASTORE_NAME: String = "Arlington, US"
private val testCoroutineScope =
    TestCoroutineScope(testCoroutineDispatcher + Job())


private val testDataStore: DataStore<Preferences> =
    PreferenceDataStoreFactory.create(
        scope = testCoroutineScope,
        produceFile = { testContext.preferencesDataStoreFile(TEST_DATASTORE_NAME)}
    )