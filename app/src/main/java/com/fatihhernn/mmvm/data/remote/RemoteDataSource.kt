package com.fatihhernn.mmvm.data.remote

import com.fatihhernn.mmvm.data.entity.login.LoginRequest
import com.fatihhernn.mmvm.data.entity.login.LoginResponse
import com.fatihhernn.mmvm.data.entity.register.RegisterRequest
import com.fatihhernn.mmvm.util.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: NetworkApiService,
    private val rickApi:RickApiService
    ) :BaseDataSource() {

    suspend fun fetchListCharacters(page:Int=1) =getResult { rickApi.listCharacter(1) }

    suspend fun postRegister(request: RegisterRequest)=getResult { apiService.register(request) }

    suspend fun postLogin(request: LoginRequest)=getResult { apiService.login(request) }
}