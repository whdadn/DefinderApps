package com.dicoding.definderapps.ui.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.definderapps.data.local.pref.HomeLocModel
import com.dicoding.definderapps.data.local.pref.HomeMbtiModel
import com.dicoding.definderapps.repository.Repository
import kotlinx.coroutines.launch

class WelcomeViewModel(private val repository: Repository): ViewModel() {
    fun saveHomeContent(content:String) {
        viewModelScope.launch {
            repository.saveHomeContent(content)
        }
    }

    fun saveHomeLoc(homeLocModel: HomeLocModel){
        viewModelScope.launch {
            repository.saveHomeLoc(homeLocModel)
        }
    }

    fun saveHomeMbti(homeMbtiModel: HomeMbtiModel){
        viewModelScope.launch {
            repository.saveHomeMbti(homeMbtiModel)
        }
    }
}