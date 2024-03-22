package com.alex.pokemon.data.api.pokemon


import com.google.gson.annotations.SerializedName

data class GenerationI(
    @SerializedName("red-blue")
    val redBlue: com.alex.pokemon.data.api.pokemon.RedBlue,
    val yellow: com.alex.pokemon.data.api.pokemon.Yellow
)
