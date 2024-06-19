package com.bangkit.application.data.db

import android.provider.BaseColumns

internal class DatabaseContract {

    internal class HistoryColumn : BaseColumns {
        companion object {
            const val TABLE_NAME = "history"
            const val _ID = "_id"
            const val EXPENSES = "expenses"
        }
    }
}