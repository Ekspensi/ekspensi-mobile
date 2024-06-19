package com.bangkit.application.data.remote.response

import com.google.gson.annotations.SerializedName

data class EkspensiResponse(

	@field:SerializedName("data")
	val data: DataEkspensiResponse? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("error")
	val error: String? = null,

	@field:SerializedName("statusCode")
	val statusCode: Int? = null
)

data class DataEkspensiResponse(

	@field:SerializedName("datetime")
	val datetime: String? = null,

	@field:SerializedName("data")
	val data: String? = null,

	@field:SerializedName("nominal")
	val nominal: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("deskripsi")
	val deskripsi: String? = null,

	@field:SerializedName("klasifikasi")
	val klasifikasi: String? = null
)
