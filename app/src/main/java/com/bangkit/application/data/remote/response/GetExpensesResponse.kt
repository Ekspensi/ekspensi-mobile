package com.bangkit.application.data.remote.response

import com.google.gson.annotations.SerializedName

data class GetExpensesResponse(

    @field:SerializedName("data")
    val data: List<DataExpenses?>? = null,

    @field:SerializedName("error")
    val error: String? = null,

    @field:SerializedName("message")
    val message: String? = null,

    @field:SerializedName("statusCode")
    val statusCode: Int? = null
)

data class DataExpenses(

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("data")
    val data: String? = null,

    @field:SerializedName("datetime")
    val dateTime: String? = null,

    @field:SerializedName("deskripsi")
    val deskripsi: String? = null,

    @field:SerializedName("nominal")
    val nominal: Int? = null,

    @field:SerializedName("klasifikasi")
    val klasifikasi: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,
)