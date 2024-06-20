package com.bangkit.application.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bangkit.application.data.UserRepository
import com.bangkit.application.data.pref.UserModel
import com.bangkit.application.data.remote.response.LoginResponse
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    //TODO: for login purpose

    fun saveSession(user: UserModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }

    suspend fun login(email: String, password: String): LoginResponse {
        return repository.login(email, password)
    }

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }
}