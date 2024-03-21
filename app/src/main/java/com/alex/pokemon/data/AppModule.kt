package com.alex.pokemon.data

import com.alex.pokemon.data.api.PokemonApi
import com.alex.pokemon.data.repos.PokemonRepositoryImpl
import com.alex.pokemon.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePokemonRepository(
        api: PokemonApi
    ) = PokemonRepositoryImpl(api)


    @Singleton
    @Provides
    fun providePokemonApi(): PokemonApi {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL).build().create(PokemonApi::class.java)
    }


}
