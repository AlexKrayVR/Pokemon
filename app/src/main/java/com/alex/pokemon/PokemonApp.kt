package com.alex.pokemon

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PokemonApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }


}
