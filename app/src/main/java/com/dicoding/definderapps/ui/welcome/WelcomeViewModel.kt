package com.dicoding.definderapps.ui.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.definderapps.data.local.pref.HomeLocModel
import com.dicoding.definderapps.data.local.pref.UserModel
import com.dicoding.definderapps.data.remote.response.mbti.MbtiDescResponse
import com.dicoding.definderapps.repository.Repository
import com.dicoding.definderapps.ui.common.ResultState
import com.yogi.foodlist.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class WelcomeViewModel(private val repository: Repository): ViewModel() {
    private val _mbti: MutableStateFlow<UiState<String>> = MutableStateFlow(UiState.Loading)
    val mbti: StateFlow<UiState<String>> get() = _mbti

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

    fun saveHomeLoc(homeLocModel: HomeLocModel){
        viewModelScope.launch {
            repository.saveHomeLoc(homeLocModel)
        }
    }
    fun getMbti(mbti:String) = repository.getMbti(mbti)

    fun saveMbti(mbti: String) {
        viewModelScope.launch {
            repository.saveMbti(mbti)
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

    fun getToken(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }


    fun yourMbti(){
        viewModelScope.launch {
            repository.yourMbti()
                .catch {
                    _mbti.value = UiState.Error(it.message.toString())
                }
                .collect{
                    _mbti.value = UiState.Success(it)
                }
        }
    }
}