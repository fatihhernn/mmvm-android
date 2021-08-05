package com.fatihhernn.mmvm.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.fatihhernn.mmvm.data.entity.rickandmortyEntity.RickAndMortyBaseResponse
import com.fatihhernn.mmvm.data.ApiRepository
import com.fatihhernn.mmvm.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: ApiRepository
) : ViewModel() {

    /*
    fun fetchRickMortyList():LiveData<Resource<RickAndMortyBaseResponse>>
                = repository.getCharacterList()

     */

    override fun onCleared() {
        super.onCleared()
    }
}