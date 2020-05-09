package com.vishalecho.android.core.network

sealed class Response<T> {
    data class Progress<T>(var loading: Boolean) :Response<T>()
    data class Success<T>(var data: T) :Response<T>()
    data class Failure<T>(var e: Throwable) :Response<T>()

    companion object {
        fun <T> loading(isLoading: Boolean): Response<T> = Progress(isLoading)
        fun <T> success(data: T): Response<T> = Success(data)
        fun <T> failure(e: Throwable): Response<T> = Failure(e)
    }
}