package com.dicoding.definderapps.ui.home

import androidx.lifecycle.ViewModel
import com.dicoding.definderapps.repository.Repository
import com.dicoding.definderapps.model.Destination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class HomeViewModel(private val repository: Repository):ViewModel() {

    private var _getDestination = MutableStateFlow(
        repository.getDestination()
    )
    val getDestination:StateFlow<List<Destination>> get() =_getDestination

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query
    fun search(newQuery: String) {
        _query.value = newQuery
        _getDestination.value = repository.searchDestination(_query.value)
            .sortedBy { it.name }
    }

}