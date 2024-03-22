package com.alex.pokemon.data.api.pokemon


import com.google.gson.annotations.SerializedName

data class GenerationIii(
    val emerald: com.alex.pokemon.data.api.pokemon.Emerald,
    @SerializedName("firered-leafgreen")
    val fireredLeafgreen: com.alex.pokemon.data.api.pokemon.FireredLeafgreen,
    @SerializedName("ruby-sapphire")
    val rubySapphire: com.alex.pokemon.data.api.pokemon.RubySapphire
)
