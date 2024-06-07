package com.bangkit.application.view.signup

import androidx.lifecycle.ViewModel
import com.bangkit.application.data.UserRepository
import com.bangkit.application.data.remote.response.RegisterResponse

class SignupViewModel(private val repository: UserRepository) : ViewModel() {
    //TODO: for signup purpose
    suspend fun register(username: String, phoneNum: String, email: String, password: String): RegisterResponse{
        return repository.register(username, phoneNum, email, password)
    }
}