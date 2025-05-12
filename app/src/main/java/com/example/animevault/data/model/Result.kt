package com.example.animevault.data.model

sealed class Result<T> {
  data class Success<T>(val data: T? = null) : Result<T>()
  data class Failure<T>(val message: String) : Result<T>()
}