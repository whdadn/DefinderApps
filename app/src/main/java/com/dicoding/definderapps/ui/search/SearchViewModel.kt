package com.dicoding.definderapps.ui.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.definderapps.data.local.dao.DestinationWithImage
import com.dicoding.definderapps.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: Repository): ViewModel() {
    private val _destinationWithImage = MutableStateFlow<List<DestinationWithImage>>(emptyList())
    val getDestinationWithImage: StateFlow<List<DestinationWithImage>> get() = _destinationWithImage

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query
    fun search(newQuery: String) {
        _query.value = newQuery
        viewModelScope.launch {
            repository.getDestinationWithImageByName(_query.value).collect{
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