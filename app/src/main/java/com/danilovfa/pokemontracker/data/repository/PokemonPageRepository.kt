package com.danilovfa.pokemontracker.data.repository

import com.danilovfa.pokemontracker.domain.model.PokemonItem
import com.danilovfa.pokemontracker.domain.repository.IPokemonsPageRepository

class PokemonPageRepository : IPokemonsPageRepository {
    override fun getPageCached(): List<PokemonItem> {
        // TODO Add implementation
        return listOf(PokemonItem("bulbasaur", 0))
    }

    override fun getPageApi(): List<PokemonItem> {
        // TODO Add implementation
        return listOf(PokemonItem("bulbasaur", 0))
    }
}