package com.danilovfa.pokemontracker.domain.di

import com.danilovfa.pokemontracker.data.repository.PokemonDetailsRepository
import com.danilovfa.pokemontracker.data.repository.PokemonPageRepository
import com.danilovfa.pokemontracker.domain.repository.IPokemonDetailsRepository
import com.danilovfa.pokemontracker.domain.usecase.GetPokemonByPageUseCase
import com.danilovfa.pokemontracker.domain.usecase.GetPokemonDetailsByIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetPokemonByPageUseCase(pokemonPageRepository: PokemonPageRepository) : GetPokemonByPageUseCase {
        return GetPokemonByPageUseCase(pokemonPageRepository)
    }

    @Provides
    fun provideGetPokemonDetailsByIdUseCase(pokemonDetailsRepository: PokemonDetailsRepository) : GetPokemonDetailsByIdUseCase {
        return GetPokemonDetailsByIdUseCase(pokemonDetailsRepository)
    }
}