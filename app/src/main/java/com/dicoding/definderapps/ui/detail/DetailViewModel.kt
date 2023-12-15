package com.dicoding.definderapps.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.definderapps.data.dao.AboutDestination
import com.dicoding.definderapps.data.dao.Destination
import com.dicoding.definderapps.data.dao.ImageDestination
import com.dicoding.definderapps.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository): ViewModel() {
    private val _getDetailDestination = MutableStateFlow<Destination?>(null)
    private val _getImageDestination = MutableStateFlow<List<ImageDestination>>(emptyList())
    private val _getAboutDestination = MutableStateFlow<AboutDestination?>(null)
    val getDetailDestination: StateFlow<Destination?> get() = _getDetailDestination
    val getImageDestination:StateFlow<List<ImageDestination>> get() = _getImageDestination
    val getAboutDestination:StateFlow<AboutDestination?> get() = _getAboutDestination

    fun setId(id:Int){
        viewModelScope.launch {
            repository.getDetailDestination(id).collect{
                _getDetailDestination.value = it
            }
        }
        viewModelScope.launch {
            repository.getDetailImageDestination(id).collect{
                _getImageDestination.value = it
            }
        }
        viewModelScope.launch {
            repository.getDetailAboutDestination(id).collect{
                _getAboutDestination.value = it
            }
        }
    }

}