package com.danilovfa.pokemontracker.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.danilovfa.pokemontracker.data.local.dao.PokemonPageDao
import com.danilovfa.pokemontracker.data.mapper.PokemonItemEntityMapper
import com.danilovfa.pokemontracker.data.mapper.PokemonPageDtoMapper
import com.danilovfa.pokemontracker.data.remote.PokemonPageAPI
import com.danilovfa.pokemontracker.domain.model.PokemonItem
import com.danilovfa.pokemontracker.utils.Constants.Companion.PAGE_SIZE
import retrofit2.HttpException
import javax.inject.Inject

class PokemonPagingSource @Inject constructor(
    private val pokemonPageDao: PokemonPageDao
) : PagingSource<Int, PokemonItem>() {
    // Mappers
    private val pokemonItemMapper = PokemonItemEntityMapper()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonItem> {
        try {
            val pageNumber = params.key ?: INITIAL_PAGE_NUMBER
            val pageSize = params.loadSize.coerceAtLeast(PAGE_SIZE)
            val entities = pokemonPageDao.getItemsByPage(pageSize, pageNumber * pageSize)
            val pokemons: List<PokemonItem> = entities.map { entity -> pokemonItemMapper.mapFromEntity(entity) }
            return if (pokemons.isNotEmpty())
                LoadResult.Page(
                    data = pokemons,
                    prevKey = if (pokemons.isEmpty()) null else pageNumber + 1,
                    nextKey = if (pageNumber > 1) pageNumber - 1 else null
                )
            else
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonItem>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

    companion object {
        const val INITIAL_PAGE_NUMBER = 0
    }
}