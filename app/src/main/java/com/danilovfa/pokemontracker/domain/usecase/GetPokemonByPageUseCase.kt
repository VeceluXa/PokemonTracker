package com.danilovfa.pokemontracker.domain.usecase

import androidx.paging.PagingData
import com.danilovfa.pokemontracker.domain.model.PokemonItem
import com.danilovfa.pokemontracker.domain.repository.IPokemonPageRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonByPageUseCase(private val pokemonPageRepository: IPokemonPageRepository) {
    fun execute() : Flow<PagingData<PokemonItem>> {
        return pokemonPageRepository.getPage()
    }
}