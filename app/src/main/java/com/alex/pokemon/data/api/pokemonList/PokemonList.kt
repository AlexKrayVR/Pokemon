package com.alex.pokemon.data.api.pokemonList

data class PokemonList(
    val count: Int, val next: String, val previous: Any, val results: List<com.alex.pokemon.data.api.pokemonList.Result>
)
