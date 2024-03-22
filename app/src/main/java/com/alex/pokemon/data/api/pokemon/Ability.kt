package com.alex.pokemon.data.api.pokemon


import com.google.gson.annotations.SerializedName

data class Ability(
    val ability: com.alex.pokemon.data.api.pokemon.AbilityX,
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    val slot: Int
)
