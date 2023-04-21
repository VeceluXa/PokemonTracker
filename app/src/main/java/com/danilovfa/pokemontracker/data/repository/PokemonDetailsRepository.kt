package com.danilovfa.pokemontracker.data.repository

import com.danilovfa.pokemontracker.data.local.PokemonDetailsCache
import com.danilovfa.pokemontracker.data.remote.PokemonDetailsAPI
import com.danilovfa.pokemontracker.domain.model.PokemonDetails
import com.danilovfa.pokemontracker.domain.model.PokemonItem
import com.danilovfa.pokemontracker.domain.repository.IPokemonDetailsRepository

class PokemonDetailsRepository(private val detailsAPI: PokemonDetailsAPI, private val detailsCache: PokemonDetailsCache) : IPokemonDetailsRepository {
    override fun getDetailsApi(pokemonItem: PokemonItem): PokemonDetails {
        // TODO Add implementation
        return detailsAPI.get()
    }

    override fun getDetailsCached(pokemonItem: PokemonItem): PokemonDetails {
        // TODO Add implementation
        return detailsCache.get(0)
    }
}