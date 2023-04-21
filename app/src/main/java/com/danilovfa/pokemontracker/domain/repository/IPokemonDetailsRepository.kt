package com.danilovfa.pokemontracker.domain.repository

import com.danilovfa.pokemontracker.domain.model.PokemonDetails
import com.danilovfa.pokemontracker.domain.model.PokemonItem

interface IPokemonDetailsRepository {
    fun getDetailsApi(pokemonItem: PokemonItem) : PokemonDetails
    fun getDetailsCached(pokemonItem: PokemonItem) : PokemonDetails
}