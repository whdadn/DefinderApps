package com.dicoding.definderapps.data.local.dao

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity("destination")
data class Destination(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val name:String,
    val location:String,
    val rating:String,
    val price:Float,
    val favorited:Boolean
)

@Entity("imageDestination")
data class ImageDestination(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val imageUrl: String,
    val idDestination:Int
)

@Entity("aboutDestination")
data class AboutDestination(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val about:String,
    val idDestination: Int
)

data class DestinationWithImage(
    @Embedded
    val destination: Destination,

    @Relation(
        parentColumn = "id",
        entityColumn = "idDestination"
    )
    val imageDestination : List<ImageDestination>
)

