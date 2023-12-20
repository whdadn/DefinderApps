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
val Context.homePref:DataStore<Preferences> by preferencesDataStore(name = "home_pref")
val Context.homeLocationPref: DataStore<Preferences> by preferencesDataStore(name = "home_location")
val Context.homeMbtiPref:DataStore<Preferences> by preferencesDataStore(name="home_mbti")

class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {
    suspend fun saveSession(user: UserModel) {
        dataStore.edit { preferences ->
            preferences[EMAIL_KEY] = user.email
            preferences[NAME_KEY] = user.name
            preferences[TOKEN_KEY] = user.token
            preferences[IS_LOGIN_KEY] = true
        }
    }

    fun getSession(): Flow<UserModel> {
        return dataStore.data.map { preferences ->
            UserModel(
                preferences[EMAIL_KEY] ?: "",
                preferences[NAME_KEY] ?: "",
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
        private val NAME_KEY = stringPreferencesKey("name")
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


class HomePreference private constructor(private val homePref: DataStore<Preferences>) {
    suspend fun saveHomeContent(homeContent: String) {
        homePref.edit { preferences ->
            preferences[HOME_CONTENT] = homeContent
        }
    }

    fun getHomeContent(): Flow<String> {
        return homePref.data.map { preferences ->
            preferences[HOME_CONTENT] ?: ""
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: HomePreference? = null

        private val HOME_CONTENT = stringPreferencesKey("home_content")

        fun getInstance(homePref: DataStore<Preferences>): HomePreference {
            return INSTANCE ?: synchronized(this) {
                val instance = HomePreference(homePref)
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

class HomeMbtiPreference private constructor(private val homeMbtiPref: DataStore<Preferences>) {
    suspend fun saveHomeMbti(homeMbtiModel: HomeMbtiModel) {
        homeMbtiPref.edit { preferences ->
            preferences[PERSONALITY] = homeMbtiModel.personality
        }
    }

//    fun getHomeMbti(): Flow<HomeMbtiModel> {
//        return homeMbtiPref.data.map { preferences ->
//            HomeMbtiModel(
//                preferences[PERSONALITY] ?: ""
//            )
//        }
//    }

    companion object {
        @Volatile
        private var INSTANCE: HomeMbtiPreference? = null

        private val PERSONALITY = stringPreferencesKey("personality_mbti")

        fun getInstance(homeMbtiPref: DataStore<Preferences>): HomeMbtiPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = HomeMbtiPreference(homeMbtiPref)
                INSTANCE = instance
                instance
            }
        }
    }
}

