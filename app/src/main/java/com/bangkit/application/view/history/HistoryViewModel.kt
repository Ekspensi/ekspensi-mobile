package com.bangkit.application.view.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.application.data.UserRepository
import com.bangkit.application.data.remote.response.EkspensiResponse
import kotlinx.coroutines.launch

class HistoryViewModel(private val repository: UserRepository): ViewModel() {
    // TODO: for history and input page purpose

    suspend fun postExpenses(data: String): EkspensiResponse{
        return repository.postExpenses(data)
    }

}