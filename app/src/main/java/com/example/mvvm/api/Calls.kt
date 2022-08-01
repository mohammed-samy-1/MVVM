package com.example.mvvm.api

import com.example.mvvm.model.DataModel
import com.example.mvvm.model.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Calls {
    @GET("products")
    fun getProducts():Call<DataModel>
    @GET("products/{id}")
    fun getProductById(@Path("id") productId: Int):Call<Product>
}