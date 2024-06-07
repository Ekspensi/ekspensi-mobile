package com.bangkit.application.data

import com.bangkit.application.data.pref.UserModel
import com.bangkit.application.data.pref.UserPreference
import com.bangkit.application.data.remote.request.LoginRequest
import com.bangkit.application.data.remote.request.RegisterRequest
import com.bangkit.application.data.remote.response.LoginResponse
import com.bangkit.application.data.remote.response.RegisterResponse
import com.bangkit.application.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow

class UserRepository private constructor(
    private val userPreference: UserPreference,
    private val apiService: ApiService,
)   {

    suspend fun saveSession(user: UserModel){
        userPreference.saveSession(user)
    }

    fun getSession(): Flow<UserModel> { // not using suspend because Flow is already async
        return userPreference.getSession()
    }

    suspend fun register(username: String, phoneNum: String, email: String, password: String ):RegisterResponse{
        return apiService.register(RegisterRequest(password, phoneNum, email, username))
    }

    suspend fun login(username: String, password: String): LoginResponse{
        return apiService.login(LoginRequest(password, username))
    }
    
    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            userPreference: UserPreference,
            apiService: ApiService,
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference, apiService)
            }.also { instance = it }
    }
}