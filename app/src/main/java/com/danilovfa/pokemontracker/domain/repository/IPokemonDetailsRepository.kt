package com.danilovfa.pokemontracker.domain.repository

import com.danilovfa.pokemontracker.domain.model.PokemonDetails
import com.danilovfa.pokemontracker.domain.model.PokemonItem

interface IPokemonDetailsRepository {
    suspend fun getDetails(pokemonItem: PokemonItem) : PokemonDetails
}