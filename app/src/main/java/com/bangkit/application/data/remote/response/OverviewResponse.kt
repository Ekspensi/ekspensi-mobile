package com.bangkit.application.data.remote.response

import com.google.gson.annotations.SerializedName

data class OverviewResponse(

	@field:SerializedName("data")
	val data: DataOveriview? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("error")
	val error: Any? = null,

	@field:SerializedName("statusCode")
	val statusCode: Int? = null
)

data class DataOveriview(

	@field:SerializedName("prev_month")
	val prevMonth: String? = null,

	@field:SerializedName("current_month")
	val currentMonth: String? = null,

	@field:SerializedName("freq_trx_current_month")
	val freqTrxCurrentMonth: Int? = null,

	@field:SerializedName("expenses_growth_%")
	val expensesGrowthPercentage: String? = null,

	@field:SerializedName("vol_trx_current_month")
	val volTrxCurrentMonth: String? = null,

	@field:SerializedName("freq_trx_prev_month")
	val freqTrxPrevMonth: Int? = null,

	@field:SerializedName("vol_trx_prev_month")
	val volTrxPrevMonth: String? = null,

	@field:SerializedName("expenses_growth")
	val expensesGrowth: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
