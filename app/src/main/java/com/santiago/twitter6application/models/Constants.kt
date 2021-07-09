package com.santiago.twitter6application.models

class Constants {
    companion object{
        //idActualizar
        var ID_ACTUALIZAR = 0

        //apiUrl
        val API_URL = "https://reqres.in/api/"

        //bd

        val DB_NAME = "TWITTER6"
        val DB_VERSION = 1

        val TABLE_NAME = "USER"
        val TABLE_COLUMN_1 = "id"
        val TABLE_COLUMN_2 = "email"
        val TABLE_COLUMN_3 = "first_name"
        val TABLE_COLUMN_4 = "last_name"
        val TABLE_COLUMN_5 = "avatar"

        val TABLE_CREATE = " CREATE TABLE " + TABLE_NAME + " ( " +
               TABLE_COLUMN_1 + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
               TABLE_COLUMN_2 + " TEXT NOT NULL , " +
               TABLE_COLUMN_3 + " TEXT NOT NULL , " +
               TABLE_COLUMN_4 + " TEXT NOT NULL , " +
               TABLE_COLUMN_5 + " BLOB ) "

        val QUERY_ALL = " SELECT * FROM "


    }
}