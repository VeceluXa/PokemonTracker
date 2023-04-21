package com.danilovfa.pokemontracker.domain.repository

import com.danilovfa.pokemontracker.domain.model.PokemonItem
import kotlinx.coroutines.flow.Flow

interface IPokemonPageRepository {
    suspend fun getPage() : List<PokemonItem>
}