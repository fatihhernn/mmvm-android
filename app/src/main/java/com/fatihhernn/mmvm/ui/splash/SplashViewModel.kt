package com.fatihhernn.mmvm.ui.splash

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.fatihhernn.mmvm.data.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val apiRepository: ApiRepository
):ViewModel() {

    private val navigationLiveData=MutableLiveData<String>()
    fun observeNavigationLiveData():LiveData<String> =navigationLiveData

    fun checkTokenAndNavigation() {
        Handler().postDelayed({
            if (apiRepository.checkToken().isNullOrEmpty()){
                navigationLiveData.value="auth"
            }
            else
                navigationLiveData.value="home"

        },2000)
    }
}