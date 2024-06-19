package com.bangkit.application.data.remote.retrofit

import com.bangkit.application.data.remote.request.EkspensiRequest
import com.bangkit.application.data.remote.request.LoginRequest
import com.bangkit.application.data.remote.request.RegisterRequest
import com.bangkit.application.data.remote.response.EkspensiResponse
import com.bangkit.application.data.remote.response.GetExpensesResponse
import com.bangkit.application.data.remote.response.LoginResponse
import com.bangkit.application.data.remote.response.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiService {

    @POST("user")
    suspend fun register( // TODO: validate if it use email or not
        @Body request: RegisterRequest
    ): RegisterResponse

    @POST("auth/signin")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @GET("/ekspensi")
    suspend fun getExpenses(
        @Header("Authorization") token: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 10,
    ): GetExpensesResponse

    @POST("/ekspensi")
    suspend fun postExpenses(
        @Header("Authorization") token: String,
        @Body request: EkspensiRequest
    ): EkspensiResponse

//    @FormUrlEncoded
//    @POST("login")
//    suspend fun login(
//        @Field("username") username: String,
//        @Field("password") password: String
//    ): LoginResponse

}