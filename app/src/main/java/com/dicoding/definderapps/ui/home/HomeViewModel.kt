package com.dicoding.definderapps.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.definderapps.data.dao.DestinationWithImage
import com.dicoding.definderapps.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository):ViewModel() {

    private val _destinationWithImage = MutableStateFlow<List<DestinationWithImage>>(emptyList())
    val getDestinationWithImage: StateFlow<List<DestinationWithImage>> get() = _destinationWithImage

    init {
        viewModelScope.launch {
            repository.getAllDestinationWithImage().collect{
                _destinationWithImage.value = it
            }
        }
    }


    fun setFavorited(id:Int, favorited:Boolean){
        viewModelScope.launch {
            repository.isFavorited(id,favorited)
        }
    }


}