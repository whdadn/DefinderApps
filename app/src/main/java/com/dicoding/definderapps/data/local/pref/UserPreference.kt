package com.dicoding.definderapps.data.local.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")
val Context.darkTheme: DataStore<Preferences> by preferencesDataStore(name = "dark_mode")
val Context.homeLocationPref: DataStore<Preferences> by preferencesDataStore(name = "home_location")

class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {
    suspend fun saveSession(user: UserModel) {
        dataStore.edit { preferences ->
            preferences[MBTI_KEY] = user.mbti
            preferences[ID_KEY] = user.id
            preferences[TOKEN_KEY] = user.token
            preferences[IS_LOGIN_KEY] = true
        }
    }

    fun getSession(): Flow<UserModel> {
        return dataStore.data.map { preferences ->
            UserModel(
                preferences[MBTI_KEY] ?: "",
                preferences[ID_KEY] ?: 0,
                preferences[TOKEN_KEY] ?: "",
                preferences[IS_LOGIN_KEY] ?: false
            )
        }
    }

    suspend fun saveMbti(mbti:String){
        dataStore.edit {preferences->
            preferences[MBTI_KEY] = mbti
        }
    }

    fun yourMbti():Flow<String>{
        return dataStore.data.map{ preferences->
            preferences[MBTI_KEY]?:""
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

        private val MBTI_KEY = stringPreferencesKey("mbti")
        private val ID_KEY = intPreferencesKey("id")
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
    suspend fun saveTheme(isEnabled: Boolean) {
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


class HomeLocPreference private constructor(private val homeLocPref: DataStore<Preferences>) {
    suspend fun saveHomeLocation(homeLocModel: HomeLocModel) {
        homeLocPref.edit { preferences ->
            preferences[NAME] = homeLocModel.name
            preferences[PROVINCE] = homeLocModel.province
        }
    }

    fun getHomeLocation(): Flow<HomeLocModel> {
        return homeLocPref.data.map { preferences ->
            HomeLocModel(
                preferences[NAME] ?: "",
                preferences[PROVINCE] ?: ""
            )
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: HomeLocPreference? = null

        private val NAME = stringPreferencesKey("name_location")
        private val PROVINCE = stringPreferencesKey("province_location")

        fun getInstance(homeLocPref: DataStore<Preferences>): HomeLocPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = HomeLocPreference(homeLocPref)
                INSTANCE = instance
                instance
            }
        }
    }
}

