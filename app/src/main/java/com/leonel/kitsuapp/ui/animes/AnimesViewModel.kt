package com.leonel.kitsuapp.ui.animes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonel.kitsuapp.model.Anime

import com.leonel.kitsuapp.repository.animeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimesViewModel @Inject constructor(private val repository: animeRepository): ViewModel() {

    val animesLiveData = MutableLiveData<List<Anime>>()
    val isLoading = MutableLiveData<Boolean>()

    fun getAnimes(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result= consultarAnimes()
            if (!result.isNullOrEmpty()) {
                animesLiveData.postValue(result)
                isLoading.postValue(false)
            }
            else
                isLoading.postValue(false)
        }
    }

    //**********Caso de Uso*************************
    private suspend fun consultarAnimes(): List<Anime>{
        return  repository.getAnimes()
    }



}