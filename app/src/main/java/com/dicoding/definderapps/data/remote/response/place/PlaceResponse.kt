package com.dicoding.definderapps.data.remote.response.place

import com.google.gson.annotations.SerializedName

data class PlaceResponse(

    @field:SerializedName("data")
	val data: List<DataItem>? = null,

    @field:SerializedName("message")
	val message: String? = null,

    @field:SerializedName("status")
	val status: String? = null,
)

data class DataItem(

	@field:SerializedName("image")
	val image: String? = "",

	@field:SerializedName("reviews")
	val reviews: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("rating")
	val rating: String? = null,

	@field:SerializedName("location")
	val location: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,
)
