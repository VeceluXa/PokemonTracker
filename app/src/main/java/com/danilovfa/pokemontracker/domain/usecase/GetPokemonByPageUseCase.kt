package com.danilovfa.pokemontracker.domain.usecase

import com.danilovfa.pokemontracker.domain.model.PokemonItem
import com.danilovfa.pokemontracker.domain.repository.IPokemonPageRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonByPageUseCase(private val pokemonPageRepository: IPokemonPageRepository) {
    suspend fun execute() : List<PokemonItem> {
        return pokemonPageRepository.getPage()
    }
}