package com.dicoding.definderapps.ui.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.definderapps.data.local.pref.UserModel
import com.dicoding.definderapps.data.local.room.Entity
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

    private val _query = mutableStateOf("nullll")
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

    fun insertFavPlace(placeId:Int){
        val place= Entity(placeId = placeId)
        viewModelScope.launch {
            repository.insertFavPlace(place)
        }
    }

    fun insertFavPlaceApi(token: String, placeId: Int) = repository.insertFavPlaceApi(token, placeId)


    fun getFavById(placeId: Int): LiveData<Entity> {
        return repository.getFavPlace(placeId).asLiveData()
    }

    fun getToken():LiveData<UserModel>{
        return repository.getSession().asLiveData()
    }

    fun deleteFavPlace(placeId: Int){
        val place= Entity(placeId = placeId)
        viewModelScope.launch {
            repository.deleteFavPlace(place)
        }
    }

    fun deleteFavPlaceApi(token: String, placeId: Int) = repository.deleteFavPlaceApi(token, placeId)

}