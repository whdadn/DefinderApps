package com.dicoding.definderapps.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.definderapps.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: Repository) : ViewModel() {
    fun logout():Boolean {
        viewModelScope.launch {
            repository.logout()
        }
        return true
    }

    fun getTheme(): Flow<Boolean> {
        return repository.getTheme()
    }

    fun saveTheme(isEnabled: Boolean) {
        viewModelScope.launch {
            repository.saveTheme(isEnabled)
        }
    }
}