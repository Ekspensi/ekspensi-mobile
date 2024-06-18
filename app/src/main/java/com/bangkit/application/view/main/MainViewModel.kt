package com.bangkit.application.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.bangkit.application.data.UserRepository
import com.bangkit.application.data.pref.UserModel
import com.bangkit.application.data.remote.response.DataItem
import com.bangkit.application.data.remote.response.GetExpensesResponse
import com.bangkit.application.data.remote.retrofit.ApiConfig
import kotlinx.coroutines.launch

class MainViewModel(private val repository: UserRepository): ViewModel() {
    // TODO: for main activity purpose
    val listData: LiveData<PagingData<DataItem>> = repository.getExpenses().cachedIn(viewModelScope)

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }

    fun logout(){
        viewModelScope.launch {
            repository.logout()
        }
    }
}