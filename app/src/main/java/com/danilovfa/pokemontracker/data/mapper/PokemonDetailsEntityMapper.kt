package com.danilovfa.pokemontracker.data.mapper

import com.danilovfa.pokemontracker.data.local.model.PokemonDetailsEntity
import com.danilovfa.pokemontracker.domain.model.PokemonDetails

class PokemonDetailsEntityMapper: Mapper<PokemonDetailsEntity, PokemonDetails> {
    override fun mapFromEntity(entity: PokemonDetailsEntity): PokemonDetails {
        return PokemonDetails(
            id = entity.id,
            name = entity.name,
            sprite = entity.sprite,
            types = entity.types,
            weightInHg = entity.weightInHg,
            heightInDm = entity.heightInDm
        )
    }

    override fun mapToEntity(domain: PokemonDetails): PokemonDetailsEntity {
        return PokemonDetailsEntity(
            id = domain.id,
            name = domain.name,
            sprite = domain.sprite,
            types = domain.types,
            weightInHg = domain.weightInHg,
            heightInDm = domain.heightInDm
        )
    }

}