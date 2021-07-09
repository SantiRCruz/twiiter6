package com.santiago.twitter6application.interfaces

import com.santiago.twitter6application.models.users.Users
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    fun getList(@Query("page")page:Int): Call<Users>

}