package com.csibtn.recipehub.data.repositories

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStoreFile
import com.csibtn.recipehub.util.RecipeApplication
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

object SharedPreferencesRepository : SharePreferencesRepositoryInterface {
    private val dataStore: DataStore<Preferences> = PreferenceDataStoreFactory.create {
        RecipeApplication.applicationContext().preferencesDataStoreFile("settings")
    }

    val storedQuery: Flow<String> = dataStore.data.map {
        it[SEARCH_QUERY_KEY] ?: ""
    }.distinctUntilChanged()

    override suspend fun setStoredQuery(query: String) {
        dataStore.edit {
            it[SEARCH_QUERY_KEY] = query
        }
    }

    private val SEARCH_QUERY_KEY = stringPreferencesKey("search_query")

}