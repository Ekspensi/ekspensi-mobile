package com.bangkit.application.data.remote.request

import com.google.gson.annotations.SerializedName

data class EkspensiRequest(

	@field:SerializedName("data")
	val data: String? = null
)
