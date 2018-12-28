package com.example.simhyunyong.minimercaru.data

import retrofit2.Call
import retrofit2.http.GET

interface WebService {

    @GET("/photos")
    fun getProducts(): Call<List<Product>>
}