package com.dicoding.definderapps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.definderapps.repository.Repository
import com.dicoding.definderapps.ui.home.HomeViewModel

class ViewModelFactory(private val repository: Repository): ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class: "+ modelClass.name)
    }
}