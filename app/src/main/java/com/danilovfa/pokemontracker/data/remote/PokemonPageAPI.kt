package com.danilovfa.pokemontracker.data.remote

import com.danilovfa.pokemontracker.data.remote.response.PokemonPage
import com.danilovfa.pokemontracker.utils.PAGE_SIZE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonPageAPI {
    @GET("pokemon/")
    suspend fun getPokemonPage(
        @Query("limit") limit: Int = PAGE_SIZE,
        @Query("offset") offset: Int = 0
    ) : Response<PokemonPage>
}