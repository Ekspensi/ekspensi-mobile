package com.bangkit.application.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.bangkit.application.data.pref.UserModel
import com.bangkit.application.data.pref.UserPreference
import com.bangkit.application.data.remote.ExpensesPagingSource
import com.bangkit.application.data.remote.request.EkspensiRequest
import com.bangkit.application.data.remote.request.LoginRequest
import com.bangkit.application.data.remote.request.RegisterRequest
import com.bangkit.application.data.remote.response.DataItem
import com.bangkit.application.data.remote.response.EkspensiResponse
import com.bangkit.application.data.remote.response.GetExpensesResponse
import com.bangkit.application.data.remote.response.LoginResponse
import com.bangkit.application.data.remote.response.RegisterResponse
import com.bangkit.application.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

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

    suspend fun register(username: String, phoneNum: String, password: String ):RegisterResponse{
        return apiService.register(RegisterRequest(password, phoneNum, username))
    }

    suspend fun login(username: String, password: String): LoginResponse{
        return apiService.login(LoginRequest(password, username))
    }

    fun getExpenses(): LiveData<PagingData<DataItem>>{
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                ExpensesPagingSource(apiService, userPreference)
            }
        ).liveData
    }

    suspend fun postExpenses(data: String): EkspensiResponse{
        return apiService.postExpenses("Bearer " + userPreference.getSession().first().token, EkspensiRequest(data))
    }

    suspend fun logout(){
        userPreference.logout()
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