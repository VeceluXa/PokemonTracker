package com.danilovfa.pokemontracker.data.repository

import android.util.Log
import com.danilovfa.pokemontracker.data.local.dao.PokemonPageDao
import com.danilovfa.pokemontracker.data.mapper.PokemonItemEntityMapper
import com.danilovfa.pokemontracker.data.mapper.PokemonPageDtoMapper
import com.danilovfa.pokemontracker.data.remote.PokemonPageAPI
import com.danilovfa.pokemontracker.domain.model.PokemonItem
import com.danilovfa.pokemontracker.domain.repository.IPokemonPageRepository
import com.danilovfa.pokemontracker.utils.Constants.Companion.PAGE_SIZE
import javax.inject.Inject

class PokemonPageRepository @Inject constructor(
    private val pokemonsPageAPI: PokemonPageAPI,
    private val dao: PokemonPageDao
) : IPokemonPageRepository {
    // Offset for paging
    private var offset = 0
    private var isFirstLoad = true

    // Mappers
    private val itemEntityMapper = PokemonItemEntityMapper()
    private val pageDtoMapper = PokemonPageDtoMapper()

    // List of pokemons to display
    val pokemons = mutableListOf<PokemonItem>()

    /**
     * Get pokemons by page
     * If function is called first time only cached items will be returned
     * Else each time new items from api will be added to list
     * After adding items from api they will be cached in the database
     */
    override suspend fun getPage(): List<PokemonItem> {
        pokemons += if (isFirstLoad)
            getFromDb()
        else
            getFromApi(pokemons)

        Log.d("Page", "getPage: List $pokemons")
        isFirstLoad = false
        return pokemons
    }

    private suspend fun getFromDb(): List<PokemonItem> {
        val list = mutableListOf<PokemonItem>()
        dao.getPage()?.forEach() { list.add(itemEntityMapper.mapFromEntity(it)) }
        return list
    }

    private suspend fun getFromApi(listFromDb: List<PokemonItem>): List<PokemonItem> {
        val list = mutableListOf<PokemonItem>()
        val lastCachedId = if (listFromDb.isEmpty()) 0 else listFromDb.size
        if (lastCachedId > offset)
            offset = lastCachedId

        val page = pokemonsPageAPI.getPokemonPage(PAGE_SIZE, offset).body()

        if (page != null) {
            val apiList = pageDtoMapper.mapFromEntity(page)
            list += apiList
            apiList.forEach { dao.insertItem(itemEntityMapper.mapToEntity(it)) }
        }

        offset += PAGE_SIZE

        return list
    }
}