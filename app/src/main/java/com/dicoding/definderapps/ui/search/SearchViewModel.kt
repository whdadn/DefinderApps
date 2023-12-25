package com.dicoding.definderapps.ui.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.definderapps.data.remote.response.place.PlaceResponse
import com.dicoding.definderapps.repository.Repository
import com.dicoding.definderapps.ui.common.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: Repository): ViewModel() {

    private val _dataLocResult: MutableStateFlow<ResultState<PlaceResponse>> = MutableStateFlow(ResultState.Loading)
    val dataLocResult: StateFlow<ResultState<PlaceResponse>> get() = _dataLocResult


    private val _query = mutableStateOf("null1")
    val query: State<String> get() = _query

    private val _tokenLiveData = MutableStateFlow("")
    init {
        viewModelScope.launch {
            repository.getSession().collect{
                _tokenLiveData.value = it.token
            }
        }
    }
    fun getPlaceByName(newQuery:String){
        _query.value = newQuery
        viewModelScope.launch {
            val token = _tokenLiveData.value
            repository.getPlaceByName(token,_query.value)
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