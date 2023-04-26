package com.danilovfa.pokemontracker.data.remote.response

import com.google.gson.annotations.SerializedName

data class StatDto(
    @SerializedName("base_stat")
    val baseStat: Int,
    val effort: Int,
    val stat: StatXDto
)