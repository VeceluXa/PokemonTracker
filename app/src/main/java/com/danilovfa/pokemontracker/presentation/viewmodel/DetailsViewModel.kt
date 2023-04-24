package com.danilovfa.pokemontracker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.danilovfa.pokemontracker.domain.usecase.GetPokemonDetailsByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPokemonDetailsByIdUseCase: GetPokemonDetailsByIdUseCase
): ViewModel() {
}