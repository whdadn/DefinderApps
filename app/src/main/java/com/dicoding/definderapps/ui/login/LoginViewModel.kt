package com.dicoding.definderapps.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.definderapps.data.pref.UserModel
import com.dicoding.definderapps.repository.Repository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: Repository) : ViewModel() {

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            Log.d("user", "${user.email}, ${user.isLogin}")
            repository.saveSession(user)
        }
    }
    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }


}