package com.example.walmartexercise.view

object Preconditions {
    fun checkNotNull(`object`: Any?, message: String) {
        requireNotNull(`object`) { message }
    }

    fun checkIfPositive(number: Int, message: String) {
        require(number > 0) { message }
    }
}