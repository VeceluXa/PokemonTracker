package com.danilovfa.pokemontracker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.danilovfa.pokemontracker.data.mapper.PokemonItemEntityMapper
import com.danilovfa.pokemontracker.domain.usecase.GetPokemonByPageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PageViewModel @Inject constructor(
    private val getPokemonByPageUseCase: GetPokemonByPageUseCase
) : ViewModel() {
    val mapper = PokemonItemEntityMapper()
    val pokemons = getPokemonByPageUseCase.execute()
}