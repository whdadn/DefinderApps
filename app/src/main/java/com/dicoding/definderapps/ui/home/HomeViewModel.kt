package com.dicoding.definderapps.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.definderapps.data.local.pref.HomeLocModel
import com.dicoding.definderapps.data.local.pref.UserModel
import com.dicoding.definderapps.data.remote.response.place.PlaceResponse
import com.dicoding.definderapps.repository.Repository
import com.dicoding.definderapps.ui.common.ResultState
import com.yogi.foodlist.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository):ViewModel() {

    private val _token: MutableStateFlow<UiState<UserModel>> = MutableStateFlow(UiState.Loading)
    val token: StateFlow<UiState<UserModel>> get() = _token

    private val _homeLocUiState: MutableStateFlow<UiState<HomeLocModel>> = MutableStateFlow(UiState.Loading)
    val homeLocUiState: StateFlow<UiState<HomeLocModel>> get() = _homeLocUiState

    private val _dataLocResult: MutableStateFlow<ResultState<PlaceResponse>> = MutableStateFlow(ResultState.Loading)
    val dataLocResult: StateFlow<ResultState<PlaceResponse>> get() = _dataLocResult


    fun getToken(){
        viewModelScope.launch {
            repository.getSession()
                .catch {
                    _token.value = UiState.Error(it.message.toString())
                }
                .collect{
                    _token.value = UiState.Success(it)
                }
        }
    }

    fun getHomeLocPref(){
        viewModelScope.launch {
            repository.getHomeLocPref()
                .catch {
                    _homeLocUiState.value =UiState.Error(it.message.toString())
                }
                .collect{
                    _homeLocUiState.value = UiState.Success(it)
                }
        }
    }


    fun getPlaceByNameAndDistrict(token:String,name:String, district:String){
        viewModelScope.launch {
            repository.getPlaceByNameAndDistrict(token,name,district)
                .catch {
                    _dataLocResult.value = ResultState.Error(it.message.toString())
                }
                .collect{resultState ->
                    when (resultState) {
                        is ResultState.Success -> {
                            val placeResponse = resultState.data
                            _dataLocResult.value = ResultState.Success(placeResponse)
                        }
                        else->{}
                    }
                }
        }
    }

    fun getPlace(token: String){
        viewModelScope.launch {
            repository.getPlaceByName(token,"")
                .catch {
                    _dataLocResult.value = ResultState.Error(it.message.toString())
                }
                .collect{resultState ->
                    when (resultState) {
                        is ResultState.Success -> {
                            val placeResponse = resultState.data
                            _dataLocResult.value = ResultState.Success(placeResponse)
                        }
                        else->{}
                    }
                }
        }
    }


}