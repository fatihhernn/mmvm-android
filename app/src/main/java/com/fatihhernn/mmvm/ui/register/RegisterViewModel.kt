package com.fatihhernn.mmvm.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.fatihhernn.mmvm.data.ApiRepository
import com.fatihhernn.mmvm.data.entity.register.RegisterRequest
import com.fatihhernn.mmvm.data.entity.register.RegisterResponse
import com.fatihhernn.mmvm.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val apiRepository: ApiRepository
) :ViewModel(

) {
    fun register(email: String, userName: String, password: String) :LiveData<Resource<RegisterResponse>>{
        val request=RegisterRequest(email,userName,password)
        return apiRepository.register(request)
    }
}