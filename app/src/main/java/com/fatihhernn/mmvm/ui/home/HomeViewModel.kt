package com.fatihhernn.mmvm.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.data.entity.listing.RickAndMortyBaseResponse
import com.fatihhernn.mmvm.data.ApiRepository
import com.fatihhernn.mmvm.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: ApiRepository
) : ViewModel() {

    fun fetchRickMortyList():LiveData<Resource<RickAndMortyBaseResponse>>
                = repository.getCharacterList()

    override fun onCleared() {
        super.onCleared()
    }
}