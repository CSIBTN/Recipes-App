package com.csibtn.recipehub.data.repositories

interface SharePreferencesRepositoryInterface {
    suspend fun setStoredQuery(query: String)
}