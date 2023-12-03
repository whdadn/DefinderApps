package com.dicoding.definderapps.di

import com.dicoding.definderapps.repository.Repository

object Injection {
    fun provideRepository(): Repository {
        return Repository.getInstance()
    }
}