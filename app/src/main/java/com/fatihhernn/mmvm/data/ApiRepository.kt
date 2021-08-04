package com.fatihhernn.mmvm.data

import com.fatihhernn.mmvm.data.local.LocalDataSource
import com.fatihhernn.mmvm.data.remote.RemoteDataSource
import com.fatihhernn.mmvm.util.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    //private val localDataSource: LocalDataSource
) {
    fun getCharacterList()= performNetworkOperation {
        remoteDataSource.fetchListCharacters()
    }
}