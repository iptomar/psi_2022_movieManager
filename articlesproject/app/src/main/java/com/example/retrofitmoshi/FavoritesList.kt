package com.example.retrofitmoshi

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< HEAD
import android.support.v7.widget.GridLayoutManager
=======
import androidx.recyclerview.widget.GridLayoutManager
>>>>>>> Login
import kotlinx.android.synthetic.main.activity_favorites_list.*



class FavoritesList : AppCompatActivity() {
    val mList = mutableListOf<Movie>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites_list)

        mList.addAll(MainApp.favouritesHelper.getFavouritesList() ?: listOf())

        listFav.adapter = ListAdapterFavourites(mList)


        listFav.adapter?.notifyDataSetChanged()

        listFav.layoutManager = GridLayoutManager(this, 2)

        btnGoBackMain?.setOnClickListener {
            setResult(Activity.RESULT_OK, Intent())
            finish()
        }
    }
}






