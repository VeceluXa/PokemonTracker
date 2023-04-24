package com.danilovfa.pokemontracker.data.remote.response
import com.google.gson.annotations.SerializedName

data class PokemonDetailsDto(
    val abilities: List<AbilityDto>,
    @SerializedName("base_experience")
    val baseExperience: Int,
    val forms: List<FormDto>,
    val height: Int,
    val id: Int,
    @SerializedName("is_default")
    val isDefault: Boolean,
    val moves: List<MoveDto>,
    val name: String,
    val order: Int,
    val species: SpeciesDto,
    val sprites: SpritesDto,
    val stats: List<StatDto>,
    val types: List<TypeDto>,
    val weight: Int
)

