package com.dicoding.definderapps.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.definderapps.data.local.dao.DestinationWithImage
import com.dicoding.definderapps.data.local.pref.HomeLocModel
import com.dicoding.definderapps.repository.Repository
import com.yogi.foodlist.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository):ViewModel() {

    private val _uiState: MutableStateFlow<UiState<String>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<String>> get() = _uiState


    private val _homeLocUiState: MutableStateFlow<UiState<List<DestinationWithImage>>> = MutableStateFlow(UiState.Loading)
    val homeLocUiState: StateFlow<UiState<List<DestinationWithImage>>> get() = _homeLocUiState

    private val _homeMbtiUiState: MutableStateFlow<UiState<List<DestinationWithImage>>> = MutableStateFlow(UiState.Loading)
    val homeMbtiUiState: StateFlow<UiState<List<DestinationWithImage>>> get() = _homeMbtiUiState

    fun getHomeContent(){
        viewModelScope.launch {
            repository.getHomeContent()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect{
                    _uiState.value = UiState.Success(it)
                }
        }
    }

    fun getHomeLocDestinationWithImage(name:String, location:String){
        viewModelScope.launch {
            repository.getDestinationByNameAndLocation(name,location)
                .catch {
                    _homeLocUiState.value =UiState.Error(it.message.toString())
                }
                .collect{
                    _homeLocUiState.value = UiState.Success(it)
                }
        }
    }

    fun getHomeMbtiDestinationWithImage(){
        viewModelScope.launch {
            repository.getAllDestinationWithImage()
                .catch {
                    _homeMbtiUiState.value =UiState.Error(it.message.toString())
                }
                .collect{
                    _homeMbtiUiState.value = UiState.Success(it)
                }
        }
    }

    fun getHomeLocPref(): LiveData<HomeLocModel> {
        return repository.getHomeLoc().asLiveData()
    }

//    fun getHomeMbtiPref():LiveData<HomeMbtiModel>{
//        return repository.getHomeMbti().asLiveData()
//    }

    fun setFavorited(id:Int, favorited:Boolean){
        viewModelScope.launch {
            repository.isFavorited(id,favorited)
        }
    }


}