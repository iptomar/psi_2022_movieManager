package com.example.retrofitmoshi

import android.app.Application

class MainApp : Application() {

    companion object {
        lateinit var favouritesHelper: FavouritesHelper
        val listArt = mutableListOf<Movie>()
    }

    override fun onCreate() {
        super.onCreate()
        favouritesHelper = FavouritesHelper(applicationContext)
    }


}