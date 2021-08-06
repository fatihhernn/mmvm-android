package com.fatihhernn.mmvm.data.remote

import android.util.Log
import com.fatihhernn.mmvm.data.entity.login.LoginRequest
import com.fatihhernn.mmvm.data.entity.login.LoginResponse
import com.fatihhernn.mmvm.data.entity.register.RegisterRequest
import com.fatihhernn.mmvm.data.entity.register.RegisterResponse
import com.fatihhernn.mmvm.data.entity.rickandmortyEntity.Character
import com.fatihhernn.mmvm.data.entity.rickandmortyEntity.RickAndMortyBaseResponse
import retrofit2.Response
import retrofit2.http.*

interface NetworkApiService {

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest):Response<RegisterResponse>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest):Response<LoginResponse>
}

