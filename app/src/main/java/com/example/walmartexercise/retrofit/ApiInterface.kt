package com.example.walmartexercise.retrofit

import com.example.walmartexercise.model.WalmartProductsApiResponse
import retrofit2.Call
import retrofit2.http.POST

interface ApiInterface {

    @POST("/walmartproducts/1/1")
    fun getProducts() : Call<WalmartProductsApiResponse>

}