package com.santiago.twitter6application.controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import com.santiago.twitter6application.R
import com.santiago.twitter6application.interfaces.ApiService
import com.santiago.twitter6application.models.Constants
import com.santiago.twitter6application.models.DBHelper
import com.santiago.twitter6application.models.DBManager
import com.santiago.twitter6application.models.users.Data
import com.santiago.twitter6application.models.users.Users
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SplashActivity : AppCompatActivity() {

    var dbManager : DBManager ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val dbHelper = DBHelper(applicationContext)
        dbHelper.writableDatabase

        dbManager = DBManager(applicationContext)

        //retrofitList()
        startTimer()

    }

    fun retrofitList() {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ApiService::class.java)
        service.getList(2).enqueue(object : Callback<Users>{
            override fun onResponse(call: Call<Users>, response: Response<Users>) {
                for (i in 0..response.body()!!.data.size-1){
                    Log.e("Lista>>>",response.body()!!.data[i].toString())
                    var result = dbManager?.insertar(Data(response.body()!!.data[i].email,response.body()!!.data[i].first_name,response.body()!!.data[i].last_name,response.body()!!.data[i].avatar))
                    if (result!!>0){
                        Toast.makeText(applicationContext, "datos insertados", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(applicationContext, "datos no insertados", Toast.LENGTH_SHORT).show()
                    }

                }
            }
            override fun onFailure(call: Call<Users>, t: Throwable) {
                Log.e("Error>>>",t.message.toString())
            }

        })
    }

    fun startTimer() {
        object : CountDownTimer(2000,1000){
            override fun onTick(millisUntilFinished: Long) {
            }
            override fun onFinish() {
                val intent = Intent(applicationContext,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.start()
    }
}