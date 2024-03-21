package com.alex.pokemon.data.repos

import com.alex.pokemon.data.api.PokemonApi
import com.alex.pokemon.data.pokemon.Pokemon
import com.alex.pokemon.data.pokemonList.PokemonList
import com.alex.pokemon.di.PokemonRepository
import com.alex.pokemon.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonApi
) : PokemonRepository {
    override suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> =
        runCatching {
            api.getPokemonList(limit, offset)
        }.fold(onSuccess = {
            Resource.Success(it)
        }, onFailure = {
            Resource.Error("Error in getPokemonList: ${it.message}")
        })

    override suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> = runCatching {
        api.getPokemonInfo(pokemonName)
    }.fold(onSuccess = {
        Resource.Success(it)
    }, onFailure = {
        Resource.Error("Error in getPokemonInfo: ${it.message}")
    })
}
