package com.example.retrofitmoshi

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_favorites_list.*
import kotlinx.android.synthetic.main.fragment_pager.*
import kotlinx.android.synthetic.main.recycle_view.*
import kotlinx.android.synthetic.main.recycle_view.view.*


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






