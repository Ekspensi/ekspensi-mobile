package com.bangkit.application.data.remote.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    // TODO: change using actual apiconfig when service is available
//    fun getApiService(): ApiService {
//        val loggingInterceptor = HttpLoggingInterceptor()
//            .setLevel(HttpLoggingInterceptor.Level.BODY)
////        val authInterceptor = Interceptor { chain ->
////            val req = chain.request()
////            val requestHeaders = req.newBuilder()
////                .addHeader("Authorization", "Bearer $token")
////                .build()
////            chain.proceed(requestHeaders)
////        }
//        val client: OkHttpClient = OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
//            .build()
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://story-api.dicoding.dev/v1/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//        return retrofit.create(ApiService::class.java)
//    }
}