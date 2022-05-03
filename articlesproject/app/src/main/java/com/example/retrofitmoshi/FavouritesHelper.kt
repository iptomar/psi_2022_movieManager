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

    fun addFavourite(article: Movie) {
        val favourites = getFavouritesList()

        if (favourites == null) {
            val newList = mutableListOf(article)

            mSharedPreferences.edit()
                .putString(FAVOURITES_KEY, jsonAdapter.toJson(newList))
                .apply()
        } else {
            val tempList = favourites.toMutableList()
            tempList.add(article)


            mSharedPreferences.edit()
                .putString(FAVOURITES_KEY, jsonAdapter.toJson(tempList))
                .apply()
        }
    }

    fun removeFavourite(article: Movie) {

        val favourites = getFavouritesList()
        if (favourites != null) {
            val tempList = favourites.toMutableList()
            tempList.remove(article)
            mSharedPreferences.edit()
                .putString(FAVOURITES_KEY, jsonAdapter.toJson(tempList))
                .apply()


        }
    }
    fun containsFavourite(article: Movie) {
        val favourites = getFavouritesList()

        favourites?.contains(article).let {
            addFavourite(article)
        }

    }

    private val mSharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(
            context.getString(R.string.app_name),
            Context.MODE_PRIVATE
        )
    }
}