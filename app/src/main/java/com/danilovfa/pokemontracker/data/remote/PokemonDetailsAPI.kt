package com.danilovfa.pokemontracker.data.remote

import com.danilovfa.pokemontracker.data.remote.response.PokemonPage
import com.danilovfa.pokemontracker.domain.model.PokemonDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonDetailsAPI {
    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(
        @Path("id") id: Int
    ) : Response<PokemonDetails>
}