package com.fatihhernn.mmvm.data.remote

import com.fatihhernn.mmvm.data.entity.rickandmortyEntity.RickAndMortyBaseResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickApiService {
    @GET("character")
    suspend fun listCharacter(@Query("page") page:Int): Response<RickAndMortyBaseResponse>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id:Int):Response<Character>
}