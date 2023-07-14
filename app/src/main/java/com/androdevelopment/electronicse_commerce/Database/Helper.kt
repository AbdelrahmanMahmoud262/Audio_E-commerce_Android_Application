package com.androdevelopment.electronicse_commerce.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.androdevelopment.electronicse_commerce.Models.HistoryModel


class Helper(
    context: Context?
) : SQLiteOpenHelper(context, DATABASE_NAME, null, VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {

        val query =
            ("CREATE TABLE $HISTORY_TABLE ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_TEXT TEXT)")

        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $HISTORY_TABLE")
        onCreate(db)
    }

    fun getHistory(): List<HistoryModel> {

        val db = this.readableDatabase
        val list = arrayListOf<HistoryModel>()

        val cursor = db.rawQuery("SELECT* FROM $HISTORY_TABLE", null)

        if(cursor.moveToFirst()) {
            do {
                val model = HistoryModel(cursor.getString(1))
                list.add(model)
            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return list
    }

    fun addHistory(model: HistoryModel) {

        val values = ContentValues()

        values.put(COLUMN_TEXT, model.text)

        val db = this.writableDatabase
        db.insert(HISTORY_TABLE, null, values)

        db.close()
    }

    companion object {
        private const val DATABASE_NAME = "Audio"

        private const val VERSION = 1

        private const val HISTORY_TABLE = "history_table"

        private const val COLUMN_ID = "id"

        private const val COLUMN_TEXT = "text"
    }
}