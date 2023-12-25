package com.dicoding.definderapps.di

import android.content.Context
import com.dicoding.definderapps.data.local.pref.DarkModePreference
import com.dicoding.definderapps.data.local.pref.HomeLocPreference
import com.dicoding.definderapps.data.local.pref.UserPreference
import com.dicoding.definderapps.data.local.pref.darkTheme
import com.dicoding.definderapps.data.local.pref.dataStore
import com.dicoding.definderapps.data.local.pref.homeLocationPref
import com.dicoding.definderapps.data.remote.retrofit.ApiConfig
import com.dicoding.definderapps.data.remote.retrofit.ApiConfigMbti
import com.dicoding.definderapps.repository.Repository

object Injection {
    fun provideRepository(context: Context): Repository {
        val pref = UserPreference.getInstance(context.dataStore)
        val darkMode = DarkModePreference.getInstance(context.darkTheme)
        val homeLocPreference = HomeLocPreference.getInstance(context.homeLocationPref)
        val apiService = ApiConfig.getApiService()
        val apiServiceMbti = ApiConfigMbti.getApiServiceMbti()
        return Repository.getInstance(pref,darkMode,homeLocPreference, apiService, apiServiceMbti)
    }
}