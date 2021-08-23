package com.mmarengo.android.recipes.data.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitApiClient(
    baseUrl: String,
    loggingEnabled: Boolean
) {

    private val moshiKotlin: Moshi by lazy {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(createHttpClient(loggingEnabled))
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshiKotlin))
            .build()
    }

    fun <T> createService(apiClass: Class<T>): T {
        return retrofit.create(apiClass)
    }

    private fun createHttpClient(loggingEnabled: Boolean): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()

        if (loggingEnabled) {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            httpClientBuilder.interceptors().add(loggingInterceptor)
            httpClientBuilder.addNetworkInterceptor(StethoInterceptor())
        }

        return httpClientBuilder.build()
    }
}
