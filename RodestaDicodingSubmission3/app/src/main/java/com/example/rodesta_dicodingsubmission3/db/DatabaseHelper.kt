package com.example.rodesta_dicodingsubmission3.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.rodesta_dicodingsubmission3.db.DatabaseContract.FavColumns.Companion.TABLE_NAME

internal class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "userDB"
        private const val DATABASE_VERSION = 1
        private const val SQL_CREATE_TABLE_NOTE = "CREATE TABLE $TABLE_NAME" +
                " (${DatabaseContract.FavColumns.USERNAME} TEXT PRIMARY KEY  NOT NULL," +
                " ${DatabaseContract.FavColumns.NAME} TEXT NOT NULL," +
                " ${DatabaseContract.FavColumns.AVATAR} TEXT NOT NULL," +
                " ${DatabaseContract.FavColumns.COMPANY} TEXT NOT NULL," +
                " ${DatabaseContract.FavColumns.LOCATION} TEXT NOT NULL," +
                " ${DatabaseContract.FavColumns.REPOSITORY} TEXT NOT NULL," +
                " ${DatabaseContract.FavColumns.FAVORITE} TEXT NOT NULL)"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_TABLE_NOTE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}