package com.alex.pokemon.data.api.pokemon


import com.google.gson.annotations.SerializedName

data class GenerationIv(
    @SerializedName("diamond-pearl")
    val diamondPearl: com.alex.pokemon.data.api.pokemon.DiamondPearl,
    @SerializedName("heartgold-soulsilver")
    val heartgoldSoulsilver: com.alex.pokemon.data.api.pokemon.HeartgoldSoulsilver,
    val platinum: com.alex.pokemon.data.api.pokemon.Platinum
)
