package com.alex.pokemon.ui

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.alex.pokemon.data.model.PokemonListEntry
import com.alex.pokemon.data.repos.PokemonRepositoryImpl
import com.alex.pokemon.domain.PokemonRepository
import com.alex.pokemon.util.Constants.PAGE_SIZE
import com.alex.pokemon.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepositoryImpl
) : ViewModel() {

    private var curPage = 0

    var pokemonList = mutableStateOf<List<PokemonListEntry>>(listOf())
    var loadError = mutableStateOf("")
    val isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)


    fun loadPokemonPaginated(){

        viewModelScope.launch {
            isLoading.value = true

            val result = repository.getPokemonList(PAGE_SIZE, curPage * PAGE_SIZE)

            when(result){
                is Resource.Success ->{
                    endReached.value = curPage * PAGE_SIZE >= result.data!!.count
                    val pokemonsEntries = result.data.results.mapIndexed { index, entry ->
                        val number = if(entry.url.endsWith("/")) {
                            entry.url.dropLast(1).takeLastWhile { it.isDigit() }
                        } else {
                            entry.url.takeLastWhile { it.isDigit() }
                        }
                        val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                        PokemonListEntry(
                            entry.name.capitalize(Locale.ROOT),
                            url,
                            number.toInt())
                    }
                    curPage++

                    loadError.value = ""
                    isLoading.value = false
                    pokemonList.value += pokemonsEntries
                }
                is Resource.Error ->{
                    loadError.value = result.message!!
                    isLoading.value = false
                }
                is Resource.Loading ->{

                }
            }
        }
    }


    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }
}
