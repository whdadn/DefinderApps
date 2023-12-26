package com.dicoding.definderapps.data.remote.response.favorite

import com.google.gson.annotations.SerializedName

data class FavoriteResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class Data(

	@field:SerializedName("placeId")
	val placeId: Int,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("userId")
	val userId: Int
)
