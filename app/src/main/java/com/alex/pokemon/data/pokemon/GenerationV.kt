package com.alex.pokemon.data.pokemon


import com.google.gson.annotations.SerializedName

data class GenerationV(
    @SerializedName("black-white")
    val blackWhite: BlackWhite
)