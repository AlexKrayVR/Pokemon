package com.alex.pokemon.data.api.pokemon


import com.google.gson.annotations.SerializedName

data class Move(
    val move: com.alex.pokemon.data.api.pokemon.MoveX,
    @SerializedName("version_group_details")
    val versionGroupDetails: List<com.alex.pokemon.data.api.pokemon.VersionGroupDetail>
)
