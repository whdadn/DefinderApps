package com.dicoding.definderapps.repository

import com.dicoding.definderapps.model.Destination
import com.dicoding.definderapps.model.DestinationData

class Repository {

    fun getDestination():List<Destination>{
        return DestinationData.destination
    }

    fun searchDestination(query: String): List<Destination>{
        return DestinationData.destination.filter {
            it.name.contains(query, ignoreCase = true)
        }
    }

    companion object {
        @Volatile
        private var instance: Repository? = null

        fun getInstance(): Repository =
            instance ?: synchronized(this) {
                Repository().apply {
                    instance = this
                }
            }
    }
}