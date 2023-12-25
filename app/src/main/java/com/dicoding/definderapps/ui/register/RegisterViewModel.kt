package com.dicoding.definderapps.ui.register

import androidx.lifecycle.ViewModel
import com.dicoding.definderapps.repository.Repository

class RegisterViewModel(private val repository: Repository) : ViewModel() {
    fun register(name: String, email: String, password: String, password_confirmation:String) = repository.register(name,email, password, password_confirmation)
}