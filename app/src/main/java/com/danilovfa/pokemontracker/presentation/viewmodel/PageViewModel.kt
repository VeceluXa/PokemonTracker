package com.danilovfa.pokemontracker.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.danilovfa.pokemontracker.data.local.model.PokemonItemEntity
import com.danilovfa.pokemontracker.data.mapper.PokemonItemEntityMapper
import com.danilovfa.pokemontracker.domain.usecase.GetPokemonByPageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class PageViewModel @Inject constructor(
    private val getPokemonByPageUseCase: GetPokemonByPageUseCase
) : ViewModel() {
    val mapper = PokemonItemEntityMapper()
    val pokemons = getPokemonByPageUseCase.execute()
}