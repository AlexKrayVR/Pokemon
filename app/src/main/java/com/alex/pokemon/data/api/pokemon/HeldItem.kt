package com.alex.pokemon.data.api.pokemon


import com.google.gson.annotations.SerializedName

data class HeldItem(
    val item: com.alex.pokemon.data.api.pokemon.Item,
    @SerializedName("version_details")
    val versionDetails: List<com.alex.pokemon.data.api.pokemon.VersionDetail>
)
