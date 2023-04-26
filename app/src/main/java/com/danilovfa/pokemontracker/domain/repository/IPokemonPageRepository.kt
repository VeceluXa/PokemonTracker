package com.danilovfa.pokemontracker.domain.repository

import androidx.paging.PagingData
import com.danilovfa.pokemontracker.domain.model.PokemonItem
import kotlinx.coroutines.flow.Flow

interface IPokemonPageRepository {
    fun getPage() : Flow<PagingData<PokemonItem>>
}