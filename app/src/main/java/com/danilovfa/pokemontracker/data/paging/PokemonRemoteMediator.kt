package com.danilovfa.pokemontracker.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.danilovfa.pokemontracker.data.local.dao.PokemonPageDao
import com.danilovfa.pokemontracker.data.mapper.PokemonItemEntityMapper
import com.danilovfa.pokemontracker.data.mapper.PokemonPageDtoMapper
import com.danilovfa.pokemontracker.data.remote.PokemonPageAPI
import com.danilovfa.pokemontracker.domain.model.PokemonItem
import com.danilovfa.pokemontracker.utils.Constants.Companion.PAGE_SIZE
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@ExperimentalPagingApi
class PokemonRemoteMediator @Inject constructor(
    private val pokemonPageDao: PokemonPageDao,
    private val pokemonPageAPI: PokemonPageAPI
): RemoteMediator<Int, PokemonItem>() {
    // Mappers
    private val pokemonPageDtoMapper = PokemonPageDtoMapper()
    private val pokemonItemEntityMapper = PokemonItemEntityMapper()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonItem>
    ): MediatorResult {
        return try {
            val page = when(loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    lastItem.id
                }
            } ?: 0

            val response = pokemonPageAPI.getPokemonPage(PAGE_SIZE, page)
            if (response.isSuccessful) {
                val pokemons = pokemonPageDtoMapper.mapFromEntity(response.body()!!)
                val entities = pokemons.map { pokemon -> pokemonItemEntityMapper.mapToEntity(pokemon) }
                pokemonPageDao.insertAll(entities)
            } else {
                return MediatorResult.Error(HttpException(response))
            }

            val endOfPagination = true
            return if (response.isSuccessful) {
                if (endOfPagination) {
                     MediatorResult.Success(true)
                } else {
                     MediatorResult.Success(false)
                }

            } else {
                 MediatorResult.Success(true)
            }
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}