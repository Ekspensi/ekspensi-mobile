package com.bangkit.application.data.entity
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class History(
    var id: Int = 0,
    var expenses: String? = null
) : Parcelable
