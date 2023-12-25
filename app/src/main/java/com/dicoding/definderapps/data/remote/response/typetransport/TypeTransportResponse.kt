package com.dicoding.definderapps.data.remote.response.typetransport

import com.google.gson.annotations.SerializedName

data class TypeTransportResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Data(

	@field:SerializedName("placeId")
	val placeId: Int? = null,

	@field:SerializedName("transportation")
	val transportation: List<TransportationItem?>? = null
)

data class TransportationItem(

	@field:SerializedName("type")
	val type: String? = null
)