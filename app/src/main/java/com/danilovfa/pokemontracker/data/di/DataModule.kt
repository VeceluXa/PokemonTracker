package com.danilovfa.pokemontracker.data.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.danilovfa.pokemontracker.data.local.dao.PokemonDetailsDao
import com.danilovfa.pokemontracker.data.local.dao.PokemonPageDao
import com.danilovfa.pokemontracker.data.local.database.PokemonDetailsDatabase
import com.danilovfa.pokemontracker.data.local.database.PokemonPageDatabase
import com.danilovfa.pokemontracker.data.remote.PokemonDetailsAPI
import com.danilovfa.pokemontracker.data.remote.PokemonPageAPI
import com.danilovfa.pokemontracker.data.repository.PokemonDetailsRepository
import com.danilovfa.pokemontracker.data.repository.PokemonPageRepository
import com.danilovfa.pokemontracker.domain.repository.IPokemonDetailsRepository
import com.danilovfa.pokemontracker.domain.repository.IPokemonPageRepository
import com.danilovfa.pokemontracker.presentation.App
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun providePokemonDetailsRepository(detailsAPI: PokemonDetailsAPI, dao: PokemonDetailsDao): IPokemonDetailsRepository {
        return PokemonDetailsRepository(detailsAPI, dao)
    }

    @Provides
    @Singleton
    fun providePokemonPageRepository(detailsAPI: PokemonPageAPI, dao: PokemonPageDao): IPokemonPageRepository {
        return PokemonPageRepository(detailsAPI, dao)
    }

    @Provides
    @Singleton
    fun providePokemonPageDatabase(app: Application) : PokemonPageDatabase {
        return Room.databaseBuilder(
            app,
            PokemonPageDatabase::class.java,
            PokemonPageDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providePokemonDetailsDatabase(app: Application) : PokemonDetailsDatabase {
        return Room.databaseBuilder(
            app,
            PokemonDetailsDatabase::class.java,
            PokemonDetailsDatabase.DATABASE_NAME
        ).build()
    }
}