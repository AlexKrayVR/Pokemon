package com.alex.pokemon.domain

import com.alex.pokemon.data.api.pokemon.Pokemon
import com.alex.pokemon.data.api.pokemonList.PokemonList
import com.alex.pokemon.util.Resource

interface PokemonRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList>
    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon>
}
