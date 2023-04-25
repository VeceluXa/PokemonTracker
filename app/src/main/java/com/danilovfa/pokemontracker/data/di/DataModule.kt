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
import com.danilovfa.pokemontracker.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun providePokemonDetailsRepository(
        detailsAPI: PokemonDetailsAPI,
        dao: PokemonDetailsDao,
        @ApplicationContext appContext: Context): IPokemonDetailsRepository {
        return PokemonDetailsRepository(detailsAPI, dao, appContext)
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
    fun providePokemonPageDao(db: PokemonPageDatabase) : PokemonPageDao {
        return db.pokemonPageDao
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

    @Provides
    @Singleton
    fun providePokemonDetailsDao(db: PokemonDetailsDatabase) : PokemonDetailsDao {
        return db.pokemonDetailsDao
    }

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providePokemonDetailsAPI(retrofit: Retrofit): PokemonDetailsAPI = retrofit.create(PokemonDetailsAPI::class.java)

    @Provides
    @Singleton
    fun providePokemonPageAPI(retrofit: Retrofit): PokemonPageAPI = retrofit.create(PokemonPageAPI::class.java)
}