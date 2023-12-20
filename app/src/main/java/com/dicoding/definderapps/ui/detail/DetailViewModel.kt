package com.dicoding.definderapps.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dicoding.definderapps.data.local.dao.AboutDestination
import com.dicoding.definderapps.data.local.dao.DestinationWithImage
import com.dicoding.definderapps.data.local.dao.TransportData
import com.dicoding.definderapps.data.local.pref.UserModel
import com.dicoding.definderapps.repository.Repository
import com.yogi.foodlist.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository): ViewModel() {

    private val _uiStateDestination: MutableStateFlow<UiState<DestinationWithImage>> = MutableStateFlow(UiState.Loading)
    val uiStateDestination: StateFlow<UiState<DestinationWithImage>> get() = _uiStateDestination

    private val _uiStateAboutDestination: MutableStateFlow<UiState<AboutDestination>> = MutableStateFlow(UiState.Loading)
    val uiStateAboutDestination: StateFlow<UiState<AboutDestination>> get() = _uiStateAboutDestination

    private val _uiStateTransportDataDestination: MutableStateFlow<UiState<List<String>>> = MutableStateFlow(UiState.Loading)
    val uiStateTransportDataDestination: StateFlow<UiState<List<String>>> get() = _uiStateTransportDataDestination

    private val _uiStateDetailTransportDataDestination: MutableStateFlow<UiState<List<TransportData>>> = MutableStateFlow(UiState.Loading)
    val uiStateDetailTransportDataDestination: StateFlow<UiState<List<TransportData>>> get() = _uiStateDetailTransportDataDestination

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun getDetailDestination(id: Int){
        viewModelScope.launch {
            repository.getDetailDestination(id)
                .catch {
                    _uiStateDestination.value = UiState.Error(it.message.toString())
                }
                .collect{
                    _uiStateDestination.value = UiState.Success(it)
                }
        }
    }

    fun getAboutDestination(id:Int) {
        viewModelScope.launch {
            repository.getDetailAboutDestination(id)
                .catch {
                    _uiStateAboutDestination.value = UiState.Error(it.message.toString())
                }
                .collect{
                    _uiStateAboutDestination.value = UiState.Success(it)
            }
        }
    }

    fun insertTransport(name:String, image:String, transportType:String, transportationName: String,transportationDesc: String,idDestination: Int){
        val data = TransportData(
            name=name,
            image = image,
            transportType = transportType,
            transportationName = transportationName,
            transportationDesc = transportationDesc,
            idDestination = idDestination
        )
        viewModelScope.launch {
            repository.insertTransport(data)
        }
    }
    fun getTransportData(id: Int){
        viewModelScope.launch {
            repository.getTransportDataByIdDestination(id)
                .catch {
                    _uiStateTransportDataDestination.value = UiState.Error(it.message.toString())
                }
                .collect{
                    _uiStateTransportDataDestination.value = UiState.Success(it)
                }
        }
    }

    fun getDetailTransportData(idDestination: Int, transportType:String){
        viewModelScope.launch {
            repository.getDetailTransportData(idDestination,transportType)
                .catch {
                    _uiStateDetailTransportDataDestination.value = UiState.Error(it.message.toString())
                }
                .collect{
                    _uiStateDetailTransportDataDestination.value = UiState.Success(it)
                }
        }
    }
}