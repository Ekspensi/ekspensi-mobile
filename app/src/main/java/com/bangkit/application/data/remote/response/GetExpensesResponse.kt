package com.bangkit.application.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetExpensesResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("error")
	val error: String? = null,

	@field:SerializedName("statusCode")
	val statusCode: Int? = null
)

data class DataItem(

	@field:SerializedName("no")
	val no: Int? = null,

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

data class Data(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("limit")
	val limit: Int? = null,

	@field:SerializedName("total_data")
	val totalData: Int? = null,

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("count_data")
	val countData: Int? = null
)
