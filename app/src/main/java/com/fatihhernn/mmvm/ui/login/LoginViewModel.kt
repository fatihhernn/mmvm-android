package com.fatihhernn.mmvm.ui.login

import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.fatihhernn.mmvm.data.ApiRepository
import com.fatihhernn.mmvm.data.entity.login.LoginRequest
import com.fatihhernn.mmvm.data.entity.login.LoginResponse
import com.fatihhernn.mmvm.databinding.FragmentSplashBinding
import com.fatihhernn.mmvm.ui.splash.SplashViewModel
import com.fatihhernn.mmvm.util.Resource
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    var apiRepository: ApiRepository
):ViewModel() {

    private val loginService= MutableLiveData<Resource<LoginResponse>>()
    fun observeNavigationLiveData(): LiveData<Resource<LoginResponse>> =loginService

    fun login(mail: String, password: String) :LiveData<Resource<LoginResponse>>{
        val request=LoginRequest(mail,password)
        apiRepository.login(request)
    }


}