package com.dicoding.definderapps.data.remote.response.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("result")
	val result: Result,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("token")
	val token: String
)

data class Result(

	@field:SerializedName("password")
	val password: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("email")
	val email: String
)
