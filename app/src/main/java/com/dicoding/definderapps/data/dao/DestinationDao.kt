package com.dicoding.definderapps.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface DestinationDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertDestination(destination: List<Destination>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertImageDestination(imageDestination: List<ImageDestination>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAboutDestination(aboutDestination: List<AboutDestination>)

    @Transaction
    @Query("SELECT * FROM destination")
    fun getAllDestinationWithImage(): Flow<List<DestinationWithImage>>

    @Transaction
    @Query("SELECT * FROM destination WHERE name LIKE '%' || :name || '%'")
    fun getDestinationWithImageByName(name:String): Flow<List<DestinationWithImage>>

    @Transaction
    @Query("SELECT * FROM destination WHERE favorited=1")
    fun getFavoritedDestinationWithImage(): Flow<List<DestinationWithImage>>

    @Query("SELECT * FROM destination WHERE id =:id")
    fun getDetailDestination(id:Int):Flow<Destination>

    @Query("SELECT * FROM imageDestination WHERE idDestination=:idDestination")
    fun getDetailImageDestination(idDestination:Int):Flow<List<ImageDestination>>

    @Query("SELECT * FROM aboutDestination WHERE idDestination=:idDestination")
    fun getDetailAboutDestination(idDestination: Int):Flow<AboutDestination>

    @Query("UPDATE destination SET favorited=:favorited WHERE id=:id")
    suspend fun updateFavorited(id: Int, favorited: Boolean)

}