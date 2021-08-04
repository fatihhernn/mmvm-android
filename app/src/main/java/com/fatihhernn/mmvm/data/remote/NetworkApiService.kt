package com.fatihhernn.mmvm.data.remote

import com.example.rickandmorty.data.entity.core.Character
import com.example.rickandmorty.data.entity.listing.RickAndMortyBaseResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkApiService {
    @GET("character")
    suspend fun listCharacter(@Query("page") page:Int): Response<RickAndMortyBaseResponse>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id:Int):Response<Character>
}

