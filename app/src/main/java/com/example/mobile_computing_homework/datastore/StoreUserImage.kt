package com.example.mobile_computing_homework.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreUserImage(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("Image")
        val USER_IMAGE_KEY = stringPreferencesKey("image")
    }

    val getImageUri: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_IMAGE_KEY] ?: "username"
        }

    suspend fun saveImageUri(name: String) {
        context.dataStore.edit {preferences ->
            preferences[USER_IMAGE_KEY] = name
        }
    }
}
