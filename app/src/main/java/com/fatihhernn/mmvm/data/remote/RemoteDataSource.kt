package com.fatihhernn.mmvm.data.remote

import com.example.rickandmorty.data.entity.listing.RickAndMortyBaseResponse
import com.fatihhernn.mmvm.util.BaseDataSource
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: NetworkApiService) :BaseDataSource() {

    suspend fun fetchListCharacters(page:Int=1) =getResult { apiService.listCharacter(1) }
}