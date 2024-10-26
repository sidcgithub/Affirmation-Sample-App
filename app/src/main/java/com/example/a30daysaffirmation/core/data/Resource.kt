package com.example.a30daysaffirmation.core.data

sealed interface Resource<out T> {
    data class Success<out T>(val data: T): Resource<T>
    data class Error<out T>(val message: String): Resource<T>
}