package com.danilovfa.pokemontracker.data.remote.response

import com.google.gson.annotations.SerializedName

data class AbilityDto(
    val ability: AbilityXDto,
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    val slot: Int
)