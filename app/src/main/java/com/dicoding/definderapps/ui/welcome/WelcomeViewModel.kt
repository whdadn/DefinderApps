package com.dicoding.definderapps.ui.welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.definderapps.data.local.pref.HomeLocModel
import com.dicoding.definderapps.data.local.pref.UserModel
import com.dicoding.definderapps.repository.Repository
import com.yogi.foodlist.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class WelcomeViewModel(private val repository: Repository): ViewModel() {
    private val _mbti: MutableStateFlow<UiState<String>> = MutableStateFlow(UiState.Loading)
    val mbti: StateFlow<UiState<String>> get() = _mbti
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