package com.danilovfa.pokemontracker.data.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.danilovfa.pokemontracker.data.local.dao.PokemonPageDao
import com.danilovfa.pokemontracker.data.local.model.PokemonItemEntity
import com.danilovfa.pokemontracker.data.mapper.PokemonItemEntityMapper
import com.danilovfa.pokemontracker.data.mapper.PokemonPageDtoMapper
import com.danilovfa.pokemontracker.utils.Constants.Companion.PAGE_SIZE
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@ExperimentalPagingApi
class PokemonRemoteMediator @Inject constructor(
    private val pokemonPageDao: PokemonPageDao,
    private val pokemonPageAPI: PokemonPageAPI
): RemoteMediator<Int, PokemonItemEntity>() {
    // Mappers
    private val pokemonPageDtoMapper = PokemonPageDtoMapper()
    private val pokemonItemEntityMapper = PokemonItemEntityMapper()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonItemEntity>
    ): MediatorResult {
        val offset = getPageIndex(loadType, state)
            ?: return MediatorResult.Success(endOfPaginationReached = true)
        val limit = PAGE_SIZE

        return try {
            val response = pokemonPageAPI.getPokemonPage(PAGE_SIZE, offset)
            if (response.isSuccessful) {
                val pokemons = pokemonPageDtoMapper.mapFromEntity(response.body()!!)
                val entities = pokemons.map { pokemon -> pokemonItemEntityMapper.mapToEntity(pokemon) }
                Log.d("Pager", "Mediator: ${entities}")
                pokemonPageDao.insertAll(entities)
                MediatorResult.Success(
                    endOfPaginationReached = pokemons.size < limit
                )
            } else {
                MediatorResult.Error(HttpException(response))
            }
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private fun getPageIndex(loadType: LoadType, state: PagingState<Int, PokemonItemEntity>): Int? {
        return when(loadType) {
            LoadType.REFRESH -> 0
            LoadType.PREPEND -> null
            LoadType.APPEND -> state.lastItemOrNull()?.id ?: 0
        }
    }

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.SKIP_INITIAL_REFRESH
    }
}