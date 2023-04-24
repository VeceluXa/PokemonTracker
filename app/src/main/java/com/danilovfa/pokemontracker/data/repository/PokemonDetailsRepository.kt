package com.danilovfa.pokemontracker.data.repository

import com.danilovfa.pokemontracker.data.local.dao.PokemonDetailsDao
import com.danilovfa.pokemontracker.data.remote.PokemonDetailsAPI
import com.danilovfa.pokemontracker.domain.model.PokemonDetails
import com.danilovfa.pokemontracker.domain.model.PokemonItem
import com.danilovfa.pokemontracker.domain.repository.IPokemonDetailsRepository
import javax.inject.Inject

class PokemonDetailsRepository @Inject constructor(
    private val detailsAPI: PokemonDetailsAPI,
    private val dao: PokemonDetailsDao
) : IPokemonDetailsRepository {
    override suspend fun getDetails(pokemonItem: PokemonItem): PokemonDetails {
        // TODO Add implementation
        // TODO Get cached items. If not cached get from API
        return dao.getDetailsById(0)?.toDomain() ?: detailsAPI.get()
    }
}