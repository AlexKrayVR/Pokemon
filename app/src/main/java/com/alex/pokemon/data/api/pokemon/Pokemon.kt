package com.alex.pokemon.data.api.pokemon


import com.google.gson.annotations.SerializedName

data class Pokemon(
    val abilities: List<com.alex.pokemon.data.api.pokemon.Ability>,
    @SerializedName("base_experience")
    val baseExperience: Int,
    val cries: com.alex.pokemon.data.api.pokemon.Cries,
    val forms: List<com.alex.pokemon.data.api.pokemon.Form>,
    @SerializedName("game_indices")
    val gameIndices: List<com.alex.pokemon.data.api.pokemon.GameIndice>,
    val height: Int,
    @SerializedName("held_items")
    val heldItems: List<com.alex.pokemon.data.api.pokemon.HeldItem>,
    val id: Int,
    @SerializedName("is_default")
    val isDefault: Boolean,
    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String,
    val moves: List<com.alex.pokemon.data.api.pokemon.Move>,
    val name: String,
    val order: Int,
    @SerializedName("past_abilities")
    val pastAbilities: List<Any>,
    @SerializedName("past_types")
    val pastTypes: List<Any>,
    val species: com.alex.pokemon.data.api.pokemon.Species,
    val sprites: com.alex.pokemon.data.api.pokemon.Sprites,
    val stats: List<com.alex.pokemon.data.api.pokemon.Stat>,
    val types: List<com.alex.pokemon.data.api.pokemon.Type>,
    val weight: Int
)
