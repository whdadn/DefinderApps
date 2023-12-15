package com.dicoding.definderapps.di

import android.content.Context
import com.dicoding.definderapps.data.dao.DestinationDatabase
import com.dicoding.definderapps.data.pref.UserPreference
import com.dicoding.definderapps.data.pref.dataStore
import com.dicoding.definderapps.repository.Repository

object Injection {
    fun provideRepository(context: Context): Repository {
        val pref = UserPreference.getInstance(context.dataStore)
        val dao = DestinationDatabase.getInstance(context).destinationDao()
        return Repository.getInstance(pref, dao)
    }
}