package com.fatihhernn.mmvm.data.entity.rickandmortyEntity


import com.google.gson.annotations.SerializedName

data class RickAndMortyBaseResponse(
    @SerializedName("info")
    val info: İnfo,
    @SerializedName("results")
    val characters: List<Character>
)