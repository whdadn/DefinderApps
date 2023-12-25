package com.dicoding.definderapps.data.remote.response.detail

import com.google.gson.annotations.SerializedName

data class DetailResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Data(

	@field:SerializedName("image")
	val image: List<ImageItem?>? = null,

	@field:SerializedName("price")
	val price: Double? = null,

	@field:SerializedName("placeId")
	val placeId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("about")
	val about: List<String?>? = null
)

data class ImageItem(

	@field:SerializedName("image")
	val image: String? = null
)
