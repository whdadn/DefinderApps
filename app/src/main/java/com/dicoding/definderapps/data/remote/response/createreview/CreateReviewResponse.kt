package com.dicoding.definderapps.data.remote.response.createreview

import com.google.gson.annotations.SerializedName

data class CreateReviewResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: String
)

data class Data(

	@field:SerializedName("review")
	val review: String,

	@field:SerializedName("placeId")
	val placeId: Int,

	@field:SerializedName("rating")
	val rating: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("userId")
	val userId: Int
)
