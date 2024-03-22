package com.alex.pokemon.data.api.pokemon


import com.google.gson.annotations.SerializedName

data class Stat(
    @SerializedName("base_stat")
    val baseStat: Int,
    val effort: Int,
    val stat: com.alex.pokemon.data.api.pokemon.StatX
)
