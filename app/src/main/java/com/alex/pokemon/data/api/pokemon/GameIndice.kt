package com.alex.pokemon.data.api.pokemon


import com.google.gson.annotations.SerializedName

data class GameIndice(
    @SerializedName("game_index")
    val gameIndex: Int,
    val version: com.alex.pokemon.data.api.pokemon.Version
)
