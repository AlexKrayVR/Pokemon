package com.alex.pokemon.data.api.pokemon


import com.google.gson.annotations.SerializedName

data class Other(
    @SerializedName("dream_world")
    val dreamWorld: com.alex.pokemon.data.api.pokemon.DreamWorld,
    val home: com.alex.pokemon.data.api.pokemon.Home,
    @SerializedName("official-artwork")
    val officialArtwork: com.alex.pokemon.data.api.pokemon.OfficialArtwork,
    val showdown: com.alex.pokemon.data.api.pokemon.Showdown
)
