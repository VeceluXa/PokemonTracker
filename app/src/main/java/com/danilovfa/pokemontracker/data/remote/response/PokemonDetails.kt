package com.danilovfa.pokemontracker.data.remote.response
import com.google.gson.annotations.SerializedName

data class PokemonDetails(
    val abilities: List<Ability>,
    @SerializedName("base_experience")
    val baseExperience: Int,
    val forms: List<Form>,
    val height: Int,
    val id: Int,
    @SerializedName("is_default")
    val isDefault: Boolean,
    val moves: List<Move>,
    val name: String,
    val order: Int,
    val species: Species,
    val sprites: Sprites,
    val stats: List<Stat>,
    val types: List<Type>,
    val weight: Int
)

