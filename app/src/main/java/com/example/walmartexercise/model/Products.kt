package com.example.walmartexercise.model


data class Products (

	val productId : String,
	val productName : String,
	val shortDescription : String,
	val longDescription : String,
	val price : String,
	val productImage : String,
	val reviewRating : Int,
	val reviewCount : Int,
	val inStock : Boolean
)