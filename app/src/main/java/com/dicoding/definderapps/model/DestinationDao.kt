package com.dicoding.definderapps.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DestinationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDestination(destination: List<Destination>)

    @Query("SELECT * from destination")
    fun getAllDestination(): Flow<List<Destination>>

    @Query("SELECT * FROM destination WHERE name LIKE '%' || :name || '%'")
    fun getDestinationByName(name:String): Flow<List<Destination>>

    @Query("UPDATE destination SET favorited=:favorited WHERE id=:id")
    suspend fun updateFavorited(id: Int, favorited: Boolean)

}