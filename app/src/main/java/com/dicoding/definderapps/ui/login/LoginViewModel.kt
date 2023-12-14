package com.dicoding.definderapps.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.definderapps.data.pref.UserModel
import com.dicoding.definderapps.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: Repository) : ViewModel() {

    private val _session = MutableStateFlow(UserModel("",false))
    val getSession: StateFlow<UserModel> get() = _session
    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            Log.d("user", "${user.email}, ${user.isLogin}")
            repository.saveSession(user)
        }
    }
    init {
        viewModelScope.launch {
            repository.getSession().collect{
                _session.value = it
            }
        }
    }

}