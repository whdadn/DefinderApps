package com.dicoding.definderapps.data.remote.response.favorite

import com.google.gson.annotations.SerializedName

data class GetFavoriteResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class DataItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("favoriteId")
	val favoriteId: Int? = null,

	@field:SerializedName("reviews")
	val reviews: Int? = null,

	@field:SerializedName("placeId")
	val placeId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("rating")
	val rating: String? = null,

	@field:SerializedName("location")
	val location: String? = null
)
