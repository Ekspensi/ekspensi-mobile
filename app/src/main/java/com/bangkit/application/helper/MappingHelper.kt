package com.bangkit.application.helper

import android.database.Cursor
import com.bangkit.application.data.db.DatabaseContract
import com.bangkit.application.data.entity.History

object MappingHelper {

    fun mapCursorToArrayList(notesCursor: Cursor?): ArrayList<History> {
        val notesList = ArrayList<History>()
        notesCursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(DatabaseContract.HistoryColumn._ID))
                val title = getString(getColumnIndexOrThrow(DatabaseContract.HistoryColumn.EXPENSES))
                notesList.add(History(id, title))
            }
        }
        return notesList
    }
}