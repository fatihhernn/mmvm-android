package com.fatihhernn.mmvm.data

import com.fatihhernn.mmvm.data.entity.login.LoginRequest
import com.fatihhernn.mmvm.data.entity.register.RegisterRequest
import com.fatihhernn.mmvm.data.local.LocalDataSource
import com.fatihhernn.mmvm.data.remote.RemoteDataSource
import com.fatihhernn.mmvm.util.performAuthTokenNetworkOperation
import com.fatihhernn.mmvm.util.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    fun getCharacterList()= performNetworkOperation {
        remoteDataSource.fetchListCharacters()
    }


    fun register(registerRequest: RegisterRequest)= performNetworkOperation {
        remoteDataSource.postRegister(registerRequest)
    }

    /*
    fun login(loginRequest: LoginRequest)= performNetworkOperation {
        remoteDataSource.postLogin(loginRequest)
    }
     */

    fun login(loginRequest: LoginRequest)= performAuthTokenNetworkOperation(
        call = {
            remoteDataSource.postLogin(loginRequest)},
        saveToken = {
            localDataSource.saveToken(it)
        }
    )
    fun checkToken():String?{
        val token= localDataSource.getToken()
        return token
    }
}