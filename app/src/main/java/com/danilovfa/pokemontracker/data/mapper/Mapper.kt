package com.danilovfa.pokemontracker.data.mapper

interface Mapper<Entity, Domain> {
    fun mapFromEntity(entity: Entity): Domain
    fun mapToEntity(domain: Domain): Entity
}