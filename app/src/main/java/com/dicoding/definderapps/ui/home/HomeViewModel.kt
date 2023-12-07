package com.dicoding.definderapps.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.definderapps.model.Destination
import com.dicoding.definderapps.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository):ViewModel() {

    private val _destination = MutableStateFlow<List<Destination>>(emptyList())

    val getDestination: StateFlow<List<Destination>> get() = _destination

    init {
        // Panggil fungsi getAllDestination() dan update nilai _destination
        viewModelScope.launch {
            repository.getAllDestination().collect {
                _destination.value = it
            }
        }
    }

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query
    fun search(newQuery: String) {
        _query.value = newQuery
        viewModelScope.launch {
            repository.getDestinationByName(_query.value).collect{
                _destination.value = it
            }
        }
    }

    fun setFavorited(id:Int, favorited:Boolean){
        viewModelScope.launch {
            repository.isFavorited(id,favorited)
        }
    }


}