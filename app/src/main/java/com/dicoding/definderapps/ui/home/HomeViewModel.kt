package com.dicoding.definderapps.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.definderapps.data.dao.DestinationWithImage
import com.dicoding.definderapps.repository.Repository
import com.yogi.foodlist.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository):ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<DestinationWithImage>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<DestinationWithImage>>> get() = _uiState

    fun getAllDestinationWithImage(){
        viewModelScope.launch {
            repository.getAllDestinationWithImage()
                .catch {
                    _uiState.value =UiState.Error(it.message.toString())
                }
                .collect{
                    _uiState.value = UiState.Success(it)
                }
        }
    }


    fun setFavorited(id:Int, favorited:Boolean){
        viewModelScope.launch {
            repository.isFavorited(id,favorited)
        }
    }


}