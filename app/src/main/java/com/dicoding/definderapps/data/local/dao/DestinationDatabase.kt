package com.dicoding.definderapps.data.local.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dicoding.definderapps.data.local.InitialDataSource
import java.util.concurrent.Executors

@Database(
    entities = [Destination::class, ImageDestination::class, AboutDestination::class, TransportData::class],
    version = 1,
    exportSchema = false
)
abstract class DestinationDatabase : RoomDatabase() {


    abstract fun destinationDao(): DestinationDao

    companion object {

        @Volatile
        private var INSTANCE: DestinationDatabase? = null

        fun getInstance(context: Context): DestinationDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DestinationDatabase::class.java,
                    "destination.db"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            INSTANCE?.let {
                                Executors.newSingleThreadExecutor().execute {
                                    val dao = it.destinationDao()
                                    dao.insertDestination(InitialDataSource.getDestination())
                                    dao.insertImageDestination(InitialDataSource.getImageDestination())
                                    dao.insertAboutDestination(InitialDataSource.getAboutDestination())
                                }
                            }
                        }
                    }).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
