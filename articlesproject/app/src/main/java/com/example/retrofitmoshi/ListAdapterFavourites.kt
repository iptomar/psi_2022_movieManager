package com.example.retrofitmoshi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
<<<<<<< HEAD
=======
import kotlinx.android.synthetic.main.activity_favorites_list.*
import kotlinx.android.synthetic.main.recyclerview_listafavs.*
>>>>>>> POG_UI
import kotlinx.android.synthetic.main.recyclerview_listafavs.view.*
import androidx.recyclerview.widget.RecyclerView


class ListAdapterFavourites(private val itemsList: MutableList<Movie>) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.ViewHolder {
        return ListAdapter.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_listafavs, parent, false))
    }

    override fun getItemCount() = itemsList.size

    override fun onBindViewHolder(holder: ListAdapter.ViewHolder, position: Int) {
        val layout = holder.layoutView
        val article = itemsList[position]

        //val pattern = "dd-MM-yyyy"
        //val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        //val formattedDate = formatter.format(DateTime.parse(article.publishedAt).toDate())

        layout.title2.text = article.title ?: "N/A"
        layout.release_date2.text = article.release_date
        layout.rating2.text = article.vote_average
        layout.overview2.text = article.overview
        (article.poster_path?.isEmpty()).let {
            Picasso.get().load(article.poster_path).into(layout.urlToImage2)
        }


        layout.container2.btnListaFav.setOnClickListener {
            MainApp.favouritesHelper.removeFavourite(article)
            itemsList.remove(article)
            notifyItemRemoved(position)


        }
    }


    data class ViewHolder(val layoutView: View) : RecyclerView.ViewHolder(layoutView)
}