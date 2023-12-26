package com.dicoding.definderapps.repository

import androidx.lifecycle.liveData
import com.dicoding.definderapps.data.local.pref.DarkModePreference
import com.dicoding.definderapps.data.local.pref.HomeLocModel
import com.dicoding.definderapps.data.local.pref.HomeLocPreference
import com.dicoding.definderapps.data.local.pref.UserModel
import com.dicoding.definderapps.data.local.pref.UserPreference
import com.dicoding.definderapps.data.local.room.Dao
import com.dicoding.definderapps.data.local.room.Entity
import com.dicoding.definderapps.data.remote.response.createreview.CreateReviewResponse
import com.dicoding.definderapps.data.remote.retrofit.ApiService
import com.dicoding.definderapps.data.remote.retrofit.ApiServiceMbti
import com.dicoding.definderapps.data.remote.response.detail.DetailResponse
import com.dicoding.definderapps.data.remote.response.detailtransport.DetailTransportResponse
import com.dicoding.definderapps.data.remote.response.favorite.FavoriteResponse
import com.dicoding.definderapps.data.remote.response.favorite.GetFavoriteResponse
import com.dicoding.definderapps.data.remote.response.login.LoginResponse
import com.dicoding.definderapps.data.remote.response.mbti.MbtiDescResponse
import com.dicoding.definderapps.data.remote.response.mbti.MbtiResponse
import com.dicoding.definderapps.data.remote.response.place.PlaceResponse
import com.dicoding.definderapps.data.remote.response.profile.EditProfileResponse
import com.dicoding.definderapps.data.remote.response.profile.GetUserResponse
import com.dicoding.definderapps.data.remote.response.register.RegisterResponse
import com.dicoding.definderapps.data.remote.response.review.GetReviewResponse
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
    private val apiServiceMbti: ApiServiceMbti,
    private val dao: Dao
) {
    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            userPreference: UserPreference,
            darkMode: DarkModePreference,
            homeLocPreference: HomeLocPreference,
            apiService: ApiService,
            apiServiceMbti: ApiServiceMbti,
            dao:Dao
        ): Repository =
            instance ?: synchronized(this) {
                instance
                    ?: Repository(userPreference,darkMode,homeLocPreference, apiService, apiServiceMbti, dao)
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

    fun getPlaceHome(token:String,daerah: String, objek:String, mbti:String) = flow {
        emit(ResultState.Loading)
        try {
            val response = apiService.getPlaceHome("Bearer $token",daerah, objek, mbti)
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

    suspend fun insertFavPlace(favPlace:Entity){
        dao.insertFavPlace(favPlace)
    }

    fun insertFavPlaceApi(token:String, placeId: Int) = liveData {
        emit(ResultState.Loading)
        try {
            val response =apiService.createFavorite("Bearer $token", placeId)
            emit(ResultState.Success(response))
        }catch (e: HttpException){
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, FavoriteResponse::class.java)
            emit(ResultState.Error(errorResponse.message))
        }
    }

    fun getFavPlace(placeId:Int):Flow<Entity>{
        return dao.getFavPlace(placeId)
    }

    fun getAllFavPlace():Flow<List<Entity>>{
        return dao.getAllFavPlace()
    }

    fun getFavPlaceApi(token: String) = flow {
        emit(ResultState.Loading)
        try {
            val response = apiService.getFavorite("Bearer $token")
            emit(ResultState.Success(response))
        }catch (e:HttpException){
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, GetFavoriteResponse::class.java)
            emit(ResultState.Error(errorResponse?.message.toString()))
        }
    }


    suspend fun deleteFavPlace(favPlace: Entity){
        dao.deleteFavPlace(favPlace)
    }

    fun deleteFavPlaceApi(token:String, placeId: Int) = liveData {
        emit(ResultState.Loading)
        try {
            val response =apiService.deleteFavorite("Bearer $token", placeId)
            emit(ResultState.Success(response))
        }catch (e: HttpException){
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, FavoriteResponse::class.java)
            emit(ResultState.Error(errorResponse.message))
        }
    }

    fun getMbtiDesc(token: String) = flow {
        emit(ResultState.Loading)
        try {
            val response = apiService.getMbtiDesc("Bearer $token")
            emit(ResultState.Success(response))
        }catch (e:HttpException){
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, MbtiDescResponse::class.java)
            emit(ResultState.Error(errorResponse?.message.toString()))
        }
    }

    fun createReview(token:String,placeId: Int, review: String) = liveData {
        emit(ResultState.Loading)
        try {
            val response =apiService.createReview("Bearer $token",placeId, review, "4")
            emit(ResultState.Success(response))
        }catch (e: HttpException){
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, CreateReviewResponse::class.java)
            emit(ResultState.Error(errorResponse.message))
        }
    }

    fun getReview(token: String, placeId: Int) = flow {
        emit(ResultState.Loading)
        try {
            val response = apiService.getReview("Bearer $token",placeId)
            emit(ResultState.Success(response))
        }catch (e:HttpException){
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, GetReviewResponse::class.java)
            emit(ResultState.Error(errorResponse?.message.toString()))
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

    fun getToken():Flow<String>{
        return preference.getToken()
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