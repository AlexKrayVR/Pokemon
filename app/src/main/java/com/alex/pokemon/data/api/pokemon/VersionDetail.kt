package com.alex.pokemon.data.api.pokemon


import com.google.gson.annotations.SerializedName

data class VersionDetail(
    val rarity: Int,
    val version: com.alex.pokemon.data.api.pokemon.Version
)
