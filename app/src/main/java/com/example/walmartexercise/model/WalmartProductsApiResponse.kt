
package com.example.walmartexercise.model


data class WalmartProductsApiResponse (

	val products : List<Products>,
	val totalProducts : Int,
	val pageNumber : Int,
	val pageSize : Int,
	val statusCode : Int
)