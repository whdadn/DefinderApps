package com.dicoding.definderapps.ui.favorite.favoritetourism

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.definderapps.data.local.pref.UserModel
import com.dicoding.definderapps.data.local.room.Entity
import com.dicoding.definderapps.data.remote.response.favorite.GetFavoriteResponse
import com.dicoding.definderapps.repository.Repository
import com.dicoding.definderapps.ui.common.ResultState
import com.yogi.foodlist.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: Repository): ViewModel() {

    private val _token: MutableStateFlow<UiState<UserModel>> = MutableStateFlow(UiState.Loading)
    val token: StateFlow<UiState<UserModel>> get() = _token

    private val _dataFavTourism: MutableStateFlow<ResultState<GetFavoriteResponse>> = MutableStateFlow(
        ResultState.Loading)
    val dataFavTourism: StateFlow<ResultState<GetFavoriteResponse>> get() = _dataFavTourism

    fun getToken(){
        viewModelScope.launch {
            repository.getSession()
                .catch {
                    _token.value = UiState.Error(it.message.toString())
                }
                .collect{
                    _token.value = UiState.Success(it)
                }
        }
    }

    fun getFavTourism(token:String){
        viewModelScope.launch {
            repository.getFavPlaceApi(token)
                .collect{resultState ->
                    when (resultState) {
                        is ResultState.Success -> {
                            val placeResponse = resultState.data
                            _dataFavTourism.value = ResultState.Success(placeResponse)
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

    fun getAllFavPlace(): LiveData<List<Entity>>{
        return repository.getAllFavPlace().asLiveData()
    }

    fun deleteFavPlace(placeId: Int):Boolean{
        val place= Entity(placeId = placeId)
        viewModelScope.launch {
            repository.deleteFavPlace(place)
        }
        return true
    }

    fun deleteFavPlaceApi(token: String, placeId: Int) = repository.deleteFavPlaceApi(token, placeId)
}