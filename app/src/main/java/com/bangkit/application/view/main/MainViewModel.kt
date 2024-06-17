package com.bangkit.application.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bangkit.application.data.UserRepository
import com.bangkit.application.data.pref.UserModel
import com.bangkit.application.data.remote.response.DataExpenses
import com.bangkit.application.data.remote.response.GetExpensesResponse
import com.bangkit.application.data.remote.retrofit.ApiConfig
import kotlinx.coroutines.launch

class MainViewModel(private val repository: UserRepository): ViewModel() {
    // TODO: for main activity purpose
    private val _listData = MutableLiveData<GetExpensesResponse>()
    val listData: LiveData<GetExpensesResponse> = _listData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getExpenses(){
        viewModelScope.launch {
            try {

                val response = repository.getExpenses()
                _listData.value = response
            } catch (_: Exception){}
        }
    }

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }
}