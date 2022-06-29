package com.example.retrofitmoshi

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitmoshi.MainApp.Companion.favouritesHelper
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.movie_item_view.view.*
import java.util.*

class ListAdapter(private val itemsList: MutableList<Movie>) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.movie_item_view, parent, false))
    }


    override fun getItemCount() = itemsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val layout = holder.layoutView
        val article = itemsList[position]

        //val pattern = "dd-MM-yyyy"
        //val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        //val formattedDate = formatter.format(DateTime.parse(article.publishedAt).toDate())

        layout.title.text = article.title ?: "N/A"
        if (layout.title.text == "N/A") {
            article.title = article.name!!
            layout.title.text = article.name ?: "N/A"
        }

        layout.release_date.text = article.release_date ?: "N/A"
        if (layout.release_date.text == "N/A") {
            article.release_date = article.first_air_date!!
            layout.release_date.text = article.first_air_date ?: "N/A"
        }

        layout.vote_average.text = article.vote_average
        layout.overview.text = article.overview
        (layout.urlToImage == null).let {
            val baseURLImage = "w500"
            layout.urlToImage.visibility = View.VISIBLE
            article.poster_path = "https://image.tmdb.org/t/p/" +baseURLImage + article.poster_path
            Picasso.get().load(article.poster_path).into(layout.urlToImage)
        }

        favouritesHelper.getFavouritesList()?.forEach { art ->
            if(art == article)  {
                        layout.container.btnFavP.setImageResource(R.drawable.ic_favpressed)
            }
            /*else{
                layout.container.btnFavP.setImageResource(R.drawable.ic_favunpressed)
            }*/
        }


        layout.container.btnFavP.setOnClickListener {

                //Insere a estrela premida ou nao consoante as estrelas da recyclerView do MainActivity
                //if(favouritesHelper.getFavouritesList()?.contains(article) == true){*/
                if (favouritesHelper.getFavouritesList()!!.contains(article)) {
                    layout.container.btnFavP.setImageResource(R.drawable.ic_favunpressed)
                    favouritesHelper.removeFavourite(article)
                } else {
                    layout.container.btnFavP.setImageResource(R.drawable.ic_favpressed)
                    favouritesHelper.addFavourite(article)
                }
            //if(favouritesHelper.getFavouritesList()!!.isEmpty()){
                //layout.container.btnFavP.setBackgroundResource(R.drawable.ic_favpressed)
                //favouritesHelper.addFavourite(article)



        }

        layout.container.setOnClickListener {

            val intent = Intent(layout.context, SecondActivity::class.java).apply {
                putExtra(SecondActivity.keyPos, position)
                putParcelableArrayListExtra(SecondActivity.keyList, ArrayList(itemsList))
            }
            layout.context.startActivity(intent)
        }
    }


    data class ViewHolder(val layoutView: View) : RecyclerView.ViewHolder(layoutView)
}
