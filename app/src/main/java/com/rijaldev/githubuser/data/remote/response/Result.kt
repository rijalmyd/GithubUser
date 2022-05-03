package com.rijaldev.githubuser.data.remote.response

sealed class Result<out R> {
    data class Success<out T>(val data: T?): Result<T>()
    data class Error(val message: String): Result<Nothing>()
}