package com.danilovfa.pokemontracker.data.mapper

import com.danilovfa.pokemontracker.data.local.model.PokemonItemEntity
import com.danilovfa.pokemontracker.domain.model.PokemonItem

class PokemonItemEntityMapper: Mapper<PokemonItemEntity, PokemonItem> {
    override fun mapFromEntity(entity: PokemonItemEntity): PokemonItem {
        return PokemonItem(
            name = entity.name,
            id = entity.id
        )
    }

    override fun mapToEntity(domain: PokemonItem): PokemonItemEntity {
        return PokemonItemEntity(
            name = domain.name,
            id = domain.id
        )
    }
}