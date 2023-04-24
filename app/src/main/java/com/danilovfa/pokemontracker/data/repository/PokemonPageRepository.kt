package com.danilovfa.pokemontracker.data.repository

import com.danilovfa.pokemontracker.data.local.dao.PokemonPageDao
import com.danilovfa.pokemontracker.data.remote.PokemonPageAPI
import com.danilovfa.pokemontracker.domain.model.PokemonItem
import com.danilovfa.pokemontracker.domain.repository.IPokemonPageRepository

class PokemonPageRepository(private val pokemonsPageAPI: PokemonPageAPI, private val dao: PokemonPageDao) : IPokemonPageRepository {
    override suspend fun getPage(): List<PokemonItem> {
        // TODO Add implementation
        // TODO Get cached items. If not cached get from API
        val list = mutableListOf<PokemonItem>()
        dao.getPage().forEach { list.add(it.toDomain()) }
        return list
    }
}