package com.alex.pokemon.data.api.pokemon


import com.google.gson.annotations.SerializedName

data class Versions(
    @SerializedName("generation-i")
    val generationI: com.alex.pokemon.data.api.pokemon.GenerationI,
    @SerializedName("generation-ii")
    val generationIi: com.alex.pokemon.data.api.pokemon.GenerationIi,
    @SerializedName("generation-iii")
    val generationIii: com.alex.pokemon.data.api.pokemon.GenerationIii,
    @SerializedName("generation-iv")
    val generationIv: com.alex.pokemon.data.api.pokemon.GenerationIv,
    @SerializedName("generation-v")
    val generationV: com.alex.pokemon.data.api.pokemon.GenerationV,
    @SerializedName("generation-vi")
    val generationVi: com.alex.pokemon.data.api.pokemon.GenerationVi,
    @SerializedName("generation-vii")
    val generationVii: com.alex.pokemon.data.api.pokemon.GenerationVii,
    @SerializedName("generation-viii")
    val generationViii: com.alex.pokemon.data.api.pokemon.GenerationViii
)
