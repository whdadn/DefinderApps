package com.dicoding.definderapps.repository

import android.content.Context
import com.dicoding.definderapps.model.Destination
import com.dicoding.definderapps.model.DestinationDao
import com.dicoding.definderapps.model.DestinationDatabase
import kotlinx.coroutines.flow.Flow

class Repository(private val destinationDao: DestinationDao) {
    companion object {

        @Volatile
        private var instance: Repository? = null

        fun getInstance(context: Context): Repository {
            return instance ?: synchronized(this) {
                if (instance == null) {
                    val database = DestinationDatabase.getInstance(context)
                    instance = Repository(database.destinationDao())
                }
                return instance as Repository
            }

        }
    }


    fun getAllDestination(): Flow<List<Destination>> {
        return destinationDao.getAllDestination()
    }

    fun getDestinationByName(name:String): Flow<List<Destination>>{
        return destinationDao.getDestinationByName(name)
    }

   suspend fun isFavorited(id:Int, favorited:Boolean){
       destinationDao.updateFavorited(id, favorited)
    }

}