package com.dicoding.definderapps.di

import android.content.Context
import com.dicoding.definderapps.data.local.dao.DestinationDatabase
import com.dicoding.definderapps.data.local.pref.DarkModePreference
import com.dicoding.definderapps.data.local.pref.HomeLocPreference
import com.dicoding.definderapps.data.local.pref.HomeMbtiPreference
import com.dicoding.definderapps.data.local.pref.HomePreference
import com.dicoding.definderapps.data.local.pref.UserPreference
import com.dicoding.definderapps.data.local.pref.darkTheme
import com.dicoding.definderapps.data.local.pref.dataStore
import com.dicoding.definderapps.data.local.pref.homeLocationPref
import com.dicoding.definderapps.data.local.pref.homeMbtiPref
import com.dicoding.definderapps.data.local.pref.homePref
import com.dicoding.definderapps.data.remote.ApiConfig
import com.dicoding.definderapps.repository.Repository

object Injection {
    fun provideRepository(context: Context): Repository {
        val pref = UserPreference.getInstance(context.dataStore)
        val darkMode = DarkModePreference.getInstance(context.darkTheme)
        val homePreference = HomePreference.getInstance(context.homePref)
        val homeLocPreference = HomeLocPreference.getInstance(context.homeLocationPref)
        val homeMbtiPreference = HomeMbtiPreference.getInstance(context.homeMbtiPref)
        val dao = DestinationDatabase.getInstance(context).destinationDao()
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(pref,darkMode,homePreference,homeLocPreference,homeMbtiPreference, dao, apiService)
    }
}