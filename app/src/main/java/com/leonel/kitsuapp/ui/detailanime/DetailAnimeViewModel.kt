package com.leonel.kitsuapp.ui.detailanime

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonel.kitsuapp.model.Anime

import com.leonel.kitsuapp.repository.animeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailAnimeViewModel @Inject constructor(private val repository: animeRepository): ViewModel() {

    val animeByIdLiveData = MutableLiveData<Anime>()
    val isLoading = MutableLiveData<Boolean>()

    fun getAnimeById(id: String){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result= consultAnimeById(id)
                if (result?.attributes!= null) {
                    animeByIdLiveData.postValue(result)
                    isLoading.postValue(false)
                } else
                    isLoading.postValue(false)

        }
    }


    //**********Caso de Uso*************************
    private suspend fun consultAnimeById(id: String): Anime? {
        return repository.getAnimeById(id)
    }

    fun convertRating(rating: String): Float{
        return try {
            val rat= rating.toFloat()
            val value = (rat*5)/100
            val roundOff = String.format("%.1f", value)
            roundOff.toFloat()
        } catch (e: Exception) {
            0f
        }
    }


}