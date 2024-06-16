package com.bangkit.application.di

import android.content.Context
import com.bangkit.application.data.UserRepository
import com.bangkit.application.data.pref.UserPreference
import com.bangkit.application.data.pref.dataStore
import com.bangkit.application.data.remote.retrofit.ApiConfig

object Injection {
    //TODO: fix injection when api are already available
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
//        val user = runBlocking { pref.getSession().first() }
        val apiService = ApiConfig.getApiService()
        return UserRepository.getInstance(pref, apiService)
    }
}