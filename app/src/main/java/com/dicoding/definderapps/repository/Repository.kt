package com.dicoding.definderapps.repository

import com.dicoding.definderapps.data.dao.AboutDestination
import com.dicoding.definderapps.data.dao.Destination
import com.dicoding.definderapps.data.dao.DestinationDao
import com.dicoding.definderapps.data.dao.DestinationWithImage
import com.dicoding.definderapps.data.dao.ImageDestination
import com.dicoding.definderapps.data.pref.UserModel
import com.dicoding.definderapps.data.pref.UserPreference
import kotlinx.coroutines.flow.Flow

class Repository private constructor(
    private val preference: UserPreference,
    private val destinationDao: DestinationDao) {
    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            userPreference: UserPreference,
            dao:DestinationDao
        ): Repository =
            instance ?: synchronized(this) {
                instance
                    ?: Repository(userPreference, dao)
            }.also { instance = it }
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
    fun getAllDestinationWithImage(): Flow<List<DestinationWithImage>>{
        return destinationDao.getAllDestinationWithImage()
    }
    fun getDestinationWithImageByName(name:String): Flow<List<DestinationWithImage>>{
        return destinationDao.getDestinationWithImageByName(name)
    }

    fun getFavoriteDestinationWithImage():Flow<List<DestinationWithImage>>{
        return destinationDao.getFavoritedDestinationWithImage()
    }

    fun getDetailDestination(id:Int):Flow<Destination>{
        return destinationDao.getDetailDestination(id)
    }

    fun getDetailImageDestination(idDestination: Int):Flow<List<ImageDestination>>{
        return destinationDao.getDetailImageDestination(idDestination)
    }

    fun getDetailAboutDestination(idDestination: Int):Flow<AboutDestination>{
        return destinationDao.getDetailAboutDestination(idDestination)
    }
    suspend fun isFavorited(id:Int, favorited:Boolean){
        destinationDao.updateFavorited(id, favorited)
    }

}