package com.alex.pokemon.data.api.pokemon


import com.google.gson.annotations.SerializedName

data class GenerationVii(
    val icons: com.alex.pokemon.data.api.pokemon.Icons,
    @SerializedName("ultra-sun-ultra-moon")
    val ultraSunUltraMoon: com.alex.pokemon.data.api.pokemon.UltraSunUltraMoon
)
