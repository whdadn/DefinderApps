package com.dicoding.definderapps.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.definderapps.data.local.pref.UserModel
import com.dicoding.definderapps.data.remote.response.mbti.MbtiDescResponse
import com.dicoding.definderapps.data.remote.response.profile.GetUserResponse
import com.dicoding.definderapps.repository.Repository
import com.dicoding.definderapps.ui.common.ResultState
import com.yogi.foodlist.ui.common.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: Repository) : ViewModel() {

    private val _dataUser: MutableStateFlow<ResultState<GetUserResponse>> = MutableStateFlow(ResultState.Loading)
    val dataUser: StateFlow<ResultState<GetUserResponse>> get() = _dataUser

    private val _session: MutableStateFlow<UiState<UserModel>> = MutableStateFlow(UiState.Loading)
    val session: StateFlow<UiState<UserModel>> get() = _session

    private val _dataMbtiDesc: MutableStateFlow<ResultState<MbtiDescResponse>> = MutableStateFlow(ResultState.Loading)
    val dataMbtiDesc: StateFlow<ResultState<MbtiDescResponse>> get() = _dataMbtiDesc

    fun getSession(){
        viewModelScope.launch {
            repository.getSession()
                .catch {
                    _session.value = UiState.Error(it.message.toString())
                }
                .collect{
                    _session.value = UiState.Success(it)
                }
        }
    }

    fun getMbtiDesc(token:String){
        viewModelScope.launch {
            repository.getMbtiDesc(token)
                .catch {
                    _dataMbtiDesc.value = ResultState.Error(it.message.toString())
                }
                .collect{resultState ->
                    when (resultState) {
                        is ResultState.Success -> {
                            val placeResponse = resultState.data
                            _dataMbtiDesc.value = ResultState.Success(placeResponse)
                        }
                        else->{}
                    }
                }
        }
    }
    fun getUser(token:String,userId:Int){
        viewModelScope.launch {
            repository.getUser(token, userId)
                .catch {
                    _dataUser.value = ResultState.Error(it.message.toString())
                }
                .collect{resultState ->
                    when (resultState) {
                        is ResultState.Success -> {
                            _dataUser.value = ResultState.Success(resultState.data)
                        }
                        else->{}
                    }
                }
        }
    }

    fun editAccount(token: String,userId: Int,name:String,email:String) = repository.editAccount(token, userId, name, email)
    fun editPassword(token: String,userId: Int,oldPass:String,newPass:String) = repository.editPassword(token,userId,oldPass,newPass)

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