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
    private val dbDetailsMapper = PokemonDetailsEntityMapper()
    private val apiDetailsMapper = PokemonDetailsDtoMapper(context)

    override suspend fun getDetails(pokemonItem: PokemonItem): PokemonDetails {
        val details: PokemonDetails = if (dao.isDetailsExist(pokemonItem.id))
            dbGetDetails(pokemonItem.id)
        else
            apiGetDetails(pokemonItem.id)

        Log.d("Details", "getDetails: ${detailsAPI.getPokemonDetails(0).body().toString()}")
        return details
    }

    private suspend fun dbGetDetails(id: Int): PokemonDetails {
        val dbDetails = dao.getDetailsById(id)
        return dbDetailsMapper.mapFromEntity(dbDetails!!)
    }

    private suspend fun apiGetDetails(id: Int): PokemonDetails {
        val apiDetails = detailsAPI.getPokemonDetails(id)
        if (apiDetails.body() != null) {
            val detailsPair = apiDetailsMapper.mapFromEntity(apiDetails.body()!!)
            dao.insertDetails(detailsPair.second)
            return detailsPair.first
        }
        // TODO handle api retrieval errors
        throw(Exception("Error"))
    }
}