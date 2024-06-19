package com.bangkit.application.data.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import com.bangkit.application.data.db.DatabaseContract.HistoryColumn.Companion.TABLE_NAME
import com.bangkit.application.data.db.DatabaseContract.HistoryColumn.Companion._ID

class HistoryHelper(context: Context) {
    private var dataBaseHelper: DatabaseHelper = DatabaseHelper(context)
    private lateinit var database: SQLiteDatabase

    companion object {
        private const val DATABASE_TABLE = TABLE_NAME
        private var INSTANCE: HistoryHelper? = null
        fun getInstance(context: Context): HistoryHelper =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: HistoryHelper(context)
            }
    }

    @Throws(SQLException::class)
    fun open() {
        database = dataBaseHelper.writableDatabase
    }
    fun close() {
        dataBaseHelper.close()
        if (database.isOpen)
            database.close()
    }

    fun queryAll(): Cursor {
        return database.query(
            DATABASE_TABLE,
            null,
            null,
            null,
            null,
            null,
            "$_ID DESC")
    }

    fun insert(values: ContentValues?): Long {
        return database.insert(DATABASE_TABLE, null, values)
    }
}