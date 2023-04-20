package com.danilovfa.pokemontracker.domain.repository

import com.danilovfa.pokemontracker.domain.models.PokemonItem

interface IPokemonsPageRepository {
    fun getPageCached() : List<PokemonItem>
    fun getPageApi() : List<PokemonItem>
}