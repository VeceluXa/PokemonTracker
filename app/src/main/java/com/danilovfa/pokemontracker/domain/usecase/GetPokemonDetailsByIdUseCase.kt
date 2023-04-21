package com.danilovfa.pokemontracker.domain.usecase

import com.danilovfa.pokemontracker.domain.model.PokemonDetails
import com.danilovfa.pokemontracker.domain.model.PokemonItem
import com.danilovfa.pokemontracker.domain.repository.IPokemonDetailsRepository

class GetPokemonDetailsByIdUseCase(private val pokemonDetailsRepository: IPokemonDetailsRepository) {
    fun execute(pokemonItem: PokemonItem) : PokemonDetails {
        // TODO Add implementation
        // TODO Get page local. If null get from api.
        return pokemonDetailsRepository.getDetailsCached(pokemonItem)
    }
}