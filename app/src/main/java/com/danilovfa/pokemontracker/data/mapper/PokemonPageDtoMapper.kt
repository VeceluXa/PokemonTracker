package com.danilovfa.pokemontracker.data.mapper

import com.danilovfa.pokemontracker.data.remote.response.PokemonPageDto
import com.danilovfa.pokemontracker.domain.model.PokemonItem

class PokemonPageDtoMapper: Mapper<PokemonPageDto, List<PokemonItem>> {
    override fun mapFromEntity(entity: PokemonPageDto): List<PokemonItem> {
        val list = mutableListOf<PokemonItem>()
        val itemMapper = PokemonDtoMapper()
        entity.results.forEach {
            list.add(itemMapper.mapFromEntity(it))
        }
        return list
    }

    override fun mapToEntity(domain: List<PokemonItem>): PokemonPageDto {
        throw UnsupportedOperationException("Not implemented")
    }
}