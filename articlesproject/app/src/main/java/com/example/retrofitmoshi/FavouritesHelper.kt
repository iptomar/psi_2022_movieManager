package com.example.retrofitmoshi

import android.content.Context
import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types


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

    fun addFavourite(Movie: Movie) {

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


    fun removeFavourite(Movie: Movie) {

        val favourites = getFavouritesList()
        if (favourites != null) {
            val tempList = favourites.toMutableList()
            tempList.remove(Movie)
            mSharedPreferences.edit()
                .putString(FAVOURITES_KEY, jsonAdapter.toJson(tempList))
                .apply()


        }
    }

    fun containsFavourite(Movie: Movie) {

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