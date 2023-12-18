package com.dicoding.definderapps.data.local.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")
val Context.darkTheme: DataStore<Preferences> by preferencesDataStore(name = "dark_mode")
class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {
    suspend fun saveSession(user: UserModel) {
        dataStore.edit { preferences ->
            preferences[EMAIL_KEY] = user.email
            preferences[TOKEN_KEY] = user.token
            preferences[IS_LOGIN_KEY] = true
        }
    }

    fun getSession(): Flow<UserModel> {
        return dataStore.data.map { preferences ->
            UserModel(
                preferences[EMAIL_KEY] ?: "",
                preferences[TOKEN_KEY] ?: "",
                preferences[IS_LOGIN_KEY] ?: false
            )
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        private val EMAIL_KEY = stringPreferencesKey("email")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val IS_LOGIN_KEY = booleanPreferencesKey("isLogin")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}

class DarkModePreference private constructor(private val darkModePreference: DataStore<Preferences>) {
    suspend fun saveTheme(isEnabled : Boolean) {
        darkModePreference.edit { preferences ->
            preferences[IS_ENABLE_KEY] = isEnabled
        }
    }

    fun getTheme(): Flow<Boolean> {
        return darkModePreference.data.map { preferences ->
            preferences[IS_ENABLE_KEY] ?: false
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: DarkModePreference? = null

        private val IS_ENABLE_KEY = booleanPreferencesKey("isEnabled")

        fun getInstance(darkModePreference: DataStore<Preferences>): DarkModePreference {
            return INSTANCE ?: synchronized(this) {
                val instance = DarkModePreference(darkModePreference)
                INSTANCE = instance
                instance
            }
        }
    }
}