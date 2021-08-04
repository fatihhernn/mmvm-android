package com.example.rickandmorty.data.entity.listing


import com.example.rickandmorty.data.entity.core.Character
import com.google.gson.annotations.SerializedName

data class RickAndMortyBaseResponse(
    @SerializedName("info")
    val info: İnfo,
    @SerializedName("results")
    val characters: List<Character>
)