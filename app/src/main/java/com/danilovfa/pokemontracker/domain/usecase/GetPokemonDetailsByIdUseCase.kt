package com.danilovfa.pokemontracker.domain.usecase

import com.danilovfa.pokemontracker.domain.model.PokemonDetails
import com.danilovfa.pokemontracker.domain.model.PokemonItem
import com.danilovfa.pokemontracker.domain.repository.IPokemonDetailsRepository

class GetPokemonDetailsByIdUseCase(private val pokemonDetailsRepository: IPokemonDetailsRepository) {
    suspend fun execute(pokemonItem: PokemonItem) : PokemonDetails {
        return pokemonDetailsRepository.getDetails(pokemonItem)
    }
}