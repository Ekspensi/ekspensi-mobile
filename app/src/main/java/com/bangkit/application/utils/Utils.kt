package com.bangkit.application.utils

import java.time.OffsetDateTime
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

fun convertToDateOnly(dateString: String): String {
    val offsetDateTime = OffsetDateTime.parse(dateString)
    val localDate = offsetDateTime.toLocalDate()
    return localDate.toString()
}

fun formatToRupiah(amount: Int): String{
    val locale = Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(locale)

    numberFormat.currency = Currency.getInstance("IDR")
    numberFormat.maximumFractionDigits = 2
    numberFormat.minimumFractionDigits = 2

    val formattedAmount = numberFormat.format(amount)

    return formattedAmount
}