package com.dicoding.definderapps.data.remote.response.mbti

import com.google.gson.annotations.SerializedName

data class MbtiResponse(

	@field:SerializedName("mbti")
	val mbti: String,

	@field:SerializedName("message")
	val message: String? = null,
)
