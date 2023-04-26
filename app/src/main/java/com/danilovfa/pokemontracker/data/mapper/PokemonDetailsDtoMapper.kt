package com.danilovfa.pokemontracker.data.mapper

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.danilovfa.pokemontracker.data.local.model.PokemonDetailsEntity
import com.danilovfa.pokemontracker.data.remote.response.PokemonDetailsDto
import com.danilovfa.pokemontracker.domain.model.PokemonDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonDetailsDtoMapper (var context: Context) {
    suspend fun mapFromEntity(entity: PokemonDetailsDto): Pair<PokemonDetails, PokemonDetailsEntity> {
        val bitmap = withContext(Dispatchers.IO) {
            Glide.with(context)
                .asBitmap()
                .load(entity.sprites.frontDefault)
                .transform(CenterCrop())
                .submit()
                .get()
        }


        return Pair(
            PokemonDetails(
                name = entity.name,
                types = entity.types.map { it.type.name },
                sprite = bitmap,
                weightInHg = entity.weight,
                heightInCm = entity.height
            ),
            PokemonDetailsEntity(
                name = entity.name,
                types = entity.types.map { it.type.name },
                sprite = bitmap,
                weightInHg = entity.weight,
                heightInCm = entity.height
            )
        )

    }
}