package com.dicoding.definderapps.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.definderapps.data.local.pref.UserModel
import com.dicoding.definderapps.data.remote.response.detail.DetailResponse
import com.dicoding.definderapps.data.remote.response.detailtransport.DetailTransportResponse
import com.dicoding.definderapps.data.remote.response.review.GetReviewResponse
import com.dicoding.definderapps.data.remote.response.typetransport.TypeTransportResponse
import com.dicoding.definderapps.repository.Repository
import com.dicoding.definderapps.ui.common.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository): ViewModel() {

    private val _dataDetailPlace: MutableStateFlow<ResultState<DetailResponse>> = MutableStateFlow(ResultState.Loading)
    val dataDetailPlace: StateFlow<ResultState<DetailResponse>> get() = _dataDetailPlace

    private val _dataTypeTransport: MutableStateFlow<ResultState<TypeTransportResponse>> = MutableStateFlow(ResultState.Loading)
    val dataTypeTransport: StateFlow<ResultState<TypeTransportResponse>> get() = _dataTypeTransport

    private val _dataDetailTransport:MutableStateFlow<ResultState<DetailTransportResponse>> = MutableStateFlow(ResultState.Loading)
    val dataDetailtransport:StateFlow<ResultState<DetailTransportResponse>> get() = _dataDetailTransport

    private val _dataReviewPlace:MutableStateFlow<ResultState<GetReviewResponse>> = MutableStateFlow(ResultState.Loading)
    val dataReviewPlace:StateFlow<ResultState<GetReviewResponse>> get() = _dataReviewPlace

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun getDetailPlace(token:String,id:Int){
        viewModelScope.launch {
            repository.getDetailPlace(token, id)
                .catch {
                    _dataDetailPlace.value = ResultState.Error(it.message.toString())
                }
                .collect{resultState ->
                    when (resultState) {
                        is ResultState.Success -> {
                            _dataDetailPlace.value = ResultState.Success(resultState.data)
                        }
                        else->{}
                    }
                }

        }
    }

    fun addTransport(token:String,placeId: Int,userId: Int, type:String, name:String, description:String) = repository.addTransport(token,placeId,userId,type,name,description)

    fun getTransport(token:String,placeId: Int){
        viewModelScope.launch {
            repository.getTransport(token, placeId)
                .catch {
                    _dataTypeTransport.value = ResultState.Error(it.message.toString())
                }
                .collect{resultState ->
                    when (resultState) {
                        is ResultState.Success -> {
                            _dataTypeTransport.value = ResultState.Success(resultState.data)
                        }
                        else->{}
                    }
                }

        }
    }

    fun getDetailTransport(token:String,placeId: Int,type: String){
        viewModelScope.launch {
            repository.getDetailTransport(token, placeId,type)
                .catch {
                    _dataDetailTransport.value = ResultState.Error(it.message.toString())
                }
                .collect{resultState ->
                    when (resultState) {
                        is ResultState.Success -> {
                            _dataDetailTransport.value = ResultState.Success(resultState.data)
                        }
                        else->{}
                    }
                }

        }
    }

    fun createReview(token: String, placeId: Int, review:String) = repository.createReview(token, placeId, review)
    fun getReviewPlace(token: String, placeId: Int){
        viewModelScope.launch {
            repository.getReview(token, placeId)
                .catch {
                    _dataReviewPlace.value = ResultState.Error(it.message.toString())
                }
                .collect{resultState ->
                    when (resultState) {
                        is ResultState.Success -> {
                            _dataReviewPlace.value = ResultState.Success(resultState.data)
                        }
                        else->{}
                    }
                }


        }
    }



}