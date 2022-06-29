package com.example.retrofitmoshi


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.viewpager.widget.ViewPager
import com.example.retrofitmoshi.MainApp.Companion.favouritesHelper
import com.example.retrofitmoshi.MainApp.Companion.listArt
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_pager.*
import kotlinx.android.synthetic.main.movie_item_view.*
import kotlinx.android.synthetic.main.movie_item_view.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import org.joda.time.LocalDateTime
import java.util.*


class MainActivity : AppCompatActivity() {

    val mList = mutableListOf<Movie>()
    val fList = mutableListOf<Movie>()
    private val rCode = 1
    private val lCode = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list.adapter = ListAdapter(fList)

        //String array.
        val myStrings = arrayOf("Author", "Title", "Clear")
        var call = API.create().getData()
        btn_movies.setBackgroundColor(Color.parseColor("#4D101010"))
        callMovies()

        btnSubmit.setOnClickListener {
                val searchTxt = lblEdit.text.toString()
                //teclado desaparece
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(lblEdit.windowToken, 0)

                fList.clear()
                fList.addAll(
                    mList.filter {
                        it.title?.toLowerCase()?.contains(searchTxt.toLowerCase()) == true
                    }
                )
        }



        btn_movies.setOnClickListener(){
            callMovies()
            btn_movies.setBackgroundColor(Color.parseColor("#4D101010"))
            btn_tv.setBackgroundColor(Color.parseColor("#4D4D4D4D"))
        }

        btn_tv.setOnClickListener(){
            callSeries()
            btn_tv.setBackgroundColor(Color.parseColor("#4D101010"))
            btn_movies.setBackgroundColor(Color.parseColor("#4D4D4D4D"))
        }

        btnLogOut.setOnClickListener(){
            Firebase.auth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(this, R.drawable.recycleview_divider)?.let {
            divider.setDrawable(it)
        }
        list.addItemDecoration(divider)
        btnFavsList.setOnClickListener {
            val intent = Intent(this, FavoritesList::class.java)
            startActivityForResult(intent, lCode)
        }
    }

    companion object {
        const val ID_KEY = "id"
    }


    private fun callMovies (){
        var call =  API.create().getData()

        call.enqueue(object : Callback<Search> {
            override fun onFailure(call: Call<Search>, t: Throwable) {
                Log.e("onFailure error", call.request().url().toString())
                Log.e("onFailure error", t.message!!)
            }

            override fun onResponse(call: Call<Search>, response: Response<Search>) {
                response.body()?.let {
                    //o simbolo de estar a pensar começa a traalhar e quando chega aqui a visibilidade passa a nula, pois estamos a adicionar o texto
                    homeprogress1.visibility = ViewPager.GONE


                    /*it.results.forEach { art ->
                        art.id = UUID.randomUUID().toString()
                    }*/

                    mList.clear()
                    mList.addAll(it.results)

                    fList.clear()
                    fList.addAll(it.results)

                    MainApp.listArt.clear()
                    MainApp.listArt.addAll(it.results)

                    //val article = MainApp.listArt.get(art.id)

                    //Insere a estrela premida ou nao consoante as estrelas da recyclerView do MainActivity
                    /*if(MainApp.favouritesHelper.getFavouritesList().forEachIndexed(it.results.get()) == true)  {
                        btnFavUnpressed.setImageResource(R.drawable.ic_favpressed)
                    }else{
                        btnFavUnpressed.setImageResource(R.drawable.ic_favunpressed)
                    }*/

                    list.adapter?.notifyDataSetChanged()
                }
            }
        })
    }

    private fun callSeries (){
        var call =  API.create().getDataTv()

        call.enqueue(object : Callback<Search> {
            override fun onFailure(call: Call<Search>, t: Throwable) {
                Log.e("onFailure error", call.request().url().toString())
                Log.e("onFailure error", t.message!!)
            }

            override fun onResponse(call: Call<Search>, response: Response<Search>) {
                response.body()?.let {
                    //o simbolo de estar a pensar começa a traalhar e quando chega aqui a visibilidade passa a nula, pois estamos a adicionar o texto
                    homeprogress1.visibility = ViewPager.GONE


                    /*it.results.forEach { art ->
                        art.id = UUID.randomUUID().toString()
                    }*/

                    mList.clear()
                    mList.addAll(it.results)

                    fList.clear()
                    fList.addAll(it.results)

                    MainApp.listArt.clear()
                    MainApp.listArt.addAll(it.results)

                    list.adapter?.notifyDataSetChanged()
                }
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            rCode -> {
                if (resultCode == Activity.RESULT_OK) {
                    data?.let {
                        val lista = it.getParcelableExtra<Movie>("art")
                        mList.add(lista!!)
                        fList.add(lista!!)
                        list.adapter?.notifyDataSetChanged()
                    }
                }
            }
            lCode -> {
                if (resultCode == Activity.RESULT_OK) {
                    data?.let {
                        btnFavP.setImageResource(R.drawable.ic_favunpressed)
                        list.adapter?.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}
