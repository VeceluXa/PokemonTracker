package com.danilovfa.pokemontracker.data.mapper

import com.danilovfa.pokemontracker.data.remote.response.PokemonDto
import com.danilovfa.pokemontracker.domain.model.PokemonItem
import com.danilovfa.pokemontracker.utils.Constants.Companion.BASE_URL

class PokemonDtoMapper: Mapper<PokemonDto, PokemonItem> {
    override fun mapFromEntity(entity: PokemonDto): PokemonItem {
        val id = entity.url
            .replace("${BASE_URL}pokemon/", "")
            .replace("/", "")
            .toInt()
        return PokemonItem(
            name = entity.name,
            id = id
        )

    }

    override fun mapToEntity(domain: PokemonItem): PokemonDto {
        val url = "${BASE_URL}pokemon/" + domain.id + "/"
        return PokemonDto(
            name = domain.name,
            url = url
        )
    }
}