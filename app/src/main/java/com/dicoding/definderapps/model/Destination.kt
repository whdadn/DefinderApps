package com.dicoding.definderapps.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("destination")
data class Destination(
    @PrimaryKey
    val id:Int,
    val name:String,
    val imageUrl: String,
    val location:String,
    val rating:String,
    val favorited:Boolean
)

