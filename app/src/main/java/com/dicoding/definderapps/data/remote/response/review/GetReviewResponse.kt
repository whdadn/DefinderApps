package com.dicoding.definderapps.data.remote.response.review

import com.google.gson.annotations.SerializedName

data class GetReviewResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Data(

	@field:SerializedName("image")
	val image: List<ImageItem?>? = null,

	@field:SerializedName("reviews")
	val reviews: List<ReviewsItem?>? = null,

	@field:SerializedName("price")
	val price: Any? = null,

	@field:SerializedName("placeId")
	val placeId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null
)

data class ImageItem(

	@field:SerializedName("image")
	val image: String? = null
)

data class ReviewsItem(

	@field:SerializedName("review")
	val review: String? = null,

	@field:SerializedName("userName")
	val userName: String? = null
)
