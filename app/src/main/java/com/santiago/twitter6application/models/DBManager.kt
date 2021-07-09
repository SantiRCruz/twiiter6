package com.santiago.twitter6application.models

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.santiago.twitter6application.models.users.Data

class DBManager(context: Context) {

    val dbHelper = DBHelper(context)
    var db: SQLiteDatabase? = null

    fun openWrDb() {
        db = dbHelper.writableDatabase
    }

    fun openRdDb() {
        db = dbHelper.readableDatabase
    }

    fun closeDb() {
        if (db != null) {
            db?.close()
        }
    }

    fun insertar(data: Data): Long {
        openWrDb()
        var values = ContentValues()
        values.put(Constants.TABLE_COLUMN_2, data.email)
        values.put(Constants.TABLE_COLUMN_3, data.first_name)
        values.put(Constants.TABLE_COLUMN_4, data.last_name)
        values.put(Constants.TABLE_COLUMN_5, data.avatar)
        var result = db?.insert(Constants.TABLE_NAME, null, values)
        closeDb()
        return result!!
    }

    fun listar(): MutableList<Data> {
        var lista: MutableList<Data> = ArrayList()
        openRdDb()
        var result = db?.rawQuery(Constants.QUERY_ALL + Constants.TABLE_NAME, null)
        if (result!!.moveToFirst())
            do {
                var data = Data()
                data.id = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_1)).toInt()
                data.email = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_2))
                data.first_name = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_3))
                data.last_name = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_4))
                data.avatar = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_5))
                lista.add(data)
            } while (result.moveToNext())
        closeDb()
        return lista
    }

    fun listarId(data: Data): MutableList<Data> {
        var lista: MutableList<Data> = ArrayList()
        openRdDb()
        var result = db?.rawQuery(
            Constants.QUERY_ALL + Constants.TABLE_NAME + " WHERE id =? ",
            arrayOf(data.id.toString())
        )
        if (result!!.moveToFirst())
            do {
                var data = Data()
                data.id = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_1)).toInt()
                data.email = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_2))
                data.first_name = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_3))
                data.last_name = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_4))
                data.avatar = result.getString(result.getColumnIndex(Constants.TABLE_COLUMN_5))
                lista.add(data)
            } while (result.moveToNext())
        openWrDb()
        return lista
    }
    fun actualizar(data: Data):Int{
        openWrDb()
        var values = ContentValues()
        values.put(Constants.TABLE_COLUMN_2,data.email)
        values.put(Constants.TABLE_COLUMN_3,data.first_name)
        values.put(Constants.TABLE_COLUMN_4,data.last_name)
        var result = db?.update(Constants.TABLE_NAME,values,"id=?", arrayOf(data.id.toString()))
        closeDb()
        return result!!
    }

    fun eliminar(data: Data) {
        openWrDb()
        db?.delete(Constants.TABLE_NAME, "id=?", arrayOf(data.id.toString()))
        closeDb()
    }
}