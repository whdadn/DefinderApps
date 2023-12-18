package com.dicoding.definderapps.di

import android.content.Context
import com.dicoding.definderapps.data.local.dao.DestinationDatabase
import com.dicoding.definderapps.data.local.pref.DarkModePreference
import com.dicoding.definderapps.data.local.pref.UserPreference
import com.dicoding.definderapps.data.local.pref.darkTheme
import com.dicoding.definderapps.data.local.pref.dataStore
import com.dicoding.definderapps.data.remote.ApiConfig
import com.dicoding.definderapps.repository.Repository

object Injection {
    fun provideRepository(context: Context): Repository {
        val pref = UserPreference.getInstance(context.dataStore)
        val darkMode = DarkModePreference.getInstance(context.darkTheme)
        val dao = DestinationDatabase.getInstance(context).destinationDao()
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(pref,darkMode, dao, apiService)
    }
}