package com.mmarengo.android.recipes.data

sealed class Response<out R> {

    object Loading : Response<Nothing>()

    data class Success<out T>(val data: T): Response<T>()

    data class Error(val throwable: Throwable) : Response<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$throwable]"
            Loading -> "Loading"
        }
    }
}

val Response<*>.succeeded
    get() = this is Response.Success<*> && data != null
