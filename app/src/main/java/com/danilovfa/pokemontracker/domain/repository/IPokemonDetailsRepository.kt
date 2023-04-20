package com.danilovfa.pokemontracker.domain.repository

import com.danilovfa.pokemontracker.domain.models.PokemonDetails
import com.danilovfa.pokemontracker.domain.models.PokemonItem

interface IPokemonDetailsRepository {
    fun getDetailsApi(pokemonItem: PokemonItem) : PokemonDetails
    fun getDetailsCached(pokemonItem: PokemonItem) : PokemonDetails
}