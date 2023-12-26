package com.dicoding.definderapps.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavPlace(favPlace:Entity)
    @Query("SELECT * FROM fav_place WHERE placeId=:placeId")
    fun getFavPlace(placeId:Int): Flow<Entity>

    @Query("SELECT * FROM fav_place")
    fun getAllFavPlace(): Flow<List<Entity>>

    @Delete
    suspend fun deleteFavPlace(favPlace: Entity)
}