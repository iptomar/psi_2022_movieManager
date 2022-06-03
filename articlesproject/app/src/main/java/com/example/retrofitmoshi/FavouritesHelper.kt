package com.example.retrofitmoshi

import android.content.Context
import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.android.synthetic.main.activity_favorites_list.*

class FavouritesHelper(context: Context) {

    private val adapterType = Types.newParameterizedType(List::class.java, Movie::class.java)
    private val jsonAdapter = Moshi.Builder().build().adapter<List<Movie>>(adapterType)

    companion object {
        const val FAVOURITES_KEY = "favourites"
    }

    fun getFavouritesList(): List<Movie>? {
        val json = mSharedPreferences.getString(FAVOURITES_KEY, null)
        json?.let {
            return jsonAdapter.fromJson(json)
        }
        return null
    }

<<<<<<< HEAD
    fun addFavourite(article: Movie) {
=======
    fun addFavourite(Movie: Movie) {
>>>>>>> Login
        val favourites = getFavouritesList()

        if (favourites == null) {
            val newList = mutableListOf(Movie)

            mSharedPreferences.edit()
                .putString(FAVOURITES_KEY, jsonAdapter.toJson(newList))
                .apply()
        } else {
            val tempList = favourites.toMutableList()
            tempList.add(Movie)


            mSharedPreferences.edit()
                .putString(FAVOURITES_KEY, jsonAdapter.toJson(tempList))
                .apply()
        }
    }

<<<<<<< HEAD
    fun removeFavourite(article: Movie) {
=======
    fun removeFavourite(Movie: Movie) {
>>>>>>> Login

        val favourites = getFavouritesList()
        if (favourites != null) {
            val tempList = favourites.toMutableList()
            tempList.remove(Movie)
            mSharedPreferences.edit()
                .putString(FAVOURITES_KEY, jsonAdapter.toJson(tempList))
                .apply()


        }
    }
<<<<<<< HEAD
    fun containsFavourite(article: Movie) {
=======
    fun containsFavourite(Movie: Movie) {
>>>>>>> Login
        val favourites = getFavouritesList()

        favourites?.contains(Movie).let {
            addFavourite(Movie)
        }

    }

    private val mSharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(
            context.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
    }
}