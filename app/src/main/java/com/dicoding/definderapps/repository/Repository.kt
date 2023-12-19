package com.dicoding.definderapps.repository

import androidx.lifecycle.liveData
import com.dicoding.definderapps.data.local.dao.AboutDestination
import com.dicoding.definderapps.data.local.dao.DestinationDao
import com.dicoding.definderapps.data.local.dao.DestinationWithImage
import com.dicoding.definderapps.data.local.dao.TransportData
import com.dicoding.definderapps.data.local.pref.DarkModePreference
import com.dicoding.definderapps.data.local.pref.UserModel
import com.dicoding.definderapps.data.local.pref.UserPreference
import com.dicoding.definderapps.data.remote.ApiService
import com.dicoding.definderapps.data.remote.response.LoginResponse
import com.dicoding.definderapps.data.remote.response.RegisterResponse
import com.dicoding.definderapps.ui.common.ResultState
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException

class Repository private constructor(
    private val preference: UserPreference,
    private val darkMode: DarkModePreference,
    private val destinationDao: DestinationDao,
    private val apiService: ApiService
) {
    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            userPreference: UserPreference,
            darkMode: DarkModePreference,
            dao: DestinationDao,
            apiService: ApiService
        ): Repository =
            instance ?: synchronized(this) {
                instance
                    ?: Repository(userPreference,darkMode, dao, apiService)
            }.also { instance = it }
    }

    fun register(name: String, email: String, password: String) = liveData {
        emit(ResultState.Loading)
        try {
            val response =apiService.register(name,email, password)
            emit(ResultState.Success(response))
        }catch (e: HttpException){
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, RegisterResponse::class.java)
            emit(ResultState.Error(errorResponse.message))
        }
    }

    fun login(email:String, password: String) = liveData {
        emit(ResultState.Loading)
        try {
            val response = apiService.login(email, password)
            emit(ResultState.Success(response))
        }catch (e:HttpException){
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, LoginResponse::class.java)
            emit(ResultState.Error(errorResponse.message))
        }
    }
    suspend fun saveSession(user: UserModel) {
        preference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return preference.getSession()
    }

    suspend fun logout() {
        preference.logout()
    }

    suspend fun saveTheme(isEnabled:Boolean){
        darkMode.saveTheme(isEnabled)
    }

    fun getTheme(): Flow<Boolean>{
        return darkMode.getTheme()
    }
    fun getAllDestinationWithImage(): Flow<List<DestinationWithImage>>{
        return destinationDao.getAllDestinationWithImage()
    }
    fun getDestinationWithImageByName(name:String): Flow<List<DestinationWithImage>>{
        return destinationDao.getDestinationWithImageByName(name)
    }

    fun getFavoriteDestinationWithImage():Flow<List<DestinationWithImage>>{
        return destinationDao.getFavoritedDestinationWithImage()
    }

    fun getDetailDestination(id:Int):Flow<DestinationWithImage>{
        return destinationDao.getDetailDestination(id)
    }

    fun getDetailAboutDestination(idDestination: Int):Flow<AboutDestination>{
        return destinationDao.getDetailAboutDestination(idDestination)
    }

    suspend fun isFavorited(id:Int, favorited:Boolean){
        destinationDao.updateFavorited(id, favorited)
    }

    suspend fun insertTransport(transportData: TransportData){
        destinationDao.insertTransportData(transportData)
    }

    fun getTransportDataByIdDestination(idDestination: Int):Flow<List<String>>{
        return destinationDao.getTransportDataByIdDestination(idDestination)
    }

    fun getDetailTransportData(idDestination: Int, transportType:String):Flow<List<TransportData>>{
        return destinationDao.getDetailTransportData(idDestination, transportType)
    }

}