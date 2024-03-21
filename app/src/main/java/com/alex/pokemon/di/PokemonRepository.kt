package com.alex.pokemon.di

import com.alex.pokemon.data.pokemon.Pokemon
import com.alex.pokemon.data.pokemonList.PokemonList
import com.alex.pokemon.util.Resource

interface PokemonRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList>
    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon>
}
