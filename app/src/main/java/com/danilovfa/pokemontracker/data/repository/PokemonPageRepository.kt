package com.danilovfa.pokemontracker.data.repository

import com.danilovfa.pokemontracker.data.local.PokemonsPageCache
import com.danilovfa.pokemontracker.data.remote.PokemonsPageAPI
import com.danilovfa.pokemontracker.domain.model.PokemonItem
import com.danilovfa.pokemontracker.domain.repository.IPokemonsPageRepository

class PokemonPageRepository(private val pokemonsPageAPI: PokemonsPageAPI, private val pokemonsPageCache: PokemonsPageCache) : IPokemonsPageRepository {
    override fun getPageCached(): List<PokemonItem> {
        // TODO Add implementation
        return pokemonsPageCache.get()
    }

    override fun getPageApi(): List<PokemonItem> {
        // TODO Add implementation
        return pokemonsPageAPI.get()
    }
}