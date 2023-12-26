package com.dicoding.definderapps.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Entity::class], version = 1, exportSchema = false)
abstract class FavDatabase : RoomDatabase() {
    abstract fun dao(): Dao

    companion object {
        @Volatile
        private var instance: FavDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): FavDatabase {
            if (instance ==null){
                synchronized(FavDatabase::class.java){
                    instance = Room.databaseBuilder(context.applicationContext,
                        FavDatabase::class.java, "fav_database.db")
                        .build()
                }
            }
            return instance as FavDatabase
        }
    }
}