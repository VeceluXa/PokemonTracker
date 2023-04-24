package com.danilovfa.pokemontracker.data.repository

import android.content.Context
import android.util.Log
import com.danilovfa.pokemontracker.data.local.dao.PokemonDetailsDao
import com.danilovfa.pokemontracker.data.mapper.PokemonDetailsDtoMapper
import com.danilovfa.pokemontracker.data.mapper.PokemonDetailsEntityMapper
import com.danilovfa.pokemontracker.data.remote.PokemonDetailsAPI
import com.danilovfa.pokemontracker.domain.model.PokemonDetails
import com.danilovfa.pokemontracker.domain.model.PokemonItem
import com.danilovfa.pokemontracker.domain.repository.IPokemonDetailsRepository
import javax.inject.Inject

class PokemonDetailsRepository @Inject constructor(
    private val detailsAPI: PokemonDetailsAPI,
    private val dao: PokemonDetailsDao,
    private val context: Context
) : IPokemonDetailsRepository {
    // Mappers
    val dbDetailsMapper = PokemonDetailsEntityMapper()
    val apiDetailsMapper = PokemonDetailsDtoMapper(context)

    override suspend fun getDetails(pokemonItem: PokemonItem): PokemonDetails {
        var details: PokemonDetails? = null

        // Check if cached
        val dbDetails = dao.getDetailsById(pokemonItem.id)
        if (dbDetails != null)
            details =  dbDetailsMapper.mapFromEntity(dbDetails)
        else {
            val apiDetails = detailsAPI.getPokemonDetails(pokemonItem.id)
            if (apiDetails.body() != null) {
                val detailsPair = apiDetailsMapper.mapFromEntity(apiDetails.body()!!)
                dao.insertDetails(detailsPair.second)
                details = detailsPair.first
            }
        }

        Log.d("Details", "getDetails: ${detailsAPI.getPokemonDetails(0).body().toString()}")
        return details!!
    }
}