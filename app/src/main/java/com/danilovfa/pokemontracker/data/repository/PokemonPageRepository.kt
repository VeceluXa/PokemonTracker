package com.danilovfa.pokemontracker.data.repository

import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.map
import com.danilovfa.pokemontracker.data.local.model.PokemonItemEntity
import com.danilovfa.pokemontracker.data.mapper.PokemonItemEntityMapper
import com.danilovfa.pokemontracker.domain.model.PokemonItem
import com.danilovfa.pokemontracker.domain.repository.IPokemonPageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PokemonPageRepository @Inject constructor(
    private val pager: Pager<Int, PokemonItemEntity>
) : IPokemonPageRepository {
    val mapper = PokemonItemEntityMapper()

    override fun getPage(): Flow<PagingData<PokemonItem>> {
        return pager.flow.map { pagingData ->
            pagingData.map { pokemonItemEntity ->
                mapper.mapFromEntity(pokemonItemEntity)
            }
        }
    }

}