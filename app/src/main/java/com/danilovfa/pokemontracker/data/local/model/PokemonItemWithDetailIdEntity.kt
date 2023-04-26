package com.danilovfa.pokemontracker.data.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class PokemonItemWithDetailIdEntity (
    @Embedded val pokemonItem: PokemonItemEntity,
    val detailsId: Int?
)