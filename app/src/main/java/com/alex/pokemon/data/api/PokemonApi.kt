package com.alex.pokemon.data.api

import com.alex.pokemon.data.api.pokemon.Pokemon
import com.alex.pokemon.data.api.pokemonList.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): com.alex.pokemon.data.api.pokemonList.PokemonList

    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(
        @Path("name") name: String,
    ): com.alex.pokemon.data.api.pokemon.Pokemon
}
