package com.alex.pokemon.data.api.pokemon


import com.google.gson.annotations.SerializedName

data class GenerationVi(
    @SerializedName("omegaruby-alphasapphire")
    val omegarubyAlphasapphire: com.alex.pokemon.data.api.pokemon.OmegarubyAlphasapphire,
    @SerializedName("x-y")
    val xY: com.alex.pokemon.data.api.pokemon.XY
)
