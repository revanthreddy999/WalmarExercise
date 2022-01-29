package com.example.walmartexercise.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.walmartexercise.model.WalmartProductsApiResponse
import com.example.walmartexercise.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MainActivityRepository {

    val serviceSetterGetter = MutableLiveData<WalmartProductsApiResponse>()

    fun getServicesApiCall(): MutableLiveData<WalmartProductsApiResponse> {

        val call = RetrofitClient.apiInterface.getProducts()

        call.enqueue(object: Callback<WalmartProductsApiResponse> {
            override fun onFailure(call: Call<WalmartProductsApiResponse>, t: Throwable) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(
                call: Call<WalmartProductsApiResponse>,
                response: Response<WalmartProductsApiResponse>
            ) {
                // TODO("Not yet implemented")
                Log.v("DEBUG : ", response.body().toString())

                val data = response.body()

                val msg = data!!.statusCode

                serviceSetterGetter.value = data
            }
        })

        return serviceSetterGetter
    }
}