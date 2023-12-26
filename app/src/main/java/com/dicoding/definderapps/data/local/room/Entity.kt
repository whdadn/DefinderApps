package com.dicoding.definderapps.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_place")
class Entity(
    @PrimaryKey(autoGenerate = false)
    var placeId:Int
)