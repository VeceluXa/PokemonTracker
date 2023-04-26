package com.danilovfa.pokemontracker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilovfa.pokemontracker.domain.model.PokemonDetails
import com.danilovfa.pokemontracker.domain.usecase.GetPokemonDetailsByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPokemonDetailsByIdUseCase: GetPokemonDetailsByIdUseCase
): ViewModel() {
    var id: Int? = null

    private val _details = MutableLiveData<PokemonDetails>()
    val details: LiveData<PokemonDetails> = _details

    private var _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun loadDetails() {
        CoroutineScope(Dispatchers.IO).launch {
            var details: PokemonDetails? = null
            try {
                details = getPokemonDetailsByIdUseCase.execute(id!!)
            } catch (e: Exception) {
                _error.postValue(e.message)
            }
            if (details != null) {
                _details.postValue(details!!)
            }
        }
    }
}