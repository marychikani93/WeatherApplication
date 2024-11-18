package com.example.myweatherappchallenge.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.example.myweatherappchallenge.data.repository.DataStoreRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class FakeDataStoreRepository {
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher + Job())

    @get:Rule
    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    private val testDataStore: DataStore<Preferences> = PreferenceDataStoreFactory.create(
        scope = testScope,
        produceFile = { tmpFolder.newFile("Arlington, US") }
    )

    private val subject: DataStoreRepository = DataStoreRepository(testDataStore)

    @Test
    fun whenGetNameForTheFirstTime_thenReturnDefaultValue() = testScope.runTest {
        //When
        val actual = subject.getCity

        //Then
        assertEquals("", actual)
    }

    @Test
    fun whenSetName_thenUpdateName() = testScope.runTest {
        // When
        subject.saveCity("Arlington, US")

        //Then
        assertEquals("Arlington, US", subject.getCity)
    }
}
