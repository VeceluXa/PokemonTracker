package com.danilovfa.pokemontracker.data.mapper

import com.danilovfa.pokemontracker.data.local.model.PokemonDetailsEntity
import com.danilovfa.pokemontracker.domain.model.PokemonDetails

class PokemonDetailsEntityMapper: Mapper<PokemonDetailsEntity, PokemonDetails> {
    override fun mapFromEntity(entity: PokemonDetailsEntity): PokemonDetails {
        return PokemonDetails(
            name = entity.name,
            sprite = entity.sprite,
            types = entity.types,
            weightInKg = entity.weightInKg,
            heightInCm = entity.heightInCm
        )
    }

    override fun mapToEntity(domain: PokemonDetails): PokemonDetailsEntity {
        return PokemonDetailsEntity(
            name = domain.name,
            sprite = domain.sprite,
            types = domain.types,
            weightInKg = domain.weightInKg,
            heightInCm = domain.heightInCm
        )
    }

}