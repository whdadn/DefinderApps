package com.dicoding.definderapps.ui.favorite.favoritetourism

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.definderapps.data.dao.DestinationWithImage
import com.dicoding.definderapps.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: Repository): ViewModel() {
    private val _favoriteDestinationWithImage = MutableStateFlow<List<DestinationWithImage>>(emptyList())
    val getFavoriteDestinationWithImage: StateFlow<List<DestinationWithImage>> get() = _favoriteDestinationWithImage

    init {
        viewModelScope.launch {
            repository.getFavoriteDestinationWithImage().collect{
                _favoriteDestinationWithImage.value = it
            }
        }
    }

    fun setFavorited(id:Int, favorited:Boolean){
        viewModelScope.launch {
            repository.isFavorited(id,favorited)
        }
    }


}