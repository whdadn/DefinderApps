package com.dicoding.definderapps.repository

import androidx.lifecycle.liveData
import com.dicoding.definderapps.data.local.pref.DarkModePreference
import com.dicoding.definderapps.data.local.pref.HomeLocModel
import com.dicoding.definderapps.data.local.pref.HomeLocPreference
import com.dicoding.definderapps.data.local.pref.UserModel
import com.dicoding.definderapps.data.local.pref.UserPreference
import com.dicoding.definderapps.data.remote.retrofit.ApiService
import com.dicoding.definderapps.data.remote.retrofit.ApiServiceMbti
import com.dicoding.definderapps.data.remote.response.detail.DetailResponse
import com.dicoding.definderapps.data.remote.response.detailtransport.DetailTransportResponse
import com.dicoding.definderapps.data.remote.response.login.LoginResponse
import com.dicoding.definderapps.data.remote.response.mbti.MbtiResponse
import com.dicoding.definderapps.data.remote.response.place.PlaceResponse
import com.dicoding.definderapps.data.remote.response.profile.EditProfileResponse
import com.dicoding.definderapps.data.remote.response.profile.GetUserResponse
import com.dicoding.definderapps.data.remote.response.register.RegisterResponse
import com.dicoding.definderapps.data.remote.response.transport.TransportResponse
import com.dicoding.definderapps.data.remote.response.typetransport.TypeTransportResponse
import com.dicoding.definderapps.ui.common.ResultState
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class Repository private constructor(
    private val preference: UserPreference,
    private val darkMode: DarkModePreference,
    private val homeLocPreference: HomeLocPreference,
    private val apiService: ApiService,
    private val apiServiceMbti: ApiServiceMbti
) {
    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            userPreference: UserPreference,
            darkMode: DarkModePreference,
            homeLocPreference: HomeLocPreference,
            apiService: ApiService,
            apiServiceMbti: ApiServiceMbti
        ): Repository =
            instance ?: synchronized(this) {
                instance
                    ?: Repository(userPreference,darkMode,homeLocPreference, apiService, apiServiceMbti)
            }.also { instance = it }
    }

    fun register(name: String, email: String, password: String, password_confirmation:String) = liveData {
        emit(ResultState.Loading)
        try {
            val response =apiService.register(name,email, password, password_confirmation)
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

    fun getPlaceByNameAndDistrict(token:String,name: String, district:String) = flow {
        emit(ResultState.Loading)
        try {
            val response = apiService.getPlaceByNameAndDistrict("Bearer $token",name,district)
            emit(ResultState.Success(response))
        }catch (e:HttpException){
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, PlaceResponse::class.java)
            emit(ResultState.Error(errorResponse?.message.toString()))
        }
    }


    fun getPlaceByName(token:String,name: String) = flow {
        emit(ResultState.Loading)
        try {
            val response = apiService.getPlaceByName("Bearer $token",name)
            emit(ResultState.Success(response))
        }catch (e:HttpException){
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, PlaceResponse::class.java)
            emit(ResultState.Error(errorResponse?.message.toString()))
        }
    }

    fun getDetailPlace(token:String,id:Int) = flow{
        emit(ResultState.Loading)
        try {
            val response= apiService.getDetailPlace("Bearer $token", id)
            emit(ResultState.Success(response))
        }catch (e:HttpException){
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, DetailResponse::class.java)
            emit(ResultState.Error(errorResponse?.message.toString()))
        }
    }

    fun addTransport(token:String,placeId: Int,userId: Int,type:String, name:String, description:String) = liveData {
        emit(ResultState.Loading)
        try {
            val response =apiService.addTransport("Bearer $token",placeId,userId,type,name,description)
            emit(ResultState.Success(response))
        }catch (e: HttpException){
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, TransportResponse::class.java)
            emit(ResultState.Error(errorResponse.message))
        }
    }

    fun getTransport(token:String,placeId: Int) = flow{
        emit(ResultState.Loading)
        try {
            val response= apiService.getTransport("Bearer $token", placeId)
            emit(ResultState.Success(response))
        }catch (e:HttpException){
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, TypeTransportResponse::class.java)
            emit(ResultState.Error(errorResponse?.message.toString()))
        }
    }

    fun getDetailTransport(token:String,placeId: Int, type: String) = flow{
        emit(ResultState.Loading)
        try {
            val response= apiService.getDetailTransport("Bearer $token", placeId,type)
            emit(ResultState.Success(response))
        }catch (e:HttpException){
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, DetailTransportResponse::class.java)
            emit(ResultState.Error(errorResponse?.message.toString()))
        }
    }

    fun getUser(token: String, userId: Int) = flow{
        emit(ResultState.Loading)
        try {
            val response= apiService.getUser("Bearer $token", userId)
            emit(ResultState.Success(response))
        }catch (e:HttpException){
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, GetUserResponse::class.java)
            emit(ResultState.Error(errorResponse?.message.toString()))
        }
    }

    fun getMbti(mbti:String) = liveData {
        emit(ResultState.Loading)
        try {
            val response = apiServiceMbti.getMbti(mbti)
            emit(ResultState.Success(response))
        }catch (e:HttpException){
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, MbtiResponse::class.java)
            emit(ResultState.Error(errorResponse?.message.toString()))
        }
    }

    fun editAccount(token:String,userId: Int,name: String,email: String) = liveData {
        emit(ResultState.Loading)
        try {
            val response =apiService.editAccount("Bearer $token",userId, name, email)
            emit(ResultState.Success(response))
        }catch (e: HttpException){
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, EditProfileResponse::class.java)
            emit(ResultState.Error(errorResponse.message))
        }
    }

    fun editPassword(token:String,userId: Int,oldPassword: String,newPassword: String) = liveData {
        emit(ResultState.Loading)
        try {
            val response =apiService.editPassword("Bearer $token", userId, oldPassword, newPassword)
            emit(ResultState.Success(response))
        }catch (e: HttpException){
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, EditProfileResponse::class.java)
            emit(ResultState.Error(errorResponse.message))
        }
    }

    suspend fun saveMbti(mbti:String) {
        preference.saveMbti(mbti)
    }

    suspend fun saveSession(user: UserModel) {
        preference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> {
        return preference.getSession()
    }

    fun yourMbti():Flow<String>{
        return preference.yourMbti()
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

    fun getHomeLocPref(): Flow<HomeLocModel>{
        return homeLocPreference.getHomeLocation()
    }

    suspend fun saveHomeLoc(homeLocModel: HomeLocModel){
        homeLocPreference.saveHomeLocation(homeLocModel)
    }
}